package gui;

import taskmanager.*;
import java.util.List;
import javax.swing.DefaultListModel;

import taskmanager.Task;

public interface Controller {
//	public DefaultListModel<Task> getToDoTasks();
//	public DefaultListModel<Task> getInProgressTasks();
//	public DefaultListModel<Task> getDoneTasks();
	
	public List<DefaultListModel<Task>> getCategoryTasks();
	public List<DefaultListModel<Task>> getDayTasks();
	
	public Task addTask(String name);
	public Student getStudent();
	
	public DefaultListModel<Student> getStudents();
}
