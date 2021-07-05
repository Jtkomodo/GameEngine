package core;

import java.util.UUID;

import animation.SpriteSheet;

public class PASSABLE_SPRITESHEET implements PassableData<SpriteSheet>{

	

	public static final UUID ID=UUID.randomUUID();
	private SpriteSheet value;
	
	protected PASSABLE_SPRITESHEET(SpriteSheet value) {
		this.value=value;
	}
	
	
	
	
	private PASSABLE_SPRITESHEET() {
		// TODO Auto-generated constructor stub
	}




	@Override
	public SpriteSheet getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public UUID getDATAID() {
		// TODO Auto-generated method stub
		return ID;
	}




	@Override
	public String getType() {
		return "SPRITESHEET";
	}
	@Override
	public void setValue(SpriteSheet value) {
		this.value=value;
		
	}
	public static DATA_HANDLE<SpriteSheet,PASSABLE_SPRITESHEET> getHandle(){
    	return new DATA_HANDLE<SpriteSheet,PASSABLE_SPRITESHEET>(new PASSABLE_SPRITESHEET());
    }




	@Override
	public <S extends PassableData<SpriteSheet>> S getNewType() {
		return (S)new PASSABLE_SPRITESHEET();
	}




	@Override
	public String printValue(String indent) {
		return this.value.getTexture().getPath();
	}
	

}
