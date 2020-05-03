package taskmanager;

public enum Levels {
	Beginner{
		@Override
		public String toString() {
			return "Beginner";
		}
	},
	Novice{
		@Override
		public String toString() {
			return "Novice";
		}
	},
	TaskMaster{
		@Override
		public String toString() {
			return "Task Master";
		}
	}
}
