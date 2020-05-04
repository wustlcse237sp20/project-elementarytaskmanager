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

It may take a bit, as the Java classes need to compile. You will be asked if you are a teacher or student.  
**To log in as a Student:**  
We have pre set up Students (with Teachers that manage them) so if you log in as a Student using "Sarah" or "Emily" you should be able to see our layout window and change tasks between categories, etc.  (to change a task to a new category double click on it in the display).  You may also notice that as you move tasks from to do to in progress to done you will see the student "level up" as denoted by the label on the right of the window that will initially read "Beginner".  There are achievements that the Students can unlock as well that will appear on the right as you obtain them (for example: the "Juggler" achievement occurs when the Student has over 10 tasks in In Progress. Then, make sure you hit save for the changes to be reflected when you run the program as a Student again.   
**To log in as a Teacher:**  
We have pre set up Teachers that manage Students so if you log in as a Teacher using "RonCytron" you will be able to see the Teacher account along with all the Students that Teacher manages.  By double clicking on a Student name in the Students column you will be able to see the task board for that particular student.  To add a task for one or multiple students, click on the "Add Task" button and you will be prompted to enter the name of the task as well as directions for how to assign the task to whichever students you want.  Like with Students, make sure to hit save before closing and all changes will be reflected on both the Teacher and Student views.  


## This Iteration
1. In this iteration we completed the user stories for setting up a clean UI for Teachers, as well as enabling Teachers to add tasks to all students.  We also added gamed components for Students with levels and achievements that they are able to unlock. 
2. If we had more time we would like to add more gamed components for Students, the ability to add more detailed tasks, and a greater level of personalization for Teachers.  
3. The only unsolved issue in the current iteration is ability to delete tasks.  
