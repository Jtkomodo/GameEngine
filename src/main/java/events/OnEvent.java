package main.java.events;


public interface OnEvent<T extends EVENT_DATA> {

	public void invoke(T data);
	
}
