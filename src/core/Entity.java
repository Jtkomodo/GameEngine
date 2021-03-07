package core;


import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.joml.Vector2f;

import animation.AnimationScripterData;
import rendering.Texture;

public class Entity {

	
	
	public final UUID ID;
	protected Vector2f position=new Vector2f();
	protected Texture texture;
	protected Vector2f Velocity=new Vector2f();
	protected Vector2f UV=new Vector2f();
	public boolean PHYSICS=true;
	public boolean ANIMATION=true;
	public boolean RENDERING=true;
	
	
	private HashMap<Integer,EntityComponent> components=new HashMap<Integer,EntityComponent>(); 

	
	public Entity(Texture texture,EntityComponent[] components){
		this.ID=UUID.randomUUID();
		addComponents(components);
		this.texture=texture;
	}
	
	public void addComponents(EntityComponent[] components) {
		for(int i=0;i<components.length;i++) {
			addComponent(components[i]);
		}
	}
	
	protected void Init() {
		Iterator<EntityComponent> i=this.components.values().iterator();
		
		
		while(i.hasNext()) {
			EntityComponent c=i.next();
			c.INIT(this);
		}
	}
	
	
	public void addComponent(EntityComponent c) {
		this.components.put(c.getID(), c);
	}
	
	
	public <T extends EntityComponent> T getComponent(int id){
	   if(components.containsKey(id)) {
		return(T)components.get(id);	
	   }else {
		   return null;
	   }
	}
	
	
	public void TakeInAniationSctipterData(AnimationScripterData data) {
		  if(data.getEntity()==ID && ANIMATION) {
			  ComponentRenderModel renderComponent= getComponent(ComponentRenderModel.COMPID);
			  if(renderComponent!=null) {
				  renderComponent.changeFrame(data.getSheet(), data.getFrame());
			  }
		  }
		
		
	}
	
	protected EntityComponent[] getComponents() {
		return this.components.values().toArray(new EntityComponent[this.components.size()]);
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public void setVelocity(Vector2f velocity) {
		Velocity = velocity;
	}
}
