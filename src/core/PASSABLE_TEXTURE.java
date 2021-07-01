package core;

import java.util.UUID;

import rendering.Texture;

public class PASSABLE_TEXTURE implements PassableData<Texture> {

	public static final UUID ID=UUID.randomUUID();
	private Texture value;
	
	
	
	
	
	
	public PASSABLE_TEXTURE(Texture value) {
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

	public static DATA_HANDLE<PASSABLE_TEXTURE> getHandle(){
    	return new DATA_HANDLE<PASSABLE_TEXTURE>(new PASSABLE_TEXTURE());
    }
	
}
