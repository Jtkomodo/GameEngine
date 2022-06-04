package core;

import java.io.IOException;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class GroovyScriptEngineLoader {

	
	
	
	
	private GroovyScriptEngine gse;
	private Binding binding;

	public GroovyScriptEngineLoader() throws IOException{
		String[] paths = { "src/TestScprits/" };

        gse= new GroovyScriptEngine(paths);
	    binding=new Binding();
     
		
	}
	protected void reloadScript() throws IOException {
		String[] paths = { "src/TestScprits/" };

        gse= new GroovyScriptEngine(paths);
	    binding=new Binding();
     
	}
	
	protected GroovyScriptEngine getGSE() {
		return this.gse;
	}
	protected Binding getBinding() {
		return this.binding;
	}
		
}
