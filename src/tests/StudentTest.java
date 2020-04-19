package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.junit.Test;


import taskmanager.Student;
import taskmanager.Teacher;
import taskmanager.Task;

public class StudentTest {

	@Test
	public void testAddSingleTask() throws Exception {
		Student toAddTask = new Student("toAddTask");
		Task toAdd = new Task("task1", "ToDo");
		toAddTask.addTask(toAdd);
		toAddTask.saveSchedule();
		List<Task> studentTasks = toAddTask.getSchedule().getTasks();
		boolean foundTask = false;
		for(Task studentTask : studentTasks) {
			if(studentTask.equals(toAdd)) {
				foundTask = true;
			}
		}
		assertTrue(foundTask);
		File toClear = new File("./src/students/toAddTask.txt");
		toClear.delete();
	}
	
	@Test
	public void testAddMultipleTasks() throws Exception {
		Student StudentMult = new Student("StudentMult");
		Task task1 = new Task("task1", "ToDo");
		Task task2 = new Task("task2", "ToDo");
		Task task3 = new Task("task3", "ToDo");
		StudentMult.addTask(task1);
		StudentMult.addTask(task2);
		StudentMult.addTask(task3);
		StudentMult.saveSchedule();
		List<Task> studentTasks = StudentMult.getSchedule().getTasks();
		int countTasks = 0;
		for(Task studentTask : studentTasks) {
			if(studentTask.equals(task1)) {
				countTasks++;
			}
			if(studentTask.equals(task2)) {
				countTasks++;
			}
			if(studentTask.equals(task3)) {
				countTasks++;
			}
		}
		assertTrue(countTasks == 3);
		File toClear = new File("./src/students/StudentMult.txt");
		toClear.delete();
	}
	@Test
	public void testUpdateTask() {
		Student toAddTask = new Student("toAddTask");
		Task toUpdate = new Task("task1", "ToDo");
		toAddTask.addTask(toUpdate);
		toAddTask.saveSchedule();
		List<Task> studentTasks = toAddTask.getSchedule().getTasks();
		boolean foundInitialTask = false;
		for(Task studentTask : studentTasks) {
			if(studentTask.equals(toUpdate)) {
				foundInitialTask = true;
			}
		}
		assertTrue(foundInitialTask);
		Task newCategory = new Task("task1", "InProgress");
		toAddTask.updateTask(newCategory);
		toAddTask.saveSchedule();
		List<Task> updatedTasks = toAddTask.getSchedule().getTasks();
		boolean foundUpdatedTask = false;
		for(Task studentTask : updatedTasks) {
			if(studentTask.equals(newCategory)) {
				foundUpdatedTask = true;
			}
		}
		assertTrue(foundUpdatedTask);
		File toClear = new File("./src/students/toAddTask.txt");
		toClear.delete();
	}

}
