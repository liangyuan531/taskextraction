package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/programlist")
public class ProgramminglistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProgramminglistServlet() {
        super();  
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		File file = new File("config.properties");
		String programming = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String str = "";
		while((str=br.readLine()) != null){
			String s[] = str.split(" ");
			if(s[0].equals("PROGRAMMING_ACTIONS")){
				programming = s[2];
			}
		}
		br.close();
		String[] verbs = programming.split(",");
		session.setAttribute("programminglist", verbs);
		response.sendRedirect("programlist.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
