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
import rendering.Render;
import rendering.RenderEntity;
import rendering.Texture;
import test.map.MapFIle;
import test.map.MapLoader;

public class Start extends Game {
	public static final int width=640,height=480;
	private Texture playerTex;
	private Model playerModel;
	private Entity player;
    private SpriteSheet playerSheet;
	private Animation walkingAnimation;
	private MapLoader currentMap;
	private Texture mapTextue;
	private float Rendercamx;
	private float Rendercamy;
	public static int amountWidth=Math.round((width/64)),amountHeight=Math.round((height/64));
	
	public static void main(String[] args) {
		Start game =new Start(width,height,"test");
	    
		game.updateGame();
		
	}	
	
	public Start(int width, int height, String name) {
		super(width, height, name);
	}

	@Override
	public void start() {   
	    playerModel=new Model(32, 46, 0, 0, 138, 138);
	    playerSheet=new SpriteSheet("playerSpriteSheet", 138);
	    mapTextue=new Texture("newsprite");
	    playerTex=playerSheet.getTexture();
	    walkingAnimation=new Animation(playerSheet, 0, 7, 7);
	    player=new Entity(playerTex,new EntityComponent[]{
	    	new ComponentRenderModel(playerModel),
	    	new ComponentAnimation(walkingAnimation)
	    	
	    });
	    
	
	    
	    
	    CoreEngine.AddEntity(player);
	    
	    
	    MapFIle map=new MapFIle("Map1TEST");
	    map.readMap();
	    currentMap=new MapLoader(mapTextue,map,128);
	      
	}

	@Override
	public void GameLoop() {
		Render.cam.addVector(new Vector2f(-(float)(100*CoreEngine.deltaT),-(float)(100*CoreEngine.deltaT)));
		this.Rendercamx=-Render.cam.getPosition().x;
		this.Rendercamy=-Render.cam.getPosition().y;
		if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_LEFT_CONTROL) && InputPoller.JustPushed(GLFW.GLFW_KEY_F)) {
		    super.toggleFullscreen();
		}
		 Vector2f newvec=currentMap.findPositionOnMap(Rendercamx,Rendercamy);
	      int gridx= Math.round(newvec.x);
	      int gridy=Math.round(newvec.y);
		  RenderMap(currentMap,gridx,gridy);
		
	}

	private void RenderMap(MapLoader loader,int gridx,int gridy) {
		  for(int i=-amountHeight+2;i<amountHeight-1;i++) {
				for(int j=-amountWidth+2;j<amountWidth-1;j++) {
					   loader.loadTile(gridx+j,gridy+i);
				
					    }
			}
			  
		    //loader.getModel().setDrawMethod(GL_LINES);
			  loader.drawtiles(mapTextue);
			   currentMap.flushModel();
		
	}
	
	
}
