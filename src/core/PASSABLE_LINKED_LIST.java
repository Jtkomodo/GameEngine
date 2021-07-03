package core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class PASSABLE_LINKED_LIST<T> implements PassableData<LinkedList<T>>,PassableList<T>{

	
	
	public static final UUID ID=UUID.randomUUID();
	
	private LinkedList<T> list;
	private String typeName;
	private PASSABLE_LINKED_LIST(String typeName) {
		this.typeName=typeName;
		list=new LinkedList<T>();
	}
	
	
	@Override
	public <S extends PassableData<LinkedList<T>>> S getNewType() {
		return (S) new PASSABLE_LINKED_LIST<T>(this.typeName);
	}

	@Override
	public void setValue(LinkedList<T> value) {
	   list=value;
	}

	@Override
	public String getType() {
		return "LIST-"+this.typeName;
	}

	@Override
	public LinkedList<T> getValue() {
		return list;
	}

	@Override
	public UUID getDATAID() {
		return ID;
	}

	public static  < T extends PassableData<K>,K>  DATA_HANDLE<LinkedList<K>,PASSABLE_LINKED_LIST<K>>  getHandle(DATA_HANDLE<K,T> handle){
	         return new DATA_HANDLE<LinkedList<K>,PASSABLE_LINKED_LIST<K>>(new PASSABLE_LINKED_LIST<K>(handle.getType().getType()));
	}
	



	@Override
	public T[] getListAsArray(T[] array) {
		return this.list.toArray(array);
	}

	@Override
	public void setListFromArray(T[] array) {
		  list.clear();
	      Collections.addAll(list, array);
		
	}


	@Override
	public void clearList() {
		list.clear();
		
	}


	@Override
	public int getListSize() {
		return list.size();
	}


	@Override
	public boolean isListEmpty() {
		return list.isEmpty();
	}


	@Override
	public void setValueAtIndex(int index, T value) {
	          if(index<list.size() && index>=0) {
	        	  this.list.set(index, value);
	          }
		
	}


	@Override
	public T getValueAtIndex(int index) {
		
		return this.list.get(index);
		 
	}


	
	
	
	
	
	
}
