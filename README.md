# Elementary Task Manager

Elementary Task Manager is an application for students to manage their tasks in a fun way.

## Running
If you are on a Mac or Linux machine, simply run the provided script:

```bash
./guiTest.sh
```

If you are on a Windows computer, you will not be able to run the program in WSL. Instead, run the following from the Windows Command Prompt, **making sure to replace jdk-13.0.2 with your jdk version**

```bash
"C:\Program Files\Java\jdk-13.0.2\bin\javac.exe" -cp miglayout15-swing.jar ./src/taskmanager/*.java ./src/gui/*.java -d bin/
"C:\Program Files\Java\jdk-13.0.2\bin\java.exe" -cp "miglayout15-swing.jar;bin/" gui.TasksWindow
```

It may take a bit, as the Java classes need to compile. You will be asked if you are a teacher or student.  We have pre set up Students (with Teachers that manage them) so if you log in as a Student using "Sarah" or "Emily" you should be able to see our layout window and change tasks between categories, etc.  (to change a task to a new category double click on it in the display).  Then, make sure you hit save for the changes to be reflected when you run the program as a Student again.   

## This Iteration
1. In this iteration we completed the user stories for having a reasonable user interface for Students and students being able to view tasks between different categories and update the categories of tasks.  
2. For our next iteration we will complete the user stories for handling invalid user input, setting up a cleaner UI for Teachers, and adding a game component for Students.
3. Right now, we are still working out a bug with our Teacher display (although you should have seen our Teacher functionality on the last iteration)
