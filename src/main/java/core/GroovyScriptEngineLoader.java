package main.java.core;

import java.io.IOException;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class GroovyScriptEngineLoader {

	
	
	
	private String scriptsLocation;
	private GroovyScriptEngine gse;

	public GroovyScriptEngineLoader(String scriptsLocation) throws IOException{
        this.scriptsLocation=scriptsLocation;
        gse= new GroovyScriptEngine(scriptsLocation);
	
     
		
	}
	protected void reloadScript() throws IOException {
		

        gse= new GroovyScriptEngine(scriptsLocation);
        
	 
     
	}
	
	protected GroovyScriptEngine getGSE() {
		return this.gse;
	}
	
		
}
