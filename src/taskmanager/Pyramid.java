package taskmanager;
import javax.swing.DefaultListModel;

public class Pyramid implements Achievement {

	@Override
	public boolean isCompleted(Student s) {
		DefaultListModel<Task> completedTasks = s.getTasksByCategory(Categories.Done);
		DefaultListModel<Task> inProgressTasks = s.getTasksByCategory(Categories.InProgress);
		DefaultListModel<Task> toDoTasks = s.getTasksByCategory(Categories.ToDo);
		if(completedTasks.getSize() > inProgressTasks.getSize() && inProgressTasks.getSize() > toDoTasks.getSize()) {
			return true;
		}
		return false;
	}
	public String toString() {
		return "Pyramid";
	}

}
