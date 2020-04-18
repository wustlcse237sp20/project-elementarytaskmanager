package gui;

import java.io.File;

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
		//TODO: fix this file part
		File userFile = new File("./parentTeacherUsers.txt");
		this.currentTeacher = new Teacher(name, userFile);
	}
	@Override
	public DefaultListModel<Task> getToDoTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultListModel<Task> getInProgressTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultListModel<Task> getDoneTasks() {
		// TODO Auto-generated method stub
		return null;
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

	

}
