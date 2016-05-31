package hashtable;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashTableTest {
	private HashTable<Integer, String> hashtable;
	
	private void buildHashTable() {
		hashtable = new HashTable<Integer, String>(10);
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
	}

	@Test
	public void testContainsKey() {
		buildHashTable();
		assertTrue(hashtable.containsKey(12));
		assertTrue(hashtable.containsKey(22));
		assertFalse(hashtable.containsKey(99));
	}

	@Test
	public void testContainsValue() {
		buildHashTable();
		assertTrue(hashtable.containsValue("key is 0"));
		assertFalse(hashtable.containsValue("aaa"));
	}

	@Test
	public void testGet() {
		buildHashTable();
		assertNotEquals(hashtable.get(2), "key is 2");
		assertEquals(hashtable.get(2), "key is 2 again");
	}

	@Test
	public void testPut() {
		buildHashTable();
		hashtable.put(120, "key is 120");
		hashtable.put(130, "key is 130");
		assertTrue(hashtable.containsKey(120));
		assertTrue(hashtable.containsKey(130));
		assertTrue(hashtable.containsValue("key is 120"));
		assertTrue(hashtable.containsValue("key is 130"));
	}

	@Test
	public void testRemove() {
		buildHashTable();
		hashtable.remove(12);
		assertFalse(hashtable.containsKey(12));
		
	}
	
	@Test
	public void testSize() {
		buildHashTable();
		assertEquals(hashtable.size(), 9);
	}

}
