import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Teacher {

    private String name;
    private File managedStudents;

    public Teacher(String name, File managedStudents) {
        this.name = name;
        this.managedStudents = managedStudents;
    }

    public void addStudent(String studentName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.managedStudents, true));
        writer.newLine();
        writer.write(studentName);
        writer.close();
    }

}