package gui;
import taskmanager.*;

import javax.swing.DefaultListModel;

import taskmanager.Task;

public interface Controller {
	public DefaultListModel<Task> getToDoTasks();
	public DefaultListModel<Task> getInProgressTasks();
	public DefaultListModel<Task> getDoneTasks();
	public Task addTask(String name);
	public Student getStudent();
	
	public DefaultListModel<Student> getStudents();
}
