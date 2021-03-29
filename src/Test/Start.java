package test;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import animation.Animation;
import animation.SpriteSheet;
import core.ComponentAnimation;
import core.ComponentRenderModel;
import core.CoreEngine;
import core.Entity;
import core.EntityComponent;
import core.Game;
import input.InputPoller;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;
import rendering.Texture;

public class Start extends Game {

	private Texture playerTex;
	private Model playerModel;
	private Entity player;
    private SpriteSheet playerSheet;
	private Animation walkingAnimation;
	
	public static void main(String[] args) {
		Start game =new Start(640,480,"test");
	    
		game.updateGame();
		
	}	
	
	public Start(int width, int height, String name) {
		super(width, height, name);
	}

	@Override
	public void start() {   
	    playerModel=new Model(32, 46, 0, 0, 138, 138);
	    playerSheet=new SpriteSheet("playerSpriteSheet", 138);
	    playerTex=playerSheet.getTexture();
	    walkingAnimation=new Animation(playerSheet, 0, 7, 7);
	    player=new Entity(playerTex,new EntityComponent[]{
	    	new ComponentRenderModel(playerModel),
	    	new ComponentAnimation(walkingAnimation)
	    	
	    }) ;
	    
	
	    
	    
	    CoreEngine.AddEntity(player);
	
	}

	@Override
	public void GameLoop() {
		if(InputPoller.JustPushed(GLFW.GLFW_KEY_F)) {
			CoreEngine.DebugPrint("JUST");
		}
		
		
	}

		
	
	
}
