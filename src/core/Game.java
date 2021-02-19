package core;

import rendering.ShaderProgram;

/**
 * 
 * @author Jesse Talbot
 *this is the engines entry point
 */
public abstract class Game {
	private Window window;
	private boolean windowClosed=false;
	private ShaderProgram MainShader,batchedShader;
	/**
     * This is where the initialization before the game loop will be put
     */
	public abstract void start();
	/**
	 * this is where all the game loop stuff will happen 
	 */
	public abstract void GameLoop();
	
	public void CloseWindow() {
		window.CloseWIndow();
	}
	
	
	public  Game(int width,int height,String name,String shader) {
	MakeWindow("test", 640,480,shader);
	}
	/**
	 * this is all you need to call to run the engine
	 */
	public void updateGame() {
		start();
		while(!windowClosed) {
			CoreEngine.UpdateInput();
		    Update();
		    CoreEngine.updateEngine();
		}
		 Close();
	}
	
	private final void MakeWindow(String name,int width,int height,String shader) {
		window=new Window(width,height,name);
		this.MainShader=new ShaderProgram(shader);
		start();
	}
	
	
	private final void Update() {
		
		window.update();
		GameLoop();
		window.render();
		window.clear();
		this.windowClosed=window.isExited();
	
	}
	
	
	private final void Close() {
		CoreEngine.DebugPrint("Closing.....");
		window.destroy();
		System.exit(0);
		
	}
	
	
	
}
