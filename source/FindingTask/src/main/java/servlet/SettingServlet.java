package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.mcgill.cs.swevo.taskextractor.utils.Configuration;

@WebServlet("/setting")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SettingServlet() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application=this.getServletContext();
		//self-defined verbs
		String customize1 = request.getParameter("customize1");
		//self-defined generic action
		String customize2 = request.getParameter("customize2");
		setExtensions(customize1,customize2,request);
		application.setAttribute("isSetting", "1");
		//request.getRequestDispatcher("index.jsp").forward(request, response);
		response.sendRedirect("index.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void setExtensions(String customize1, String customize2, HttpServletRequest request){
		Configuration.setCustomise1(customize1);
		Configuration.setCustomise2(customize2);
		if(customize1.equals("yes") && customize2.equals("no")){
			String verbs = request.getParameter("verbs");
			Configuration.setPro_option("");
			String gen_option=request.getParameter("generic");
			Configuration.setGen_option(gen_option);
			if(gen_option.equals("yes"))
				writeProperties("PROGRAMMING_ACTIONS", "", verbs, "", "customizedconfigwithgeneric.properties");
			else if(gen_option.equals("no"))
				writeProperties("PROGRAMMING_ACTIONS", "", verbs, "", "customizedconfigwithoutgeneric.properties");
			Configuration.setCustomise1(customize1);
		}else if(customize1.equals("yes") && customize2.equals("yes")){
			String verbs = request.getParameter("verbs");
			String generic = request.getParameter("generic");
			Configuration.setGen_option("");
			Configuration.setPro_option("");
			writeProperties("PROGRAMMING_ACTIONS","GENERIC_ACCUSATIVES",verbs, generic, "customizedconfigwithboth.properties");
		}else if(customize1.equals("no") && customize2.equals("yes")){
			String selfgeneric = request.getParameter("selfgeneric");
			Configuration.setGen_option("");
			String pro_option=request.getParameter("programming");
			Configuration.setGen_option(pro_option);
			if(pro_option.equals("yes"))
				writeProperties("", "GENERIC_ACCUSATIVES", "", selfgeneric, "customizedconfigwithprogramming.properties");
			else if(pro_option.equals("no"))
				writeProperties("", "GENERIC_ACCUSATIVES", "", selfgeneric, "customizedconfigwithoutprogramming.properties");
			Configuration.setCustomise1(customize1);
		}else if(customize1.equals("no") && customize2.equals("no")){
			//do not use self-defined verbs and generic action
			String gen_option=request.getParameter("generic");
			String pro_option=request.getParameter("programming");
			Configuration.setGen_option(gen_option);
			Configuration.setPro_option(pro_option);
		}
	}
	
	public void writeProperties(String action1, String action2, String actions1, String actions2, String fileName){
		String property1 = null;
		String property2 = null;
		if(action1 != "")
			property1 = action1 + " = " + actions1 + System.getProperty("line.separator");
		if(action2 != "")
			property2 = action2 + " = " + actions2 + System.getProperty("line.separator");
		try {
			File temp = new File("temp");
			File origin = new File(fileName);
			FileReader reader = new FileReader(origin);
			FileWriter tempwriter = new FileWriter(temp,true);
			BufferedReader br = new BufferedReader(reader);
			String str = "";
			boolean isProgramming = false;
			boolean isGeneric = false;
			while((str=br.readLine()) != null){
				String[] s = str.split(" ");
				for(int i=0;i<s.length;i++){
					if(s[i].equals("PROGRAMMING_ACTIONS")){
						isProgramming = true;
					}
					if(s[i].equals("GENERIC_ACCUSATIVES")){
						isGeneric = true;
					}
				}
				if(!isProgramming || !isGeneric){
					tempwriter.write(str+System.getProperty("line.separator"));
				}
			}
			br.close();
			tempwriter.close();
			origin.delete();
			temp.renameTo(origin);
			FileWriter writer = new FileWriter(fileName,true);
			if(action1 != "")
				writer.write(property1+System.getProperty("line.separator"));
			if(action2 != "")
				writer.write(property2+System.getProperty("line.separator"));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
