package core

class GroovyScriptFinder {
	
	private core.GroovyScriptEngineLoader load;
	def script;
	String name;
	
	public GroovyScriptFinder(core.GroovyScriptEngineLoader load,String name){
		this.load=load;
		this.name=name;
		this.script=load.getGSE().run(name+".groovy",load.getBinding())
		
	}
	def reloadScript() {
		//this.load.reloadScript();
		this.script=load.getGSE().run(name+".groovy",load.getBinding())
	}
	
    def invoke(String name,Object... a) {
		this.script.invokeMethod(name, a);
	}
	
		
	
}
