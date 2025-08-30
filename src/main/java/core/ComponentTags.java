package main.java.core;

import java.util.LinkedList;
import java.util.UUID;

public class ComponentTags extends EntityComponent {

	public static final UUID ID=UUID.randomUUID();
	private static final VAR_RW<PASSABLE_LINKED_LIST<String>> VAR_TAGS=createNewVAR("VAR_TAGS",PASSABLE_LINKED_LIST.getHandle(PASSABLE_STRING.getHandle())); 
	private String[] tags;
	private Entity e;



	public ComponentTags(String[] tags) {
		this.tags=tags;
	}


	@Override
	protected void INIT(Entity entity) {
		this.e=entity;
		entity.ListClear(VAR_TAGS);
		entity.ListSet(VAR_TAGS,tags);


	}
	@Override
	protected void enable() {
		INIT(this.currentEntity);
		
	}


	@Override
	protected boolean DISABLE() {
		this.e.ListClear(VAR_TAGS);
		return true;
	}

	@Override
	public UUID getCOMPONENTID() {

		return ID;
	}


	public static VAR_R<PASSABLE_LINKED_LIST<String>> get_VAR_TAGS() {
		return VAR_TAGS.getAsReadOnly();
	}


	
}
