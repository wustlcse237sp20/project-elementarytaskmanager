package taskmanager;

public class Task {
	String name;
	String category;
	
	public Task(String name, String category) {
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return name + " - " + category;
	}
}
