package servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		InetAddress IP = InetAddress.getLocalHost();
		String address = IP.getHostAddress(); 
		String createTableSql = "CREATE TABLE Extraction ("
				+ "IP_Addr varchar(20), "
				+ "Results varchar(500),"
				+ "Text varchar(500),"
				+ "isNonprogramming varchar(10),"
				+ "isGenericAction varchar(10),"
				+ "verbs varchar(100),"
				+ "generic varchar(100))";	
		//content type
		response.setContentType("text/html;charset=UTF-8");
		//ServletContext application=this.getServletContext();
		HttpSession session = request.getSession();
		String setting = (String)session.getAttribute("isSetting");
		//get input from page
		String text =new String(request.getParameter("text"));
		String[] tempOptions = (String[])session.getAttribute("options");
		TaskExtractor taskExtractor = new TaskExtractor();
		List<String> tasks = new ArrayList<String>();
		List<Sentence> sentencesWithTasks = null;
		//if have not choose settings
		if(setting == null){
			Configuration.setGen_option("yeswithdefined");
			Configuration.setPro_option("yeswithdefined");
			sentencesWithTasks = taskExtractor.extractTasks(text,true,true,true,true,true,true);
		}else{
			List<Boolean> options = checkOptions(tempOptions);
			sentencesWithTasks = taskExtractor.extractTasks(text,options.get(0),options.get(1),options.get(2),options.get(3),options.get(4),options.get(5));
		}
		for (Sentence sentenceWithTasks : sentencesWithTasks) {
			for (Task task : sentenceWithTasks.getTasks()) {
				tasks.add(task.toString().trim());
			}
		}
		String result = "";
		if(tasks.size() != 0)
			result = formatResults(tasks);
		String itemSql = "INSERT INTO Extraction VALUES ('"+ address +"','"+ result +"','"+ text +"','a','b','sss','ss')";
		Connection conn = null;
		Statement state = null;
		try {
			conn = getConnection();
			state = conn.createStatement();
			//state.execute(createTableSql);
			state.execute(itemSql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		request.setAttribute("tasks", tasks);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));
//        System.out.println("url: "+dbUri);
        String username = "adgjkzpqxsbwqh";//dbUri.getUserInfo().split(":")[0];
        String password = "0c697f9b508a9a750572bee945d95478251615208313d8be8ddf521ca90e680d";//dbUri.getUserInfo().split(":")[1];
        //postgres://adgjkzpqxsbwqh:0c697f9b508a9a750572bee945d95478251615208313d8be8ddf521ca90e680d@ec2-54-221-255-153.compute-1.amazonaws.com:5432/ddteol71om45n4
        //jdbc:postgresql://<host>:<port>/<dbname>?sslmode=require&user=<username>&password=<password>
        String dbUrl = "jdbc:postgresql://ec2-54-221-255-153.compute-1.amazonaws.com:5432/ddteol71om45n4?sslmode=require&user=adgjkzpqxsbwqh&password=0c697f9b508a9a750572bee945d95478251615208313d8be8ddf521ca90e680d";
        return DriverManager.getConnection(dbUrl, username, password);
    }
	
	private String formatResults(List<String> tasks){
		String result = "";
		for(int i=0;i<tasks.size();i++){
			result += tasks.get(i) + ",";
		}
		result = result.substring(0, result.length()-1);
		return result;
	}
	
	private List<Boolean> checkOptions(String[] options){
		List<Boolean> ops = new ArrayList<Boolean>();
		for(int i=0;i<options.length;i++){
			if(options[i] == null){
				options[i] = "no";
			}
		}
		for(int i=0;i<options.length;i++){
			if(options[i].equals("yes"))
				ops.add(true);
			else
				ops.add(false);
		}
		return ops;
	}
}
