package gui;

import java.util.List;

import javax.swing.DefaultListModel;

import taskmanager.Task;

public interface Controller {

	
	public DefaultListModel<Task> getToDoTasks();
	public DefaultListModel<Task> getInProgressTasks();
	public DefaultListModel<Task> getDoneTasks();
}
