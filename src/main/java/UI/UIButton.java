package main.java.UI;

import org.joml.Vector2f;

public class UIButton extends UIEntity {
	
	

	
	
	
	
	public UIButton(Vector2f position,String label, float width, float height,float sizeOfString) {
		super(position, width, height);
		//add in the very specific components
		UIComponent[] list= {
				//this.tex
				//texture
				//trigger
		};
		super.addComponents(list);
		
	}
	
	
	
	
	
	

}
