package core

import audio.Sound

import audio.Source
import events.Event
import input.InputPoller;
import org.lwjgl.glfw.GLFW;
class GroovyScript extends core.Script{
	
	Entity entity;
	core.GroovyScriptFinder script;
	Event reloadScript;
	
	
	public GroovyScript(core.GroovyScriptFinder script){
		this.script=script;
		this.reloadScript=InputPoller.makeEventOnKeyUpdated(GLFW.GLFW_KEY_R,{->reloadScript()});
		this.reloadScript.ActivateFlags();
	}
	
	public GroovyScript(core.GroovyScriptEngineLoader gse,String name){
		
		
		this.script=new core.GroovyScriptFinder(gse,name);
		
		this.reloadScript=InputPoller.makeEventOnKeyUpdated(GLFW.GLFW_KEY_R,{->reloadScript()});
		this.reloadScript.ActivateFlags();
	}
	
	def void reloadScript() {
		script.reloadScript();
		script.invoke("Start",this.entity);
	}
	
	
	def void InvokeOtherMethod(String name,Object... o) {
		script.invoke(name, o);
		
	}
	
	public void GAMELOOP_TICK_BEFORE_PHYSICS(){
		script.invoke("GAMELOOP_TICK_BEFORE_PHYSICS");
}
   public void GAMELOOP_TICK_AFTER_PHYSICS() {
	 script.invoke("GAMELOOP_TICK_AFTER_PHYSICS");
   }


  public  void RENDER_TICK(){
     script.invoke("RENDER_TICK");
 }
	@Override
	public void Start(Entity entity) {
      this.entity=entity;
	  script.invoke("Start",entity)
		
	}

	@Override
	public boolean DISABLE() {
		Boolean result=script.invoke("DISABLE");
		
		return result;
	}

	@Override
	public UUID getSCRIPTID() {
		return script.invoke("getSCRIPTID");
	}
	
	
}
