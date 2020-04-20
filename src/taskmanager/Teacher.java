package taskmanager;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Teacher {
	private String name;
	private File managedStudents;

	public Teacher(String name) {
		this.name = name;
		this.managedStudents = new File("./src/teachers/" + name + ".txt"); //has to change path for the gui
		try {
			managedStudents.createNewFile();
		} catch (IOException e) {
			System.out.println("Couldn't create new file " + managedStudents);
			e.printStackTrace();
		}
	}

	/**
	 * adds a student to the list of students the teacher manages
	 * @param studentName name of student to add
	 */
	public void addStudent(String studentName) {
		FileWriterHandler writer = new FileWriterHandler(managedStudents);
		writer.writeLine(studentName);
	}

	/**
	 * if the teacher enters a list of names for a task, this will process them
	 * @param listOfNames full teacher entry
	 * @return list of the separated names
	 */
	public List<String> processInput(String listOfNames) {
		List<String> processedNames;
		if (listOfNames.equals(".")) {
			listOfNames = "";
			FileReaderHandler reader = new FileReaderHandler(managedStudents);
			processedNames = reader.getLines();
		} else {
			String[] tempProcessedNames = listOfNames.split("\\s*,\\s*");
			processedNames = new LinkedList<String>();
			for (String name : tempProcessedNames) {
				processedNames.add(name);
			}
		}
		return processedNames;
	}

	/**
	 * assigns a task to given students
	 * @param task task to be assigned 
	 * @param studentNames list of students to add task to
	 */
	public void assignTask(Task task, List<String> studentNames) {
		for (int studentCounter = 0; studentCounter < studentNames.size(); studentCounter++) {
			Student student = new Student(studentNames.get(studentCounter));
			student.addTask(task);
			System.out.println(
					"Task " + task.getName() + " has been successfully added for " + studentNames.get(studentCounter));
		}
	}

	/**
	 * processes login for teachers
	 * @param teacherName name of teacher wanting to login
	 */
	public static void login(String teacherName) {
		File userFile = new File("./parentTeacherUsers.txt");
		FileReaderHandler reader = new FileReaderHandler(userFile);
		boolean usernameAlreadyInFile = reader.containsLine(teacherName);

		if (!usernameAlreadyInFile) {
			FileWriterHandler writer = new FileWriterHandler(userFile);
			writer.writeLine(teacherName);
		}
	}

	public static void main(String[] args) {
		String username = UserInputUtils
				.promptUser("Please type your username and hit Enter to login to Elementary Task Manager");

		login(username);
		Teacher teacher = new Teacher(username);

		String userFirstChoice = UserInputUtils.promptUser("Welcome, " + username
				+ "! Type 's' to add a student to your caseload, 't' to add a task to a student schedule, or 'q' to quit");

		if (userFirstChoice.equals("t")) {
			String taskName = UserInputUtils.promptUser("Please enter the name of the task you would like to create");

			Task task = new Task(taskName);

			String taskAssignee = UserInputUtils.promptUser(
					"Who would you like to assign this task to? Either type a name, a list of names separated by commas, or . for all");
			List<String> processedNames = teacher.processInput(taskAssignee);
			teacher.assignTask(task, processedNames);

		} else if (userFirstChoice.equals("s")) {
			String studentName = UserInputUtils
					.promptUser("Please enter the name of the student you would like to add to your caseload");
			teacher.addStudent(studentName);
		} else if (userFirstChoice.equals("q")) {
			return;
		} else {
			// TODO: put invalid input code here
		}
	}
}
