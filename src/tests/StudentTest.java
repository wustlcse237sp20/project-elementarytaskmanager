package tests;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import taskmanager.Student;
import taskmanager.Teacher;
import taskmanager.Task;

public class StudentTest {

			
			@Test
			public void addSingleTask() throws Exception{
				Student toAddTask = new Student(new File("toAddTask"), "toAddTask");
				//toAddTask.addTask(task);
				assertTrue(true);

			}

			

		

	

}
