#!/bin/bash

cd src

javac ./taskmanager/Student.java
javac ./taskmanager/Teacher.java
javac ./taskmanager/Task.java
javac ./taskmanager/FileReaderHandler.java

echo "Are you a teacher? (y/n)"
read ISTEACHER

if [[ $ISTEACHER == "y" || $ISTEACHER == "Y" ]]
then
	java taskmanager/Teacher
else
	java taskmanager/Student
fi

rm ./taskmanager/Student.class
rm ./taskmanager/Teacher.class
rm ./taskmanager/Task.class
rm ./taskmanager/FileReaderHandler.class
