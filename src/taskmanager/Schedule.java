package taskmanager;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Schedule {
	private File file;

	public Schedule(String name) {
		this.file = new File("./" + name + ".txt");
	}

	public void writeTaskToFile(Task task) {
		String line = task.toString();
		FileWriterHandler writer = new FileWriterHandler(file);
		writer.writeLine(line);
	}

	public List<Task> getTasks() {
		List<Task> tasks = new LinkedList<Task>();
		
		FileReaderHandler reader = new FileReaderHandler(file);
		List<String> lines = reader.getLines();
		
		for(String line : lines) {
			Task task = createTaskFromLine(line);
			tasks.add(task);
		}
				
		return tasks;
	}
	
	private Task createTaskFromLine(String line) {
		int divide = line.indexOf('-');
		if(divide > -1) {
			String name = line.substring(0, divide);
			String category = line.substring(divide+1, line.length());
			Task task = new Task(name, category);
			return task;
		} else {
			System.out.println("Could not read task from line");
			return null;
		}
	}
}
