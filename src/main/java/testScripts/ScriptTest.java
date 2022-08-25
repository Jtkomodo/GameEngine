package main.java.testScripts;

import java.util.UUID;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import main.java.audio.Sound;
import main.java.audio.Source;
import main.java.core.Entity;
import main.java.core.Script;
import main.java.core.VAR_RW;
import main.java.input.InputPoller;

public class ScriptTest extends Script {

	
	private Sound sound;
	private Source source;
	
	
	
	
	public ScriptTest(Source source,Sound sound) {
	    this.sound=sound;
	    this.source=source;
	}
	
	
	
	@Override
	public void Start(Entity entity) {
		

	}

	
	@Override
	public void GAMELOOP_TICK_BEFORE_PHYSICS() {
		
		if(InputPoller.JustPushed(GLFW.GLFW_KEY_S)) {
			source.play(sound);
		}


	}
	
	
	
	
	@Override
	public boolean DISABLE() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UUID getSCRIPTID() {
		// TODO Auto-generated method stub
		return null;
	}

}
