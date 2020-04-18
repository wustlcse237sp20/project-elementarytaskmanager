package gui;

import javax.swing.DefaultListModel;

import taskmanager.*;

public class StudentController implements Controller{
	Student currentStudent;
	
	public StudentController(String name) {
		this.currentStudent = new Student(name);
	}

	@Override
	public DefaultListModel<Task> getToDoTasks() {
		return currentStudent.getTasksByCategory(Categories.ToDo);
	}

	@Override
	public DefaultListModel<Task> getInProgressTasks() {
		return currentStudent.getTasksByCategory(Categories.InProgress);
	}

	@Override
	public DefaultListModel<Task> getDoneTasks() {
		return currentStudent.getTasksByCategory(Categories.Done);
	}
	
	public Task addTask(String name) {
		Task newTask = new Task(name);
		currentStudent.addTask(newTask);
		return newTask;
	}
	
}
