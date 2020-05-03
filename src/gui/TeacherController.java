package gui;

import java.util.ArrayList;
import java.util.List;

//import java.io.File;

import javax.swing.DefaultListModel;

import taskmanager.Categories;
import taskmanager.Days;
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
	
	@Override
	public List<DefaultListModel<Task>> getCategoryTasks() {
		List<DefaultListModel<Task>> tasks = new ArrayList<>();
		
		for(Categories category : Categories.values()) {
			DefaultListModel<Task> listModel = new DefaultListModel<>();
			listModel.addElement(new Task("test"));
			tasks.add(listModel);
		}
				
		return tasks;
	}
	@Override
	public List<DefaultListModel<Task>> getDayTasks() {
		List<DefaultListModel<Task>> tasks = new ArrayList<>();
		
		for(Days day : Days.values()) {
			DefaultListModel<Task> listModel = new DefaultListModel<>();
			listModel.addElement(new Task("test"));
			tasks.add(listModel);
		}
				
		return tasks;
	}

	

}
