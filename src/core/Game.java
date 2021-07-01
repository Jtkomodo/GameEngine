package core;

import rendering.Camera;
import rendering.Render;
import rendering.ShaderProgram;
import rendering.Texture;

/**
 * 
 * @author Jesse Talbot
 *this is the engines entry point
 */
public abstract class Game {
	
	
	public static Texture DEFAULT_TEXTURE;
	
	private Window window;
	private boolean windowClosed=false;
	private ShaderProgram batchedShader;	
	
	
	/**
     * This is where the initialization before the game loop will be put
     */
	public abstract void start();
	/**
	 * this is where all the game loop stuff will happen 
	 */
	public abstract void GameLoop();
	
	
	public void toggleFullscreen() {
		window.toggleFullscreen();
	}
	
	public void CloseWindow() {
		window.CloseWIndow();
	}
	
	
	public  Game(int width,int height,String name) {	
	MakeWindow(name, 640,480);
	start();
	}
	/**
	 * this is all you need to call to run the engine
	 */
	public void updateGame() {
		while(!windowClosed) {
			
		    Update();
		   
		}
		 Close();
	}
	
	private final void MakeWindow(String name,int width,int height) {
		window=new Window(width,height,name);
		this.batchedShader= new ShaderProgram("BatchShader");
		batchedShader.bind();
		Render.s=this.batchedShader;
		try {
			
			Render.location=batchedShader.makeLocation("sampler");
			Render.Projection=batchedShader.makeLocation("projection");
			Render.RTS=batchedShader.makeLocation("rts");
			Render.UIProjection=batchedShader.makeLocation("UIProjection");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.exit(20);
		}
		Render.cam=new Camera(width,height);
		if(DEFAULT_TEXTURE==null) {
		DEFAULT_TEXTURE=new Texture("whitebox");
		}
	}
	
	
	private final void Update() {
		CoreEngine.UpdateInput();
		window.update();
		GameLoop();
		CoreEngine.updateEngine();
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
