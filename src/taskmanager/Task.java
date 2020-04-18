package taskmanager;

public class Task {	
	private String name;
	private Categories category;
	private Days day;
	
	public Task(String name) {
		this.name = name;
		this.category = Categories.ToDo;
		this.day = Days.Monday;
	}
	
	public Task(String name, String category) {
		this.name = name;
		this.category = Categories.valueOf(category);
		this.day = Days.Monday;
	}
	
	public Task(String name, String category, String day) {
		this.name = name;
		this.category = Categories.valueOf(category);
		this.day = Days.valueOf(day);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}
	
	public void setCategory(String category) {
		this.category = Categories.valueOf(category);
	}
	
	public Days getDay(Days day) {
		return day;
	}
	
	public void setDay(Days day) {
		this.day = day;
	}
	
	public void setDay(String day) {
		this.day =  Days.valueOf(day);
	}

	@Override
	public String toString() {
		return name + " - " + category.name();
	}
}
