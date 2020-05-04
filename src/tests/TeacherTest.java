package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import taskmanager.Categories;
import taskmanager.FileReaderHandler;
import taskmanager.Student;
import taskmanager.Teacher;
import taskmanager.Task;

public class TeacherTest {
	
	@Test
	public void testAddSingleStudent() throws Exception {
		Teacher testTeacher = new Teacher("testTeacher");
		testTeacher.addStudent("Student1");
		FileReaderHandler reader = new FileReaderHandler("./src/teachers/testTeacher.txt");
		boolean foundStudent = false;
		List<String> lines = reader.getLines();
		for(String line : lines) {
			if(line.equals("Student1")) {
				foundStudent = true;
			}
		}
		assertTrue(foundStudent);
		File toClearTeacher = new File("./src/teachers/testTeacher.txt");
		File toClearStudent = new File("./src/students/Student1.txt");
		toClearTeacher.delete();
		toClearStudent.delete();
	}
	
	@Test
	public void testAddMultipleStudents() throws Exception {
		Teacher testTeacher = new Teacher("testTeacher");
		testTeacher.addStudent("Student1");
		testTeacher.addStudent("Student2");
		FileReaderHandler reader = new FileReaderHandler("./src/teachers/testTeacher.txt");
		boolean foundStudent1 = false;
		boolean foundStudent2 = false;
		List<String> lines = reader.getLines();
		for(String line : lines) {
			if(line.equals("Student1")) {
				foundStudent1 = true;
			}
			if(line.equals("Student2")) {
				foundStudent2 = true;
			}
		}
		boolean foundStudents = false;
		if(foundStudent1 && foundStudent2) {
			foundStudents = true;
		}
		assertTrue(foundStudents);
		File toClearTeacher = new File("./src/teachers/testTeacher.txt");
		File toClearStudent1 = new File("./src/students/Student1.txt");
		File toClearStudent2 = new File("./src/students/Student2.txt");
		toClearTeacher.delete();
		toClearStudent1.delete();
		toClearStudent2.delete();
	}
	
	@Test
	public void testProcessInputListOfNames() throws Exception {
		Teacher testTeacher = new Teacher("testTeacher");
		List<String> answers = new ArrayList<String>();
		answers.add("Alice");
		answers.add("Bob");
		answers.add("Carly");
		assertEquals(answers, testTeacher.processInput("Alice, Bob, Carly"));
		File toClearTeacher = new File("./src/teachers/testTeacher.txt");
		toClearTeacher.delete();
	}
	
	@Test
	public void testProcessInputAllStudents() throws Exception {
		Teacher testTeacher = new Teacher("testTeacher");
		List<String> answers = new ArrayList<String>();
		testTeacher.addStudent("Alice");
		testTeacher.addStudent("Bob");
		testTeacher.addStudent("Carly");
		answers.add("Alice");
		answers.add("Bob");
		answers.add("Carly");
		assertEquals(answers, testTeacher.processInput("."));
		File toClearTeacher = new File("./src/teachers/testTeacher.txt");
		File toClearAlice = new File("./src/students/Alice.txt");
		File toClearBob = new File("./src/students/Bob.txt");
		File toClearCarly = new File("./src/students/Carly.txt");
		toClearTeacher.delete();
		toClearAlice.delete();
		toClearBob.delete();
		toClearCarly.delete();
	}

	@Test
	public void testAssignTaskToOneStudent() throws Exception {
		Teacher testTeacher = new Teacher("testTeacher");
		Task taskForStudent1 = new Task("taskForStudent1");
		testTeacher.addStudent("Student1");
		testTeacher.addStudent("Student2");
		List<String> studentNames = new ArrayList<String>();
		studentNames.add("Student1");
		testTeacher.assignTask(taskForStudent1, studentNames);
		FileReaderHandler reader1 = new FileReaderHandler("./src/students/Student1.txt");
		boolean foundTaskStudent1 = false;
		List<String> student1Tasks = reader1.getLines();
		for(String student1Task : student1Tasks) {
			if(student1Task.equals("taskForStudent1-ToDo-Monday")) {
				foundTaskStudent1 = true;
			}
		}
		assertTrue(foundTaskStudent1);
		File toClearTeacher = new File("./src/teachers/testTeacher.txt");
		File toClearStudent1 = new File("./src/students/Student1.txt");
		File toClearStudent2 = new File("./src/students/Student2.txt");
		toClearTeacher.delete();
		toClearStudent1.delete();
		toClearStudent2.delete();
	}
	
	@Test
	public void testAssignTaskToMultipleStudents() throws Exception {
		Teacher testTeacher = new Teacher("testTeacher");
		Task taskForStudent1 = new Task("taskForStudent1");
		testTeacher.addStudent("Student1");
		testTeacher.addStudent("Student2");
		List<String> studentNames = new ArrayList<String>();
		studentNames.add("Student1");
		studentNames.add("Student2");
		testTeacher.assignTask(taskForStudent1, studentNames);
		FileReaderHandler reader1 = new FileReaderHandler("./src/students/Student1.txt");
		boolean foundTaskStudent1 = false;
		List<String> student1Tasks = reader1.getLines();
		for(String student1Task : student1Tasks) {
			if(student1Task.equals("taskForStudent1-ToDo-Monday")) {
				foundTaskStudent1 = true;
			}
		}
		assertTrue(foundTaskStudent1);
		FileReaderHandler reader2 = new FileReaderHandler("./src/students/Student2.txt");
		boolean foundTaskStudent2 = false;
		List<String> student2Tasks = reader2.getLines();
		for(String student2Task : student2Tasks) {
			if(student2Task.equals("taskForStudent1-ToDo-Monday")) {
				foundTaskStudent2 = true;
			}
		}
		assertTrue(foundTaskStudent2);
		File toClearTeacher = new File("./src/teachers/testTeacher.txt");
		File toClearStudent1 = new File("./src/students/Student1.txt");
		File toClearStudent2 = new File("./src/students/Student2.txt");
		toClearTeacher.delete();
		toClearStudent1.delete();
		toClearStudent2.delete();
	}
	
	@Test
	public void testAssignTaskToAllStudents() {
		Teacher testTeacher = new Teacher("testTeacher");
		Task taskForStudent1 = new Task("taskForStudent1");
		testTeacher.addStudent("Student1");
		testTeacher.addStudent("Student2");
		testTeacher.assignTask(taskForStudent1, testTeacher.processInput("."));
		FileReaderHandler reader1 = new FileReaderHandler("./src/students/Student1.txt");
		boolean foundTaskStudent1 = false;
		List<String> student1Tasks = reader1.getLines();
		for(String student1Task : student1Tasks) {
			if(student1Task.equals("taskForStudent1-ToDo-Monday")) {
				foundTaskStudent1 = true;
			}
		}
		assertTrue(foundTaskStudent1);
		FileReaderHandler reader2 = new FileReaderHandler("./src/students/Student2.txt");
		boolean foundTaskStudent2 = false;
		List<String> student2Tasks = reader2.getLines();
		for(String student2Task : student2Tasks) {
			if(student2Task.equals("taskForStudent1-ToDo-Monday")) {
				foundTaskStudent2 = true;
			}
		}
		assertTrue(foundTaskStudent2);
		File toClearTeacher = new File("./src/teachers/testTeacher.txt");
		File toClearStudent1 = new File("./src/students/Student1.txt");
		File toClearStudent2 = new File("./src/students/Student2.txt");
		toClearTeacher.delete();
		toClearStudent1.delete();
		toClearStudent2.delete();
	}

}