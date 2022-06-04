package TestScprits

import audio.Sound

import audio.Source
import core.ComponentScript
import core.CoreEngine
import core.Entity
import events.Event
import input.InputPoller;
import org.lwjgl.glfw.GLFW;
class Test extends core.Script{
	
	
	core.GroovyScriptFinder script;
	Event reloadScript;
	
	
	public Test(core.GroovyScriptFinder script){
		this.script=script;
		this.reloadScript=InputPoller.makeEventOnKeyUpdated(GLFW.GLFW_KEY_R,{->reloadScript()});
		this.reloadScript.ActivateFlags();
	}
	
	def void reloadScript() {
		script.reloadScript();
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
	  script.invoke("Start",entity)
		
	}

	@Override
	public boolean DISABLE() {
		Boolean result=script.invoke("DISABLE");
		
		return result;
	}

	@Override
	public UUID getSCRIPTID() {
		return null;
	}
	
	
}
