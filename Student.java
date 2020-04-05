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

    public static String[] processInput(String listOfNames) {
        String[] processedNames;
        //if (listOfNames.equals(".")) {
        // TODO: Add an option that can read names from the file of parent's students
        //} 
        // TODO: When do we check for valid input?
        //else {
        processedNames = listOfNames.split("\\s*,\\s*");
        //}
        return processedNames;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Please type your username and hit Enter to login to Elementary Task Manager");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String username = inputReader.readLine();

        File userFile = new File("parentTeacherUsers.txt");
        BufferedReader fileReader = new BufferedReader(new FileReader(userFile));
        String fileUsername;
        boolean usernameAlreadyInFile = false;
        while ((fileUsername = fileReader.readLine()) != null) {
            if (fileUsername.equals(username)) {
                usernameAlreadyInFile = true;
            }
        }
        fileReader.close();

        if (!usernameAlreadyInFile) {
            BufferedWriter usernameWriter = new BufferedWriter(new FileWriter(userFile));
            usernameWriter.newLine();
            usernameWriter.write(username);
            usernameWriter.close();
        }

        // TODO: Create a file for the parent or teacher user upon login

        System.out.println("Welcome, " + username
                + "! Type 's' to add a student to your caseload, 't' to add a task to a student schedule, or 'q' to quit");
        String userFirstChoice = inputReader.readLine();

        if (userFirstChoice.equals("t")) {
            System.out.println("Please enter the name of the task you would like to create");
            String taskName = inputReader.readLine();
            System.out.println(
                    "Who would you like to assign this task to? Either type a name, a list of names separated by commas, or . for all");
            String taskAssignee = inputReader.readLine();
            String[] processedNames = processInput(taskAssignee);

            for (int assigneeCounter = 0; assigneeCounter < processedNames.length; assigneeCounter++) {
                File schedule = new File(processedNames[assigneeCounter] + ".txt");
                schedule.createNewFile();
                Student student = new Student(schedule, processedNames[assigneeCounter]);
                try {
                    student.addTask(taskName);
                    System.out.println(
                            "Task " + taskName + " has been successfully added for " + processedNames[assigneeCounter]);
                } catch (IOException e) {
                    System.out.println(
                            "Error: Task " + taskName + " was not added for " + processedNames[assigneeCounter]);
                }
            }
        } else if (userFirstChoice.equals("s")) {
            // TODO: put add a student code here
        } else if (userFirstChoice.equals("q")) {
            return;
        } else {
            // TODO: put invalid input code here
        }

    }
}