package test;

import java.util.Collections;
import java.util.LinkedList;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import TestScprits.EnemyScript;
import TestScprits.PlayerScript;
import UI.UIBox;
import UI.UIButton;
import UI.UIManager;
import UI.UITextField;
import animation.Animation;
import animation.ComponentAnimation;
import animation.SpriteSheet;
import audio.Music;
import audio.Sound;
import audio.Source;
import core.ComponentRenderModel;
import core.ComponentScript;
import core.CoreEngine;
import core.Entity;
import core.EntityComponent;
import core.Game;
import core.PASSABLE_BOOL;
import core.PASSABLE_INT;
import core.PASSABLE_VEC2F;
import core.VAR_RW;
import events.ActionDebugPrint;
import events.ActionDebugPrintVar;
import events.ActionSetVar;
import events.Condition;
import events.Operation;
import events.Events;
import events.Flag;
import input.InputPoller;
import physics.AABB;
import physics.Collision;
import physics.ComponentColision;
import physics.PhysicsEngine;
import rendering.Model;
import rendering.Render;
import rendering.Texture;
import test.map.MapFIle;
import test.map.MapLoader;
import textrendering.TextBuilder;
import static events.Operation.*;

public class Start extends Game {
	public static final int width=640,height=480;
	private Texture playerTex;
	private Model playerModel;
	private Entity player,player2;
    private SpriteSheet playerSheet;
	private Animation walkingAnimation;
	private MapLoader currentMap;
	private Texture mapTextue;
	private float Rendercamx;
	private float Rendercamy;
	private Flag test,buttonPressed;
	private TextBuilder text;
	private Sound Heal;
	private Sound Select;
	private Sound Move;
	private Sound Back;
	private Sound NO;
	private Sound TimedBad;
	private Source source;
	
	public static int amountWidth=Math.round((width/64)),amountHeight=Math.round((height/64));
	
	public static void main(String[] args) {
		Start game =new Start(width,height,"Game_Engine");
		game.updateGame();
		
	}	
	
	
	@Override
	public void GameLoop() {
	
		MusicSource.updateMusic(music);
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
		buttonPressed=new Flag(false);
		
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
	    	new ComponentScript(new PlayerScript())    
	    	
	    });
	    
	
	    
	   player2=new Entity(new EntityComponent[]{
		    	new ComponentColision(16,16,0),
		    	//new ComponentScript(new EnemyScript(player))
		    });
	   Entity player3=new Entity(new EntityComponent[]{
		    	new ComponentColision(50,50,0),
				new ComponentRenderModel(playerModel,playerTex),
		    });
		    
	   CoreEngine.AddEntity(player);
	   CoreEngine.AddEntity(player2);
	   CoreEngine.AddEntity(player3);
	 
	   player3.setVar(ComponentTest.VAR_TEST,true);
      
    
		player.DEBUG=true;
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
	 
		Heal=new Sound("healing sound");
		Select=new Sound("select_GUI");
		Move=new Sound("move_GUI");
		Back=new Sound("Back_GUI");
		NO=new Sound("NO_GUI");
		TimedBad=new Sound("Timed_Button_BAD");
	    music=new Music("TEST");
		

        
        source=new Source(new Vector2f(0), 1, 1, 0, 0,0);
		source.setSourceRelitive(true);	
        MusicSource.playMusic(music);
        UIBox box=new UIBox(new Vector2f(0,0),300,200,new Vector2f(10,10));
        UIManager.addBox(box);
        UIButton button=new UIButton(100,20);
        UIButton button2=new UIButton(100,20);
        box.addElement(button);
        box.addElement(new UITextField("test",100,0.25f));
        Events on=new Events(new Condition(button.getONFlag(),EQUALS,true),new ActionSetVar<Integer,PASSABLE_INT>(player,ComponentRenderModel.VAR_LAYER,1000));
        Events off=new Events(new Condition(button.getONFlag(),EQUALS,false),new ActionSetVar<Integer,PASSABLE_INT>(player,ComponentRenderModel.VAR_LAYER,10));
       
        on.ActivateFlags();
        off.ActivateFlags();
     
	  
	}

	
	
	private void input() {
		if(InputPoller.JustPushed(GLFW.GLFW_KEY_F)) {
			if(player2.isHIDDDEN()) {
		        player2.show();
		      
			}else {
				player2.hide();
			}
		}
		
		
		if(InputPoller.JustPushed(GLFW.GLFW_KEY_ESCAPE)) {
			super.CloseWindow();
		}
		if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_LEFT_CONTROL) && InputPoller.JustPushed(GLFW.GLFW_KEY_F)) {
		    super.toggleFullscreen();
		}
	   
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
