package core;


public enum PASSABLE_DATA_TYPE {
  
	VEC4F("VEC4F"),SPRITESHEET("SPRITESHEET"),INT("INT"),BOOL("BOOL"),MODEL("MODEL"),VEC2F("VEC2F"),TEXTURE("TEXTURE"),AABB("AABB");
	
	
	
	
	protected  String Debug_name;
	
    PASSABLE_DATA_TYPE(String debug_name) {
		this.Debug_name=debug_name;
	}
	

	
	
}

