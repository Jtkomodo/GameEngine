package main.java.core;

import java.util.LinkedList;
import java.util.List;

public interface PassableList<K> {
	
	
	
	public boolean isListEmpty();
	public void setValueAtIndex(int index,K value);
    public K getValueAtIndex(int index);
    public boolean hasValue(K value);
    public int getListSize();	
	public void clearList();
	public void setListFromArray(K[] array);
	public K[] getListAsArray(K[] array);

}
