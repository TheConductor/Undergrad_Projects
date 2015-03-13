// AvlTreeRevised class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class AvlTreeRevised<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public AvlTreeRevised( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        //System.out.println( "Sorry, remove unimplemented" );
    	// Guinn added the following
    	root = remove(x, root);
    }

    /*
     * Remove a node with data x from the AVL tree node p
     * @param x The item to remove from the AVL tree
     * @param p The AVL node to start removing x from 
     * @return This methods returns p after it has been modified to have x deleted from its subtree
     * @author Guinn
     * @version 0.1
     */
    private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> p) {

    	if (p == null)
    		return null;
    	
    	int c = x.compareTo(p.element);
    	
    	if (c < 0) {
    		p.left = remove(x,p.left);		
    	}
    	else if (c > 0) {
    		p.right = remove(x,p.right);
    	}
    	else if (p.left == null && p.right == null) { // c == 0 and the node is a leaf
    		return null;
    	}
    	else if (p.left == null) { // c == 0, the left is empty
    		p = p.right;
    	}
    	else if (p.right == null) { // c == 0, the right is empty
    		p =  p.left;
    	}
    	else { // c == 0 and the node has two children
    		// randomly decide whether to favor the right or left side
    		// this eliminates bias if there are N^2 insert/remove pairs
    		int ran = (int) (2*Math.random());
    		if (ran == 0) {
    			p.element = findMin(p.right).element;
    			p.right = remove(p.element,p.right);
     		}
    		else {
    			p.element = findMax(p.left).element;
    			p.left = remove(p.element, p.left);
      		}
    	}
   		// if out of balance rotate
		if(height( p.right ) - height( p.left ) >= 2 ) {
                if( height(p.right.right) >= height(p.right.left))
                    p = rotateWithRightChild( p );
                else 
                	p = doubleWithRightChild( p ); 
        }
  		if(height( p.left ) - height( p.right ) >= 2 ) {
            if( height(p.left.left) >= height(p.left.right))
                p = rotateWithLeftChild( p );
            else 
            	p = doubleWithLeftChild( p ); 
		}   			
  		// calculate the new height of the node
    	p.height = Math.max( height( p.left ), height( p.right ) ) + 1;
    	return p;	
    }
    

    
    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
		if( isEmpty( ) )
			throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
		if( isEmpty( ) )
			throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
	
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t )
    {
        if( t == null )
            return new AvlNode<AnyType>( x, null, null );
		
		int compareResult = x.compareTo( t.element );
		
        if( compareResult < 0 )
        {
            t.left = insert( x, t.left );
            if( height( t.left ) - height( t.right ) == 2 )
                if( x.compareTo( t.left.element ) < 0 )
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
        }
        else if( compareResult > 0 )
        {
            t.right = insert( x, t.right );
            if( height( t.right ) - height( t.left ) == 2 )
                if( x.compareTo( t.right.element ) > 0 )
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
        }
        else
            ;  // Duplicate; do nothing
        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlNode<AnyType> findMin( AvlNode<AnyType> t )
    {
        if( t == null )
            return t;

        while( t.left != null )
            t = t.left;
        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private AvlNode<AnyType> findMax( AvlNode<AnyType> t )
    {
        if( t == null )
            return t;

        while( t.right != null )
            t = t.right;
        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return true if x is found in subtree.
     */
    private boolean contains( AnyType x, AvlNode<AnyType> t )
    {
        while( t != null )
		{
			int compareResult = x.compareTo( t.element );
			
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
		}

        return false;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlNode<AnyType> t )
    {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithLeftChild( AvlNode<AnyType> k2 )
    {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 )
    {
        AvlNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    int count() {
    	return count(root);
    }

    int count(AvlNode<AnyType> a) {
    	if (a == null)
		return 0;
	else return 1 + count(a.left) + count(a.right);
    }


    private static class AvlNode<AnyType>
    {
            // Constructors
        AvlNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        AvlNode( AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }

        AnyType           element;      // The data in the node
        AvlNode<AnyType>  left;         // Left child
        AvlNode<AnyType>  right;        // Right child
        int               height;       // Height
    }

      /** The tree root. */
    private AvlNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        AvlTreeRevised<Integer> t = new AvlTreeRevised<Integer>( );
        
        // Generate a random AVL tree with 100,000 elements
        int size = 100000;
        for (int x = 0; x < size; x++) {
        	int toInsert;
        	do {
        		toInsert = (int) (2*size*Math.random() + 1);
        	} while (t.contains(toInsert));
        	t.insert(toInsert);
	    }
        

        System.out.println("The height of the tree is " + t.height(t.root));

        System.out.println("The height of the left subtree is " +
        		t.height(t.root.left));

        System.out.println("The height of the right subtree is " +
        		t.height(t.root.right));
	
        System.out.println("The number of nodes is " + t.count());
	

        // do a bunch of inserts and deletes
        // to confirm that remove actually works and the tree remains balanced
        for (int j = 0; j < size; j++) {
		int toInsert;
		int toRemove;
		int ran = (int)(3*Math.random());  // Notice this generates 0, 1, 2
		// randomly decide whether to do an insert or a remove.
		// Make removes twice as likely.
		// notice that this code does not guarantee the tree will 
		// stay the same size.  In fact, probablistically, its going
		// to shrink quite a bit
		// For testing remove, this isn't important
		if (ran == 0) {
			do {
				toInsert = (int) (2*size*Math.random() + 1);
			} while (t.contains(toInsert));
			t.insert(toInsert);
		}
		else {
			do {
				toRemove = (int) (2*size*Math.random() + 1);
			} while (!t.contains(toRemove));
			t.remove(toRemove);
		}
	} // end for
	

	System.out.println("The number of nodes is " + t.count());
	System.out.println("The height of the tree is " + t.height(t.root));

	System.out.println("The height of the left subtree is " +
		t.height(t.root.left));

	System.out.println("The height of the right subtree is " +
		t.height(t.root.right));
	

   } // end main
} // end class