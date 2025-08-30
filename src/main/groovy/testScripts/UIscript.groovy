package main.groovy.testScripts

import main.java.core.Entity
import main.java.core.Script

class UIbox extends Script {
	
	
	public static final UUID ID=new UUID();
	
	
	
	@Override
	public void Start(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean DISABLE() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UUID getSCRIPTID() {
		
		return ID;
	}
	
}
new UIbox();
