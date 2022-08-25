package main.groovy.core

import main.java.audio.Sound
import main.java.audio.Source
import main.java.core.Entity
import main.java.core.PASSABLE_HASH_MAP
import main.java.core.PASSABLE_STRING
import main.java.core.PASSABLE_UUID
import main.java.core.VAR_RW
import main.java.events.Event
import main.java.input.InputPoller

import org.lwjgl.glfw.GLFW;
class GroovyScript extends main.java.core.Script{
	
	private final static VAR_RW<PASSABLE_HASH_MAP<String,UUID>> VAR_SCRIPTS=VAR_RW.makeNewVar("VAR_SCRIPTS",PASSABLE_HASH_MAP.getHandle(PASSABLE_STRING.getHandle(), PASSABLE_UUID.getHandle()));
	
	Entity entity;
	main.groovy.core.GroovyScriptFinder script;
	Event reloadScript;
	
	
	
	public GroovyScript(main.groovy.core.GroovyScriptFinder script){
		this.script=script;
		this.reloadScript=InputPoller.makeEventOnKeyUpdated(GLFW.GLFW_KEY_R,{->reloadScript()});
		this.reloadScript.ActivateFlags();
	}
	
	public GroovyScript(main.java.core.GroovyScriptEngineLoader gse,String name){
		
		
		this.script=new main.groovy.core.GroovyScriptFinder(gse,name);
		
		this.reloadScript=InputPoller.makeEventOnKeyUpdated(GLFW.GLFW_KEY_R,{->reloadScript()});
		this.reloadScript.ActivateFlags();
	}
	public GroovyScript(main.java.core.GroovyScriptEngineLoader gse,String name,GroovyArgument... arg){
		
		
		this.script=new main.groovy.core.GroovyScriptFinder(gse,name,arg);
		this.reloadScript=InputPoller.makeEventOnKeyUpdated(GLFW.GLFW_KEY_R,{->reloadScript()});
		this.reloadScript.ActivateFlags();
	}
	def void reloadScript() {
		script.invoke("DISABLE")
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
	  String name=this.script.name;
	  UUID ID=getSCRIPTID();
	  entity.HashMapPut(VAR_SCRIPTS,name,ID);
	  script.invoke("Start",entity)
	
		
	}

	@Override
	public boolean DISABLE() {
		Boolean result=script.invoke("DISABLE");
		entity.HashMapRemove(VAR_SCRIPTS, script.name);
		return result;
	}

	@Override
	public UUID getSCRIPTID() {
		return script.invoke("getSCRIPTID");
	}
	
	public static UUID GET_UUID_FOR_SCRIPT_INSTANCE(Entity e,String nameOfScript) {
	         if(e.hasVAR(VAR_SCRIPTS)) {
				return e.HashMapGet(VAR_SCRIPTS,nameOfScript);
				
			 }else {
				 return null;
			 }
	}
}
