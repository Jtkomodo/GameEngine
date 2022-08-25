package main.java.core;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ResourceNotFoundException(String path) {
	    super("sorry there is no resource at "+path+"!");
	}
}
