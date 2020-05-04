package gui;

import taskmanager.*;
import java.util.List;
import javax.swing.DefaultListModel;

public interface Controller {
//	public DefaultListModel<Task> getToDoTasks();
//	public DefaultListModel<Task> getInProgressTasks();
//	public DefaultListModel<Task> getDoneTasks();

	public List<DefaultListModel<Task>> getCategoryTasks();

	public List<DefaultListModel<Task>> getDayTasks();

	public Task addTask(String name, List<String> list);

	public Student getStudent();

	public DefaultListModel<Student> getStudents();

	public void setStudent(Student student);

	public List<String> processInput(String listOfNames);
	
	public void addStudent(String name);
	
	public Levels getStudentLevel();
	
	public DefaultListModel<Achievement> getStudentAchievements();
}
