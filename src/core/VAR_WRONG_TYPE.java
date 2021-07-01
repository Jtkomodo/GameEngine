package core;

public class VAR_WRONG_TYPE extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4746403738940255989L;

	public VAR_WRONG_TYPE(String name,PASSABLE_DATA_TYPE oldType,PASSABLE_DATA_TYPE newType){    
		
		super(name+":Trying to change type "+newType.Debug_name+" excpected "+oldType.Debug_name);
	}
	
	
	
}
