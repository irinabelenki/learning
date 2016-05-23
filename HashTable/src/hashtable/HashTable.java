package hashtable;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/*
What will happen if two different HashMap key objects have same hashcode?
They will be stored in same bucket but no next node of linked list. 
And keys equals () method will be used to identify correct key value pair in HashMap.
*/


public class HashTable {
	class Node {
	    Node next;
	    Object key;
	    Object value;
	 
	    public Node(Object key, Object value) {
	        this.key = key;
	        this.value = value;
	        next = null;
	    }
	    
	    public String toString() {
			return "[" + key + "," + value + "]";
		}
	}//Node
	
	private Node[] table = null;
	private int tableSize = 0;
	private int DEFAULT_TABLE_SIZE = 32;
	private int size = 0;
	
	public HashTable() {
		tableSize = DEFAULT_TABLE_SIZE;
		table = new Node[tableSize];
	}
	
	public HashTable(int size) {
		tableSize = size;
		table = new Node[size];
	}
	
	//public HashTable(int size, float fillRatio) {
	//}
	
	//public HashTable(Object key, Object value) {
	//}
	
	public void clear( ) {
		table = new Node[tableSize];
	}
	
	//public Object clone( ) {
	//	return null;
	//}
	
	public boolean containsKey(Object key) {
		int pos = getHash(key);                       
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
	
	public boolean containsValue(Object value) {
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
	public Object get(Object key) {
		int pos = getHash(key);                       
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
	Returns true if the hash table is empty; 
	returns false if it contains at least one key.
	*/
	public boolean isEmpty( ) {
		return size == 0;
	}
	
	public Enumeration keys( ) {
		return new HashTableKeysEnumeration();
	}

	/*
	Inserts a key and a value into the hash table. 
	Returns null if key isn't already in the hash table; 
	returns the previous value associated with key if key is already in the hash table.
	*/
	public Object put(Object key, Object value) {
        int pos = getHash(key);        
        Node node = new Node(key, value);                
        if (table[pos] == null) {
            table[pos] = node;
            size++;
            return null;
        } else {
        	Object ret = null;
        	Node curr = table[pos];
        	while (true) {
        		if (curr.key.equals(key)) {
        			ret = curr.value;
        			curr.value = value;
        			return ret;
        		}
        		if (curr.next == null) {
        			break;
        		}
        		curr = curr.next;
        	}
        	curr.next = node;
        	size++;
        	return ret;
        }    
	}
	/*
	 * Increases the size of the hash table and rehashes all of its keys. 
	 * */
	public	void rehash( ) {
		
	}

	/*
	 * Removes key and its value. Returns the value associated with key. 
	 * If key is not in the hash table, a null object is returned.
	 * */
	public 	Object remove(Object key) {
		int pos = getHash(key);                       
        if (table[pos] == null) {
            return null;
        } else {
        	Node curr = table[pos];
        	Node prev = table[pos];
        	while (true) {
        		if (curr.key.equals(key)) {
        			Object ret = curr.value;
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
	
	private int getHash(Object object ) {
        int hash = object.hashCode( ) % table.length;
        if (hash < 0) {
            hash += table.length;
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
	
	class HashTableKeysEnumeration implements Enumeration {
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
		public Object nextElement() {
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
	
	class HashTableValuesEnumeration implements Enumeration {
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
		public Object nextElement() {
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

