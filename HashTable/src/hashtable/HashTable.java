package hashtable;

import java.lang.reflect.Array;
import java.util.Enumeration;

/*
What will happen if two different HashMap key objects have same hashcode?
They will be stored in same bucket but no next node of linked list. 
And keys equals () method will be used to identify correct key value pair in HashMap.
*/


public class HashTable<K,V> {
	class Node {
	    Node next;
	    K key;
	    V value;
	 
	    public Node(K key, V value) {
	        this.key = key;
	        this.value = value;
	        next = null;
	    }
	    
	    public String toString() {
			return "[" + key + "," + value + "]";
		}
	}//Node
	
	private Node[] table = null;
	private int DEFAULT_TABLE_SIZE = 16;
	private double DEFAULT_LOAD_FACTOR = 0.75;
	private double loadFactor = DEFAULT_LOAD_FACTOR;
	private int size = 0;
	Node tmpNode = new Node(null, null);
	
	public HashTable() {
		//table = new Node[DEFAULT_TABLE_SIZE];	//Cannot create a generic array of HashTable<K,V>.Node
		//table = (Node[]) new Node[DEFAULT_TABLE_SIZE];
		table = (Node[]) Array.newInstance(tmpNode.getClass(), DEFAULT_TABLE_SIZE);
	}
	
	public HashTable(int size) {
		//table = new Node[size];
		//table = (Node[]) new Object[size];
		table = (Node[]) Array.newInstance(tmpNode.getClass(), size);
	}
	
	public HashTable(int size, double loadFactor) {
		//table = new Node[size];
		//table = (Node[]) new Object[size];
		table = (Node[]) Array.newInstance(tmpNode.getClass(), size);
		this.loadFactor = loadFactor;
	}

	public void clear( ) {
		//table = new Node[table.length];
		//table = (Node[]) new Object[table.length];
		table = (Node[]) Array.newInstance(tmpNode.getClass(), table.length);
	}
	
	//public Object clone( ) {
	//	return null;
	//}
	
	public boolean containsKey(K key) {
		int pos = getHash(key, table.length);                       
        if (table[pos] == null) {
            return false;
        } else {
        	Node curr = table[pos];
        	while (curr != null) {
        		if (curr.key.equals(key)) {
        			return true;
        		}
        		curr = curr.next;
        	}
        	return false;
        }
	}
	
	public boolean containsValue(V value) {
		for (int i = 0; i < table.length; i++) {
			Node node = table[i];
			while (node != null) {
				if (value.equals(node.value)) {
					return true;
				}
				node = node.next;
			}
		}
		return false;
	}
	
	/*
	 * Returns an enumeration of the values contained in the hash table.
	 */
	public Enumeration elements() {
		return new HashTableValuesEnumeration();
	}
	
	/*
	 * Returns the object that contains the value associated with key. 
	 * If key is not in the hash table, a null object is returned.
	 */
	public V get(K key) {
		return get(key, table);
	}
	
	private V get(K key, Node[] table) {
		int pos = getHash(key, table.length);                       
        if (table[pos] == null) {
            return null;
        } else {
        	Node curr = table[pos];
        	while (true) {
        		if (curr.key.equals(key)) {
        			return curr.value;
        		}
        		if (curr.next == null) {
        			break;
        		}
        		curr = curr.next;
        	}
        	return null;
        }
	}
	
	/*
	 * Returns true if the hash table is empty; 
	 * returns false if it contains at least one key.
	 */
	public boolean isEmpty( ) {
		return size == 0;
	}
	
	public Enumeration keys( ) {
		return new HashTableKeysEnumeration();
	}

	/*
	 * Inserts a key and a value into the hash table. 
	 * Returns null if key isn't already in the hash table; 
	 * returns the previous value associated with key if key is already in the hash table.
	 */
	public V put(K key, V value) {
		V ret = put(key, value, table);
		if (needRehash()) {
			rehash();
		}
		return ret;
	}
	
	private V put(K key, V value, Node[] table) {
        int pos = getHash(key, table.length);        
        Node node = new Node(key, value);                
        if (table[pos] == null) {
            table[pos] = node;
            size++;
            return null;
        } else {
        	V ret = null;
        	Node curr = table[pos];
        	Node prev = table[pos];
        	while (curr != null) {
        		if (curr.key.equals(key)) {
        			ret = curr.value;
        			curr.value = value;
        			return ret;
        		}
        		prev = curr;
        		curr = curr.next;
        	}
        	prev.next = node;
        	size++;
        	return ret;
        }    
	}
	
	private boolean needRehash() {
		return ((float)size/table.length > loadFactor);
	}
	
	/*
	 * Increases the size of the hash table and rehashes all of its keys. 
	 * */
	private void rehash( ) {
		//Node[] newTable = new Node[table.length * 2];
		//Node[] newTable = (Node[]) new Object[table.length * 2];
		Node[] newTable = (Node[]) Array.newInstance(tmpNode.getClass(), table.length * 2);
		Enumeration<K> keys = keys();
		while(keys.hasMoreElements()) {
			K key = keys.nextElement();
			put(key, get(key), newTable);
		}
		table = newTable;
	}

	/*
	 * Removes key and its value. Returns the value associated with key. 
	 * If key is not in the hash table, a null object is returned.
	 * */
	public 	V remove(K key) {
		int pos = getHash(key, table.length);                       
        if (table[pos] == null) {
            return null;
        } else {
        	Node curr = table[pos];
        	Node prev = table[pos];
        	while (true) {
        		if (curr.key.equals(key)) {
        			V ret = curr.value;
        			if (curr == table[pos]) {
        				table[pos] = curr.next;
        			} else {
        				prev.next = curr.next;
        			}        			
        			return ret;
        		}
        		if (curr.next == null) {
        			break;
        		}
        		prev = curr;
        		curr = curr.next;
        	}
        	return null;
        }
	}
	
	/*
	 * Returns the number of entries in the hash table.
	 * */
	public	int size( ) {
		return size;
	}

	/*
	* Returns the string equivalent of a hash table.
	* */
	public	String toString( ) {
		return "";
	}
	
	private int getHash(Object object, int tableSize ) {
        int hash = object.hashCode( ) % tableSize;
        if (hash < 0) {
            hash += tableSize;
        }
        return hash;
    }
	
	public void printHashTable () {
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            System.out.print ("Bucket " + i + ":  ");             
            Node node = table[i];
            while(node != null) {
                System.out.print(node.toString() +"; ");
                node = node.next;
            }
            System.out.println();
        }
    }
	
	class HashTableKeysEnumeration implements Enumeration<K> {
		int currBasket = -1;
		Node currNode = null;
		
		@Override
		public boolean hasMoreElements() {
			if (currBasket == table.length -1 && currNode.next == null) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public K nextElement() {
			if (currBasket == -1) {
				int pos = 0;
				while(pos <= table.length-1) {
					if (table[pos] == null) {
						pos++;
					} else {
						break;
					}
				}
				currBasket = pos;
				currNode = table[pos];
			} else {
				if (currNode.next != null) {
					currNode = currNode.next;
				} else {
					int pos = currBasket + 1;
					while(pos <= table.length-1) {
						if (table[pos] == null) {
							pos++;
						} else {
							break;
						}
					}
					currBasket = pos;
					currNode = table[pos];
				}
			}
			return currNode.key;
		}
	}//HashTableKeysEnumeration
	
	class HashTableValuesEnumeration implements Enumeration<V> {
		int currBasket = -1;
		Node currNode = null;
		
		@Override
		public boolean hasMoreElements() {
			if (currBasket == table.length -1 && currNode.next == null) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public V nextElement() {
			if (currBasket == -1) {
				int pos = 0;
				while(pos <= table.length-1) {
					if (table[pos] == null) {
						pos++;
					} else {
						break;
					}
				}
				currBasket = pos;
				currNode = table[pos];
			} else {
				if (currNode.next != null) {
					currNode = currNode.next;
				} else {
					int pos = currBasket + 1;
					while(pos <= table.length-1) {
						if (table[pos] == null) {
							pos++;
						} else {
							break;
						}
					}
					currBasket = pos;
					currNode = table[pos];
				}
			}
			return currNode.value;
		}
	}//HashTableValuesEnumeration
}

