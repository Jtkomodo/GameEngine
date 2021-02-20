package test;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import core.CoreEngine;
import core.Entity;
import core.EntityComponent;
import core.Game;
import input.InputPoller;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;
import rendering.Texture;

public class start extends Game {

	private Texture player;
	private Model p;
	private Entity entity;

	public start(int width, int height, String name) {
		super(width, height, name);
	}

	@Override
	public void start() {   
	    p=new Model(32, 46, 0, 0, 138, 138);
	    player=new Texture("Spritesheets/playerSpriteSheet");
	}

	@Override
	public void GameLoop() {
	     MainRenderHandler.addEntity(new RenderEntity(p, new Vector3f(), 0,1,player));
	     CoreEngine.DebugPrint("KEY I="+InputPoller.checkState(GLFW.GLFW_KEY_I));
	}

	public static void main(String[] args) {
		start game =new start(640,480,"test");
	   
		game.updateGame();
		
	}		
	
	
}
