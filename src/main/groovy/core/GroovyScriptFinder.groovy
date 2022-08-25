package main.groovy.core

class GroovyScriptFinder {
	
	private main.java.core.GroovyScriptEngineLoader load;
	private Binding binding;
	def script;
	String name;
	
	public GroovyScriptFinder(main.java.core.GroovyScriptEngineLoader load,String name,GroovyArgument... args){
		this.load=load;
		this.name=name;
		binding=new Binding();
		for(arg in args) {
			String argName=arg.name;
			def value=arg.object;
			println argName+"-------------------------------------------------";
			binding.setVariable(argName, value);
			
		}
		this.script=load.getGSE().run(name+".groovy",binding);
		
	}
	def reloadScript() {
		this.load.reloadScript();
		this.script=load.getGSE().run(name+".groovy",binding)
	}
	
    def invoke(String name,Object... a) {
		this.script.invokeMethod(name, a);
	}
	
		
	
}
