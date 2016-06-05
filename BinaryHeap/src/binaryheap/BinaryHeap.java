package binaryheap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

class BinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {
	
	private static final int d = 2;//number of children each node has
	private int heapSize;
	private E[] heap;

	public BinaryHeap(int capacity) {
		heapSize = 0;
		heap = (E[])new Comparable[capacity + 1]; 
		Arrays.fill(heap, -1);
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public boolean isFull() {
		return heapSize == heap.length;
	}

	public void makeEmpty() {
		heapSize = 0;
	}

	private int parent(int i) {
		return (i - 1) / d;
	}

	/** Function to get index of k th child of i **/
	private int kthChildIndex(int i, int k) {
		return d * i + k;
	}

	/** Function to insert element */
	public boolean insert(E x) {
		if (isFull()) {
			return false;
		}
		/** Percolate up **/
		heap[heapSize++] = x;
		heapifyUp(heapSize - 1);
		return true;
	}

	/** Function to find least element **/
	public E findMin() {
		if (isEmpty()) {
			return null;
		}
		return heap[0];
	}

	public E deleteMin() {
		E minElement = heap[0];
		delete(0);
		return minElement;
	}

	/** Function to delete element at an index **/
	public E delete(int ind) {
		if (isEmpty()) {
			return null;
		}
		E element = heap[ind];
		heap[ind] = heap[heapSize - 1];
		heapSize--;
		heapifyDown(ind);
		return element;
	}

	/** Function heapifyUp **/
	private void heapifyUp(int childInd) {
		E tmp = heap[childInd];
		while (childInd > 0 && tmp.compareTo(heap[parent(childInd)]) < 0) {
			heap[childInd] = heap[parent(childInd)];
			childInd = parent(childInd);
		}
		heap[childInd] = tmp;
	}

	/** Function heapifyDown **/
	private void heapifyDown(int ind) {
		int childPos;
		E tmp = heap[ind];
		while (kthChildIndex(ind, 1) < heapSize) {
			childPos = minChild(ind);
			if (heap[childPos].compareTo(tmp) < 0) {
				heap[ind] = heap[childPos];
			}
			else {
				break;
			}
			ind = childPos;
		}
		heap[ind] = tmp;
	}

	/** Function to get smallest child **/
	private int minChild(int ind) {
		int bestChildPos = kthChildIndex(ind, 1);
		int k = 2;
		int pos = kthChildIndex(ind, k);
		while ((k <= d) && (pos < heapSize)) {
			if (heap[pos].compareTo(heap[bestChildPos]) < 0) {
				bestChildPos = pos;
			}
			pos = kthChildIndex(ind, k++);
		}
		return bestChildPos;
	}

	public void printHeap() {
		System.out.print("\nHeap = ");
		for (int i = 0; i < heapSize; i++) {
			System.out.print(heap[i] + " ");
		}
		System.out.println();
	}

	@Override
	public boolean add(E e) {
		return insert(e);
	}

	@Override
	public void clear() {
		this.makeEmpty();
		
	}

	@Override
	public Comparator<? super E> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Object o) {
		if (this.isEmpty()) {
			return false;
		} else {
			for (int i = 0; i < heapSize; i++) {
				if (heap[i].equals(o)) {
					return true;
				}
			}
		} 
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(E e) {
		return insert(e);
	}

	@Override
	public E peek() {
		return findMin();
	}

	@Override
	public E poll() {
		return deleteMin();
	}

	@Override
	public boolean remove(Object o) {
		if (this.isEmpty()) {
			return false;
		} else {
			for (int i = 0; i < heapSize; i++) {
				if (heap[i].equals(o)) {
					delete(i);
					return true;
				}
			}
		} return false;
	}

	@Override
	public int size() {
		return heapSize;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
}
