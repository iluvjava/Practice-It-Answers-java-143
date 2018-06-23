// Simple class that includes methods to construct a random tree of ints, to
// print the structure, and to print the data using a preorder, inorder or
// postorder traversal.

package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class IntTree implements Testable{
    private IntTreeNode overallRoot;

    public IntTree() {
        this((IntTreeNode) null);
    }

    // pre : height >= 0
    // post: constructs a perfect binary tree of given height with random
    //       data values between 0 and 99 inclusive
    public IntTree(IntTreeNode overallRoot) {
        this(overallRoot, true);
    }

   
    
    /**
     * 
     *  post: constructs a binary tree using the given root;
     *  if copy is true, makes a deep copy of all of its nodes
     * @param overallRoot
     * The root (node)given. 
     * @param copy
     * boolean; whether should copy all the sub nodes of the given nodes. 
     */
    public IntTree(IntTreeNode overallRoot, boolean copy) 
    {
        if (copy) 
        {
            this.overallRoot = deepCopy(overallRoot);
        }
        else 
        {
            // just use this one
            this.overallRoot = overallRoot;
        }
    }

    /*
	 *  pre : height >= 0
	 *  post: constructs a perfect binary tree of given height with random    
	 *   data values between 0 and 99 inclusive
	 */
	public IntTree(int height) {
	    if (height < 0) {
	        throw new IllegalArgumentException();
	    }
	    overallRoot = randomTree(height);
	}

	/**
	 * Pre: a string representation of the tree,
	 * <br>
	 * post: a tree that is represented by the string. 
	 * Construct a new tree from the string representation of the tree. 
	 * @param s
	 */
	public IntTree(String s) {
	    overallRoot = fromString(new StringBuilder(s.toLowerCase().trim()));
	}

	private IntTreeNode deepCopy(IntTreeNode root)
    {
        if (root == null)
        {
            return null;
        }
        else
        {
            return new IntTreeNode(root.data, deepCopy(root.left), deepCopy(root.right));
        }
    }

    /*
     *  pre : height >= 0
     *  post: constructs a perfect binary tree of given height with random    
     *   data values between 0 and 99 inclusive
     */
   
    private IntTreeNode randomTree(int h) {
        return randomTree(h, true);
    }

    
    /**
     * pre : height >= 0
     * post: constructs and returns a reference to a perfect binary tree
     * of height h with random data values between 0 and 99 inclusive
     * @param h
     * @param perfect
     * @return
     */
    private IntTreeNode randomTree(int h, boolean perfect) {
        if (h == 0) {
            return null;
        } else {
            int n = (int) (Math.random() * 100);
            IntTreeNode node = new IntTreeNode(n);
            if (perfect || Math.random() >= 0.75) {
                node.left = randomTree(h - 1);
            }
            if (perfect || Math.random() >= 0.75) {
                node.right = randomTree(h - 1);
            }
            return node;
        }
    }

    
    public boolean equals(Object o) {
        IntTreeNode.clearCycleData();
        if (o instanceof IntTree) {
            IntTree other = (IntTree) o;
            return this.overallRoot == other.overallRoot || equals(this.overallRoot, other.overallRoot);
        } else {
            return false;
        }
    }

    private boolean equals(IntTreeNode root1, IntTreeNode root2) {
        if (root1 == null || root2 == null) 
        {
            return root1 == root2;
        } else {
            return root1.data == root2.data
                    && (root1.left == root2.left || equals(root1.__gotoLeft(),  root2.__gotoLeft()))
                    && (root1.right == root2.right || equals(root1.__gotoRight(), root2.__gotoRight()));
        }
    }

    // post: prints the data fields of the tree using a preorder traversal
    public void printPreOrder() {
        IntTreeNode.clearCycleData();
        System.out.print("preorder:");
        printPreOrder(overallRoot);
        System.out.println();
    }

    // post: prints in preorder the data fields of the tree with given root
    private void printPreOrder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreOrder(root.__gotoLeft());
            printPreOrder(root.__gotoRight());
        }
    }

    // post: prints the data fields of the tree using an inorder traversal
    public void printInOrder() {
        IntTreeNode.clearCycleData();
        System.out.print("inorder:");
        printInOrder(overallRoot);
        System.out.println();
    }

    // post: prints in inorder the data fields of the tree with given root
    private void printInOrder(IntTreeNode root) {
        if (root != null) {
            printInOrder(root.__gotoLeft());
            System.out.print(" " + root.data);
            printInOrder(root.__gotoRight());
        }
    }

    // post: prints the data fields of the tree using a postorder traversal
    public void printPostOrder() {
        IntTreeNode.clearCycleData();
        System.out.print("postorder:");
        printPostOrder(overallRoot);
        System.out.println();
    }

    // post: prints in postorder the data fields of the tree with given root
    private void printPostOrder(IntTreeNode root) {
        if (root != null) {
            printPostOrder(root.__gotoLeft());
            printPostOrder(root.__gotoRight());
            System.out.print(" " + root.data);
        }
    }

    // post: prints the data fields of the tree, one per line, following
    //       an inorder traversal and using indentation to indicate node depth;
    //       prints right to left so that it looks correct when the output is
    //       rotated.
    public void printStructure() {
        IntTreeNode.clearCycleData();
        printStructure(overallRoot, 0);
    }

    // post: prints in preorder the data fields of the given tree indenting
    //       each line to the given level
    private void printStructure(IntTreeNode root, int level) {
        if (root != null) {
            printStructure(root.__gotoRight(), level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.data);
            printStructure(root.__gotoLeft(), level + 1);
        }
    }

    public IntTreeNode getRoot() {
        return overallRoot;
    }

    public void setRoot(IntTreeNode root) {
        overallRoot = root;
    }

    public String toString()
    {
        int size   = __getSize();
        int height = __getHeight();
        int widest = __getWidest() + 1;

		IntTreeNode.clearCycleData(20);
        String result = toString(overallRoot, 0, 0, size, height, widest);

        if (overallRoot == null) {
            result = "overallRoot\n   null";
        } else {
            String firstLine = "";
            int nodesLeft = __getSize(overallRoot.left);
            if (overallRoot.left != null && (overallRoot.cycle() || overallRoot.left.cycle())) {
				// nodesLeft = 0;
			}
            int spaces = (nodesLeft * widest) - Math.max(0, "overallRoot".length() - widest + 1) / 2;
            for (int i = 0; i < spaces; i++) {
                firstLine += " ";
            }
            firstLine += "overallRoot\n";

            int len = result.length();
            while (len < firstLine.length()) {
                result = " " + result;
                len += 2;
            }

            result = firstLine + result;
        }

        return result;
    }

    private String toString(IntTreeNode root, int sizeAboveLeft, int level,
            int size, int height, int width) {
        // System.out.println("toString(root, " + sizeAboveLeft + ", " + level + ", " + size + ", " + height + ", " + width + ")");
        if (root == null) {
            return "";
        } else {
            String result = "";
            int sizeBelowLeft = __getSize(root.left);
            if (root.cycle()) {
				// sizeBelowLeft = 0;
			}
            int sizeLeft = sizeAboveLeft + sizeBelowLeft;

            // create line for this element
            // (must potentially put leading __ marks for left/right pointers)
            String thisElementLine = "";
            String nextLine = "";

            if (root.left == null) {
                // indent this node
                for (int i = 0; i < width * sizeAboveLeft; i++) {
                    thisElementLine += " ";
                    if (root.right != null) {
                        nextLine += " ";
                    }
                }
            } else {
                // indent this node, but insert / and _____ for left pointer
                int widthOfLeft = root.left.toString().length();
                int dWidthLeft = width - widthOfLeft;

                int betweenLeft = __getSizeBetweenLeft(root);
                if (root.cycle()) {
					// betweenLeft = 0;
				}
                for (int i = 0; i < width * (sizeLeft - betweenLeft) - dWidthLeft; i++) {
                    thisElementLine += " ";
                    nextLine += " ";
                }
                thisElementLine += " ";
                nextLine += "/";
                for (int i = 0; i < width * betweenLeft - 1 + dWidthLeft; i++) {
                    thisElementLine += "_";
                    if (root.right != null) {
                        nextLine += " ";
                    }
                }
            }

            thisElementLine += root;

            if (root.right != null) {
                // insert _____ and \ for right pointer
                for (int i = 0; i < root.toString().length(); i++) 
                {
                    nextLine += " ";
                }

                for (int i = 0; i < width - root.toString().length() - 1; i++)
                {
                    thisElementLine += "_";
                    nextLine += " ";
                }

                int betweenRight = root.cycle() ? 0 : __getSizeBetweenRight(root);
                for (int i = 0; i < width * betweenRight; i++) 
                {
                    thisElementLine += "_";
                    nextLine += " ";
                }
                nextLine += "\\";
            }

            if (root.left == null && root.right == null) {
                result += thisElementLine;
            } else {
                thisElementLine += "\n";
                nextLine += "\n";

                // append all left elements
                String leftLines = root.cycle() ? "" : toString(root.__gotoLeft(), sizeAboveLeft, level + 1, size, height, width);

                // append all right elements
                String rightLines = root.cycle() ? "" : toString(root.__gotoRight(), sizeLeft + 1, level + 1, size, height, width);

                result += thisElementLine + nextLine + mergeLines(leftLines, rightLines);
            }

            return result;
        }
    }

    private String mergeLines(String left, String right) {
        String[] leftLines  = left.split("\n");
        String[] rightLines = right.split("\n");

        String[] resultLines = new String[Math.max(leftLines.length, rightLines.length)];
        for (int i = 0; i < resultLines.length; i++) {
            if (i >= rightLines.length) {
                resultLines[i] = leftLines[i];
            } else if (i >= leftLines.length) {
                resultLines[i] = rightLines[i];
            } else {
                resultLines[i] = leftLines[i];
                if (rightLines[i].length() > leftLines[i].length()) {
                    resultLines[i] += rightLines[i].substring(leftLines[i].length());
                }
            }
        }

        String result = "";
        for (String line : resultLines) {
            if (result.length() > 0) {
                result += "\n";
            }
            result += line;
        }
        return result;
    }

    private int __getSizeBetweenLeft(IntTreeNode root) {
        if (root == null || root.left == null) {
            return 0;
        } else {
            return __getSize(root.__gotoLeft().__gotoRight());
        }
    }

    private int __getSizeBetweenRight(IntTreeNode root) {
        if (root == null || root.right == null) {
            return 0;
        } else {
            return __getSize(root.__gotoRight().__gotoLeft());
        }
    }

    private int __getHeight() {
		IntTreeNode.clearCycleData();
		return __getHeight(overallRoot);
	}

    private int __getHeight(IntTreeNode root) {
        if (root == null) {
            return 0;
        } else {
			int leftHeight = 0;
			IntTreeNode left = root.__gotoLeft();
			if (left != null && !left.cycle()) {
				leftHeight = __getHeight(left);
			}
			int rightHeight = 0;
			IntTreeNode right = root.__gotoRight();
			if (right != null && !right.cycle()) {
				rightHeight = __getHeight(right);
			}
            return (root.cycle() ? 0 : 1) + Math.max(leftHeight, rightHeight);
        }
    }

    private int __getSize() {
		IntTreeNode.clearCycleData();
		return __getSize(overallRoot);
	}

    private int __getSize(IntTreeNode root) {
        if (root == null) {
            return 0;
        } else {
			int leftSize = 0;
			IntTreeNode left = root.__gotoLeft();
			if (left != null && !left.cycle()) {
				leftSize = __getSize(left);
			}
			int rightSize = 0;
			IntTreeNode right = root.__gotoRight();
			if (right != null && !right.cycle()) {
				rightSize = __getSize(right);
			}
            return (root.cycle() ? 0 : 1) + leftSize + rightSize;
        }
    }

    private int __getWidest() {
		IntTreeNode.clearCycleData();
		return __getWidest(overallRoot);
	}

    private int __getWidest(IntTreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int width = root.toString().length();
			int widestLeft = 0;
			IntTreeNode left = root.__gotoLeft();
			if (left != null && !left.cycle()) {
				widestLeft = __getWidest(left);
			}
			int widestRight = 0;
			IntTreeNode right = root.__gotoRight();
			if (right != null && !right.cycle()) {
				widestRight = __getWidest(right);
			}
            return Math.max(width, Math.max(widestLeft, widestRight));
        }
    }


    public String toBracketedString() {
        return toBracketedString(overallRoot);
    }

    private String toBracketedString(IntTreeNode root) {
        if (root == null) {
            return "null";
        } else if (root.left == null && root.right == null) {
            return "[" + root.data + "]";
        } else {
            return "[" + root.data + " " + toBracketedString(root.left) + " " + toBracketedString(root.right) + "]";
        }
    }

    // for testing
    public static void main(String[] args) {
        IntTree tree = new IntTree("[19 [47 [23] [-2 [55]]] [63 null [94 null [28]]]]");
        System.out.println(tree);
        System.out.println();

        tree = new IntTree("[12]");
        System.out.println(tree);
    }

    // "[19 [47 [23] [-2 [55]]] [63 null [94 null [28]]]]"
    private IntTreeNode fromString(StringBuilder s) {
        String next = nextToken(s);
        if (next.length() == 0 || next.equals("null") || next.equals("/")) {
            return null;
        } else {
            next = next.substring(1, next.length() - 1).trim();  // remove [] from ends
            StringBuilder nextBuilder = new StringBuilder(next);
            String rootStr  = nextToken(nextBuilder);
            int data = Integer.parseInt(rootStr);
            String leftStr  = nextToken(nextBuilder);
            String rightStr = nextToken(nextBuilder);
            return new IntTreeNode(data,
                                   fromString(new StringBuilder(leftStr)),
                                   fromString(new StringBuilder(rightStr)));
        }
    }

    // returns string representation of next complete node or data value from given text
    // (e.g. if you pass "[42 null [23]] [25]" it will return "[42 null [23]]")
    // (e.g. if you pass "null [23]" it will return "null")
    private String nextToken(StringBuilder s) {
        // trim leading whitespace
        while (s.indexOf(" ") == 0) {
            s.deleteCharAt(0);
        }
        if (s.length() == 0) {
            return "";
        }

        int i = 0;
        if (s.charAt(0) == '[' || s.charAt(0) == '(') {
            // scan ahead looking for matching ] bracket (respects nesting)
            int lbracketCount = 0;
            do {
                if (s.charAt(i) == '[' || s.charAt(i) == '(') {
                    lbracketCount++;
                } else if (s.charAt(i) == ']' || s.charAt(i) == ')') {
                    lbracketCount--;
                }
                i++;
            } while (i < s.length() && lbracketCount > 0);
            if (lbracketCount > 0) {
                throw new IllegalArgumentException("no ] bracket seen for [");
            }
        } else {
            // scan ahead looking for space or end of string
            while (i < s.length() && s.charAt(i) != ' ') {
                i++;
            }
        }

        String result = s.substring(0, i).trim();
        s.delete(0, i);   // consume characters from buffer
        while (s.indexOf(" ") == 0) {
            s.deleteCharAt(0);
        }

        return result;
    }

   
    // IntTreeNode is a class for storing a single node of a binary tree of ints.
    // It has just data fields and constructors.
    public static class IntTreeNode {
      
    	private static final java.util.List<IntTreeNode> ALL_NODES = new java.util.ArrayList<IntTreeNode>();

        public static void clearCycleData() {
			clearCycleData(5);
		}

        /**
         * For all the nodes that has been created, we set the 
         * number visits allowed to whatever the input is. 
         * @param visitsAllowed
         */
		public static void clearCycleData(int visitsAllowed) {
            for (IntTreeNode node : ALL_NODES) {
                node.visitsLeft = visitsAllowed;
            }
        }

        public int data;            // data stored at this node
        public IntTreeNode left;    // reference to left subtree
        public IntTreeNode right;   // reference to right subtree
        public int visitsLeft;

        /**
         *  Constructs a leaf node with given data.
         */
        public IntTreeNode(int data) {
            this(data, null, null);
        }

        /**
         * Constructs a IntTreeNode with the given data and links.
         * @param data
         * @param left
         * @param right
         */
        public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) 
        {
            ALL_NODES.add(this);
            this.data = data;
            this.left = left;
            this.right = right;
            this.visitsLeft = 5;
        }

        public IntTreeNode __gotoLeft()
        {
            return __gotoLeft(true);
        }

        /**
         * 
         * @param checkForCycle
         * Boolean that tells whether should check for cyccle in the tree. 
         * @return
         */
        public IntTreeNode __gotoLeft(boolean checkForCycle) {
            if (checkForCycle) {
                if (left != null) {
					if (left.visitsLeft > 0) {
						left.visitsLeft--;
					}
                    if (left.cycle()) {
                        // throw new IllegalStateException("cycle detected in tree");
                    }
                }
            }
            return left;
        }

        public IntTreeNode __gotoRight() {
            return __gotoRight(true);
        }

        public IntTreeNode __gotoRight(boolean checkForCycle) 
        {
            if (checkForCycle) {
                if (right != null) {
                    if (right.visitsLeft > 0) {
                        right.visitsLeft--;
                    }
                    if (right.cycle()) {
                        // throw new IllegalStateException("cycle detected in tree");
					}
                }
            }
            return right;
        }

        public String toString() {
            if (this.cycle()) {
				return "(cycle!)";
			} else {
				return String.format("[%d]", data);
			}
        }

        
        /**
         * <pre>visitsLeft == 0;</pre>
         * @return
         */
        private boolean cycle() {
			return visitsLeft == 0;
		}
    }

// YOUR CODE GOES HERE
    
    
    
    /**
     * A method that will sum up all the int in the 
     * tree. 
     * <br>
     * This method is the solution to a problem on practice it: <br>
     * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/sum">
     * See here</a>
     * @return
     */
    public int sum()
    {
        if(this.overallRoot == null)return 0 ;
        return __sum(this.overallRoot);
    }
    
    private static int __sum(IntTreeNode node)
    {
    	boolean left = node.left!=null, right = node.right!=null; 
        // the case when it is a leaf node
        if(!left&&!right)
            return node.data;
        if(left^right)
            return node.data+((left)?__sum(node.left):__sum(node.right));
            return __sum(node.left)+__sum(node.right)+ node.data;
    }

    
	@Override
	public boolean test() 
	{
		return false;
	}
	
	/**
	 * This method counts the number of left nodes in the tree. 
	 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/countLeftNodes">
	 * see here</a>
	 * @param tree
	 * @return
	 * The number of left nodes in the tree. 
	 */
	public int countLeftNodes()
	{
		if(this.overallRoot==null)return 0;
		return __countLeftNodes(this.overallRoot) ;
	}
	
	private static int __countLeftNodes(IntTreeNode n)
	{
		// look around and see how can I recurse...
		boolean hasleftnode = n.left!=null,hasrightnode = n.right!=null;
		int count =0; 
		
		// If i am currently inside a leaf node. 
		if(!hasleftnode&&!hasrightnode)
		{
			return count;
		}
		if(hasleftnode)count++;
		if(hasleftnode) count+=__countLeftNodes(n.left);
		if(hasrightnode)count+= __countLeftNodes(n.right);
		return count;
	}
	
	
	/**
	 * This method is part of the solution to the problem on practic it.<br>
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/depthSum">
	 * Click here</a>
	 * <br>
	 * This method will be able to sum elements in the tree and weighted them with different weights. 
	 * 
	 */
	public int depthSum()
	{
		if(this.overallRoot==null)return 0; 
		return __depthSum(this.overallRoot, 0); 
	}
	
	
	/**
	 * Helper method for depthsum. 
	 * @param n
	 * @return
	 */
	private static int __depthSum(IntTreeNode n, int depth)
	{
		// look around. 
		boolean left = n.left!=null, right = n.right!=null; 
		int result = n.data*depth;
		if(left) result+= __depthSum(n.left, depth+1);
		if(right)result+=__depthSum(n.right, depth+1);
		return result;
		
	}
	
	
	/**
	 * This method is part of the solution to problem on practice it
	 * <br>
	 * <a href ="https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/doublePositives">
	 * click here</a>
	 * <br>
	 * THis method will doubles all the non negative number in the tree. 
	 */
	public void doublePositives()
	{
		if(this.overallRoot==null)return; 
		__doublePositives(this.overallRoot);
	}
	
	private void __doublePositives(IntTreeNode n)
	{
		boolean left =n.left!=null, right = n.right!=null; 
		if(n.data>0)n.data*=2;
		if(left)__doublePositives( n.left);
		if(right)__doublePositives(n.right);
	}

	
	/**
	 * This method is part of the solution to the problem on practice it
	 * <br>
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/numEmpty">click here</a>
	 * <br>
	 * @return
	 * This method return the number of empty branches in the tree. 
	 */
	public int 	numEmpty()
	{
		if(this.overallRoot==null)return 1; 
		return __numEmpty(this.overallRoot); 
	}
	
	public static int __numEmpty(IntTreeNode n)
	{	
		boolean left =n.left!=null, right = n.right!=null; 
		int result = 0; 
		return (left?__numEmpty(n.left):1)+(right?__numEmpty(n.right):1); 
	}
	
	/**
	 * This method is part of the solution to the problem on practice it
	 * <br>
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/height">click here</a>
	 * <br>
	 * @return
	 * This method return longest path from root to leaf. 
	 */
	public int height()
	{
		if(this.overallRoot==null)return 0; 
		return __height( this.overallRoot ); 
	}
	private static int __height(IntTreeNode n )
	{
		boolean left =n.left!=null, right = n.right!=null; 
		int result = 1; 
		if(left)result += __height( n.left );
		if(right)result =Math.max(  __height(n.right)+1,result);
		return result;
	}
	
	
	/**
	 * This method is part of the solution to the problem on practice it
	 * <br>
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/printLevel">click here</a>
	 * <br>
	 * @return
	 * This method return longest path from root to leaf. 
	 */
	public void printLevel(int level)
	{
		if(level<1)throw new IllegalArgumentException();
		if(this.overallRoot==null)return;
		 __printLevel( this.overallRoot, 1, level);
	}
	
	private static void __printLevel(IntTreeNode n, int currentlevel, int tartgetlevel)
	{
		if(currentlevel==tartgetlevel)System.out.println(n.data);
		boolean left =n.left!=null, right = n.right!=null; 
		if(left)__printLevel( n.left, currentlevel+1,  tartgetlevel);
		if(right)__printLevel( n.right, currentlevel+1,  tartgetlevel);
	}
	
	
	/**
	 * This method is part of the solution to the problem on practice it: 
	 * <br>
	 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/printLeaves">click here</a>
	 * 
	 * This method print out the leaves of the tree from right to left. 
	 */
	public void printLeaves()
	{
		if(this.overallRoot==null)
		{System.out.println("no leaves");return;}
		
		System.out.print("leaves: ");
		__printLeaves(this.overallRoot);
	}
	
	private static void __printLeaves(IntTreeNode n)
	{
		boolean left =n.left!=null, right = n.right!=null; 
		// see if the current node is a leave node. 
		if(!left&&!right)System.out.print(n.data+" ");
		if(right)__printLeaves(n.right);
		if(left)__printLeaves(n.left);
	}
	
	/**
	 * This method is part of the solution to the problem on practice it: 
	 * <br>
	 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/evenBranches">click here</a>
	 * <br>
	 * This method print out all the non leaf nodes that has a even number in it. 
	 * 
	 */
	public int evenBranches()
	{
		if(this.overallRoot==null)return 0;
		return __evenBranches( this.overallRoot); 
		
	}
	private static int __evenBranches(IntTreeNode n)
	{
		boolean left =n.left!=null, right = n.right!=null; 
		int result = 0;
		// if the current node i am at is a node with at least one chidren 
		// and it has a even number in it. 
		if((left||right)&&n.data%2==0)result++;
		if(left)result+= __evenBranches(n.left);	
		if(right)result+= __evenBranches(n.right);
		return  result;
	}
	
	
	/**
	 * This method is part of the solution to the problem on practice it: 
	 * <br>
	 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/writeTree">click here</a>
	 * <br>
	 * 
	 * This method will pre_order transverse the tree while doing some pretty fancy stuff. 
	 */
	public void 	writeTree()
	{
		if(this.overallRoot==null)return;
		__writeTree(this.overallRoot);
	}
	
	
	private static void __writeTree(IntTreeNode n)
	{
		boolean left =n.left!=null, right = n.right!=null; 
		int num =0;
		if(left^right)num = left?1:2;
		else num = left?3:0;
		System.out.println(num+" "+n.data);
		if(left)__writeTree(n.left);
		if(right)__writeTree(n.right);
	}
	
	/**
	 * This method is part of the solution to the problem on practice it.<br>
	 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/readTree"></a>
	 * @param sc
	 * a scanner that contains the result from write tree function. 
	 * @return
	 * A tree that is read from the scanner. 
	 */
	public void readTree(Scanner sc)
	{
		// read the data from the sc and convert it to a data structure. 
		Queue<Integer> lst = new LinkedList<>();
		for(String s= new String(); sc.hasNextLine(); )
		{
            s=sc.nextLine();
			String[] parsed = s.split(" ");
			lst.add(Integer.parseInt(parsed[1]));
			lst.add(Integer.parseInt(parsed[0]));
		}
		this.overallRoot=__readTree(new IntTreeNode(lst.poll()),  lst);
	}
	
	
	private static IntTreeNode __readTree(IntTreeNode n, Queue q)
	{
		if(q.isEmpty())return n; 
		int type=(int) q.poll();
		switch(type)
		{
		case 0:
			break;
		case 1:
			n.left= __readTree( new IntTreeNode((int) q.poll()),  q);
			break;
		case 2:
			n.right= __readTree( new IntTreeNode((int) q.poll()),  q);
			break;
		case 3:
			n.left= __readTree( new IntTreeNode((int) q.poll()),  q);
			n.right= __readTree( new IntTreeNode((int) q.poll()),  q);
			break;
		}
		return n;
	}
	
	
	/**
	 * This method is part of solution to a problem on practice it. <br>
	 * <a href=https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/numberNodes">See here</a>
	 * <br>
	 * It will modify the data store in the tree such that its pre_order traverse will 
	 * produce sequential integer from 1 to whatever, while also returning 
	 * how many nodes are in the tree. 
	 */
	public int numberNodes()
	{
		if(this.overallRoot==null)return 0 ; 
		return __NumberNodes(this.overallRoot, 0);
	}
	
	private static int __NumberNodes(IntTreeNode n,int count)
	{
		count++;
		n.data= count;boolean left =n.left!=null, right = n.right!=null; 
		if(left)count=__NumberNodes(n.left, count);
		if(right)count=__NumberNodes(n.right,count);
		return count;
	}
	
	/**
	 * This method will eliminate branch nodes, it is part of the solution to the problem on practice it.
	 * <br>
	 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/tighten">
	 * see here</a>
	 * 
	 */
	public void tighten()
	{
		if(this.overallRoot==null)return;
		this.overallRoot=__tighten(this.overallRoot);
	}
	
	private static IntTreeNode __tighten( IntTreeNode n)
	{
		// look around. 
		boolean left =n.left!=null, right = n.right!=null; 
		// if this is a branch node 
		if(left^right)
		{
			if(left)return __tighten(n.left);
			return __tighten(n.right);
		}
		// else if not a leaf 
		else if(left&&right)
		{
			n.left=__tighten(n.left);
			n.right=__tighten(n.right);
		}
		// a leaf
		return n;
	}
	
	
	/**
	 * This method will try to fill up a certain leve of the tree with integer -1, it is part of 
	 * the solution to
	 *  <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/completeToLevel">
	 *   this problem</a> on practice it. 
	 */
	public void completeToLevel(int level)
	{
		if(level<1)throw new IllegalArgumentException();
		if(this.overallRoot==null)this.overallRoot=new IntTreeNode(-1);
		__completeToLevel(this.overallRoot,level);
	}
	private static void __completeToLevel(IntTreeNode n, int atlevel)
	{
		boolean left =n.left!=null, right = n.right!=null; 
		if(atlevel>1)
		{
			if(!left) n.left=new IntTreeNode(-1);
			if(!right) n.right=new IntTreeNode(-1);
			__completeToLevel(n.left,atlevel-1 );
			__completeToLevel(n.right ,atlevel-1);
		}
	}
	
	
	/**
	 * This method will contruct a new tree out from the given integer of, 
	 * it is part of the solution to <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/construct">
	 * this</a> problem on practice it. 
	 * <br>
	 * The challenge is that the in-order traversal of the tree constructed is in sequential order....Thus, the more than one numbers must be passed 
	 * at the same time. Right? 
	 * @param num
	 */
	public void construct(int num)
	{
		if(num<0)throw new IllegalArgumentException();
		this.overallRoot = __construct(0,num);
	}

	private static  IntTreeNode __construct(int already_constructed , int num) 
	{
		IntTreeNode cons = new IntTreeNode(already_constructed+(num -1)/2);
		// base cases; 
		if(num ==1)return cons;
		if(num ==0)return null;
		// prepare for the parameter. 
		int construct_left = (num-1)/2, construct_right=construct_left+(num-1)%2;
		cons.left =  __construct(already_constructed ,construct_left ) ;
		cons.right =  __construct(already_constructed+construct_left +1 ,construct_right ) ;
		return cons;
	}
	
	
	/**
	 * This method removes nodes from the tree, such that 
	 * the sum of number from any node to any other node will be within the defined limit. 
	 * This method is part of the solution to <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/binarytrees/limitPathSum">
	 * this</a> problem on practice it. 
	 */
	public void limitPathSum(int limit)
	{
		if(this.overallRoot==null)return;
		if(this.overallRoot.data>limit)
		{
			this.overallRoot=null;return;
		}
		__limitPathSum(this.overallRoot,0,limit);
	}
	
	private static IntTreeNode __limitPathSum(IntTreeNode n, int sum, int limit)
	{
		sum+=n.data;
		boolean left =n.left!=null, right = n.right!=null; 
		if(sum>limit)
		return  null;
		if(left)n.left =  __limitPathSum(n.left, sum,limit);
		if(right)n.right =  __limitPathSum(n.right, sum,limit);
		return n;
	}
	
	
	
}