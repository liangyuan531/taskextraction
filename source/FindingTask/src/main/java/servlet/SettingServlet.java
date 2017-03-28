package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.mcgill.cs.swevo.taskextractor.utils.Configuration;

@WebServlet("/setting")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SettingServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.setAttribute("isSetting", "1");
		response.sendRedirect("/index.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
