package tests;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;
import taskmanager.Student;
import taskmanager.Teacher;
import taskmanager.Task;

public class StudentTest {

			
			@Test
			public void addSingleTask() throws Exception{
				Student toAddTask = new Student(new File("toAddTask"), "toAddTask");
				Task toAdd = new Task("task1", "to-do");
				toAddTask.addTask(toAdd);
				BufferedReader reader = new BufferedReader(new FileReader(new File("toAddTask")));
						String task;
						boolean found = false;
						while ((task = reader.readLine()) != null) {	
							if(task.equals("task1 - to-do")) {
								found = true;
							}
						}
				assertTrue(found);

			}

			

		

	

}
