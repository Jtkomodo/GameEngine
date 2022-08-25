package main.java.events;

public interface OnEvent<T> {

	public void invoke(T data);
	
}
