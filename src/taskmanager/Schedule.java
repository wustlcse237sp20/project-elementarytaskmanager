package taskmanager;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;

public class Schedule {
	private File file;
	List<Task> tasks;

	public Schedule(String name) {
		this.file = FileWriterHandler.makeFile("students/" + name);
		this.tasks = getTasks();
	}

	public void addTask(Task task) {
		tasks.add(task);
	}
	
	/**
	 * used when ending the program to write the current status of the tasks to the schedule file
	 */
	public void writeTasks() {
		FileWriterHandler writer = new FileWriterHandler(file, false);
		List<String> taskStrings = new LinkedList<>();
		for(Task task : tasks) {
			taskStrings.add(task.writeToFileFormat());
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
	 * 
	 * @return list of tasks in the schedule
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
		DefaultListModel<Task> tasks = new DefaultListModel<>();

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
	 * used by the gui to return tasks by specific day
	 * @param day desired day of tasks
	 * @return default list model of tasks
	 */
	public DefaultListModel<Task> getTasksByListDay(Days day) {
		DefaultListModel<Task> tasks = new DefaultListModel<>();

		FileReaderHandler reader = new FileReaderHandler(file);
		List<String> lines = reader.getLines();
		int index = 0;
		for (String line : lines) {
			Task task = createTaskFromLine(line);
			if(task.getDay().equals(day)) {
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
		String[] dividedLine = line.split("-");
		
		if(dividedLine.length != 3) {
			System.out.println("Could not read task from line");
			return null;
		}
		
		String name = dividedLine[0];
		String category = dividedLine[1];
		String day = dividedLine[2];
		Task task = new Task(name, category, day);
		return task;
	}
}
