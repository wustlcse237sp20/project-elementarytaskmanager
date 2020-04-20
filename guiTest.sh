#!/bin/bash
cd src

javac -cp ../miglayout15-swing.jar  ./taskmanager/*.java ./gui/*.java

java -cp ../miglayout15-swing.jar:. gui/TasksWindow

cd ..
rm ./src/taskmanager/*class
rm ./src/gui/*class
