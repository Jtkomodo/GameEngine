package events;

import gameEngine.Start;

public class Condition {

	
	
	
	private Flag flag;
	private boolean condition;
   
	public Condition(Flag flag,boolean condition) {
		this.condition=condition;
		this.flag=flag;
		
	}
	
	public boolean isTrue() {
		return flag.State()==this.condition;
	}

	public Flag getFlag() {
		return flag;
	}
	
	
	
}
