package main.java.core;

import java.util.UUID;

import main.java.rendering.Texture;

public class PASSABLE_TEXTURE implements PassableData<Texture> {

	public static final UUID ID=UUID.randomUUID();
	private Texture value;
	
	
	
	
	
	
	protected PASSABLE_TEXTURE(Texture value) {
		this.value = value;
	}

	private PASSABLE_TEXTURE() {
	}

	@Override
	public Texture getValue() {
		return value;
	}

	@Override
	public UUID getDATAID() {
		return ID;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "TEXTURE";
	}
	@Override
	public void setValue(Texture value) {
		this.value=value;
		
	}
	public static DATA_HANDLE<Texture,PASSABLE_TEXTURE> getHandle(){
    	return new DATA_HANDLE<Texture,PASSABLE_TEXTURE>(new PASSABLE_TEXTURE());
    }

	@Override
	public <S extends PassableData<Texture>> S getNewType() {
		return (S) new PASSABLE_TEXTURE();
	}

	@Override
	public String printValue(String indent) {
		return this.value.getPath();
	}
	
}
