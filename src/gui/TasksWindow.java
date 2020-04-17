package gui;

import java.awt.EventQueue;
import taskmanager.*;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

//import jdk.internal.org.jline.utils.ShutdownHooks.Task;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class TasksWindow {

	private JFrame frame;
	private Controller controller;
	private boolean isTeacher = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TasksWindow window = new TasksWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TasksWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		if(isTeacher) {
			controller = new TeacherController();
		} else {
			controller = new StudentController();
		}

		
		JButton btnNewButton = new JButton("Add Task");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
		
//		JList<Task> toDoList = new JList(controller.getToDoTasks());
		JList<Task> toDoList = new JList(controller.getToDoTasks());
		springLayout.putConstraint(SpringLayout.NORTH, toDoList, 25, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, toDoList, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, toDoList, 0, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, toDoList, -287, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(toDoList);
		
		JList<Task> inProgressList = new JList<Task>();
		springLayout.putConstraint(SpringLayout.NORTH, inProgressList, 31, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, inProgressList, 10, SpringLayout.EAST, toDoList);
		springLayout.putConstraint(SpringLayout.SOUTH, inProgressList, 0, SpringLayout.SOUTH, btnNewButton);
		frame.getContentPane().add(inProgressList);
		
		JList<Task> doneList = new JList<Task>();
		springLayout.putConstraint(SpringLayout.EAST, inProgressList, -62, SpringLayout.WEST, doneList);
		springLayout.putConstraint(SpringLayout.NORTH, doneList, 25, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, doneList, 273, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, doneList, 0, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, doneList, -34, SpringLayout.WEST, btnNewButton);
		frame.getContentPane().add(doneList);
	}
}
