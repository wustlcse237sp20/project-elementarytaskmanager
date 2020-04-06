package taskmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
	
	private static void addUserToRoster(String username) throws IOException {
		File userFile = new File("./studentUsers.txt");
		
		boolean usernameAlreadyInFile = userExistsInFile(username, userFile);

		if (!usernameAlreadyInFile) {
			addUser(username, userFile);
		}
	}
	
	private static boolean userExistsInFile(String username, File userFile) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(userFile));

		String fileUsername;
		while ((fileUsername = fileReader.readLine()) != null) {
			if (fileUsername.equals(username)) {
				fileReader.close();
				return true;
			}
		}
		fileReader.close();
		return false;
	}
	
	private static void addUser(String username, File userFile) throws IOException {
		BufferedWriter usernameWriter = new BufferedWriter(new FileWriter(userFile));
		usernameWriter.newLine();
		usernameWriter.write(username);
		usernameWriter.close();
	}
}
