package taskmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Schedule {
	private File file;

	public Schedule(String name) {
		this.file = new File("./" + name + ".txt");
	}

	public void writeTaskToFile(Task task) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true)); // Set true for append mode
			writer.write(task.toString());
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			System.out.println("Could not add task to file");
		} 
	}

	public List<Task> getTasks() {
		List<Task> tasks = new LinkedList<Task>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.file));
			String line;
			while ((line = reader.readLine()) != null) {
				Task task = createTaskFromLine(line);
				tasks.add(task);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not find schedule file");
		} catch (IOException e) {
			System.out.println("Could not read tasks from file");
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
