package UI;

import java.util.LinkedList;

import org.joml.Vector2f;

import core.Game;
import events.Flag;
import input.InputPoller;

public class UIManager {
       
	
	  private static LinkedList<UIBox> box_list=new LinkedList<UIBox>();
      public static Flag takingInput=new Flag(false);	
	
	
	

	  public static void addBox(UIBox box) {

		  box_list.add(box);

	  }
	  public static void removeBox(UIBox box) {

		  box_list.remove(box);

	  }
	  public static void RenderUpdate() {
		  for(int i=0;i<box_list.size();i++) {
			  box_list.get(i).Render_update();
		  }
	  }
	  
	  
	  
	  public static void CollisionUpdate() {


		  Vector2f UIPosition=InputPoller.NO_FOV_getWorldMousePosition();

		  for(int i=0;i<box_list.size();i++) {
			  box_list.get(i).updateFlags(UIPosition);
			  
		  }


	  }
    
	
	
	
	
	
	
}
