package taskmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.io.File;

public class Student {
	private Schedule schedule;

	public Student(String name) {
		this.schedule = new Schedule(name);
	}

	public void addTask(Task task) {
		schedule.writeTaskToFile(task);
	}

	public void viewTasks() {
		List<Task> tasks = schedule.getTasks();
		
		for(Task task : tasks) {
			System.out.println(task);
		}
	}

	public static void main(String[] args) throws IOException {
		promptForUsername();
	}
	
	private static void promptForUsername() throws IOException {
		System.out.println("Please type your username and hit Enter to login to Elementary Task Manager");
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		String username = inputReader.readLine();
		
		addUserToRoster(username);
		
		Student thisStudent = new Student(username);
		System.out.println("Welcome, " + username + "! Type 'v' to view your tasks, , or 'q' to quit");
		String userFirstChoice = inputReader.readLine();
		if (userFirstChoice.equals("v")) {
			thisStudent.viewTasks();
		}
		// TODO: Create a file for the parent or teacher user upon login
	}
	
	private static void addUserToRoster(String username) {
		File userFile = new File("./studentUsers.txt");
		FileReaderHandler reader = new FileReaderHandler(userFile);
		
		boolean usernameAlreadyInFile = reader.containsLine(username);

		if (!usernameAlreadyInFile) {
			addUser(username, userFile);
		}
	}
	
	private static void addUser(String username, File userFile) {
		FileWriterHandler usernameWriter = new FileWriterHandler(userFile);
		usernameWriter.writeLine(username);
	}
}
