package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.mcgill.cs.swevo.taskextractor.analysis.TaskExtractor;
import ca.mcgill.cs.swevo.taskextractor.model.Sentence;
import ca.mcgill.cs.swevo.taskextractor.model.Task;



@WebServlet(
        name = "FindingServlet", 
        urlPatterns = {"/FindingServlet"}
    )
public class FindingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        ServletOutputStream out = resp.getOutputStream();
//        out.write("hello heroku".getBytes());
//        out.flush();
//        out.close();
    }
	
	@Override  
	protected void doPost(HttpServletRequest request,  
	     HttpServletResponse response) throws ServletException, IOException {  
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "using post";
		// 处理中文
		String name =new String(request.getParameter("text"));
		TaskExtractor taskExtractor = new TaskExtractor();
		System.out.println("xxxxx");
		List<Sentence> sentencesWithTasks = taskExtractor.extractTasks(name);
		for (Sentence sentenceWithTasks : sentencesWithTasks) {
			System.out.println("xx");
			for (Task task : sentenceWithTasks.getTasks()) {
				System.out.println("x");
				System.out.println(task.toString().trim());
			}
		}    
	}    
}
