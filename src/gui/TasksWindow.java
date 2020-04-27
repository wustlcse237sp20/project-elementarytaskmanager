package gui;

import java.awt.EventQueue;
import taskmanager.*;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
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
	private DefaultListModel<Task> toDoListModel;
	private DefaultListModel<Task> inprogressListModel;
	private DefaultListModel<Task> doneListModel;
	private DefaultListModel<Student> studentListModel;
	private JList<Task> toDoList;
	private JList<Task> inProgressList;
	private JList<Task> doneList;
	private JList<Student> studentList;
	private JButton saveButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		String teacher = UserInputUtils.promptUser("Hello! Are you are teacher? (y/n)");
		if (teacher.equals("y")) {
			isTeacher = true;
		}
		username = UserInputUtils.promptUser("Please type your username and hit Enter to login to Elementary Task Manager");

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
		initializeFrame();

		if (isTeacher) {
			controller = new TeacherController(username);
			// TODO: initialze lists?
			toDoListModel = controller.getToDoTasks();
			inprogressListModel = controller.getInProgressTasks();
			doneListModel = controller.getDoneTasks();
			
			studentListModel = controller.getStudents();
		} else {
			controller = new StudentController(username);
			toDoListModel = controller.getToDoTasks();
			inprogressListModel = controller.getInProgressTasks();
			doneListModel = controller.getDoneTasks();
		}

		initializeGUIElements();
		createAddTaskButton();
		createTaskLists();
		saveChanges();
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[133px][133px][133px][right]", "[][243px][][]"));
	}

	private void initializeGUIElements() {
		if (isTeacher) {

			JLabel lblNewLabel = new JLabel("Students");
			frame.getContentPane().add(lblNewLabel, "cell 0 0");

			JLabel lblNewLabel_1 = new JLabel("To Do");
			frame.getContentPane().add(lblNewLabel_1, "cell 1 0");

			JLabel lblNewLabel_2 = new JLabel("In Progress");
			frame.getContentPane().add(lblNewLabel_2, "cell 2 0");

			JLabel lblNewLabel_3 = new JLabel("Done");
			frame.getContentPane().add(lblNewLabel_3, "cell 3 0");
		} else {
			JLabel lblNewLabel = new JLabel("To Do");
			frame.getContentPane().add(lblNewLabel, "cell 0 0");

			JLabel lblNewLabel_1 = new JLabel("In Progress");
			frame.getContentPane().add(lblNewLabel_1, "cell 1 0");

			JLabel lblNewLabel_2 = new JLabel("Done");
			frame.getContentPane().add(lblNewLabel_2, "cell 2 0");
		}
	}

	private void createAddTaskButton() {
		JButton addTaskButton = new JButton("Add Task");
		frame.getContentPane().add(addTaskButton, "cell 4 1,alignx right,aligny bottom");

		addTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameString = (String) JOptionPane.showInputDialog(frame, "What is the task?", null);
				if (nameString != null && (nameString.length() > 0)) {
					Task task = controller.addTask(nameString);
					toDoListModel.addElement(task);
				}
			}
		});
	}

	private void saveChanges() {
		saveButton = new JButton("Save");
		frame.getContentPane().add(saveButton, "cell 4 3");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getStudent().saveSchedule();
			}
		});
	}

	private void createTaskLists() {
		toDoList = new JList<Task>(toDoListModel);
		inProgressList = new JList<Task>(inprogressListModel);
		doneList = new JList<Task>(doneListModel);

		if (isTeacher) {
			studentList = new JList<Student>(studentListModel);
			frame.getContentPane().add(studentList, "cell 0 1 1 3,grow");
			frame.getContentPane().add(toDoList, "cell 1 1 1 3,grow");
			frame.getContentPane().add(inProgressList, "cell 2 1 1 3,grow");
			frame.getContentPane().add(doneList, "cell 3 1 1 3,grow");
		} else {
			frame.getContentPane().add(toDoList, "cell 0 1 1 3,grow");
			frame.getContentPane().add(inProgressList, "cell 1 1 1 3,grow");
			frame.getContentPane().add(doneList, "cell 2 1 1 3,grow");
		}
		toDoList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int index = toDoList.locationToIndex(evt.getPoint());
					changeCategory(toDoListModel, index);
				}
			}
		});

		inProgressList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int index = inProgressList.locationToIndex(evt.getPoint());
					changeCategory(inprogressListModel, index);
				}
			}
		});

		doneList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int index = doneList.locationToIndex(evt.getPoint());
					changeCategory(doneListModel, index);
				}
			}
		});
	}

	private void changeCategory(DefaultListModel<Task> currentListModel, int index) {
		Categories newCategory = updateTaskWindow();

		Task task = currentListModel.get(index);
		if (!task.getCategory().equals(newCategory)) {
			task.setCategory(newCategory);
			currentListModel.remove(index);
			if (newCategory.equals(Categories.ToDo)) {
				toDoListModel.addElement(task);
			} else if (newCategory.equals(Categories.InProgress)) {
				inprogressListModel.addElement(task);
			} else {
				doneListModel.addElement(task);
			}

			controller.getStudent().updateTask(task);

			// TODO: make it save to student schedule
		}
	}

	public Categories updateTaskWindow() {
		Categories[] choices = Categories.values();
		String newCategory = (String) JOptionPane.showInputDialog(null, "New category:", "Update",
				JOptionPane.QUESTION_MESSAGE, null, Arrays.stream(choices).map(Categories::name).toArray(String[]::new),
				choices[0].name());
		return Categories.valueOf(newCategory);
	}
}
