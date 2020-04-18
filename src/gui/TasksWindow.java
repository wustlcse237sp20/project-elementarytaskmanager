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
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

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
		frame.getContentPane().setLayout(new MigLayout("", "[133px][133px][133px][right]", "[][243px]"));
		
		JLabel lblNewLabel = new JLabel("To Do");
		frame.getContentPane().add(lblNewLabel, "cell 0 0");
		
		JLabel lblNewLabel_1 = new JLabel("In Progress");
		frame.getContentPane().add(lblNewLabel_1, "cell 1 0");
		
		JLabel lblNewLabel_2 = new JLabel("Done");
		frame.getContentPane().add(lblNewLabel_2, "cell 2 0");
		frame.getContentPane().add(btnNewButton, "cell 3 1,alignx right,aligny bottom");
		
//		JList<Task> toDoList = new JList(controller.getToDoTasks());
		JList<Task> toDoList = new JList(controller.getToDoTasks());
		frame.getContentPane().add(toDoList, "cell 0 1,grow");
		
		JList<Task> inProgressList = new JList<Task>();
		frame.getContentPane().add(inProgressList, "cell 1 1,grow");
		
		JList<Task> doneList = new JList<Task>();
		frame.getContentPane().add(doneList, "cell 2 1,grow");
	}
}
