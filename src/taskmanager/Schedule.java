package taskmanager;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;

public class Schedule {
	private File file;
	List<Task> tasks;

	public Schedule(String name) {
		this.file = new File("./src/students/" + name + ".txt");	//has to change path for the gui
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Couldn't create new file " + file);
			e.printStackTrace();
		}
		
		this.tasks = getTasks();
	}

	public void addTask(Task task) {
		tasks.add(task);
	}
	
	/**
	 * used when ending the program to write the current status of the tasks to the schedule file
	 */
	public void writeTasks() {
		FileWriterHandler writer = new FileWriterHandler(file);
		List<String> taskStrings = new LinkedList<>();
		for(Task task : tasks) {
			taskStrings.add(task.toString());
		}
		writer.writeLines(taskStrings);
	}
	
	/**
	 * updates a current task in the list
	 * @param newTask updated task to add
	 */
	public void updateTask(Task newTask) {
		for(Task task : tasks) {
			if(task.getName().equals(newTask.getName())) {
				task.setCategory(newTask.getCategory());
			}
		}
	}

	/**
	 * returns the list of tasks in the schedule
	 * @return list of tasks
	 */
	public List<Task> getTasks() {
		List<Task> tasks = new LinkedList<Task>();

		FileReaderHandler reader = new FileReaderHandler(file);
		List<String> lines = reader.getLines();

		for (String line : lines) {
			Task task = createTaskFromLine(line);
			tasks.add(task);
		}

		return tasks;
	}

	/**
	 * used by the gui to return tasks by specific category
	 * @param category desired category of tasks
	 * @return default list model of tasks
	 */
	public DefaultListModel<Task> getTasksByListCategory(Categories category) {
		DefaultListModel<Task> tasks = new DefaultListModel<Task>();

		FileReaderHandler reader = new FileReaderHandler(file);
		List<String> lines = reader.getLines();
		int index = 0;
		for (String line : lines) {
			Task task = createTaskFromLine(line);
			if(task.getCategory().equals(category)) {
				tasks.add(index, task);
				index++;
			}
		}
		return tasks;
	}

	/**
	 * parses line from schedule file and creates a task from it
	 * @param line line from file
	 * @return newly created task
	 */
	private Task createTaskFromLine(String line) {
		int divide = line.indexOf('-');
		if (divide > -1) {
			String name = line.substring(0, divide - 1);
			String category = line.substring(divide + 2, line.length());
			Task task = new Task(name, category);
			return task;
		} else {
			System.out.println("Could not read task from line");
			return null;
		}
	}
}
