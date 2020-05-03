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
//import java.util.LinkedList;
import java.util.List;
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
	private JButton saveButton;
	private List<DefaultListModel<Task>> dlmCols;
	private List<JList<Task>> jlistCols;
	private DefaultListModel<Student> studentDefaultListModel;
	private JList<Student> studentJList;

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
		if (isTeacher) {
			controller = new TeacherController(username);
		} else {
			controller = new StudentController(username);
		}

		dlmCols = controller.getCategoryTasks();
		this.jlistCols = new ArrayList<>();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initializeFrame();
		initializeGUIElements();
		addTaskButton();
		saveChangesButton();
		createTaskLists();
		if (isTeacher) {
			addStudentButton();
			createStudentList();
		}
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[133px][133px][133px][right]", "[][243px][][]"));
	}

	private void initializeGUIElements() {
		for (int i = 0; i < Categories.values().length; i++) { // CHANGE~~~~~~~~~~~~~~~~~~~~~~~~~
			JLabel lblNewLabel = new JLabel(Categories.values()[i].toString()); // CHANGE~~~~~~~~~~~~~~~~~~~~~~~~~
			frame.getContentPane().add(lblNewLabel, "cell " + i + " 0");
		}
		if (isTeacher) {
			JLabel students = new JLabel("Students");
			frame.getContentPane().add(students, "cell " + Categories.values().length + " 0");
		}
	}

	private void addTaskButton() {
		int col = dlmCols.size();
		if (isTeacher) {
			col++;
		}
		JButton addTaskButton = new JButton("Add Task");
		frame.getContentPane().add(addTaskButton, "cell " + col + " 1,alignx right,aligny bottom");

		addTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameString = (String) JOptionPane.showInputDialog(frame, "What is the task?", null);
				List<String> processedNames;
				if (isTeacher) {
					String taskAssignee = (String) JOptionPane.showInputDialog(frame,
							"Who would you like to assign this task to? Either type a name, a list of names separated by commas, or . for all",
							null);
					processedNames = controller.processInput(taskAssignee);
				} else {
					processedNames = null;
				}
				if (nameString != null && (nameString.length() > 0)) {
					Task task = controller.addTask(nameString, processedNames);					
					dlmCols.get(0).addElement(task);
				}
			}
		});
	}

	private void saveChangesButton() {
		int col = dlmCols.size();
		if (isTeacher) {
			col++;
		}
		saveButton = new JButton("Save");
		frame.getContentPane().add(saveButton, "cell " + col + " 3,alignx right,aligny bottom");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isTeacher) {
					for (int i = 0; i < controller.getStudents().getSize(); i++) { // TODO: not saving to file
						controller.getStudents().get(i).saveSchedule();
					}
				} else {
					controller.getStudent().saveSchedule();
				}
			}
		});
	}

	private void addStudentButton() {
		int col = dlmCols.size() + 1;
		JButton addStudentButton = new JButton("Add Student");
		frame.getContentPane().add(addStudentButton, "cell " + col + " 2,alignx right,aligny bottom");
		addStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentName = (String) JOptionPane.showInputDialog(frame, "What is the name of the student?",
						null);
				controller.addStudent(studentName);
				studentDefaultListModel.addElement(new Student(studentName));
			}
		});
	}

	private void createTaskLists() {
		for (DefaultListModel<Task> dlm : dlmCols) {
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

		for (int i = 0; i < dlmCols.size(); i++) {
			JList<Task> list = jlistCols.get(i);
			frame.getContentPane().add(list, "cell " + i + " 1 1 3,grow");
		}

	}

	private void createStudentList() {
		if (isTeacher) {
			studentDefaultListModel = controller.getStudents();
			studentJList = new JList<Student>(studentDefaultListModel);
			studentJList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					if (evt.getClickCount() == 2) {
						int index = studentJList.locationToIndex(evt.getPoint());
						Student student = studentDefaultListModel.get(index);
						controller.setStudent(student);
						dlmCols = controller.getCategoryTasks();
						for (int i = 0; i < dlmCols.size(); i++) {
							jlistCols.get(i).setModel(dlmCols.get(i));
						}
					}
				}
			});
			frame.getContentPane().add(studentJList, "cell " + jlistCols.size() + " 1 1 3,grow");
		}
	}

	private void changeCategory(DefaultListModel<Task> currentListModel, int index) {
		Categories newCategory = updateTaskWindow(); // CHANGE~~~~~~~~~~~~~~~~~~~~~~~~~

		Task task = currentListModel.get(index);
		if (!task.getCategory().equals(newCategory)) {
			task.setCategory(newCategory); // CHANGE~~~~~~~~~~~~~~~~~~~~~~~~~
			currentListModel.remove(index);

			int dlmIndex = newCategory.ordinal();
			dlmCols.get(dlmIndex).addElement(task);

			controller.getStudent().updateTask(task);
			controller.getStudent().saveSchedule();	//had to add for teachers
		}
	}

	public Categories updateTaskWindow() { // CHANGE~~~~~~~~~~~~~~~~~~~~~~~~~
		Categories[] choices = Categories.values(); // CHANGE*2~~~~~~~~~~~~~~~~~~~~~~~~~
		String newCategory = (String) JOptionPane.showInputDialog(null, "New category:", "Update",
				JOptionPane.QUESTION_MESSAGE, null, Arrays.stream(choices).map(Categories::name).toArray(String[]::new),
				choices[0].name()); // CHANGE~~~~~~~~^~~~~~~~~~~~~~~~~~
		return Categories.valueOf(newCategory); // CHANGE~~~~~~~~~~~~~~~~~~~~~~~~~
	}
}
