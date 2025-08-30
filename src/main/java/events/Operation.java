package main.java.events;

public enum Operation implements Operationable {

	
	
	CHANGED{

		@Override
		public boolean check(Flag a, Flag b) {
			return a.StateChanged()==b.State();
		}
	
	
	
	},
	AND{
		
		@Override
		public boolean check(Flag a, Flag b) {
			return a.State()&& b.State();
		}
		
		
	},
	OR{

		@Override
		public boolean check(Flag a, Flag b) {
			return a.State()||b.State();
		}
		
	},
	EQUALS{

		@Override
		public boolean check(Flag a, Flag b) {
			return a.State()==b.State();
		}
		
	},
	NOT_EQUAL{

		@Override
		public boolean check(Flag a, Flag b) {
			return a.State()!=b.State();
		}
		
	}
	
	
	;
	
	
	

	
	
}
