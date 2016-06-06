package binaryheap;

import java.util.Comparator;
import java.util.Iterator;

public interface PriorityQueue<E> {
    
    /*Inserts the specified element into this priority queue.*/
    boolean	add(E e);
    
    /*Removes all of the elements from this priority queue.*/
    void	clear();
    
    /*Returns the comparator used to order the elements in this queue, or null if this queue is sorted according to the natural ordering of its elements.*/
    Comparator<? super E>	comparator();
    
    /*Returns true if this queue contains the specified element.*/
    boolean	contains(Object o);
    
    /*Returns an iterator over the elements in this queue.*/
    Iterator<E>	iterator();
    
    /*Inserts the specified element into this priority queue.*/
    boolean	offer(E e);
    
    /*Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.*/
    E	peek();
    
    /*Retrieves and removes the head of this queue, or returns null if this queue is empty.*/
    E	poll();
    
    /*Removes a single instance of the specified element from this queue, if it is present.*/
    boolean	remove(Object o);
    
    /*Returns the number of elements in this collection.*/
    int	size();
    
    /*Returns an array containing all of the elements in this queue.*/
    Object[]	toArray();
    
    /*Returns an array containing all of the elements in this queue; the runtime type of the returned array is that of the specified array.*/
    <T> T[]	toArray(T[] a);
    
}  