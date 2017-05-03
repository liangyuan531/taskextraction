package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String userName = request.getParameter("username").trim();  
	    String userPwd = request.getParameter("password").trim();  
	    HttpSession session = request.getSession();
	    session.setAttribute("myname", userName);
	    session.setAttribute("mypwd", userPwd);
	    session.setAttribute("name", "111");
	    session.setAttribute("pwd", "222");
	    if(userName.equals("111") && userPwd.equals("222")) {  
	    	List<String> database = new ArrayList<String>();
	        try {
				Connection conn = getConnection();
		    	Statement state = conn.createStatement();
		    	String sql = "SELECT * FROM extraction";
		    	ResultSet tempData = state.executeQuery(sql);
		    	while(tempData.next()){
		    		//String data = "";
		    		for(int i=1;i<10;i++){
		    			//data += tempData.getString(i) + " ";
		    			database.add(tempData.getString(i));
		    		}
		    		//database.add(data);
		    	}
		    	conn.close();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        //System.out.println("data: "+database);
	        session.setAttribute("data", database);
	        response.sendRedirect("database.jsp");
	    } 
	    else {  
	        //response.sendRedirect("login.jsp");   
	    	PrintWriter out = response.getWriter();
	    	out.println("username or password error");
	    }
	}
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
//      URI dbUri = new URI(System.getenv("DATABASE_URL"));
//      System.out.println("url: "+dbUri);
      String username = "adgjkzpqxsbwqh";//dbUri.getUserInfo().split(":")[0];
      String password = "0c697f9b508a9a750572bee945d95478251615208313d8be8ddf521ca90e680d";//dbUri.getUserInfo().split(":")[1];
      //postgres://adgjkzpqxsbwqh:0c697f9b508a9a750572bee945d95478251615208313d8be8ddf521ca90e680d@ec2-54-221-255-153.compute-1.amazonaws.com:5432/ddteol71om45n4
      //jdbc:postgresql://<host>:<port>/<dbname>?sslmode=require&user=<username>&password=<password>
      String dbUrl = "jdbc:postgresql://ec2-54-221-255-153.compute-1.amazonaws.com:5432/ddteol71om45n4?sslmode=require&user=adgjkzpqxsbwqh&password=0c697f9b508a9a750572bee945d95478251615208313d8be8ddf521ca90e680d";
      return DriverManager.getConnection(dbUrl, username, password);
	}
	
}
