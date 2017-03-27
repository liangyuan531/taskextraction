package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.mcgill.cs.swevo.taskextractor.analysis.TaskExtractor;
import ca.mcgill.cs.swevo.taskextractor.model.Sentence;
import ca.mcgill.cs.swevo.taskextractor.model.Task;
import ca.mcgill.cs.swevo.taskextractor.utils.Configuration;



@WebServlet(
        name = "tasks", 
        urlPatterns = {"/finding"}
    )
public class FindingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		this.doPost(req, resp);
    }
	
	@Override  
	protected void doPost(HttpServletRequest request,  
	     HttpServletResponse response) throws ServletException, IOException {
		//content type
		response.setContentType("text/html;charset=UTF-8");
		//get input from page
		String text =new String(request.getParameter("text"));
		String gen_option=request.getParameter("generic");
		Configuration.setGen_option(gen_option);
		String customize = request.getParameter("customize");
		if(customize.equals("yes")){
			Configuration.setPro_option("");
			String verbs = request.getParameter("verbs");
			if(gen_option.equals("yes"))
				writeProperties(verbs, "customizedconfigwithgeneric.properties");
			else if(gen_option.equals("no"))
				writeProperties(verbs, "customizedconfigwithoutgeneric.properties");
			Configuration.setCustomise(customize);
		}else{
			//get selections
			String pro_option=request.getParameter("programming");
			//set configuration file 
			Configuration.setPro_option(pro_option);
		}
		
		TaskExtractor taskExtractor = new TaskExtractor();
		List<String> tasks = new ArrayList<String>();
		List<Sentence> sentencesWithTasks = taskExtractor.extractTasks(text);
		for (Sentence sentenceWithTasks : sentencesWithTasks) {
			for (Task task : sentenceWithTasks.getTasks()) {
				tasks.add(task.toString().trim());
			}
		}
		request.setAttribute("tasks", tasks);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	public void writeProperties(String verbs, String fileName){
		String property = "PROGRAMMING_ACTIONS = " + verbs + System.getProperty("line.separator");
		try {
			File temp = new File("temp");
			File origin = new File(fileName);
			FileReader reader = new FileReader(origin);
			FileWriter tempwriter = new FileWriter(temp,true);
			BufferedReader br = new BufferedReader(reader);
			String str = "";
			boolean isProgramming = false;
			while((str=br.readLine()) != null){
				String[] s = str.split(" ");
				for(int i=0;i<s.length;i++){
					if(s[i].equals("PROGRAMMING_ACTIONS")){
						isProgramming = true;
					}		
				}
				if(!isProgramming){
					tempwriter.write(str+System.getProperty("line.separator"));
				}
			}
			br.close();
			tempwriter.close();
			origin.delete();
			temp.renameTo(origin);
			FileWriter writer = new FileWriter(fileName,true);
			writer.write(property+System.getProperty("line.separator"));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
