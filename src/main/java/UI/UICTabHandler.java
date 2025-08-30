package main.java.UI;

import java.util.UUID;


import main.java.core.Entity;
/**this will be the UI component that will control tabs in a UIBox it will simply change which tab context the user is currently
 * in and update what collisions will be activated and what context is seen
 * 
 */


public class UICTabHandler extends UIComponent {

	
	
	
	
	public UICTabHandler(UITab[] Tabs) {
		
	}
	
	
	
	/**
	 * Initializes the UITab
	 * @param entity  this is the current UI entity we are in
	 *
	 */
	@Override
	protected void INIT(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean DISABLE() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UUID getCOMPONENTID() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	protected void enable() {
		INIT(currentEntity);
		
	}

}
