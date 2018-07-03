package Stack_and_Queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * 
 * 
 * 
Write a method stutter that takes a stack of integers as a parameter and replaces
 every value in the stack with two occurrences of that value. For example, suppose the stack stores these values:
bottom [3, 7, 1, 14, 9] top
Then the stack should store these values after the method terminates:
bottom [3, 3, 7, 7, 1, 1, 14, 14, 9, 9] top
Notice that you must preserve the original order. In the original list the 9 was at the top and would have been
 popped first. In the new stack the two 9s would be the first values popped from the stack. You may use a single
  queue as auxiliary storage to solve this problem.

 * @author victo
 *
 */
public class Stutter {
	
	
	
	/**
	 * Repeat each of the elements in the stack. <br>
	 * <a href ="https://practiceit.cs.washington.edu/problem/view/cs2/sections/stacksandqueues/copyStack">
	 * Original Question</a>
	 * @param arg
	 */
	public static <T> void stutter(Stack<T> arg)
	{
		Queue<T> q = new LinkedList<>();
		while(!arg.isEmpty())q.add(arg.pop());
		for(T element : q)
			{arg.push(element); arg.push(element);}
		// now that stack is in reverse order... which is good, we 
		// can reverse it back by doing what we did...
		q.clear();
		while(!arg.isEmpty())q.add(arg.pop());
		for(T element : q)
		{arg.push(element);}
		
	}

}
