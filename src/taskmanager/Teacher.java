package taskmanager;

import java.io.File;
import java.io.IOException;

public class Teacher {
	private String name;
	private File managedStudents;

	public Teacher(String name, File managedStudents) {
		this.name = name;
		this.managedStudents = managedStudents;
	}

	public void addStudent(String studentName) {
		FileWriterHandler writer = new FileWriterHandler(managedStudents);
		writer.writeLine(studentName);
	}

	public static String[] processInput(String listOfNames) {
		String[] processedNames;
		// TODO: Add an option that can read names from the file of parent's students
		// TODO: When do we check for valid input?
		processedNames = listOfNames.split("\\s*,\\s*");
		return processedNames;
	}

	public static void main(String[] args) {
		String username = UserInputUtils.promptUser("Please type your username and hit Enter to login to Elementary Task Manager");

		File userFile = new File("./parentTeacherUsers.txt");
		FileReaderHandler reader = new FileReaderHandler(userFile);
		boolean usernameAlreadyInFile = reader.containsLine(username);

		if (!usernameAlreadyInFile) {
			FileWriterHandler writer = new FileWriterHandler(userFile);
			writer.writeLine(username);
		}

		String userFirstChoice = UserInputUtils.promptUser("Welcome, " + username + "! Type 's' to add a student to your caseload, 't' to add a task to a student schedule, or 'q' to quit");

		if (userFirstChoice.equals("t")) { 
			String taskName = UserInputUtils.promptUser("Please enter the name of the task you would like to create");
			
			Task task = new Task(taskName, "To do");

			String taskAssignee = UserInputUtils.promptUser("Who would you like to assign this task to? Either type a name, a list of names separated by commas, or . for all");
			String[] processedNames = processInput(taskAssignee);

			for (int assigneeCounter = 0; assigneeCounter < processedNames.length; assigneeCounter++) {
				File schedule = new File("./students/" + processedNames[assigneeCounter] + ".txt");
				try {
					schedule.createNewFile();
				} catch (IOException e) {
					System.out.println("Couldn't create new file " + schedule);
					e.printStackTrace();
				}
				Student student = new Student(processedNames[assigneeCounter]);
				student.addTask(task);
				System.out.println("Task " + taskName + " has been successfully added for " + processedNames[assigneeCounter]);
			}
		} else if (userFirstChoice.equals("s")) {
			// TODO: put add a student code here
		} else if (userFirstChoice.equals("q")) {
			return;
		} else {
			// TODO: put invalid input code here
		}
	}
}
