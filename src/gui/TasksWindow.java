package gui;

import java.awt.EventQueue;
import taskmanager.*;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class TasksWindow {

	private JFrame frame;
	private Controller controller;
	static boolean isTeacher = false;
	static String username;
	DefaultListModel<Task> todoList;
	DefaultListModel<Task> inprogressList;
	DefaultListModel<Task> doneList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		String teacher = UserInputUtils.promptUser("Hello! Are you are teacher? (y/n)");
		if (teacher.equals("y")) {
			isTeacher = true;
		}
		username = UserInputUtils
				.promptUser("Please type your username and hit Enter to login to Elementary Task Manager");

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

		if (isTeacher) {
			controller = new TeacherController(username);
			//TODO: initialze lists?
		} else {
			controller = new StudentController(username);
			todoList = controller.getToDoTasks();
			inprogressList = controller.getInProgressTasks();
			doneList = controller.getDoneTasks();
		}

		JButton btnNewButton = new JButton("Add Task");

		frame.getContentPane().setLayout(new MigLayout("", "[133px][133px][133px][right]", "[][243px]"));

		JLabel lblNewLabel = new JLabel("To Do");
		frame.getContentPane().add(lblNewLabel, "cell 0 0");

		JLabel lblNewLabel_1 = new JLabel("In Progress");
		frame.getContentPane().add(lblNewLabel_1, "cell 1 0");

		JLabel lblNewLabel_2 = new JLabel("Done");
		frame.getContentPane().add(lblNewLabel_2, "cell 2 0");
		frame.getContentPane().add(btnNewButton, "cell 3 1,alignx right,aligny bottom");

		JList<Task> toDoList = new JList<Task>(todoList);
		frame.getContentPane().add(toDoList, "cell 0 1,grow");

		JList<Task> inProgressList = new JList<Task>(controller.getInProgressTasks());
		frame.getContentPane().add(inProgressList, "cell 1 1,grow");

		JList<Task> doneList = new JList<Task>(controller.getDoneTasks());
		frame.getContentPane().add(doneList, "cell 2 1,grow");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameString = (String) JOptionPane.showInputDialog(frame, "What is the task?", null);
				if (nameString != null && (nameString.length() > 0)) {
					Task task = controller.addTask(nameString);
					todoList.add(todoList.getSize(), task);
				}
			}
		});
	}
}
