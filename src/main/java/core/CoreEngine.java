package main.java.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


import java.util.UUID;

import main.java.animation.AnimationEngine;
import main.java.events.Flag;
import main.java.events.FlagHandler;
import main.java.input.InputPoller;
import main.java.physics.PhysicsEngine;
import main.java.rendering.MainBatchRender;
import main.java.rendering.MainRenderHandler;
import main.java.rendering.RenderingEngine;
import main.java.UI.UIEngine;

public class CoreEngine {
	public static boolean DebugPrint=true;

	private static HashMap<UUID,Entity> entities=new HashMap<UUID,Entity>();

	private static Clock gameClock=new Clock();
	public static Flag clockStopped=new Flag(false);
	public static double deltaT;

	public static boolean Debugdraw=false;
	/**
	 * main game loop this updates everything in the game
	 */
	protected static void updateEngine() {
		deltaT=gameClock.getDeltaTime();


		FlagHandler.updateFlags();

		UpdateGameLoop_BEFORE();
		UpdatePhysicsEngine();
		UpdateGameLoop_AFTER();
		UpdateAnimationEngine();
		UpdateRenderEngine();



	}

	public static void pauseGameClock() {
		gameClock.Pause();
	}
	public static void playGameClock() {
		gameClock.Play();;
	}

	private static void UpdateAnimationEngine() {

		AnimationEngine.update();

	}



	public static void UpdateInput() {
		InputPoller.POll();
		UIEngine.update();
	}

	private static void UpdatePhysicsEngine() {

		PhysicsEngine.update();
	}
	private static void UpdateRenderEngine() {

		RenderingEngine.update();    



	}

	private static void UpdateGameLoop_BEFORE() {
		Iterator<Entry<UUID,Entity>>	i=entities.entrySet().iterator();


		while(i.hasNext()) {
			Entry<UUID,Entity> entry=i.next();
			UUID ID=entry.getKey();
			Entity E=entry.getValue();

			E.GAMELOOP_TICK_BEFORE_PHYSICS();
		}
	}
	private static void UpdateGameLoop_AFTER() {
		Iterator<Entry<UUID,Entity>>	i=entities.entrySet().iterator();


		while(i.hasNext()) {
			Entry<UUID,Entity> entry=i.next();
			UUID ID=entry.getKey();
			Entity E=entry.getValue();

			E.GAMELOOP_TICK_AFTER_PHYSICS();
		}
	}

	public static void RemoveAllEntites() {
		Iterator<Entry<UUID,Entity>>  I=entities.entrySet().iterator();
		while(I.hasNext()) {
			Entry<UUID,Entity> e=I.next();
			removeEntity(e.getValue());
		}
	}

	public static void RemoveEntities(Entity[] es) {
		for(int i=0;i<es.length;i++) {
			removeEntity(es[i]);
		}
	}


	public static void AddEntities(Entity[] es) {
		Iterator<Entry<UUID,Entity>>  I=entities.entrySet().iterator();
		while(I.hasNext()) {
			Entry<UUID,Entity> e=I.next();
			AddEntity(e.getValue());
		}

	}



	public static void AddEntity(Entity e) {
		if(!entities.containsKey(e.ID)) {
			entities.put(e.ID, e);
			e.Init();
		}
	}
	public static boolean removeEntity(Entity e) {
		if(entities.containsKey(e.ID)) {
			boolean success=e.DISABLE();
			entities.remove(e.ID);
			PhysicsEngine.removeEntity(e);
			RenderingEngine.removeEntity(e);
			AnimationEngine.removeEntityAnimation(e.ID);


			return success;


		}else {
			return false;
		}


	}




	public static boolean hasEntity(Entity e) {
		return entities.containsKey(e.ID);
	}



	public static void DebugPrint(String s) {
		if(DebugPrint && s!="") {
			System.out.println(s);
		}
	}
	public static Entity getEntity(UUID ID) {
		return  entities.get(ID);

	}




	public static<T extends PassableData<?>> boolean HasALLVars(UUID ENTITY_ID,VAR_RW<T>[] vars) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			return e.hasAllVars(vars);
		}else {
			return false;
		}

	}




	public static<ST,T extends PassableData<ST>> boolean HasVar(UUID ENTITY_ID,VAR_RW<T> var) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			return e.hasVAR(var);
		}else {
			return false;
		}

	}



	public static<ST,T extends PassableData<ST>> ST RecieveData(UUID ENTITY_ID,VAR_RW<T> var) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			return e.getVar(var);
		}else {
			return null;
		}

	}





	public static<ST,T extends PassableData<ST>> void sendData(UUID ENTITY_ID,VAR_RW<T> var,ST data) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			e.setVar(var,data);
		}

	}
	public static<ST,T extends PassableData<ST>> void InitData(UUID ENTITY_ID,VAR_RW<T> var,ST data) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			e.INITVar(var,data);
		}

	}


	public static<T extends PassableData<?>> boolean HasALLVars(UUID ENTITY_ID,VAR_R<T>[] vars) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			return e.hasAllVars(vars);
		}else {
			return false;
		}

	}




	public static<ST,T extends PassableData<ST>> boolean HasVar(UUID ENTITY_ID,VAR_R<T> var) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			return e.hasVAR(var);
		}else {
			return false;
		}

	}



	public static<ST,T extends PassableData<ST>> ST RecieveData(UUID ENTITY_ID,VAR_R<T> var) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			return e.getVar(var);
		}else {
			return null;
		}

	}





	public static<ST,T extends PassableData<ST>> void sendData(UUID ENTITY_ID,VAR_W<T> var,ST data) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			e.setVar(var,data);
		}

	}



	public static<ST,T extends PassableData<ST>> void InitData(UUID ENTITY_ID,VAR_W<T> var,ST data) {
		Entity e=getEntity(ENTITY_ID);
		if(e!=null) {
			e.INITVar(var,data);
		}

	}


	public static void DebugPrint(String string,Class calledFrom) {
		if(DebugPrint)	
			System.out.println("["+calledFrom.getName()+"]---"+string);
	}	


}
