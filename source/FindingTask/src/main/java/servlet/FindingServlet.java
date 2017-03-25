package servlet;

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
		
		String pro_option=request.getParameter("programming");
		String gen_option=request.getParameter("generic");
		Configuration.pro_option=pro_option;
		Configuration.gen_option=gen_option;
		
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
}
