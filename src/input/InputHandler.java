package input;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import input.GetInput;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
public class InputHandler {

	
	
	private static  List<Integer> Buttons=new LinkedList<Integer>();

	
	public static  byte getStateofButton(int Button) {
		if(Buttons.contains(Button) ||(Button==GLFW_KEY_F || Button==GLFW_KEY_RIGHT_CONTROL || Button==GLFW_KEY_LEFT_CONTROL)) {
			return GetInput.getStateofButton(Button);
		
		}else {
			return 0;
		}
		
		
	}

	
	
	public static void EnableButton(int Button) {
		
	if(!Buttons.contains(Button)) 
		Buttons.add(Button);
	
	}
	
	public static void DisableButton(int Button) {
		if(Buttons.contains(Button)) 
			Buttons.remove((Integer)Button);
			
		
		
		
	}
	
	
    public static void DisableAll() {
		
		Buttons.clear();
	}
	
	
	public static void DisableAllBut(int[] buttons) {
		
		Buttons.clear();
	   EnableButtons(buttons);
		
	}
	
	public static void DisableButtons(int[] Buttons) {
		
		
		for(int i=0;i<Buttons.length;i++) {
		
	      DisableButton(Buttons[i]);
		
	}
	}
	
	public static void EnableButtons(int[] Buttons) {
		
		
		for(int i=0;i<Buttons.length;i++) {
		
	      EnableButton(Buttons[i]);
		
	}
	}
}
