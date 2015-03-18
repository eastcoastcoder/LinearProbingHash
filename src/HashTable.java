public class HashTable {
	protected Bucket[] table;
	protected int divisor;
	
	HashTable(int divisor){
		this.divisor = divisor;
		
		//Allocate new hash table array
		table = new Bucket[divisor];
		
		//Instantiate Bucket Objects
		for(int i = 0; i < table.length; i++)
			table[i] = new Bucket();
		
	}
	
	/** Hash Insertion Function
	 * @param keys[] insertion values
	 */
	private void insert(int[] keys){
		for(int i = 0; i < keys.length; i++){
			int hashFunction = keys[i] % divisor;
			
			//If home bucket is used, value is placed into next available bucket
			while(!table[hashFunction].setValue(keys[i]) && hashFunction < 13){
				hashFunction++;
				
				//End of array, start back at beginning
				if(hashFunction == 13)
					hashFunction = 0;
			}
			System.out.println("Insert - Value: " + table[hashFunction].getValue() + " Index: " + hashFunction);
		}
	}
	
	/** Deletion Function
	 *  Uses search function to get index of each element
	 *  Deletes buckets by re-instantiation
	 * @param keys[] deletion values
	 */
	public void remove(int[] keys){		
		int getSearch[] = search(keys);
		for(int i = 0; i < getSearch.length; i++){
			table[getSearch[i]] = new Bucket();
		}
	}
	
	/** Hash Search Function
	 *  Begins at hashFunction index
	 * @param keys[] search values
	 * @return indexes[] location of each search value
	 */
	public int[] search(int[] keys){
		int[] indexes = new int[divisor];
		
		for(int i = 0; i < keys.length; i++){
			for(int hashFunction = keys[i] % divisor; hashFunction < table.length; hashFunction++)
				if(table[hashFunction].getValue() == keys[i])
					indexes[i] = hashFunction;
		
			if(indexes[i] == 0 && table[i].getValue() == -1)
				System.out.println("Search - Value: " + keys[i] + " Not Found");
			else
				System.out.println("Search - Value: " + keys[i] + " Index: " + indexes[i]);
			
		}
		return indexes;
	}
	
	@Override
	public String toString(){
		String result = "[";
		for(int i = 0; i < table.length-1; i++)
			result += table[i].getValue() + ", ";	
		result += table[table.length-1].getValue() + "]";
		
		return result;
	}
	
	public static void main(String[] args){
		//Hash table has 13 buckets, numbered from 0 to 12
		HashTable ht = new HashTable(13);
		
		int[] insertKeys = {9, 10, 22, 35, 11, 23, 2, 4, 15, 16, 48, 28};
		int[] removeKeys = {22, 2, 11, 35, 28};
		int[] searchKeys = {2, 4, 28};
		
		//Insert Keys, show how keys are distributed in the table after insertions
		ht.insert(insertKeys);
		System.out.println("Original: " + ht);
		
		//Delete Keys and show the resulting table
		ht.remove(removeKeys);
		System.out.println("Resulting: " + ht);
		
		//Search for keys
		ht.search(searchKeys);
		
	}
}
