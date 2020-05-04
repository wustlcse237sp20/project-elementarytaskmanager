package taskmanager;
import javax.swing.DefaultListModel;
public class Juggler implements Achievement {

	/**
	 * checks whether or not the given student has completed this achievement
	 * @return true is they have, false otherwise
	 */
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
