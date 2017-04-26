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
		//content type
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String generic = (String)session.getAttribute("gen_option");
		String programming = (String)session.getAttribute("pro_option");
		String myVerbs = (String)session.getAttribute("myVerbs");
		String myAccusatives = (String)session.getAttribute("myAccusatives");
		String otherOptions = "";
		
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
			generic = "yeswithdefined";
			programming = "yeswithdefined";
			sentencesWithTasks = taskExtractor.extractTasks(text,true,true,true,true,true,true);
		}else{
			List<Boolean> options = checkOptions(tempOptions);
			otherOptions = otherOptions(options);
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
		
		//System.out.println("g: "+otherOptions);
		//System.out.println("p:" +programming);
		
		//get IP address
		String address = InetAddress.getLocalHost().getHostAddress();
		
		String itemSql = "INSERT INTO Extraction VALUES ('"
				+ address +"','"
				+ result +"','"
				+ programming +"','"
				+ generic +"','"
				+ myVerbs +"','"
				+ myAccusatives +"','"
				+ otherOptions +"','"
				+ text +"')";	
		//createTable();
		//insertItem(itemSql);
		
		request.setAttribute("tasks", tasks);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	private String otherOptions(List<Boolean> options){
		String option = "";
		if(options.get(0) == true){
			option += "direct object,";
		}
		if(options.get(1) == true){
			option += "passive nominal subject,";
		}
		if(options.get(2) == true){
			option += "relative clause modifier,";
		}
		if(options.get(3) == true){
			option += "prepositional modifier,";
		}
		if(options.get(4) == true){
			option += "RegexedCode,";
		}
		if(options.get(5) == true){
			option += "TaggedCode,";
		}
		option = option.substring(0, option.length()-1);
		return option;
	}
	
	private void insertItem(String itemSql){
	    try {
	    	Connection conn = getConnection();
	    	Statement state = conn.createStatement();
	    	state.execute(itemSql);
	    } catch (URISyntaxException e1) {
	    	e1.printStackTrace();
	    } catch (SQLException e1) {
	    	e1.printStackTrace();
	    }
	}
	
	private void createTable(){
	    String createTableSql = "CREATE TABLE extraction ("
	     		+ "IP_Addr varchar(20),"
	     		+ "Results varchar(500),"
	            + "isNonprogramming varchar(100),"
	            + "isGenericAction varchar(10),"
	            + "PROGRAMMING_ACTIONS varchar(100),"
	     		+ "GENERIC_ACCUSATIVES varchar(100),"
	            + "GRAMMATICAL_DEPENDENCIES and CODE varchar(50),"
	     		+ "TEXT varchar(500))";
	    try {
	    	Connection conn = getConnection();
	    	Statement state = conn.createStatement();
	    	state.execute(createTableSql);
	    } catch (URISyntaxException e1) {
	    	e1.printStackTrace();
	    } catch (SQLException e1) {
	    	e1.printStackTrace();
	    }
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
