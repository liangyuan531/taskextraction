package au.edu.adelaide.cs.stringtotasks;

import java.io.IOException;
import java.util.List;

import ca.mcgill.cs.swevo.taskextractor.analysis.TaskExtractor;
import ca.mcgill.cs.swevo.taskextractor.model.Sentence;
import ca.mcgill.cs.swevo.taskextractor.model.Task;

public class StringToTasks {

	public static void main(String[] args) throws IOException {
		TaskExtractor taskExtractor = new TaskExtractor();
		 List<Sentence> sentencesWithTasks =
		 taskExtractor.extractTasks(args[0], true, true, true, true, true, true);
//		List<Sentence> sentencesWithTasks = taskExtractor
//				.extractTasks(
//						"This test creates a task1. This task2 is created. I saw the task3 you create. There are ways to create from text. This text inCludes camel case. This text <tt>includes</tt> code.",
//						true, true, true, true, true, true);
		for (Sentence sentenceWithTasks : sentencesWithTasks) {
			for (Task task : sentenceWithTasks.getTasks()) {
				System.out.println(task.toString().trim());
			}
		}
	}
}
