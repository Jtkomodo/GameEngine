package test;

import java.util.Collections;
import java.util.LinkedList;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import animation.Animation;
import animation.SpriteSheet;
import core.ComponentAnimation;
import core.ComponentColision;
import core.ComponentRenderModel;
import core.CoreEngine;
import core.Entity;
import core.EntityComponent;
import core.Game;
import core.PASSABLE_BOOL;
import core.PASSABLE_VEC2F;
import core.VAR;
import events.ActionDebugPrint;
import events.Condition;
import events.Events;
import events.Flag;
import input.InputPoller;
import physics.AABB;
import physics.Collision;
import physics.PhysicsEngine;
import rendering.Model;
import rendering.Render;
import rendering.Texture;
import test.map.MapFIle;
import test.map.MapLoader;
import textrendering.TextBuilder;

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
	private Flag test;
	private TextBuilder text;
	
	public static int amountWidth=Math.round((width/64)),amountHeight=Math.round((height/64));
	
	public static void main(String[] args) {
		Start game =new Start(width,height,"Game_Engine");
		game.updateGame();
		
	}	
	
	
	@Override
	public void GameLoop() {
		input();
	
		if(player.hasVAR(Entity.VAR_POSITION)){
	  
	    Vector2f position=player.getVar(Entity.VAR_POSITION);
	    
	    Render.cam.setPosition(new Vector2f(-position.x,-position.y));
		this.Rendercamx=-Render.cam.getPosition().x;
		this.Rendercamy=-Render.cam.getPosition().y;
	    Vector2f newvec=currentMap.findPositionOnMap(Rendercamx,Rendercamy);
	    int gridx= Math.round(newvec.x);
	    int gridy=Math.round(newvec.y);
		RenderMap(currentMap,gridx,gridy);
		}
	}
	
	public Start(int width, int height, String name) {
		super(width, height, name);
	}

	@Override
	public void start() {   
		text=new TextBuilder("aakar",512);
		test=new Flag(false);
		CoreEngine.Debugdraw=true;
	    playerModel=new Model(32, 46, 0, 0, 138, 138);
	    playerSheet=new SpriteSheet("playerSpriteSheet", 138);
	    mapTextue=new Texture("newsprite");
	    playerTex=playerSheet.getTexture();
	    walkingAnimation=new Animation(playerSheet, 0, 7, 7);
	    player=new Entity(new EntityComponent[]{
	    	new ComponentRenderModel(playerModel,playerTex),
	    	new ComponentAnimation(walkingAnimation),
	    	new ComponentColision(16,42,0),
	    	new ComponentTest(),
	    	new ComponentTest2()
	    	
	    });
	    
	
	    
	   Entity player2=new Entity(new EntityComponent[]{
		    	new ComponentColision(100,10,1)
		    });
	   Entity player3=new Entity(new EntityComponent[]{
		    	new ComponentColision(50,50,0)
		    });
		    
	   CoreEngine.AddEntity(player);
	   CoreEngine.AddEntity(player2);
	   CoreEngine.AddEntity(player3);
	 
	 
      
      
    player.HashMapPut(Entity.VAR_TESTHASH_AABBB,"player",player.getVar(Entity.VAR_AABB));
    player.HashMapPut(Entity.VAR_TESTHASH_AABBB,"player2",player2.getVar(Entity.VAR_AABB));
    player.HashMapPut(Entity.VAR_TESTHASH_AABBB,"player3",player3.getVar(Entity.VAR_AABB));
	
  
    player.ListSet(Entity.VAR_TESTLIST,new AABB[] {player.getVar(Entity.VAR_AABB),player2.getVar(Entity.VAR_AABB)});
	   
	player.ListClear(Entity.VAR_TESTLIST);
		
	    player3.DEBUG=true;
	    player2.DEBUG=true;
	    //player2.TakeInData(Entity.VAR_VELOCITY,new PASSABLE_VEC2F(new Vector2f(.1f,0)));
	    //player.DEBUG=true;	    
	    
	    player2.setVar(Entity.VAR_POSITION,new Vector2f(-200,0));
	   // player2.TakeInData(Entity.VAR_VELOCITY,new PASSABLE_VEC2F(new Vector2f(0.5f,0)));
	    player3.setVar(Entity.VAR_POSITION,new Vector2f(200*2,0));
	 //   player3.TakeInData(Entity.VAR_VELOCITY,new PASSABLE_VEC2F(new Vector2f(-0.5f,0)));
	    MapFIle map=new MapFIle("Map1TEST");
	    map.readMap();
	 
	  
	  
	    currentMap=new MapLoader(mapTextue,map,128);
	    if(player.hasVAR(Entity.VAR_AABB) && player2.hasVAR(Entity.VAR_AABB)){
	     Collision col=new Collision(player.getVar(Entity.VAR_AABB),player2.getVar(Entity.VAR_AABB));
	     PhysicsEngine.WatchForCollision(col, test);
	     Events e=new Events(new Condition[] {new Condition(test,true)},new ActionDebugPrint("player colided with player2"));
	     e.ActivateFlags();
	     player.DebugPrintAllVars("player");
	    } 
	  
	}

	
	
	private void input() {
		if(player.hasAllVars(new VAR<?>[] {Entity.VAR_POSITION,Entity.VAR_MIRROR})){
	    boolean mirror=player.getVar(Entity.VAR_MIRROR);
		Vector2f position=player.getVar(Entity.VAR_POSITION);
	    Vector2f movement=new Vector2f();
	    Vector2f direction=new Vector2f();
	 
	    
	  
	    
	  		float speed=2;
		
		if(InputPoller.JustPushed(GLFW.GLFW_KEY_P)) {
			if(player.hasVAR(ComponentTest.VAR_TEST)) {
				player.setVar(ComponentTest.VAR_TEST,!player.getVar(ComponentTest.VAR_TEST));
					
				
			}
		
		}
		if(InputPoller.JustPushed(GLFW.GLFW_KEY_ESCAPE)) {
			super.CloseWindow();
		}
		if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_LEFT_CONTROL) && InputPoller.JustPushed(GLFW.GLFW_KEY_F)) {
		    super.toggleFullscreen();
		}
		if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_LEFT)) {
			movement.x=-1;
			mirror=false;
		}
        if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_RIGHT)) {
        	movement.x=1;
        	mirror=true;
        }
        if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_UP)) {
			movement.y=1;
			
		}
        if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_DOWN)) {
        	movement.y=-1;
        }
        if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_W)) {
        	speed=5;
        }
        
       if(movement.length()!=0) {
    	   
       movement.normalize(direction);
       direction.mul((float)(100*speed*CoreEngine.deltaT),movement);
       player.setVar(Entity.VAR_VELOCITY,movement);
       PlayAnimation(player);
       }else {
         stopAnimation(player);
         player.setVar(Entity.VAR_VELOCITY,new Vector2f());
          
         
         
       }
       
	   player.setVar(Entity.VAR_MIRROR,mirror);
	   
	   
	   
		}
	   
	}
	private static void PlayAnimation(Entity e) {
		 e.setVar(Entity.VAR_ANAIMATION_PAUSE,false);
	}
	
	
	
	private static void stopAnimation(Entity e) {
		  e.setVar(Entity.VAR_ANAIMATION_RESET,true);
	      e.setVar(Entity.VAR_ANAIMATION_PAUSE,true);
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
