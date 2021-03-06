package taskmanager;

import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import java.io.File;

public class Student {
	private Schedule schedule;
	String name;
	List<Achievement> possibleAchievements;

	public Student(String name) {
		this.name = name;
		this.schedule = new Schedule(name);
		possibleAchievements = new LinkedList<Achievement>();
		possibleAchievements.add(new Juggler());
		possibleAchievements.add(new Pyramid());
	}
	
	public Schedule getSchedule() {
		return this.schedule;
	}
	/**
	 * adds a single task to the student's schedule
	 * @param task the task to add
	 */
	public void addTask(Task task) {
		schedule.addTask(task);
	}

	/**
	 * prints out each task on a new line
	 */
	public void viewTasks() {
		List<Task> tasks = schedule.getTasks();

		for (Task task : tasks) {
			System.out.println(task);
		}
	}

	/**
	 * used by the gui to return tasks in a particular category
	 * @param category the desired category 
	 * @return a default list model of those tasks
	 */
	public DefaultListModel<Task> getTasksByCategory(Categories category) {
		return schedule.getTasksByListCategory(category);
	}
	
	/**
	 * used by the gui to return tasks in a particular day
	 * @param day the desired day 
	 * @return a default list model of those tasks
	 */
	public DefaultListModel<Task> getTasksByDay(Days day) {
		return schedule.getTasksByListDay(day);
	}
	

	/**
	 * writes the current list of tasks to the schedule
	 */
	public void saveSchedule() {
		this.schedule.writeTasks();
	}

	/**
	 * updates the task with the same name as the param
	 * @param task the updated task
	 */
	public void updateTask(Task task) {
		this.schedule.updateTask(task);
	}

	public static void main(String[] args) {
		String username = UserInputUtils
				.promptUser("Please type your username and hit Enter to login to Elementary Task Manager");

		addStudentToRoster(username);

		Student thisStudent = new Student(username);
		String userFirstChoice = UserInputUtils
				.promptUser("Welcome, " + username + "! Type 'v' to view your tasks, , or 'q' to quit");

		if (userFirstChoice.equals("v")) {
			thisStudent.viewTasks();
		}
	}

	/**
	 * adds this student to the text file roster of students
	 * @param username name of student to add
	 */
	private static void addStudentToRoster(String username) {
		File userFile = new File("./studentUsers.txt");
		FileReaderHandler reader = new FileReaderHandler(userFile);

		boolean usernameAlreadyInFile = reader.containsLine(username);

		if (!usernameAlreadyInFile) {
			FileWriterHandler usernameWriter = new FileWriterHandler(userFile, true);
			usernameWriter.writeLine(username);
			System.out.println("User " + username + " created");
		}
	}
	
	/**
	 * determines what level of accomplishment the student is at based on the tasks
	 * @return a level enum based on number of tasks IP/Done
	 */
	public Levels calculateLevel() {
		DefaultListModel<Task> completedTasks = getTasksByCategory(Categories.Done);
		DefaultListModel<Task> inProgressTasks = getTasksByCategory(Categories.InProgress);
		if(completedTasks.getSize() > 10) {
			return Levels.TaskMaster;
		}
		else if(completedTasks.getSize() + inProgressTasks.getSize() > 10) {
			return Levels.Novice;
		}
		else {
			return Levels.Beginner;
		}
	}
	
	/**
	 * checks whether or not a student has accomplished certain achievements 
	 * @return a list of all achievements the student has earned
	 */
	public DefaultListModel<Achievement> checkAcheivements() {
		DefaultListModel<Achievement> achievements = new DefaultListModel<Achievement>();
		for(Achievement a : possibleAchievements) {
			if(a.isCompleted(this)) {
				achievements.addElement(a);
			}
		}
		return achievements;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
