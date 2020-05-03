package gui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//import java.io.File;

import javax.swing.DefaultListModel;

import taskmanager.Categories;
import taskmanager.Days;
import taskmanager.FileReaderHandler;
import taskmanager.Student;
//import taskmanager.Student;
import taskmanager.Task;
import taskmanager.Teacher;

public class TeacherController implements Controller {
	String username;
	Teacher currentTeacher;
	Student currentStudent;

	public TeacherController(String name) {
		this.username = name;
		this.currentTeacher = new Teacher(name);
		// TODO: login teacher method???
		try {
			this.currentStudent = currentTeacher.getAllStudents().firstElement();
		} catch (Exception e) {
			this.currentStudent = new Student("temp student");
		}

	}

	@Override
	public Task addTask(String name, List<String> list) {
		Task task = new Task(name);
		currentTeacher.assignTask(task, list);
		return task;
	}

	@Override
	public Student getStudent() {
		return currentStudent;
	}

	@Override
	public DefaultListModel<Student> getStudents() {
		return currentTeacher.getAllStudents();
	}

	@Override
	public List<DefaultListModel<Task>> getCategoryTasks() {
		List<DefaultListModel<Task>> tasks = new ArrayList<>();
		for (Categories category : Categories.values()) {
			DefaultListModel<Task> taskColumn = currentStudent.getTasksByCategory(category);
			tasks.add(taskColumn);
		}
		return tasks;
	}

	@Override
	public List<DefaultListModel<Task>> getDayTasks() {
		List<DefaultListModel<Task>> tasks = new ArrayList<>();
		for (Days day : Days.values()) {
			DefaultListModel<Task> taskColumn = currentStudent.getTasksByDay(day);
			tasks.add(taskColumn);
		}
		return tasks;
	}

	@Override
	public void setStudent(Student student) {
		this.currentStudent = student;
	}

	@Override
	public List<String> processInput(String listOfNames) {
		return currentTeacher.processInput(listOfNames);
	}

	@Override
	public void addStudent(String name) {
		this.currentTeacher.addStudent(name);
	}

}
