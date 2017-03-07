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
		PrintWriter out = response.getWriter();
		String title = "using post";
		//get input from page
		String name =new String(request.getParameter("text"));
		TaskExtractor taskExtractor = new TaskExtractor();
		List<Sentence> sentencesWithTasks = taskExtractor.extractTasks(name);
		for (Sentence sentenceWithTasks : sentencesWithTasks) {
			for (Task task : sentenceWithTasks.getTasks()) {
				System.out.println(task.toString().trim());
			}
		}
		String docType = "<!DOCTYPE html> \n";
		out.println(docType +
		    "<html>\n" +
		    "<head><title>" + title + "</title></head>\n" +
		    "<body bgcolor=\"#f0f0f0\">\n" +
		    "<h1 align=\"center\">" + title + "</h1>\n" +
		    "<ul>\n" +
		    "  <li><b>input string</b>："
		    + name + "\n" +
		    "</ul>\n" +
		    "</body></html>");    
	}    
}
