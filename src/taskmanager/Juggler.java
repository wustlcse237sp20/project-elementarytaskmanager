package taskmanager;
import javax.swing.DefaultListModel;
public class Juggler implements Achievement {

	@Override
	public boolean isCompleted(Student s) {
		DefaultListModel<Task> inProgressTasks = s.getTasksByCategory(Categories.InProgress);
		if(inProgressTasks.getSize() > 5) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "Juggler";
	}
}
