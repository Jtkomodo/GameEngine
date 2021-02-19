package rendering;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;

import org.joml.Vector2f;
import org.joml.Vector3f;

import core.Constants;
import rendering.RenderEntity;
/**This is where we will add all our render entities to be drawn({@link #addEntity(RenderEntity) addEntity()},{@link #addUIEntity(RenderEntity) addUIEntity()})
 * .Once all entities are added it will sort them by the z coord({@link #SortEntities()})
 * it they will be sent to the {@link MainBatchRender} where it will batch all the
 * models to draw them in as few draw calls as possible.({@link #addToBatchedRender()})
 * 
 * 
 * @author Jesse Talbot
 *
 */
public class MainRenderHandler {

	
	
	private static LinkedList<RenderEntity> entities=new LinkedList<RenderEntity>();
    
	/**
	 * This will move the entities to the Batched render to be batched into as few draw calls as possible.
	 */
	public static void addToBatchedRender() {
		
	    Iterator<RenderEntity> i=entities.iterator();	
		
if(!entities.isEmpty()) {
	//Start.DebugPrint(" Number of entities="+ entities.size());
		while(i.hasNext()) {
			
			RenderEntity e=i.next();
			Vector3f p= e.getPosition();
			if(e.isMirror()) {
				MainBatchRender.Mirror();
			}
			if(e.isHasColor()) {
				
			if(!e.isHasNonSqaureSize()) {	
			MainBatchRender.addModel(e.getUvs(),e.getVerts(),new Vector2f(p.x,p.y) ,e.getAngle(), e.getSize(),e.getTexture(),e.getColor(),e.getUIPojeection());
			}else {
				MainBatchRender.addModel(e.getUvs(),e.getVerts(),new Vector2f(p.x,p.y) ,e.getAngle(), e.getNonSquareSize(),e.getTexture(),e.getColor(),e.getUIPojeection());
			}
			
			}else {
				if(!e.isHasNonSqaureSize()) {	
					
				
				MainBatchRender.addModel(e.getUvs(),e.getVerts(),new Vector2f(p.x,p.y) ,e.getAngle(), e.getSize(),e.getTexture(),e.getUIPojeection());
				}else {
					MainBatchRender.addModel(e.getUvs(),e.getVerts(),new Vector2f(p.x,p.y) ,e.getAngle(), e.getNonSquareSize(),e.getTexture(),e.getUIPojeection());
					
				}
				
				}
		  
			}
		entities.clear();
}
	}
	/**
	 * Adds the entity to be drawn to the list
	 * @param e Entity to be drawn
	 */
	public static void addEntity(RenderEntity e) {
		entities.add(e);
	
		 
	}
	public static void addUIEntity(RenderEntity e) {
		e.setUIPojeection(true);
		entities.add(e);
		
	}
	
	/**
	 * Sorts the Entities by the z coords
	 */
	public static void SortEntities() {
		
		if(!entities.isEmpty()) {
			Comparator<RenderEntity> byZvalue = new Comparator<RenderEntity>() {
		          
		          @Override
		          public int compare(RenderEntity e1,RenderEntity e2) {
		              Float v1 = e1.getPosition().z;
		              Float v2 = e2.getPosition().z;
		              int result=v1.compareTo(v2);
		              if(result==0) {
		            	 v1=e1.getPosition().y; 
		            	 v2=e2.getPosition().y;
		            	 
		            	 result=v2.compareTo(v1);
		              }
		              
		              
		              return result;
		          }
		      };
			 	Collections.sort(entities, byZvalue);    
				
		}
	
		
		
		
	}
	
	
	
	
	public static int getAmountOfEntities() {
		return entities.size();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
