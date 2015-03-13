import java.util.ArrayList;

public class MyArrayList<AnyType> implements Iterable<AnyType> {
	public static void main(String[] args) {
		MyArrayList<Integer> lst = new MyArrayList<Integer>();

		for (int i = 0; i < 10; i++)
			lst.addLast(i);
		for (int i = 20; i < 30; i++)
			lst.addFirst(i);

		lst.removeFirst();
		lst.removeLast();
		
		ArrayList<Integer> items = new ArrayList<Integer>();
		items.add(125);
		items.add(5321);
		items.add(231);
		
		lst.addAll(items);

		System.out.println(lst);
		
		System.out.println(lst.getFirst());

		System.out.println(lst.getLast());
		
		System.out.println(lst.contains(5));
		
	}
	/**
	 * Construct an empty ArrayList.
	 */
	public MyArrayList() {
		clear();
	}

	/**
	 * Returns the number of items in this collection.
	 * @return the number of items in this collection.
	 */
	public int size() {
		return theSize;
	}

	/**
	 * Returns true if this collection is empty.
	 * @return true if this collection is empty.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the item at position idx.
	 * @param idx the index to search in.
	 * @throws ArrayIndexOutOfBoundsException if index is out of range.
	 */
	public AnyType get(int idx) {
		if (idx < 0 || idx >= size())
			throw new ArrayIndexOutOfBoundsException("Index " + idx + "; size "
					+ size());
		return theItems[idx];
	}

	/**
	 * Changes the item at position idx.
	 * @param idx the index to change.
	 * @param newVal the new value.
	 * @return the old value.
	 * @throws ArrayIndexOutOfBoundsException if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		if (idx < 0 || idx >= size())
			throw new ArrayIndexOutOfBoundsException("Index " + idx + "; size "
					+ size());
		AnyType old = theItems[idx];
		theItems[idx] = newVal;

		return old;
	}

	@SuppressWarnings("unchecked")
	public void ensureCapacity(int newCapacity) {
		if (newCapacity < theSize)
			return;

		AnyType[] old = theItems;
		theItems = (AnyType[]) new Object[newCapacity];
		for (int i = 0; i < size(); i++)
			theItems[i] = old[i];
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
	 * Adds an item to this collection, at the specified index.
	 * @param x any object.
	 * @return true.
	 */
	public void add(int idx, AnyType x) {
		if (theItems.length == size())
			ensureCapacity(size() * 2 + 1);

		for (int i = theSize; i > idx; i--)
			theItems[i] = theItems[i - 1];

		theItems[idx] = x;
		theSize++;
	}

	/**
	 * Removes an item from this collection.
	 * @param idx the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove(int idx) {
		AnyType removedItem = theItems[idx];

		for (int i = idx; i < size() - 1; i++)
			theItems[i] = theItems[i + 1];
		theSize--;

		return removedItem;
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void clear() {
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<AnyType> iterator() {
		return new ArrayListIterator();
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
	 * Adds an item to the beginning of the ArrayList. O(n).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void addFirst(AnyType x) {
		add(0, x);
	}// End addFirst

	/**
	 * Adds an item to the end of the ArrayList. O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void addLast(AnyType x) {
		add(x);
	}// End addLast

	/**
	 * Removes the item from the beginning of the ArrayList. O(n). 
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void removeFirst() {
		remove(0);
	}// End removeFirst

	/**
	 * Removes the item from the end of the ArrayList. O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void removeLast() {
		remove(theSize);
	}// End removeLast

	/**
	 * Returns the item at the beginning of the ArrayList. O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public AnyType getFirst() {
		AnyType returnValue = get(0);
		return returnValue;
	}// End getFirst

	/**
	 * Returns the item at the end of the ArrayList. O(1).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public AnyType getLast() {
		AnyType returnValue = get(theSize - 1);
		return returnValue;
	}// End getFirst

	/**
	 * Returns true if a given item is in the ArrayList. O(n).
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public boolean contains(AnyType x) {
		boolean returnValue = false;
		for (int i = 0; i < theSize; i++) {
			if (x.equals(get(i))) {
				returnValue = true;
			}// End If
		}// End For
		return returnValue;
	}// End contains

	/**
	 * Adds all given elements to the end of the ArrayList. O(n)
	 * @param x
	 * @author Weiss modified by Alex Aziz
	 */
	public void addAll(Iterable<? extends AnyType> items) {
		for (AnyType element : items) {
			addLast(element);
		}
	}// End addAll

	/**
	 * This is the implementation of the ArrayListIterator. It maintains a
	 * notion of a current position and of course the implicit reference to the
	 * MyArrayList.
	 */
	private class ArrayListIterator implements java.util.Iterator<AnyType> {
		private int current = 0;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current < size();
		}

		public AnyType next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			okToRemove = true;
			return theItems[current++];
		}

		public void remove() {
			if (!okToRemove)
				throw new IllegalStateException();

			MyArrayList.this.remove(--current);
			okToRemove = false;
		}
	}

	private static final int DEFAULT_CAPACITY = 10;

	private AnyType[] theItems;
	private int theSize;

}