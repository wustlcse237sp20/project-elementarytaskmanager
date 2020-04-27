package gui;

import java.awt.EventQueue;
import taskmanager.*;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import taskmanager.*;

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
	private List<DefaultListModel<Task>> dlmCols;
	private List<JList<Task>> jlistCols;

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
		this.dlmCols = new ArrayList<>();
		this.jlistCols = new ArrayList<>();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		if (isTeacher) {
			controller = new TeacherController(username);
			// TODO: initialze lists?
			
			dlmCols.add(controller.getToDoTasks());
			dlmCols.add(controller.getInProgressTasks());
			dlmCols.add(controller.getDoneTasks());
//			dlmCols.add(controller.getStudents());			
		} else {
			controller = new StudentController(username);
			
			dlmCols.add(controller.getToDoTasks());
			dlmCols.add(controller.getInProgressTasks());
			dlmCols.add(controller.getDoneTasks());
			
//			toDoListModel = controller.getToDoTasks();
//			inprogressListModel = controller.getInProgressTasks();
//			doneListModel = controller.getDoneTasks();
		}
		
		initializeFrame();
		initializeGUIElements();
		addTaskButton();
		saveChangesButton();
		createTaskLists();
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[133px][133px][133px][right]", "[][243px][][]"));
	}

	private void initializeGUIElements() {
		for(int i = 0; i < Categories.values().length; i++) {
			JLabel lblNewLabel = new JLabel(Categories.values()[i].toString());
			frame.getContentPane().add(lblNewLabel, "cell " + i + " 0");
		}
	}

	private void addTaskButton() {
		int col = dlmCols.size();
		JButton addTaskButton = new JButton("Add Task");
		frame.getContentPane().add(addTaskButton, "cell " + col + " 1,alignx right,aligny bottom");

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

	private void saveChangesButton() {
		int col = dlmCols.size();

		saveButton = new JButton("Save");
		frame.getContentPane().add(saveButton, "cell " + col + " 3");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getStudent().saveSchedule();
			}
		});
	}

	private void createTaskLists() {
		for(DefaultListModel<Task> dlm : dlmCols) {
			JList<Task> jList = new JList<Task>(dlm);
			jlistCols.add(jList);
			
			jList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					if (evt.getClickCount() == 2) {
						int index = jList.locationToIndex(evt.getPoint());
						changeCategory(dlmCols.get(jlistCols.indexOf(jList)), index);
					}
				}
			});
		}
		
		for(int i = 0; i < dlmCols.size(); i++) {
			JList<Task> list = jlistCols.get(i);
			frame.getContentPane().add(list, "cell " + i + " 1 1 3,grow");
		}
//		toDoList = new JList<Task>(toDoListModel);
//		inProgressList = new JList<Task>(inprogressListModel);
//		doneList = new JList<Task>(doneListModel);

//		if (isTeacher) {
//			studentList = new JList<Student>(studentListModel);
//			frame.getContentPane().add(studentList, "cell 0 1 1 3,grow");
//			frame.getContentPane().add(toDoList, "cell 1 1 1 3,grow");
//			frame.getContentPane().add(inProgressList, "cell 2 1 1 3,grow");
//			frame.getContentPane().add(doneList, "cell 3 1 1 3,grow");
//		} else {
//			frame.getContentPane().add(toDoList, "cell 0 1 1 3,grow");
//			frame.getContentPane().add(inProgressList, "cell 1 1 1 3,grow");
//			frame.getContentPane().add(doneList, "cell 2 1 1 3,grow");
//		}
//		toDoList.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent evt) {
//				if (evt.getClickCount() == 2) {
//					int index = toDoList.locationToIndex(evt.getPoint());
//					changeCategory(toDoListModel, index);
//				}
//			}
//		});
//
//		inProgressList.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent evt) {
//				if (evt.getClickCount() == 2) {
//					int index = inProgressList.locationToIndex(evt.getPoint());
//					changeCategory(inprogressListModel, index);
//				}
//			}
//		});
//
//		doneList.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent evt) {
//				if (evt.getClickCount() == 2) {
//					int index = doneList.locationToIndex(evt.getPoint());
//					changeCategory(doneListModel, index);
//				}
//			}
//		});
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
