package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;
import taskmanager.Student;
import taskmanager.Teacher;
import taskmanager.Task;

public class TeacherTest {

    Teacher ronCytron = new Teacher("Ron cytron");

    @Test
    public void addSingleStudent() throws Exception {
        ronCytron.addStudent("Emily");
        BufferedReader reader = new BufferedReader(new FileReader(new File("RonCytronStudents.txt")));
        String studentName;
        boolean found = false;
        while ((studentName = reader.readLine()) != null) {
            if (studentName.equals("Emily")) {
                found = true;
            }
        }
        reader.close();
        assertTrue(found);
    }

    @Test
    public void addAnotherStudent() throws Exception {
        ronCytron.addStudent("Sarah");
        BufferedReader reader = new BufferedReader(new FileReader(new File("RonCytronStudents.txt")));
        String studentName;
        boolean found = false;
        while ((studentName = reader.readLine()) != null) {
            if (studentName.equals("Emily")) {
                if (studentName.equals("Sarah")) {
                    found = true;
                }
            }
        }
        reader.close();
        assertTrue(found);
    }

}
