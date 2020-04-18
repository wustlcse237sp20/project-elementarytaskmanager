package gui;

import java.awt.EventQueue;
import taskmanager.*;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	DefaultListModel<Task> todosListModel;
	DefaultListModel<Task> inprogressListModel;
	DefaultListModel<Task> doneListModel;

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

	public String updateTaskWindow() {
		String[] choices = { "To do", "In progress", "Done" };
		String newCategory = (String) JOptionPane.showInputDialog(null, "New category:", "Update",
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		return newCategory;

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
			// TODO: initialze lists?
		} else {
			controller = new StudentController(username);
			todosListModel = controller.getToDoTasks();
			inprogressListModel = controller.getInProgressTasks();
			doneListModel = controller.getDoneTasks();
		}

		JButton addButton = new JButton("Add Task");

		frame.getContentPane().setLayout(new MigLayout("", "[133px][133px][133px][right]", "[][243px][]"));

		JLabel lblNewLabel = new JLabel("To Do");
		frame.getContentPane().add(lblNewLabel, "cell 0 0");

		JLabel lblNewLabel_1 = new JLabel("In Progress");
		frame.getContentPane().add(lblNewLabel_1, "cell 1 0");

		JLabel lblNewLabel_2 = new JLabel("Done");
		frame.getContentPane().add(lblNewLabel_2, "cell 2 0");
		frame.getContentPane().add(addButton, "cell 3 1,alignx right,aligny bottom");

		JList<Task> toDoList = new JList<Task>(todosListModel);
		frame.getContentPane().add(toDoList, "cell 0 1 1 2,grow");

		JList<Task> inProgressList = new JList<Task>(controller.getInProgressTasks());
		frame.getContentPane().add(inProgressList, "cell 1 1 1 2,grow");

		JList<Task> doneList = new JList<Task>(controller.getDoneTasks());
		frame.getContentPane().add(doneList, "cell 2 1 1 2,grow");

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameString = (String) JOptionPane.showInputDialog(frame, "What is the task?", null);
				if (nameString != null && (nameString.length() > 0)) {
					Task task = controller.addTask(nameString);
					todosListModel.addElement(task);
				}
			}
		});
		toDoList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
//				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					int index = toDoList.locationToIndex(evt.getPoint());
					String newCategory = updateTaskWindow();

					Task task = todosListModel.get(index);
					if (!task.getCategory().equals(newCategory)) {
						task.setCategory(newCategory);
						todosListModel.remove(index);
						if (newCategory.equals("In progress")) {
							inprogressListModel.addElement(task);
							inProgressList.setModel(inprogressListModel);
						} else {
							doneListModel.addElement(task);
							doneList.setModel(doneListModel);
						}

						// TODO: make it save to student schedule
					}

				}
			}
		});
		inProgressList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
//				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					int index = inProgressList.locationToIndex(evt.getPoint());
					String newCategory = updateTaskWindow();

					Task task = inprogressListModel.get(index);
					if (!task.getCategory().equals(newCategory)) {
						task.setCategory(newCategory);
						inprogressListModel.remove(index);
						if (newCategory.equals("To do")) {
							todosListModel.addElement(task);
							toDoList.setModel(todosListModel);
						} else {
							doneListModel.addElement(task);
							doneList.setModel(doneListModel);
						}

						// TODO: make it save to student schedule
					}

				}
			}
		});
		doneList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
//				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					int index = doneList.locationToIndex(evt.getPoint());
					String newCategory = updateTaskWindow();

					Task task = doneListModel.get(index);
					if (!task.getCategory().equals(newCategory)) {
						task.setCategory(newCategory);
						doneListModel.remove(index);
						if (newCategory.equals("In progress")) {
							inprogressListModel.addElement(task);
							inProgressList.setModel(inprogressListModel);
						} else {
							todosListModel.addElement(task);
							toDoList.setModel(todosListModel);
						}

						// TODO: make it save to student schedule
					}

				}
			}
		});
	}
}
