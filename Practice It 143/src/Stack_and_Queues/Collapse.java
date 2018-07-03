package Stack_and_Queues;

import java.util.Stack;

/**
 * 
 * 
 * 
 * 
Write a method collapse that takes a stack of integers as a parameter and that collapses 
it by replacing each successive pair of integers with the sum of the pair. For example, 
suppose a stack stores these values:
bottom [7, 2, 8, 9, 4, 13, 7, 1, 9, 10] top
The first pair should be collapsed into 9 (7 + 2),
 the second pair should be collapsed into 17 (8 + 9),
  the third pair should be collapsed into 17 (4 + 13) and so on to yield:
bottom [9, 17, 17, 8, 19] top
If the stack stores an odd number of elements, the final element is not collapsed. For example, the stack:
bottom [1, 2, 3, 4, 5] top
Would collapse into:
bottom [3, 7, 5] top
With the 5 at the top of the stack unchanged. You may use one queue as auxiliary storage.
 * @author victo
 *
 */
public class Collapse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * For this problem, I prefer to use recursion becsuse of its intrisic beauty. 
	 * <br>
	 * <a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/stacksandqueues/collapse">
	 * Original Question</a>
	 * @param arg
	 */
	public static void collapse(Stack<Integer> arg)
	{
		if(arg.size()<=1)return;
		if(arg.size()%2==1)
		{
			Integer a = arg.pop();
			collapse(arg);
			arg.add(a);
			return;
		}
		Integer a = arg.pop()+arg.pop();
		collapse(arg);
		arg.add(a);
		
	}
	
	
	
	

}
