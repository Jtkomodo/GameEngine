package main.java.test;

import static main.java.events.Operation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.UUID;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import groovy.util.ResourceException;
import groovy.util.ScriptException;
import main.groovy.core.GroovyArgument;
import main.groovy.core.GroovyScript;
import main.groovy.core.GroovyScriptFinder;
import main.groovy.testScripts.Enemy;
import main.java.UI.DATA_MOUSE_COLLISION;
import main.java.UI.GUIScene;
import main.java.UI.UICText;
import main.java.UI.UIEngine;
import main.java.UI.UIEntity;
import main.java.UI.UIText;
import main.java.animation.Animation;
import main.java.animation.ComponentAnimation;
import main.java.animation.SpriteSheet;
import main.java.audio.Music;
import main.java.audio.Sound;
import main.java.audio.Source;
import main.java.core.ComponentRenderModel;
import main.java.core.ComponentScript;
import main.java.core.ComponentTags;
import main.java.core.Constants;
import main.java.core.CoreEngine;
import main.java.core.Entity;
import main.java.core.EntityComponent;
import main.java.core.Game;
import main.java.core.GroovyScriptEngineLoader;
import main.java.core.PASSABLE_BOOL;
import main.java.core.PASSABLE_INT;
import main.java.core.PASSABLE_VEC2F;
import main.java.core.VAR_RW;
import main.java.events.ActionDebugPrint;
import main.java.events.ActionDebugPrintVar;
import main.java.events.ActionSetVar;
import main.java.events.Condition;
import main.java.events.DATA_Float;
import main.java.events.ConditionalEvent;
import main.java.events.EventDispatcher;
import main.java.events.EventHandle;
import main.java.events.EventTypeTest;
import main.java.events.Flag;
import main.java.events.FlagHandler;

import main.java.events.Operation;
import main.java.input.InputPoller;
import main.java.physics.AABB;
import main.java.physics.Collision;
import main.java.physics.ComponentColision;
import main.java.physics.PhysicsEngine;
import main.java.physics.UICTrigger;
import main.java.rendering.Model;
import main.java.rendering.Render;
import main.java.rendering.Texture;
import main.java.test.maps.MapFIle;
import main.java.test.maps.MapLoader;
import main.java.testScripts.ScriptTest;
import main.java.textRendering.Fontloader;
import main.java.textRendering.TextBuilder;


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
	private GroovyScriptFinder scripter;
	private GroovyScriptEngineLoader engineloader;
	private GroovyScriptFinder test2;
	private GUIScene gui;
	private UIEntity UIEntity;


	public static int amountWidth=Math.round((width/64)),amountHeight=Math.round((height/64));

	public static void main(String[] args) {
		Start game =new Start(width,height,"Game_Engine");
		game.updateGame();

	}	


	@Override
	public void GameLoop() {

		MusicSource.updateMusic(music);
		

		if(player.hasVAR(Entity.VAR_POSITION)){

			Vector2f position=player.getVar(Entity.VAR_POSITION);

			Render.cam.setPosition(new Vector2f(-position.x,-position.y));
			this.Rendercamx=-Render.cam.getPosition().x;
			this.Rendercamy=-Render.cam.getPosition().y;
			Vector2f newvec=currentMap.findPositionOnMap(Rendercamx,Rendercamy);
			int gridx= Math.round(newvec.x);
			int gridy=Math.round(newvec.y);
			RenderMap(currentMap,gridx,gridy);
			// player.setVar(UIText.VAR_TEXT_POSITION,player.getVar(Entity.VAR_POSITION));
			

		   //UIText.setString("Mouse Position:"+Math.round(InputPoller.getScreenMousePosition().x)+","+Math.round(InputPoller.getScreenMousePosition().y));
		   
		   if(InputPoller.JustPushed(GLFW.GLFW_KEY_Q)) {
			   UIEngine.disableScene(gui);
		   }
		   if(InputPoller.JustPushed(GLFW.GLFW_KEY_E)) {
			   UIEngine.enableScene(gui);
		   }


		}
	}

	public Start(int width, int height, String name) {
		super(width, height, name);
	}

	@Override
	public void start() { 
		//	CoreEngine.DebugPrint("start");
		Source.SOUNDON=false;
		Fontloader aakar=new Fontloader("aakar",512);
		text=new TextBuilder(aakar);
		test=new Flag(false);
		buttonPressed=new Flag(false);

		CoreEngine.Debugdraw=true;
		playerModel=new Model(32, 46, 0, 0, 138, 138);
		playerSheet=new SpriteSheet("playerSpriteSheet", 138);
		mapTextue=new Texture("newsprite");
		playerTex=playerSheet.getTexture();
		walkingAnimation=new Animation(playerSheet, 0, 7, 7);

		Heal=new Sound("healing sound");
		Select=new Sound("select_GUI");
		Move=new Sound("move_GUI");
		Back=new Sound("Back_GUI");
		NO=new Sound("NO_GUI");
		TimedBad=new Sound("Timed_Button_BAD");
		music=new Music("CASCADE");


		source=new Source(new Vector2f(0), 1, 1, 0, 0,0);
		source.setSourceRelitive(true);	




		try {
			engineloader=new GroovyScriptEngineLoader("src/main/groovy/testScripts/");

			// test2=new GroovyScriptFinder(engineloader,"Test2");


		} catch (IOException e) {
			e.printStackTrace();
		} 



		player=new Entity(new EntityComponent[]{
				new ComponentRenderModel(playerModel,playerTex),
				new ComponentAnimation(walkingAnimation),
				new ComponentColision(16,42,0),
				new ComponentTags(new String[]{"player"}),
				new ComponentScript(new GroovyScript(engineloader,"PlayerScript")),
		});



		player2=new Entity(new EntityComponent[]{
				new ComponentColision(16,16,0),
				new ComponentScript(new GroovyScript(engineloader,"EnemyScript",new GroovyArgument("player",player))),
				new ComponentTags(new String[] {"Enemy"}),
		});
		
		
		Entity player3=new Entity(new EntityComponent[]{
				new ComponentColision(50,50,0),
				new ComponentTags(new String[]{"Interactable"}),
				

		});


		gui=new GUIScene("test");
		UICText UIText=new UICText(new Vector2f(0,0), aakar,"text"
				+ "",50);
		
		
		UICTrigger trigger=new UICTrigger(new Vector2f(100,100),new AABB(100,50,0),
				(DATA_MOUSE_COLLISION data)->MouseStillIn(data),(DATA_MOUSE_COLLISION data)->MouseEnter(data)
				,(DATA_MOUSE_COLLISION data)->MouseExit(data));
		
		
		
		
		//TODO also add hover functionality
		
		UIEntity=new UIEntity(new Vector2f(0,3),20,50 );
		UIEntity.addComponent(UIText);
		UIEntity.addComponent(trigger);
		
		
		gui.addEntity(UIEntity);

		
		CoreEngine.AddEntity(player);



		CoreEngine.AddEntity(player2);
		CoreEngine.AddEntity(player3);



		player3.setVar(ComponentTest.VAR_TEST,true);

		player.setVar(Entity.VAR_FLAG, new Flag());


		UIEntity.DEBUG=true;
		player.DEBUG=true;
		player3.DEBUG=true;
		player2.DEBUG=true;
		//player2.TakeInData(Entity.VAR_VELOCITY,new PASSABLE_VEC2F(new Vector2f(.1f,0)));
		//player.DEBUG=true;	    

		player2.setVar(Entity.VAR_POSITION,new Vector2f(-200,0));



		// player2.TakeInData(Entity.VAR_VELOCITY,new PASSABLE_VEC2F(new Vector2f(0.5f,0)));
		player3.setVar(Entity.VAR_POSITION,new Vector2f(200*2,0));



		EventDispatcher.subscribe(new EventTypeTest("function"),(DATA_Float f)->OnEvent(f));
		EventDispatcher.subscribe(new EventTypeTest("function2"),(DATA_Float f)->OnEvent2(f));

		
		
		
		//   player3.TakeInData(Entity.VAR_VELOCITY,new PASSABLE_VEC2F(new Vector2f(-0.5f,0)));
		MapFIle map=new MapFIle("Map1TEST");
		map.readMap();



		currentMap=new MapLoader(mapTextue,map,128);


		MusicSource.playMusic(music);



		ConditionalEvent escape=InputPoller.makeEventOnUIKeyUpdated("escape",GLFW.GLFW_KEY_ESCAPE,()->esacpe());
		
		Condition c=new Condition("full",InputPoller.UIkeyTriggered("key ctrl",GLFW.GLFW_KEY_LEFT_CONTROL),AND,InputPoller.UIkeyTriggered("key_F",GLFW.GLFW_KEY_F));

		ConditionalEvent toggleFullscreen=new ConditionalEvent(c,()->fullscreen());
	

		AABB a=player.getVar(ComponentColision.READ_VAR_AABB());
		AABB b=player2.getVar(ComponentColision.READ_VAR_AABB());
	



		//	PhysicsEngine.WatchForCollision(new Collision(a,b),player.getVar(Entity.VAR_FLAG));


		ConditionalEvent enter=InputPoller.makeEventOnKeyUpdated("ener",GLFW.GLFW_KEY_ENTER,()->enter());
		//   Event reloadGroovy=InputPoller.makeEventOnKeyUpdated(amountHeight,);
		ConditionalEvent flagTest=new ConditionalEvent(new Condition("flagtest",player.getVar(Entity.VAR_FLAG),EQUALS,true),()->flagEvent());



		flagTest.ActivateFlags();
		enter.ActivateFlags();
		toggleFullscreen.ActivateFlags();
		escape.ActivateFlags();


	}
	
	
	
	private void MouseEnter(DATA_MOUSE_COLLISION data) {
		CoreEngine.DebugPrint("enter");
		
		
		
	}

	private void MouseExit(DATA_MOUSE_COLLISION data) {
		CoreEngine.DebugPrint("exit");
		
	}
	private void MouseStillIn(DATA_MOUSE_COLLISION data) {
		if(InputPoller.JustPushed(GLFW.GLFW_MOUSE_BUTTON_1)) {
			CoreEngine.DebugPrint("left click");
		}
		if(InputPoller.JustPushed(GLFW.GLFW_MOUSE_BUTTON_2)) {
			CoreEngine.DebugPrint("rgiht click");
		}
		
	}
	
	
	
	
	private void OnEvent2(DATA_Float f) {
		
		CoreEngine.DebugPrint(f.get_f()+"---------------------------------------2 test");
	}
	private void OnEvent(DATA_Float f) {
		CoreEngine.DebugPrint(f.get_f()+"---------------------------------------");
	}

	private void flagEvent() {
		CoreEngine.DebugPrint("flag activated");
	}


	private void enter() {

	}


	private void esacpe() {
		CoreEngine.DebugPrint("triggered");

	if(InputPoller.JustPushed(GLFW.GLFW_KEY_ESCAPE)) {
		super.CloseWindow();
	}
	}
	private void fullscreen() {
		CoreEngine.DebugPrint("triggered");
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
