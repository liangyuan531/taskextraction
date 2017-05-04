package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username").trim();  
	    String userPwd = request.getParameter("password").trim();  
	    HttpSession session = request.getSession();
	    session.setAttribute("userinfo", "OK");
	    Map<String,String> account = getAccount();
	    if(account.containsKey(userName)){
	    	String pwd = account.get(userName);
	    	if(userPwd.equals(pwd)) {  
		    	List<String> database = new ArrayList<String>();
		        try {
					Connection conn = getConnection();
			    	Statement state = conn.createStatement();
			    	String sql = "SELECT * FROM extraction";
			    	ResultSet tempData = state.executeQuery(sql);
			    	while(tempData.next()){
			    		for(int i=1;i<10;i++){
			    			database.add(tempData.getString(i));
			    		}	
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
		    	out.println("password is wrong, try again");
		    }
	    }else{
	    	out.println("username does not exist");
	    }
	    
	}
	
	private Map<String,String> getAccount(){
		Map<String,String> account = new HashMap<String,String>();
		try {
			Connection conn = getConnection();
	    	Statement state = conn.createStatement();
	    	String sql = "SELECT * FROM account";
	    	ResultSet tempData = state.executeQuery(sql);
	    	while(tempData.next()){
	    		String key = tempData.getString(1);
	    		String value = tempData.getString(2);
	    		account.put(key, value);
	    	}
	    	conn.close();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
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
