package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

class Student {

    private File schedule;
    private String name;

    public Student(File schedule, String name) {
        this.schedule = schedule;
        this.name = name;
    }

    public void addTask(String taskName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.schedule, true) //Set true for append mode
        );
        writer.newLine();
        writer.write(taskName);
        writer.close();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Please type your username and hit Enter to login to Elementary Task Manager");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String username = inputReader.readLine();

        // TODO: Create a file for the parent or teacher user upon login

    }
}