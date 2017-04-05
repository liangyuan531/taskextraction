package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		setExtensions(request);
		String[] options = setCheckbox(request);
		application.setAttribute("isSetting", "1");
		application.setAttribute("options", options);
		response.sendRedirect("index.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String[] setCheckbox(HttpServletRequest request){
		String[] checkbox = new String[6];
		String direct_object = request.getParameter("direct_object");
		String passive_nominal_subject = request.getParameter("passive_nominal_subject");
		String relative_clause_modifier = request.getParameter("relative_clause_modifier");
		String prepositional_modifier = request.getParameter("prepositional_modifier");
		String RegexedCode = request.getParameter("RegexedCode");
		String TaggedCode = request.getParameter("TaggedCode");
		checkbox[0] = direct_object;
		checkbox[1] = passive_nominal_subject;
		checkbox[2] = relative_clause_modifier;
		checkbox[3] = prepositional_modifier;
		checkbox[4] = RegexedCode;
		checkbox[5] = TaggedCode;
		return checkbox;
	}
	
	private void setExtensions(HttpServletRequest request){
		String pro_option=request.getParameter("programming");
		String gen_option=request.getParameter("generic");
		//no, yes, yeswithdefined
		Configuration.setGen_option(gen_option);
		//no, yes, yeswithdefined
		Configuration.setPro_option(pro_option);
		if(pro_option.equals("yes") && gen_option.equals("yes")){
			String verbs = request.getParameter("verbs");
			String selfgeneric = request.getParameter("selfgeneric");
			writeProperties("PROGRAMMING_ACTIONS","GENERIC_ACCUSATIVES",verbs, selfgeneric, "customizedconfigwithboth.properties");
		}else if(pro_option.equals("yes") && gen_option.equals("no")){
			String verbs = request.getParameter("verbs");
			writeProperties("PROGRAMMING_ACTIONS", "", verbs, "", "customizedconfigwithoutgeneric.properties");
		}else if(pro_option.equals("yes") && gen_option.equals("yeswithdefined")){
			String verbs = request.getParameter("verbs");
			writeProperties("PROGRAMMING_ACTIONS", "", verbs, "", "customizedconfigwithgeneric.properties");
		}else if(pro_option.equals("no") && gen_option.equals("yes")){
			String selfgeneric = request.getParameter("selfgeneric");
			writeProperties("", "GENERIC_ACCUSATIVES", "", selfgeneric, "customizedconfigwithoutprogramming.properties");
		}else if(pro_option.equals("yeswithdefined") && gen_option.equals("yes")){
			String selfgeneric = request.getParameter("selfgeneric");
			writeProperties("", "GENERIC_ACCUSATIVES", "", selfgeneric, "customizedconfigwithprogramming.properties");
		}else if(pro_option.equals("yeswithdefined") && gen_option.equals("no")){
			String selfgeneric = request.getParameter("selfgeneric");
			writeProperties("", "GENERIC_ACCUSATIVES", "", selfgeneric, "customizedconfigwithprogramming.properties");
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
