// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 * 
 * @author Mark Allen Weiss edited by Alex Aziz
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	/** The tree root. */
	private BinaryNode<AnyType> root;

	int size(BinaryNode<AnyType> node) {
		if (node == null)
			return 0;
		int left = size(node.left);
		int right = size(node.right);
		return 1 + left + right;
	}

	int size() {
		return size(root);
	}

	// Test program
	public static void main(String[] args) {
		System.out.println("Start");
		int totalHeight=0;
		int totalAvgDepth=0;
		int numberOfTests = 20;
		int incrementer = 1000000/numberOfTests;
		for (int j=450000; j<550000 ; j+= incrementer){
			System.out.println(j);
			int sumHeight = 0;
			int N = j; // Number of Nodes the Tree will have
			java.util.Random gen = new java.util.Random();
			BinarySearchTree<Integer> t = new BinarySearchTree<Integer>(); 
			for (int i = 1; i < N; i++) {
				//System.out.println(i);
				int number = gen.nextInt(10 * N) + 1;
				while (t.contains(number)) {
					number = gen.nextInt(10 * N) + 1;
				}// End while to check if t already contains number
				sumHeight += t.height(t.root);
				t.insert(number);
			}// End For to make tree
			totalHeight += t.height(t.root);
			totalAvgDepth += t.avgDepth(sumHeight);
			//t.printTree();
			t.setHeight(t.height(t.root));
			System.out.println("The height of the tree is " + t.height());
			System.out.println("The avgdepth of the tree is " + t.avgDepth(sumHeight));
			System.out.println("There are " + t.count() + " nodes in tree t");
			System.out.println();
		}
		System.out.println("The avg heights of the trees was "+totalHeight/numberOfTests);
		System.out.println("The avg of the avgDepths of the trees was "+totalAvgDepth/numberOfTests);
	}// End Main

	/**
	 * Used to keep track of the number of nodes in the tree.
	 */
	private int numberOfNodes = 1;

	/**
	 * Returns the total number of nodes in the Tree O(1)
	 * 
	 * @return numberOfNodes
	 */
	public int count() {
		return numberOfNodes;
	}// End Count
	
	private int height = 0;
	public void setHeight(int height) {
		this.height = height ;
	}

	/**
	 * Returns the height of the tree 
	 * O(1)
	 * @return height
	 */
	public int height() {
		return height;
	}// End height

	/**
	 * Returns the average depth of the tree
	 */
	public double avgDepth(int x) {
		double returnValue = x/numberOfNodes;
		return returnValue;
	}// End avgDepth

	/**
	 * Construct the tree.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public void insert(AnyType x) {
		root = insert(x, root);
		numberOfNodes++;
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x
	 *            the item to remove.
	 */
	public void remove(AnyType x) {
		root = remove(x, root);
		numberOfNodes--;
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public AnyType findMin() {
		if (isEmpty())
			throw new UnderflowException();
		return findMin(root).element;
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 */
	public AnyType findMax() {
		if (isEmpty())
			throw new UnderflowException();
		return findMax(root).element;
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return true if found.
	 */
	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");

		else
			printTree(root);
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return new BinaryNode<AnyType>(x, null, null);

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = insert(x, t.left);

		else if (compareResult > 0)
			t.right = insert(x, t.right);

		else
			; // Duplicate; do nothing
		return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 * 
	 * @param x
	 *            the item to remove.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return t; // Item not found; do nothing
		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = remove(x, t.left);

		else if (compareResult > 0)
			t.right = remove(x, t.right);

		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		}

		else
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
		if (t == null)
			return null;

		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
		if (t != null)
			while (t.right != null)
				t = t.right;

		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x
	 *            is item to search for.
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return false;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			return contains(x, t.left);

		else if (compareResult > 0)
			return contains(x, t.right);

		else
			return true; // Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private void printTree(BinaryNode<AnyType> t) {
		if (t != null)

		{

			printTree(t.left);
			System.out.println(t.element+"\t");
			printTree(t.right);

		}

	}

	/**
	 * Internal method to compute height of a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private int height(BinaryNode<AnyType> t) {
		if (t == null)
			return -1;

		else
			return 1 + Math.max(height(t.left), height(t.right));
	}

	// Basic node stored in unbalanced binary search trees
	// TO-DO ADD a height feild to this class
	private static class BinaryNode<AnyType> {
		AnyType element; // The data in the node
		BinaryNode<AnyType> left; // Left child
		BinaryNode<AnyType> right; // Right child
		// Constructors
		BinaryNode(AnyType theElement) {
			this(theElement, null, null);
		}

		BinaryNode(AnyType theElement, BinaryNode<AnyType> lt,
				BinaryNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}

	}

}
