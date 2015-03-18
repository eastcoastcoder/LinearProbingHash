public class Bucket{
	//When table initialized, neverUsed is set to 'true' for all buckets
	protected boolean neverUsed = true;
	protected int key;
		
	public Bucket(){
		this.key = -1;
	}
				
	/** Mutator, when element is placed in bucket, neverUsed is false
	 * @param key value to be assigned
	 * @return true if key is assigned, false if collision occurs
	 */
	public boolean setValue(int key){
		if(neverUsed){
			this.key = key;
			neverUsed = false;
			return true;
		} else
			return false;
		
	}

	/** Returns key value of current bucket  */
	public int getValue(){ 
		return key; 
	}
}