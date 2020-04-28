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
	
	public Days getDay() {
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
		return name + "-" + category.name() + "-" + day.name();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (category != other.category)
			return false;
		if (day != other.day)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
