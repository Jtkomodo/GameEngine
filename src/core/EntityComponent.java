package core;

public abstract class  EntityComponent {

	private final int ID;//all entity components start with hex  2000 so EnttityComponent- 2 would be 0x2002

	protected EntityComponent(int ID) {
		this.ID=ID;
	}

    
	protected abstract void INIT(Entity entity);
	protected abstract void GAMELOOP_TICK();//we put stuff here for things that need to happen before rendering
	protected abstract void RENDER_TICK();//we put stuff here for things that happen during rendering
	protected abstract boolean DISABLE();
    public int getID() {
    	return this.ID;
    }



}
