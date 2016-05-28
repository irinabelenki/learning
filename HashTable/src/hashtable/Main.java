package hashtable;

import java.util.Enumeration;

public class Main {
	
	public static void main(String[] args) {
		HashTable<Integer, String> hashtable = new HashTable<Integer, String>(10);
		hashtable.put(2,  "key is 2");
		hashtable.put(3,  "key is 3");
		hashtable.put(33, "key is 33");
		hashtable.put(12, "key is 12");
		hashtable.put(22, "key is 22");
		hashtable.put(2,  "key is 2 again");
		hashtable.put(12, "key is 12 again");
		hashtable.put(22, "key is 22 again");
		hashtable.put(-1, "key is -1");
		hashtable.put(-11,"key is -11");
		hashtable.put(0, "key is 0");
		hashtable.put(18, "key is 18");
		
		hashtable.printHashTable();
		
		hashtable.put(120, "key is 120");
		hashtable.put(130, "key is 130");
		
		hashtable.printHashTable();
		
		System.out.println("Size: " + hashtable.size());
		System.out.println("Get key 22: " + hashtable.get(22));
		
		System.out.println("Contains key 12: " + hashtable.containsKey(12));
		System.out.println("Contains key 22: " + hashtable.containsKey(22));
		System.out.println("Contains key 99: " + hashtable.containsKey(99));
		
		System.out.println("Contains value [key is 0]: " + hashtable.containsValue("key is 0"));
		System.out.println("Contains value [aaa]: " + hashtable.containsValue("aaa"));
		
		hashtable.remove(12);
		
		hashtable.printHashTable();
		
		Enumeration<Integer> keys = hashtable.keys();
		while (keys.hasMoreElements()) {
			System.out.println("Key: " + keys.nextElement());
		}
		
		Enumeration<String> values = hashtable.elements();
		while (values.hasMoreElements()) {
			System.out.println("Value: " + values.nextElement());
		}
	}
}
