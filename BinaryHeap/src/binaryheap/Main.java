package binaryheap;

public class Main {
	public static void main(String[] args) {
		//tryBinaryHeap();
		tryPriorityQueue();
	}
	
	public static void tryBinaryHeap() {
		BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(10);
		binaryHeap.insert(6);
		binaryHeap.insert(5);
		binaryHeap.insert(1);
		binaryHeap.insert(10);
		binaryHeap.insert(11);
		binaryHeap.printHeap();
		
		binaryHeap.deleteMin();
		binaryHeap.printHeap();	
	}
	
	public static void tryPriorityQueue() {
		PriorityQueue<Integer> priorityQueue = new BinaryHeap<Integer>(10);
		priorityQueue.add(6);
		priorityQueue.add(5);
		priorityQueue.add(1);
		priorityQueue.add(10);
		priorityQueue.add(11);
		((BinaryHeap<Integer>)priorityQueue).printHeap();
		
		System.out.println(priorityQueue.contains(5));
		System.out.println(priorityQueue.contains(100));
		priorityQueue.poll();
		((BinaryHeap<Integer>)priorityQueue).printHeap();
		
		priorityQueue.remove(11);
		((BinaryHeap<Integer>)priorityQueue).printHeap();
		
		
	}
}
