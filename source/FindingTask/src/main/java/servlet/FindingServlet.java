package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        int OutMemory=1;
		//if have not choose settings
		if(setting == null){
			Configuration.setGen_option("yeswithdefined");
			Configuration.setPro_option("yeswithdefined");
			generic = "yeswithdefined";
			programming = "yeswithdefined";
			myVerbs = "";
			myAccusatives = "";
			try {
				sentencesWithTasks = taskExtractor.extractTasks(text,true,true,true,true,true,true);
			} catch (OutOfMemoryError E) {
				// TODO Auto-generated catch block
				//E.printStackTrace();
                //HttpServletResponse.sendRedirect("index.jsp");
                System.err.println("Catch the error of OutOfMemory in if statement");
                //response.sendRedirect("index.jsp");
                String errormessage="memoryerror";
                request.setAttribute("error",errormessage);
                request.getRequestDispatcher("index.jsp").forward(request,response);
                OutMemory=0;
                //System.exit(1);
			}
			otherOptions = "direct object, passive nominal subject, relative clause modifier, "
					+ "prepositional modifier, RegexedCode, TaggedCode";
		}else{
			List<Boolean> options = checkOptions(tempOptions);
			otherOptions = otherOptions(options);
            try{
                sentencesWithTasks = taskExtractor.extractTasks(text,options.get(0),options.get(1),options.get(2),options.get(3),options.get(4),options.get(5));
            }catch(OutOfMemoryError E){
                System.err.println("Catch the error of OutOfMemory in if statement");
                //response.sendRedirect("index.jsp");
                String errormessage="memoryerror";
                request.setAttribute("error",errormessage);
                request.getRequestDispatcher("index.jsp").forward(request,response);
                OutMemory=0;
            }
		}
        if(OutMemory == 1){
            System.err.println("still execute if statement");
            for (Sentence sentenceWithTasks : sentencesWithTasks) {
                for (Task task : sentenceWithTasks.getTasks()) {
                    tasks.add(task.toString().trim());
                }
            }
            String result = "";
            if(tasks.size() != 0)
                result = formatResults(tasks);          
            String time = getTime();
            String country = request.getLocale().getLanguage();
            if(text.length() > 1000){
                text = text.substring(0,1000);
            }
            text = text.replaceAll("\'", "");
            
            String itemSql = "INSERT INTO extraction VALUES ('"
                    + time +"','"
                    + country +"','"
                    + result +"','"
                    + programming +"','"
                    + generic +"','"
                    + myVerbs +"','"
                    + myAccusatives +"','"
                    + otherOptions +"','"
                    + text +"')";           
            //createTable();
            
            //add users for viewing database (first use or add another account)
            //List<String> accounts = getAccounts();
            //insertAccounts(accounts);
            
            //add searching items
            insertItem(itemSql);
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
	}
	
	private void insertAccounts(List<String> accounts){
		try {
	    	Connection conn = getConnection();
	    	Statement state = conn.createStatement();
	    	String delete = "DELETE FROM account";
	    	state.execute(delete);
	    	for(int i=0;i<accounts.size();i+=2){
				String accountSql = "INSERT INTO account VALUES ('"+ accounts.get(i)+"','"+accounts.get(i+1)+"')";
				//System.out.println(accountSql);
				state.execute(accountSql);
			}
	    	conn.close();
	    } catch (URISyntaxException e1) {
	    	e1.printStackTrace();
	    } catch (SQLException e1) {
	    	e1.printStackTrace();
	    }
		
	}
	
	private List<String> getAccounts(){
		List<String> accounts = new ArrayList<String>();
		BufferedReader bf;
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("accounts")), "UTF8"));
			String str = "";
			while((str=bf.readLine()) != null){
				String[] s = str.split(" ");
				//username
				accounts.add(s[0]);
				//password
				accounts.add(s[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	private String getTime(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String time = dateFormat.format(now); 
		return time;
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
		if(option.length() > 0)
			option = option.substring(0, option.length()-1);
		return option;
	}

	private void insertItem(String itemSql){
	    try {
	    	Connection conn = getConnection();
	    	Statement state = conn.createStatement();
	    	state.execute(itemSql);
	    	conn.close();
	    } catch (URISyntaxException e1) {
	    	e1.printStackTrace();
	    } catch (SQLException e1) {
	    	e1.printStackTrace();
	    }
	}

	private void createTable(){
	    String infoTable = "CREATE TABLE extraction ("
	    		+ "Time varchar(20),"
	     		+ "Country varchar(20),"
	     		+ "Results varchar(5000),"
	            + "isNonprogramming varchar(1000),"
	            + "isGenericAction varchar(1000),"
	            + "PROGRAMMING_ACTIONS varchar(1000),"
	     		+ "GENERIC_ACCUSATIVES varchar(1000),"
	            + "GRAMMATICAL_DEPENDENCIES_and_CODE varchar(1000),"
	     		+ "TEXT varchar(5000))";
	    String accountTable = "CREATE TABLE account (username varchar(20), password varchar(20))";
	    try {
	    	Connection conn = getConnection();
	    	Statement state = conn.createStatement();
	    	state.execute(infoTable);
	    	state.execute(accountTable);
	    	conn.close();
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
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		return DriverManager.getConnection(dbUrl, username, password);
  }
}
