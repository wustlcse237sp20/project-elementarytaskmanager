package taskmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Student {
	private File schedule;
	private String name;

	public Student(File schedule, String name) {
		this.schedule = schedule;
		this.name = name;
	}

	public void addTask(Task task) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(this.schedule, true) // Set true for append mode
		);
		// need to not add new line for the first task
		writer.newLine();
		writer.write(task.toString());
		writer.close();
	}

	public void viewTasks() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(this.schedule) // Set true for append mode
		);
		String task;
		while ((task = reader.readLine()) != null) {	
			int divide = task.indexOf('-');
			System.out.println(task);
		}
	}

	public static void main(String[] args) throws IOException {

		System.out.println("Please type your username and hit Enter to login to Elementary Task Manager");
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		String username = inputReader.readLine();
		File userFile = new File("./studentUsers.txt");
		BufferedReader fileReader = new BufferedReader(new FileReader(userFile));
		String fileUsername;
		boolean usernameAlreadyInFile = false;
		while ((fileUsername = fileReader.readLine()) != null) {
			if (fileUsername.equals(username)) {
				usernameAlreadyInFile = true;
			}
		}
		fileReader.close();

		if (!usernameAlreadyInFile) {
			BufferedWriter usernameWriter = new BufferedWriter(new FileWriter(userFile));
			usernameWriter.newLine();
			usernameWriter.write(username);
			usernameWriter.close();
		}
		Student thisStudent = new Student(new File("./" + username + ".txt"), username);
		System.out.println("Welcome, " + username + "! Type 'v' to view your tasks, , or 'q' to quit");
		String userFirstChoice = inputReader.readLine();
		if (userFirstChoice.equals("v")) {
			thisStudent.viewTasks();
		}
		// TODO: Create a file for the parent or teacher user upon login

	}
}
