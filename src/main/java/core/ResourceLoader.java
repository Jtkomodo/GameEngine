package main.java.core;

import java.io.InputStream;

public class ResourceLoader {
	
	InputStream I;
	
	public ResourceLoader(String path) throws ResourceNotFoundException {
		I=getClass().getResourceAsStream(path);
		if(I==null) {
			I=getClass().getResourceAsStream("/src"+path);
		}
		if(I==null) {
			throw new ResourceNotFoundException(path);
		}
	}
	
    public InputStream getInputStream() {
    	return I;
    }
}
