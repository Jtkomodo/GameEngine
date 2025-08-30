package main.java.events;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

import main.java.UI.DATA_MOUSE_COLLISION;
import main.java.core.CoreEngine;

public class EventDispatcher {
	
	
	
	private static HashMap<String,LinkedList<EventHandle<?>>> subscribers= new HashMap<String,LinkedList<EventHandle<?>>>();
    
	
	

	public static<T extends EVENT_DATA> EventHandle<T> subscribe(EventType<T> type,OnEvent<T> action) {
		EventHandle<T> handle=new EventHandle<T>(type,action); 
		subscribe(handle);
		return handle;

	
	}


	
	public static<T extends EVENT_DATA> void unsubscribe(EventHandle<T> handle){
		LinkedList<EventHandle<?>> list=subscribers.get(handle.getType());
		if(list!=null) {
			list.remove(handle);
		
		}
	}



	public static<T extends EVENT_DATA> void post(EventType<T> type,T data) {
		LinkedList<EventHandle<?>> list=subscribers.get(type.getID());
		if(list!=null) {
			for(int i=0;i<list.size();i++) {
				EventHandle<T> handle=(EventHandle<T>)list.get(i);
				handle.dispatch(data);
				CoreEngine.DebugPrint(data.debug_data_sent(type.getID()));
			}
		}else {
			CoreEngine.DebugPrint(data.debug_no_sub(type.getID()));
		}

	}



	public static <T extends EVENT_DATA> EventHandle<T> subscribe(EventHandle<T> handle) {
		
		LinkedList<EventHandle<?>> list=subscribers.get(handle.getType());
		if(list==null) {
			list=new LinkedList<EventHandle<?>>();
		}


		if(!list.contains(handle)) {
			list.add(handle);
		}
		subscribers.put(handle.getType(), list);
		return handle;
	}







	

	
	

}
