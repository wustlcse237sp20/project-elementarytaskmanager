package gui;

import javax.swing.DefaultListModel;

import taskmanager.*;

public class StudentController implements Controller{
	String username; 
	Student currentStudent;
	
	public StudentController(String name) {
		this.username = name;
		this.currentStudent = new Student(this.username);
	}

	@Override
	public DefaultListModel<Task> getToDoTasks() {
		return currentStudent.getTasksByCategory("To do");
	}

	@Override
	public DefaultListModel<Task> getInProgressTasks() {
		return currentStudent.getTasksByCategory("In progress");
	}

	@Override
	public DefaultListModel<Task> getDoneTasks() {
		return currentStudent.getTasksByCategory("Done");
	}
	
	public Task addTask(String name) {
		Task newTask = new Task(name, "To do");
		currentStudent.addTask(newTask);
		return newTask;
//		currentStudent.getTasksByCategory("To do");
	}
	
}
