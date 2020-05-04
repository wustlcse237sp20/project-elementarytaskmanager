package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import javax.swing.DefaultListModel;

import org.junit.Test;


import taskmanager.Student;
import taskmanager.Teacher;
import taskmanager.Task;
import taskmanager.Categories;
import taskmanager.Schedule;

public class ScheduleTest {

	@Test
	public void testAddSingleTask() throws Exception {
		Schedule testSchedule = new Schedule("testSchedule");
		Task toAdd = new Task("task1", "ToDo");
		testSchedule.addTask(toAdd);
		testSchedule.writeTasks();
		List<Task> scheduleTasks = testSchedule.getTasks();
		boolean foundTask = false;
		for(Task scheduleTask : scheduleTasks) {
			if(scheduleTask.equals(toAdd)) {
				foundTask = true;
			}
		}
		assertTrue(foundTask);
		File toClear = new File("./src/students/testSchedule.txt");
		toClear.delete();
	}
	
	@Test
	public void testAddMultipleTasks() throws Exception {
		Schedule testMultipleTasksSchedule = new Schedule("TestMultipleTasksSchedule");
		Task task1 = new Task("task1", "ToDo");
		Task task2 = new Task("task2", "ToDo");
		Task task3 = new Task("task3", "ToDo");
		testMultipleTasksSchedule.addTask(task1);
		testMultipleTasksSchedule.addTask(task2);
		testMultipleTasksSchedule.addTask(task3);
		testMultipleTasksSchedule.writeTasks();
		List<Task> scheduleTasks = testMultipleTasksSchedule.getTasks();
		int countTasks = 0;
		for(Task scheduleTask : scheduleTasks) {
			if(scheduleTask.equals(task1)) {
				countTasks++;
			}
			if(scheduleTask.equals(task2)) {
				countTasks++;
			}
			if(scheduleTask.equals(task3)) {
				countTasks++;
			}
		}
		assertTrue(countTasks == 3);
		File toClear = new File("./src/students/TestMultipleTasksSchedule.txt");
		toClear.delete();
	}
	
	@Test
	public void testUpdateTask() {
		Schedule toUpdateSchedule = new Schedule("ToUpdateSchedule");
		Task toUpdate = new Task("task1", "ToDo");
		toUpdateSchedule.addTask(toUpdate);
		toUpdateSchedule.writeTasks();
		List<Task> scheduleTasks = toUpdateSchedule.getTasks();
		boolean foundInitialTask = false;
		for(Task scheduleTask : scheduleTasks) {
			if(scheduleTask.equals(toUpdate)) {
				foundInitialTask = true;
			}
		}
		assertTrue(foundInitialTask);
		Task newCategory = new Task("task1", "InProgress");
		toUpdateSchedule.updateTask(newCategory);
		toUpdateSchedule.writeTasks();
		List<Task> updatedTasks = toUpdateSchedule.getTasks();
		boolean foundUpdatedTask = false;
		for(Task studentTask : updatedTasks) {
			if(studentTask.equals(newCategory)) {
				foundUpdatedTask = true;
			}
		}
		assertTrue(foundUpdatedTask);
		File toClear = new File("./src/students/ToUpdateSchedule.txt");
		toClear.delete();
	}

}