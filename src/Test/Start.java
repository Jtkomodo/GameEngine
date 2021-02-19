package Test;

import core.Game;
import rendering.ShaderProgram;
import rendering.Texture;

public class Start extends Game {

	private static Texture player;
	

	public Start(int width, int height, String name,String shader) {
		super(width, height, name,shader);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
	    
	}

	@Override
	public void GameLoop() {
		
		
	}

	public static void main(String[] args) {
		Start game =new Start(640,480,"test","shader");
	   
		game.updateGame();
		
	}		
	
	
}
