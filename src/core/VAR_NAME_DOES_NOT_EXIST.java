package core;

public class VAR_NAME_DOES_NOT_EXIST extends Exception {


	
	public VAR_NAME_DOES_NOT_EXIST(String varNameMangeled) {
		super("Entity does not contain the VAR with the name "+varNameMangeled);
	}
	
	
	
	
	private static final long serialVersionUID = -5024349618221195934L;

}
