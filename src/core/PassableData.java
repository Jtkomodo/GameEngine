package core;

import java.util.UUID;

/**If you want to pass a new type of data into Entity using the engine's VAR system this is the interface you need
   * to use T is the actual data type so for a PASSABLE_BOOL you would put in <Boolean>. All of the built in classes
   * also has a public static getHandle class this is so that we can now only call a empty constructor when a handle is created.
   * The handle allows the Engine to know the type and the ID and to use the empty value as a temporary object to pass into the Entity
   * data when a VAR is set. A handle does not allow you to actually get the PASSABLE_DATA class it holds because only the Engine should
   * handle them. The handle also allows VAR to know everything about the data without needing to pass in any info except the PAASSABLE_DATA type.
   *  
   * 
   */

public interface PassableData<T> {
    /**This allows the Engine to get a new instance of the Object
     * even though it is generic.(you can not instantiate a generic using new)
     * @param <S> this is the type of this class the interface extends
     * @return a new instance of this class
     */
	public  <S extends PassableData<T>> S getNewType();
	/**This is our data's setter
	 * 
	 * @param value the value we want to set it to
	 */
	public void setValue(T value);
	/**
	 * This is the name we will use for the TYPE both for debugging
	 * and for the engine to be able to have VARs with the same name
	 * ,but different type
	 * @return The String that will represent the type
	 */
    public String getType();
    /**This is the getter of the value
     * 
     * @return the value
     */
	public T getValue();
	/**this is the getter for the ID of the type
	 * 
	 * @return the ID of the type
	 */
	public UUID getDATAID(); 
	
	
	
}
