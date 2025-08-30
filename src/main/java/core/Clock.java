package main.java.core;

public class Clock {



	private boolean paused=false;
	private double timeWhenPaused;
	private double timeLastFrame;

	public Clock() {
		this.timeLastFrame=getTime();
	}



	public double getDeltaTime() {
		double timeNow=getTime();
		double timePaused=0;
		double deltaT=timeNow-(timeLastFrame);
		if(paused) {
			timePaused=timeNow-timeWhenPaused;
			deltaT=0;
		}


		this.timeLastFrame=getTime();


		return deltaT;
	}


	public void Pause() {
		if(!paused) {
			this.timeWhenPaused=getTime();
			paused=true;
		}
	}
	public void Play() {
		if(paused) {
			this.timeWhenPaused=0;
			paused=false;
		}
	}

	private  double getTime() {

		return (double)System.nanoTime()/(double)1000000000;

	}


}
