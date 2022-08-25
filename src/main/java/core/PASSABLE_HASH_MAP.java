package main.java.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class PASSABLE_HASH_MAP<K,V> implements PassableData<HashMap<K,V>> {

	
	public static final UUID ID=UUID.randomUUID();
	private String Ktype;
	private String Vtype;
	private DATA_HANDLE<K,? extends PassableData<K>> KeyHandle;
	private DATA_HANDLE<V,? extends PassableData<V>> ValueHandle;
	
	
	private HashMap<K,V> value;
	
	private <VT extends PassableData<V>,KT extends PassableData<K>>PASSABLE_HASH_MAP(DATA_HANDLE<K,KT>Khandle, DATA_HANDLE<V,VT> Vhandle){
		this.KeyHandle=Khandle;
		this.ValueHandle=Vhandle;
		this.Ktype=Khandle.getTypeName();
		this.Vtype=Vhandle.getTypeName();
		this.value=new HashMap<K,V>();
	}
	
	
        
	
	
	
	
	
	
	
	@Override
	public <S extends PassableData<HashMap<K, V>>> S getNewType() {
		return (S)new PASSABLE_HASH_MAP<K,V>(KeyHandle,ValueHandle);
	}

	@Override
	public void setValue(HashMap<K, V> value) {
		   this.value=value;
		
	}
	
	@Override
	public String getType() {
		return "H_M-("+Ktype+","+Vtype+")";
	}

	@Override
	public HashMap<K, V> getValue() {
		return this.value;
	}

	@Override
	public UUID getDATAID() {
		return ID;
	}
	

	
	
	
	public void clearMap() {
		this.value.clear();
	}
	
	
	public boolean isEmpty() {
		return this.value.isEmpty();
	}
	
	public V getOrDefault(K key,V defaultValue) {
		return this.value.getOrDefault(key, defaultValue);
	
	}
	
	public boolean containsValue(V value) {
		return this.value.containsValue(value);
	}
	
	
	public boolean containsKey(K key) {
		return this.value.containsKey(key);
	}
	
	public void put(K key,V value) {
		this.value.put(key, value);
	}
	public V get(K key) {
		return this.value.get(key);
	}
	
	public void remove(K key) {
		this.value.remove(key);
	}
	
	//HANDLE
	public static <K,V,KT extends PassableData<K>,VT extends PassableData<V>> DATA_HANDLE<HashMap<K,V>,PASSABLE_HASH_MAP<K,V>> getHandle(DATA_HANDLE<K,KT> Khandle,DATA_HANDLE<V,VT> Vhandle) {
		   return new DATA_HANDLE<HashMap<K,V>,PASSABLE_HASH_MAP<K,V>>(new PASSABLE_HASH_MAP<K,V>(Khandle,Vhandle));
		
	}










	@Override
	public String printValue(String indent) {
		String S="HASHMAP:{\n";
		Iterator<Entry<K,V>> I=this.value.entrySet().iterator();
		while(I.hasNext()) {
			Entry<K,V> e=I.next();
			K key=e.getKey();
			V vlaue=e.getValue();
			KeyHandle.getType().setValue(key);
			ValueHandle.getType().setValue(vlaue);
			S=S.concat("\n	Key<"+this.Ktype+">:"+KeyHandle.getType().printValue(indent+"	"));
			S=S.concat("\n	Value<"+this.Vtype+">:"+ValueHandle.getType().printValue(indent+"	")+"\n");
			
		}

			S=S.concat("\n"+indent+"        }");
		return S;

	}
	

}
