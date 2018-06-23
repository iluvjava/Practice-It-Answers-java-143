// A LinkedIntList object can be used to store a list of integers.
package LinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedIntList {
    private ListNode front;   // node holding first value in list (null if empty)
    private String name = "front";   // string to print for front of list
    
    public static void main(String[] args)
    {
    	LinkedIntList lit = new LinkedIntList(-1,-2,-3);
    	System.out.println(lit);
    	System.out.println(lit.isSorted());
    	lit = new LinkedIntList(42);
    	System.out.println(lit.lastIndexOf(42));
    	System.out.println(new LinkedIntList(1).equals2(new LinkedIntList(1,2)));
    	lit = new LinkedIntList(0,1,2,3,4);
    	System.out.println(lit);
    	System.out.println(lit.removeEvens());
    	System.out.println(lit);
    }
    
    // Constructs an empty list.
    public LinkedIntList() {
        front = null;
    }
    
    // Constructs a list containing the given elements.
    // For quick initialization via Practice-It test cases.
    public LinkedIntList(int... elements) {
        this("front", elements);
    }
    
    public LinkedIntList(String name, int... elements) {
        this.name = name;
        if (elements.length > 0) {
            front = new ListNode(elements[0]);
            ListNode current = front;
            for (int i = 1; i < elements.length; i++) {
                current.next = new ListNode(elements[i]);
                current = current.next;
            }
        }
    }
    
    // Constructs a list containing the given front node.
    // For quick initialization via Practice-It ListNode test cases.
    private LinkedIntList(String name, ListNode front) {
        this.name  = name;
        this.front = front;
    }
    
    // Appends the given value to the end of the list.
    public void add(int value) {
        if (front == null) {
            front = new ListNode(value, front);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            } 
            current.next = new ListNode(value);
        }
    }
    
    // Inserts the given value at the given index in the list.
    // Precondition: 0 <= index <= size
    public void add(int index, int value) {
        if (index == 0) {
            front = new ListNode(value, front);
        } else {
            ListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            } 
            current.next = new ListNode(value, current.next);
        }
    }
    
    public boolean equals(Object o) {
        if (o instanceof LinkedIntList) {
            LinkedIntList other = (LinkedIntList) o;
            return toString().equals(other.toString());   // hackish
        } else {
            return false;
        }
    }
    
    // Returns the integer at the given index in the list.
    // Precondition: 0 <= index < size
    public int get(int index) {
        ListNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    // Removes the value at the given index from the list.
    // Precondition: 0 <= index < size
    public void remove(int index) {
        if (index == 0) {
            front = front.next;
        } else {
            ListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
    }
    
    // Returns the number of elements in the list.
    public int size() {
        int count = 0;
        ListNode current = front;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    // Returns a text representation of the list, giving
    // indications as to the nodes and link structure of the list.
    // Detects student bugs where the student has inserted a cycle
    // into the list.
    public String toFormattedString() {
        ListNode.clearCycleData();
        
        String result = this.name;
        
        ListNode current = front;
        boolean cycle = false;
        while (current != null) {
            result += " -> [" + current.data + "]";
            if (current.cycle) {
                result += " (cycle!)";
                cycle = true;
                break;
            }
            current = current.__gotoNext();
        }

        if (!cycle) {
            result += " /";
        }
        
        return result;
    }
    
    // Returns a text representation of the list.
    public String toString() {
        return toFormattedString();
    }
    
    // Returns a shorter, more "java.util.LinkedList"-like text representation of the list.
    public String toStringShort() {
        ListNode.clearCycleData();
        
        String result = "[";
        
        ListNode current = front;
        boolean cycle = false;
        while (current != null) {
            if (result.length() > 1) {
				result += ", ";
			}
            result += current.data;
            if (current.cycle) {
                result += " (cycle!)";
                cycle = true;
                break;
            }
            current = current.__gotoNext();
        }

        if (!cycle) {
            result += "]";
        }
        
        return result;
    }
    

    // ListNode is a class for storing a single node of a linked list.  This
    // node class is for a list of integer values.
    // Most of the icky code is related to the task of figuring out
    // if the student has accidentally created a cycle by pointing a later part of the list back to an earlier part.

    public static class ListNode {
        private static final List<ListNode> ALL_NODES = new ArrayList<ListNode>();
        
        public static void clearCycleData() {
            for (ListNode node : ALL_NODES) {
                node.visited = false;
                node.cycle = false;
            }
        }
        
        public int data;          // data stored in this node
        public ListNode next;     // link to next node in the list
        public boolean visited;   // has this node been seen yet?
        public boolean cycle;     // is there a cycle at this node?

        // post: constructs a node with data 0 and null link
        public ListNode() {
            this(0, null);
        }

        // post: constructs a node with given data and null link
        public ListNode(int data) {
            this(data, null);
        }

        // post: constructs a node with given data and given link
        public ListNode(int data, ListNode next) {
            ALL_NODES.add(this);
            this.data = data;
            this.next = next;
            this.visited = false;
            this.cycle = false;
        }
        
        public ListNode __gotoNext() {
            return __gotoNext(true);
        }
        
        public ListNode __gotoNext(boolean checkForCycle) {
            if (checkForCycle) {
                visited = true;
                
                if (next != null) {
                    if (next.visited) {
                        // throw new IllegalStateException("cycle detected in list");
                        next.cycle = true;
                    }
                    next.visited = true;
                }
            }
            return next;
        }
    }

// YOUR CODE GOES HERE
    /**
     * This method is part of the solution to problem on practice it, 
     * click <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/set">here </a>to see access. 
     * @param index
     * @param num
     */
    public void set(int index, int num)
    {
    	if(this.front==null)return;
    	for(ListNode n = this.front ; index>=0 ; index--)
    	{
    		if(index==0)n.data=num;
    		n=n.next;
    	}
    	
    }
    
    
    /**
     * This method is the solution to
     *  <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/toString">this</a> problem on practice it. 
     * @return
     */
    public String toString2()
    {
    	String s = new String("[");
    	for(ListNode n = this.front;n!=null;n=n.next)
    		s+=n.data+", ";
    	return s=(s.length()==1?s:s.substring(0, s.length()-2))+"]";
    }
    
    
    /**
     * This is a common method that is used in many collection, while it 
     * is also a solution to 
     * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/indexOf">
     * this</a> problem on practice it. 
     * @param num
     * @return
     */
    public int indexOf(int num)
    {
    	{
	    	int i=0;
	    	for(ListNode n = this.front;n!=null;n=n.next,i++)
	    		if(n.data==num)return i;
    	}
    	return -1; 
    }
    
    
    /**
     * This method return the minimum integer in the linkedlist, it 
     * is a solution to <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/min">
     * this</a> problem on practice it.
     * @return
     */
    public int min()
    {
    	if(this.front==null)throw new NoSuchElementException();
    	int i=this.front.data;
    	for(ListNode n = this.front;n!=null;n=n.next)
    	i=Math.min(i, n.data);
    	return i;
    	
    }
    
    /**
     * Return a boolean that tells whether the linkedlist is sorted. 
     * It solves <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/isSorted">
     * this </a>problem on practice it. 
     * @return
     */
    public boolean isSorted()
    {
    	int i = 0;
    	for(ListNode n = this.front; n!=null&&n.next!=null;i=n.next.data-n.data,n=n.next)
    	if(i<0)return false;
    	return true; 
    }
    
    /**
     * This is a method that solve THIS problem on practice it. 
     * @return
     * The last index of a certain number. 
     */
    public int lastIndexOf(int num)
    {
    	int i=this.front==null?-1:0, result =i; 
    	boolean occured=false;
    	for(ListNode n = this.front; n!=null;n=n.next,i++)
    	if(n.data==num)
    	{
    		result=i;
    		occured =true;
    	}
    	return occured?result:-1;
    }
    
    /**
     * This is the solution to <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/countDuplicates">
     * this</a> problem on practice it. 
     * @return
     */
    public int countDuplicates()
    {
    	int count = 0, i=0;
    	for(ListNode n = this.front; n!=null&&n.next!=null;i=n.next.data-n.data,n=n.next)
    	if(i==0)count++;
    	return count;
    }
    
    
    /**
     * This function is part of the solution to <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/countDuplicates">THIS</a> problem on practice it. 
     * @return
     */
    public boolean hasTwoConsecutive()
    {
    	for(Object[] lst ={this.front, 0}; lst[0]!=null&&((ListNode)lst[0]).next!=null; lst[1]=((ListNode)lst[0]).next.data-((ListNode)lst[0]).data,lst[0]=((ListNode)lst[0]).next)
    	if((int)lst[1]==1)return true;
    	return false;
    }
    
    
    /**
     * This method is solution to <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/deleteBack">
     * this</a> problem on practice it. <br>
     * It delete the last element in the list. 
     */
    public int deleteBack()
    {
    	if(this.front==null)throw new NoSuchElementException();
    	for
    	(
	    	ListNode pre =this.front, current = pre==null?null:pre.next;
	    	current!=null;
	    	pre =current, 
	    	current=current.next
    	)if(current.next==null){pre.next=null;return current.data;};
    	int result = this.front.data;
    	this.front=null;
    	return result;
    }
    
    
    /**
     * This method doubles each of the element in the list. 
     * It solves <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/stutter"> THIS</a> problem on practice it. 
     */
    public void stutter()
    {
    	if(this.front ==null)return; 
    	ListNode last = this.front;
    	for
    	(
    			ListNode pre = this.front, current = pre.next;
    			current!=null;
    			pre.next=new ListNode(pre.data,current),
    			last=current,
    			pre=current,current = current.next
    			
    	);
    	last.next=new ListNode(last.data);
    	
    }
    
    /**
     * This method moves all the negative numbers to the front of the list, 
     * it solves <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/split">this </a>problem on Practice it. 
     */
    public void split()
    {
    	if(this.front==null||this.front.next==null)return;
    	for(ListNode pre= this.front, current = pre.next;current!=null;)
    	{
    		if(current.data<0)
    		{
    			current=current.next;
    			ListNode temp = pre.next;
    			pre.next=pre.next.next;
    			temp.next=this.front;
    			this.front=temp;
    			continue;
    		}
    		pre=current;
    		current=current.next;
    	}
    }
    
    
    /**
     * Add the list to this list, it is a solution to
     *  <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/transferFrom">THIS </a>
     * problem on practice it. 
     * 
     * 
     */
    public void transferFrom(LinkedIntList arg)
    {
    	if(this.front==null)
    	{
    		this.front=arg.front;
    		arg.front=null;
    		return;
    	}
    	ListNode n = this.front;
    	while(n.next!=null)n=n.next;
    	n.next=arg.front;
    	arg.front=null;
    }
    
    /**
     * This method removes all the occurrences of a given number. 
     * It is a solution to THIS <a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/removeAll">
     * problem</a> on practice it. 
     * @param num
     */
    public void removeAll(int num)
    {
    	for(ListNode pre= this.front, current =pre!=null? pre.next:null;current!=null;)
    	{	
    		if(pre.data==num)
    		{
    			pre=current;
        		current=current.next;
        		this.front=pre;
        		continue;
    		}
    		if(current.data==num)
    		{
    			current=current.next;
    			pre.next=pre.next.next;
    			continue;
    		}
    		pre=current;
    		current=current.next;
    	}
    }
    
    
    /**
     * 
     * @param lst
     * @return
     */
    public boolean equals2(LinkedIntList lst)
    {
    	for(ListNode n1 = this.front, n2=lst.front;n1!=null;n1=n1.next==null?null:n1.next,n2=n2.next==null?n2.next:n2.next)
    	{
    		// length difference,         data difference         length difference but n1 has a single node....
    		if((n1==null^n2==null)||(n1.data!=n2.data)||(n1.next==null&&n2.next!=null))return false;
    	}
    	return true;
    }
    
    
    /**
     * Extract all the element at even index in the list and remove them them return it into a new list. 
     * <br>
     * We make two nodes that are right next to the targeted node....that should do it. 
     * <br>
     * Hint: Use recursion will be esier. 
     * @return
     */
    public LinkedIntList removeEvens()
    {
    	if(this.front==null)return new LinkedIntList();
    	LinkedIntList newlst = new LinkedIntList(this.front.data);
    	ListNode newtail = newlst.front;
    	this.front=this.front.next;
    	for(ListNode n = this.front; n!=null&&n.next!=null;n=n.next)
    	{
    		newtail.next=n.next;
    		newtail = newtail.next;
    		n.next=n.next.next;
    		if(newtail!=null)newtail.next=null;
    		
    	}
    	
    	return newlst;
    }
    
    
    /**
     * Remove element whoes index is within the range, inclusive;
     *  it is solution to <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/linkedlists/removeRange">this</a>
     *   problem on practice it. <br>
     *   <br><b>We need to realize that we need that node that comes before the lower and after the high 
     *   to remove all that nodes that comes in between.</b>
     *   <p>Notice</p>
     *   If the higher int is bigger than the size, this function will still works 
     *   <br>
     *   suggestion: Use recursion with a helper method will be way easier....
     * @param low
     * @param hight
     * @IllegalArgumentException
     * if the higher index is lower than the lower one, or any of the index is negative
     * 
     */
    public void removeRange(int low, int high)
    {
    	if(high<low||low<0||high<0)throw new IllegalArgumentException();
    	ListNode left =null, right=null;
    	int i=0;
    	for(ListNode n = this.front;n!=null&&i<=high;n=n.next,i++)
    	{
    		if(i-1==low)left=n;if(i+1==high)right=n;
    	}
    	
    	
    	
    }
    
    
    
    
}
