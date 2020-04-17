package gui;
import java.util.List;

import javax.swing.DefaultListModel;

import taskmanager.*;

public class StudentController implements Controller{

//	Student currentStudent = new Student("Emily");

	@Override
	public DefaultListModel<Task> getToDoTasks() {
		// TODO Auto-generated method stub
//		return currentStudent.getTasksByCategory("to do");
		DefaultListModel<Task> list = new DefaultListModel<Task>();
		list.add(0, new Task("lab1", "to do"));
		list.add(1, new Task("lab2", "to do"));
		
		return list;
	}
	
//	public DefaultListModel<Task> getAllTasks(){
//		return (DefaultListModel<Task>) currentStudent.getTasks();
//	}
}
