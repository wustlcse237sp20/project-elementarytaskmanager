import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileWriter;

class Tasks {

    private File listOfTasks;
    private String taskAssignee;

    public Tasks(File listOfTasks, String taskAssignee) {
        this.listOfTasks = listOfTasks;
        this.taskAssignee = taskAssignee;
    }

    public boolean addTask(String taskName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.listOfTasks, true) //Set true for append mode
        );
        writer.newLine();
        writer.write(taskName);
        writer.close();
        // TODO: return whether or not file was successfully added to
        return false;
    }

    public String[] processInput(String listOfNames) {
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

        System.out.println("Please enter the name of the task you would like to create");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String taskName = reader.readLine();
        System.out.println(
                "Who would you like to assign this task to? Either type a name, a list of names separated by commas, or . for all");
        String taskAssignee = reader.readLine();
        // TODO: Fix this depending on input to be for a list or all as well as just one kid
        File taskFile = new File(taskAssignee + ".txt");
        taskFile.createNewFile();
        Tasks tasks = new Tasks(taskFile, taskAssignee);
        boolean successAddingTask = tasks.addTask(taskName);
        if (successAddingTask) {
            System.out.println("Task " + taskName + " has been successfully added for " + taskAssignee);
        } else {
            System.out.println("Error: Task " + taskName + " was not added for " + taskAssignee);
        }
        // TODO: If addTask() returns true, give success message, else, give error message
    }
}