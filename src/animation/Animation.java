package animation;

import java.util.UUID;

import core.Entity;

public class Animation {


	protected AnimationData data[];
	protected float fps;
	protected int currentFrame=0;
	protected double unp=0;
	
	
	public Animation(AnimationData[] data,float fps) {
		this.data=data;
		this.fps=fps;
    
	}
	
	public Animation(SpriteSheet sheet,int start,int end,float fps) {
        data=new AnimationData[(end-start)];
		for(int i=0;i<(end-start);i++) {
		   data[i]= new AnimationData(sheet, start+i);
		}
        this.fps=fps;
	}
	
	public Animation(Animation a) {
		this.data=a.data;
		this.fps=a.fps;
		
	}

	public void setFps(float fps) {
		this.fps = fps;
	}

}