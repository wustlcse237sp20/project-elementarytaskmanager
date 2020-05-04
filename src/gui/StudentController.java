package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

import taskmanager.*;

public class StudentController implements Controller{
	private Student currentStudent;
	
	public StudentController(String name) {
		this.currentStudent = new Student(name);
	}
	
	public Student getStudent() {
		return currentStudent;
	}
	
	public Task addTask(String name, List<String> list) {
		Task newTask = new Task(name);
		currentStudent.addTask(newTask);
		return newTask;
	}

	@Override
	public DefaultListModel<Student> getStudents() {
		return null;
	}

	@Override
	public List<DefaultListModel<Task>> getCategoryTasks() {
		List<DefaultListModel<Task>> tasks = new ArrayList<>();
		
		for(Categories category : Categories.values()) {
			DefaultListModel<Task> taskColumn = currentStudent.getTasksByCategory(category);
			tasks.add(taskColumn);
		}
				
		return tasks;
	}

	@Override
	public List<DefaultListModel<Task>> getDayTasks() {
		List<DefaultListModel<Task>> tasks = new ArrayList<>();
		
		for(Days day : Days.values()) {
			DefaultListModel<Task> taskColumn = currentStudent.getTasksByDay(day);
			tasks.add(taskColumn);
		}
				
		return tasks;
	}

	@Override
	public void setStudent(Student student) {
	}

	@Override
	public List<String> processInput(String listOfNames) {
		return null;
	}

	@Override
	public void addStudent(String name) {
	}

	@Override
	public Levels getStudentLevel() {
		return currentStudent.calculateLevel();
	}

	@Override
	public DefaultListModel<Achievement> getStudentAchievements() {
		return currentStudent.checkAcheivements();
	}
	
}
