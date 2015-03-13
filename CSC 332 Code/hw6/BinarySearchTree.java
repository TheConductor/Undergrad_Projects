// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insrt x
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
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */ 
  public class BinarySearchTree < AnyType extends Comparable <
  ? super AnyType >> 
{
  int size (BinaryNode < AnyType > node)
  {
    if (node == null)
      return 0;
    int left = size (node.left);
    int right = size (node.right);
    return 1 + left + right;
  }
  int size ()
  {
    return size (root);
  }
  
    /**
     * Construct the tree.
     */ 
    public BinarySearchTree () 
  {
    root = null;
  }
 
    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */ 
  public void insert (AnyType x) 
  {
    root = insert (x, root);
  } 
    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */ 
  public void remove (AnyType x) 
  {
    root = remove (x, root);
  } 
    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */ 
    public AnyType findMin () 
  {
    if (isEmpty ())
      throw new UnderflowException ();
    return findMin (root).element;
  }
  
    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */ 
    public AnyType findMax () 
  {
    if (isEmpty ())
      throw new UnderflowException ();
    return findMax (root).element;
  }
  
    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if found.
     */ 
    public boolean contains (AnyType x) 
  {
    return contains (x, root);
  }
  
    /**
     * Make the tree logically empty.
     */ 
  public void makeEmpty () 
  {
    root = null;
  } 
    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */ 
    public boolean isEmpty () 
  {
    return root == null;
  }
  
    /**
     * Print the tree contents in sorted order.
     */ 
  public void printTree () 
  {
    if (isEmpty ())
      System.out.println ("Empty tree");
    
    else
      printTree (root);
  }
  
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */ 
    private BinaryNode < AnyType > insert (AnyType x,
					   BinaryNode < AnyType > t) 
  {
    if (t == null)
      return new BinaryNode < AnyType > (x, null, null);
    int compareResult = x.compareTo (t.element);
    if (compareResult < 0)
      t.left = insert (x, t.left);
    
    else if (compareResult > 0)
      t.right = insert (x, t.right);
    
    else
      ;			// Duplicate; do nothing
    return t;
  }
  
    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */ 
    private BinaryNode < AnyType > remove (AnyType x,
					   BinaryNode < AnyType > t) 
  {
    if (t == null)
      return t;		// Item not found; do nothing
    int compareResult = x.compareTo (t.element);
    if (compareResult < 0)
      t.left = remove (x, t.left);
    
    else if (compareResult > 0)
      t.right = remove (x, t.right);
    
    else if (t.left != null && t.right != null)	// Two children
      {
	t.element = findMin (t.right).element;
	t.right = remove (t.element, t.right);
      }
    
    else
      t = (t.left != null) ? t.left : t.right;
    return t;
  }
  
    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */ 
    private BinaryNode < AnyType > findMin (BinaryNode < AnyType > t) 
  {
    if (t == null)
      return null;
    
    else if (t.left == null)
      return t;
    return findMin (t.left);
  }
  
    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */ 
    private BinaryNode < AnyType > findMax (BinaryNode < AnyType > t) 
  {
    if (t != null)
      while (t.right != null)
	t = t.right;
    return t;
  }
  
    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */ 
    private boolean contains (AnyType x, BinaryNode < AnyType > t) 
  {
    if (t == null)
      return false;
    int compareResult = x.compareTo (t.element);
    if (compareResult < 0)
      return contains (x, t.left);
    
    else if (compareResult > 0)
      return contains (x, t.right);
    
    else
      return true;		// Match
  }
  
    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */ 
  private void printTree (BinaryNode < AnyType > t) 
  {
    if (t != null)
      
      {
	printTree (t.left);
	System.out.println (t.element);
	printTree (t.right);
      }
  }
  
    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */ 
  private int height (BinaryNode < AnyType > t) 
  {
    if (t == null)
      return -1;
    
    else
      return 1 + Math.max (height (t.left), height (t.right));
  }
  
    // Basic node stored in unbalanced binary search trees
  private static class BinaryNode < AnyType > 
  {
    
      // Constructors
    BinaryNode (AnyType theElement) 
    {
      this (theElement, null, null);
    } BinaryNode (AnyType theElement, BinaryNode < AnyType > lt,
		     BinaryNode < AnyType > rt) 
    {
      element = theElement;
      left = lt;
      right = rt;
    } AnyType element;	// The data in the node
    BinaryNode < AnyType > left;	// Left child
    BinaryNode < AnyType > right;	// Right child
  } 
      /** The tree root. */ 
    private BinaryNode < AnyType > root;
  
    // Test program
  public static void main (String[]args) 
  {
    int NUM = 20;
    int N = 1024;
    for (N = 2; N < 10020; N *= 1.5) 
      {
	System.out.print (N + "\t");
	
	  // create a NUM tree with N nodes
	  java.util.Random gen = new java.util.Random ();
	int sumHeight = 0;
	int sumHeightAfterDeletes = 0;
	for (int experiment = 0; experiment < NUM; experiment++)
	  {
	    BinarySearchTree < Integer > t =
	      new BinarySearchTree < Integer > ();
	    for (int i = 0; i < N; i++)
	      {
		int number = gen.nextInt (2 * N);
		while (t.contains (number))
		  number = gen.nextInt (2 * N);
		t.insert (number);
	      }
	    
	      //System.out.println("the size of t is " + t.size());
	      //System.out.println("the height of t is " + t.height(t.root));
	      sumHeight += t.height (t.root);
	    
	      // do N^2 insert/delete
	      for (int count = 0; count < N * N; count++)
	      {
		
		  // do an insert, number must NOT be in tree
		int insertNum = gen.nextInt (2 * N);
		while (t.contains (insertNum))
		  insertNum = gen.nextInt (2 * N);
		t.insert (insertNum);
		
		  // do a delete, number must be in tree
		int deleteNum = gen.nextInt (2 * N);
		while (!t.contains (deleteNum))
		  deleteNum = gen.nextInt (2 * N);
		t.remove (deleteNum);
	      }
	    
	      //      System.out.println("The size after insert/delete is " +
	      //                      t.size());
	      //      System.out.println("The height after insert/delete is " +
	      //                      t.height(t.root));
	      sumHeightAfterDeletes += t.height (t.root);
	  }
	
	  //System.out.println("The average height is " + (double)
	  //      sumHeight/NUM);
	  //System.out.println("The average height (after) is " + (double)
	  //      sumHeightAfterDeletes/NUM);
	  System.out.print ((double) sumHeight / NUM + "\t");
	System.out.print ((double) sumHeightAfterDeletes / NUM + "\t");
	System.out.println ();
} } } 
