package events;

public class ActionSystemUpdateCondition implements EventAction {

	
	private Condition condition;
	
	protected  ActionSystemUpdateCondition(Condition condition) {
		this.condition=condition;
	}
	
	
	@Override
	public void invoke() {
	    this.condition.check();

	}

}
