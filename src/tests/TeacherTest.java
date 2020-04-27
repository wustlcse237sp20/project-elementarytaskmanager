package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import taskmanager.FileReaderHandler;
import taskmanager.Student;
import taskmanager.Teacher;
import taskmanager.Task;

public class TeacherTest {
    Teacher ronCytron = new Teacher("RonCytron");

    @Test
    public void addSingleStudent() throws Exception {
        ronCytron.addStudent("Emily");
        FileReaderHandler reader = new FileReaderHandler(ronCytron.getFile());
        boolean found = reader.containsLine("Emily");
        assertTrue(found);
    }

    @Test
    public void addAnotherStudent() throws Exception {
        ronCytron.addStudent("Sarah");
        FileReaderHandler reader = new FileReaderHandler(ronCytron.getFile());
        boolean foundSecond = reader.containsLine("Emily");
        assertTrue(foundSecond);
    }

}
