package taskmanager;

public enum Categories {
	ToDo{
		@Override
		public String toString() {
			return "To Do";
		}
	},
	InProgress{
		@Override
		public String toString() {
			return "In Progress";
		}
	},
	Done{
		@Override
		public String toString() {
			return "Done";
		}
	}
}
