import java.util.ArrayList;

/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
	public static void main(String[] args) {
		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();

		for (int i = 0; i < 10; i++)
			lst.addLast(i);
		for (int i = 20; i < 30; i++)
			lst.addFirst(i);
		
		System.out.println(lst);
		
		lst.removeFirst();
		lst.removeLast();
		
		ArrayList<Integer> items = new ArrayList<Integer>();
		items.add(21);
		items.add(20);
		items.add(4);
		
		lst.removeAll(items);
		
		System.out.println(lst);
		
		System.out.println(lst.getLast());
		System.out.println(lst.getFirst());
		System.out.println(lst.contains(27));
		
		java.util.Iterator<Integer> itr = lst.iterator();
		while (itr.hasNext()) {
			itr.next();
			itr.remove();
			System.out.println(lst);
		}
		
		
	}
	/**
	 * Construct an empty LinkedList.
	 */
	public MyLinkedList() {
		clear();
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void clear() {
		beginMarker = new Node<AnyType>(null, null, null);
		endMarker = new Node<AnyType>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
	}

	/**
	 * Returns the number of items in this collection.
	 * @return the number of items in this collection.
	 */
	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Adds an item to this collection, at the end.
	 * @param x any object.
	 * @return true.
	 */
	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	/**
	 * Adds an item to this collection, at specified position. Items at or after
	 * that position are slid one position higher.
	 * @param x any object.
	 * @param idx position to add at.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
	 */
	public void add(int idx, AnyType x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at specified position p. Items at or
	 * after that position are slid one position higher.
	 * @param p Node to add before.
	 * @param x any object.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
	 */
	private void addBefore(Node<AnyType> p, AnyType x) {
		Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
	}

	/**
	 * Returns the item at position idx.
	 * @param idx the index to search in.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * @param idx the index to change.
	 * @param newVal the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * @param idx index to search at.
	 * @return internal node corrsponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
	 */
	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * @param idx index to search at.
	 * @param lower lowest valid index.
	 * @param upper highest valid index.
	 * @return internal node corrsponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx
					+ "; size: " + size());

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Removes an item from this collection.
	 * @param idx the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Removes the object contained in Node p.
	 * @param p the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;

		return p.data;
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");

		for (AnyType x : this)
			sb.append(x + " ");
		sb.append("]");

		return new String(sb);
	}

	/**
	 * Adds an item to the beginning of the LinkedList. 
	 * O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void addFirst(AnyType x) { // O(1)
		add(0, x);
	}// End addFirst

	/**
	 * Adds an item to the end of the LinkedList. 
	 * O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void addLast(AnyType x) { // O(1)
		add(x);
	}// End addLast

	/**
	 * Removes the item at the beginning of the LinkedList. 
	 * O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void removeFirst() { // O(1)
		remove(0);
	}// End removeFirst

	/**
	 * Removes the item at the end of the LinkedList. 
	 * O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void removeLast() { // O(1)
		remove(theSize-1);
	}// End removeLast

	/**
	 * Returns the item at the beginning of the LinkedList. 
	 * O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public AnyType getFirst() { // O(1)
		return get(0);
	}// End getFrist

	/**
	 * Returns the item at the end of the LinkedList. 
	 * O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public AnyType getLast() { // O(1)
		return get(theSize - 1);
	}// End getLast

	/**
	 * Returns true if a given item is in the LinkedList.
	 * O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	
	private int index; // Used to keep track of the index of an item if it was found by the contains method. Implemented in the removeAll method. 
	
	public boolean contains(AnyType x) { // O(n)
		boolean returnValue = false;
		for (int i = 0; i < theSize; i++) {
			if (x.equals(get(i))) {
				returnValue = true;
				index = i;
			}// End If
		}// End For
		return returnValue;
	}// End contains 

	/**
	 * Removes all given elements from the LinkedList.
	 * O(n^2)
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void removeAll(Iterable<? extends AnyType> items){
		for (AnyType element : items) {
			if(contains(element)){
				remove(index);
			}
		}
	}// End removeAll
	
	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a
	 * notion of a current position and of course the implicit reference to the
	 * MyLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<AnyType> {
		private Node<AnyType> current = beginMarker.next;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public AnyType next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (!okToRemove)
				throw new IllegalStateException();

			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
		}
	}

	/**
	 * This is the doubly-linked list node.
	 */
	private static class Node<AnyType> {
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			data = d;
			prev = p;
			next = n;
		}

		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
	}

	private int theSize;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;
}
