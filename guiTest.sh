#!/bin/bash

javac -cp miglayout15-swing.jar  ./src/taskmanager/*.java ./src/gui/*.java -d bin/
java -cp bin/ gui/TasksWindow

rm ./bin/gui/*class
rm ./bin/taskmanager/*class

