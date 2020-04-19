#!/bin/bash

cd src

javac ./taskmanager/Student.java
javac ./taskmanager/Teacher.java
javac ./taskmanager/Task.java
javac ./taskmanager/Schedule.java
javac ./taskmanager/FileReaderHandler.java
javac ./taskmanager/FileWriterHandler.java
javac ./taskmanager/UserInputUtils.java

echo "Are you a teacher? (y/n)"
read ISTEACHER

if [[ $ISTEACHER == "y" || $ISTEACHER == "Y" ]]
then
	java taskmanager/Teacher
else
	java taskmanager/Student
fi

cd ..

rm ./src/taskmanager/Student.class
rm ./src/taskmanager/Teacher.class
rm ./src/taskmanager/Task.class
rm ./src/taskmanager/Schedule.class
rm ./src/taskmanager/FileReaderHandler.class
rm ./src/taskmanager/FileWriterHandler.class
rm ./src/taskmanager/UserInputUtils.class
