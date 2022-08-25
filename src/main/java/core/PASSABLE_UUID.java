package main.java.core;

import java.util.UUID;

import main.java.animation.SpriteSheet;
import main.java.rendering.Texture;

public class PASSABLE_UUID implements PassableData<UUID> {

	
	
	public static final UUID ID=UUID.randomUUID();
	private UUID value;
	
	protected PASSABLE_UUID(UUID value) {
		this.value=value;
	}
	
	
	private PASSABLE_UUID() {
		
	}
	
	
	
	
	@Override
	public <S extends PassableData<UUID>> S getNewType() {
		// TODO Auto-generated method stub
		return (S) new PASSABLE_UUID();
	}

	@Override
	public void setValue(UUID value) {
		this.value=value;
		
	}

	@Override
	public String getType() {
		return "UUID";
	}

	@Override
	public UUID getValue() {
		return this.value;
	}

	@Override
	public UUID getDATAID() {
		return ID;
	}

	public static DATA_HANDLE<UUID,PASSABLE_UUID> getHandle(){
    	return new DATA_HANDLE<UUID,PASSABLE_UUID>(new PASSABLE_UUID());
    }


	@Override
	public String printValue(String indent) {
		return ""+this.value;
	}
}
