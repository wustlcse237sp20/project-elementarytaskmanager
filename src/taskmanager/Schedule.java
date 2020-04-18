package taskmanager;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;

public class Schedule {
	private File file;

	public Schedule(String name) {
		this.file = new File("./src/students/" + name + ".txt");	//has to change path for the gui
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Couldn't create new file " + file);
			e.printStackTrace();
		}
	}

	public void addTask(Task task) {
		String line = task.toString();
		FileWriterHandler writer = new FileWriterHandler(file);
		writer.writeLine(line);
	}

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

	public DefaultListModel<Task> getTasksByListCategory(String cat) {
		DefaultListModel<Task> tasks = new DefaultListModel<Task>();

		FileReaderHandler reader = new FileReaderHandler(file);
		List<String> lines = reader.getLines();
		int index = 0;
		for (String line : lines) {
			Task task = createTaskFromLine(line);
			if(task.category.equals(cat)) {
				tasks.add(index, task);
				index++;
			}
		}
		return tasks;
	}

	private Task createTaskFromLine(String line) {
		int divide = line.indexOf('-');
		if (divide > -1) {
			String name = line.substring(0, divide);
			String category = line.substring(divide + 2, line.length());
			Task task = new Task(name, category);
			return task;
		} else {
			System.out.println("Could not read task from line");
			return null;
		}
	}
}
