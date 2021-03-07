package core;

public class Timer {

	
	public static double getTIme() {
		
		return (double)System.nanoTime()/(double)1000000000;
		
	}
	
	
	private double timeToWaitFor;
	private double timeAlreadyPassed,time1;
	private boolean paused=false;
	private boolean hasFinishd=false;
	
	public Timer(double timeToWaitFor) {
		this.timeToWaitFor=timeToWaitFor;
		this.time1=getTIme();
	}
	
	
	public void pause() {
		this.paused=true;
		this.timeAlreadyPassed=(getTIme()+this.timeAlreadyPassed)-time1;
	}
	public void play() {
		this.paused=false;
	}
	public boolean isPaused() {
		return this.paused;
	}
	
	public boolean update() {
		boolean finished=false;
		if(!this.hasFinishd) {
			double time2=getTIme();
			if(!paused) {	
				if(((time2+this.timeAlreadyPassed)-time1)>=timeToWaitFor) {
					finished=true;

				}


			}else {
				time1=getTIme();
			}
			
		}else {
			finished=true;
		}
		this.hasFinishd=finished;
		return finished;

	}
	public double getTimeTaken() {
		return (getTIme()+this.timeAlreadyPassed)-time1;
	}

	public void setTimer(float timeToWaitFor) {
		this.timeToWaitFor=timeToWaitFor;
		resetTimer();
	}
	
	
	public void resetTimer() {
		this.timeAlreadyPassed=0;
		this.time1=getTIme();
		this.hasFinishd=false;
		this.paused=false;
	}
    public boolean hasFinished() {
    	return this.hasFinishd;
    }
	
}
