package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		String createTableSql = "CREATE TABLE Extraction ("
				+ "IP_Addr varchar(20), "
				+ "Results varchar(500),"
				+ "Text varchar(500)"
				+ "isNonprogramming varchar(10)"
				+ "isGenericAction varchar(10)"
				+ "verbs varchar(100)"
				+ "generic varchar(100))";
		String itemSql = "INSERT INTO Extraction VALUES ('192.168.1.1','find','xxxxx','a','b','sss','ss')";
		Connection conn = getConnection();
		System.out.println(conn);
		Statement state = null;
		try {
			state = conn.createStatement();
			state.execute(createTableSql);
			state.execute(itemSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//content type
		response.setContentType("text/html;charset=UTF-8");
		ServletContext application=this.getServletContext();
		String setting = (String)application.getAttribute("isSetting");
		if(setting == null){
			Configuration.setGen_option("yes");
			Configuration.setPro_option("yes");
			Configuration.setCustomise1("no");
			Configuration.setCustomise2("no");
		}
		//get input from page
		String text =new String(request.getParameter("text"));
		TaskExtractor taskExtractor = new TaskExtractor();
		List<String> tasks = new ArrayList<String>();
		List<Sentence> sentencesWithTasks = null;
		synchronized(this){
			sentencesWithTasks = taskExtractor.extractTasks(text);
		}
		
		for (Sentence sentenceWithTasks : sentencesWithTasks) {
			for (Task task : sentenceWithTasks.getTasks()) {
				tasks.add(task.toString().trim());
			}
		}
		request.setAttribute("tasks", tasks);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	private static Connection getConnection(){
		System.out.println("connect...");
	    String dbUrl = System.getenv("JDBC_DATABASE_URL");
	    System.out.println("url: "+dbUrl);
	    Connection conn = null;
	    try {
	    	conn = DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return conn;
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
