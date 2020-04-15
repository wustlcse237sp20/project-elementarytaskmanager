package taskmanager;

import java.util.List;
import java.io.File;

public class Student {
	private Schedule schedule;
	String name;
	
	public Student(String name) {
		this.name = name;
		this.schedule = new Schedule(name);
	}

	public void addTask(Task task) {
		schedule.addTask(task);
	}

	public void viewTasks() {
		List<Task> tasks = schedule.getTasks();
		
		for(Task task : tasks) {
			System.out.println(task);
		}
	}

	public static void main(String[] args) {
		String username = UserInputUtils.promptUser("Please type your username and hit Enter to login to Elementary Task Manager");
		
		addStudentToRoster(username);
		
		Student thisStudent = new Student(username);
		String userFirstChoice = UserInputUtils.promptUser("Welcome, " + username + "! Type 'v' to view your tasks, , or 'q' to quit");

		if (userFirstChoice.equals("v")) {
			thisStudent.viewTasks();
		}
		// TODO: Create a file for the parent or teacher user upon login
	}
	
	private static void addStudentToRoster(String username) {
		File userFile = new File("./studentUsers.txt");
		FileReaderHandler reader = new FileReaderHandler(userFile);
		
		boolean usernameAlreadyInFile = reader.containsLine(username);

		if (!usernameAlreadyInFile) {
			FileWriterHandler usernameWriter = new FileWriterHandler(userFile);
			usernameWriter.writeLine(username);
		}
	}
}
