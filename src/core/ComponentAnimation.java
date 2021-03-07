package core;

import animation.Animation;
import animation.AnimationEngine;

public class ComponentAnimation extends EntityComponent {

	
	public final static int COMPID=0x2003;
	
	
	public Animation a;
	
	
	
	public ComponentAnimation(Animation a) {
		super(COMPID);
		this.a=new Animation(a);
	}
	
	
	@Override
	protected void INIT(Entity entity) {
     this.currentEntity=entity;
	 AnimationEngine.addEntityAnimation(entity.ID, a);

	}

	@Override
	protected void GAMELOOP_TICK() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void RENDER_TICK() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean DISABLE() {
		AnimationEngine.removeEntityAnimation(currentEntity.ID);
		return false;
	}

}
