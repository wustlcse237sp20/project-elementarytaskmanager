package gui;

//import java.io.File;

import javax.swing.DefaultListModel;

import taskmanager.Student;
//import taskmanager.Student;
import taskmanager.Task;
import taskmanager.Teacher;

public class TeacherController implements Controller{
	String username; 
	Teacher currentTeacher;
	
	public TeacherController(String name) {
		this.username = name;
		this.currentTeacher = new Teacher(name);
		//TODO: login teacher method???
		
	}
	@Override
	public DefaultListModel<Task> getToDoTasks() {
		// TODO Auto-generated method stub
		DefaultListModel<Task> listModel = new DefaultListModel<Task>();
		listModel.addElement(new Task("test"));
		return listModel;
	}

	@Override
	public DefaultListModel<Task> getInProgressTasks() {
		// TODO Auto-generated method stub
		DefaultListModel<Task> listModel = new DefaultListModel<Task>();
		listModel.addElement(new Task("test"));
		return listModel;
	}

	@Override
	public DefaultListModel<Task> getDoneTasks() {
		// TODO Auto-generated method stub
		DefaultListModel<Task> listModel = new DefaultListModel<Task>();
		listModel.addElement(new Task("test"));
		return listModel;
	}
	@Override
	public Task addTask(String name) {
		return null;
		// TODO Auto-generated method stub
		
	}
	@Override
	public Student getStudent() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DefaultListModel<Student> getStudents() {
		// TODO Auto-generated method stub
		return currentTeacher.getAllStudents();
	}

	

}
