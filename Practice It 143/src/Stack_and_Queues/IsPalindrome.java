package Stack_and_Queues;

import java.util.Queue;
import java.util.Stack;

/**
 * 

Write a method isPalindrome that takes a queue of integers as a parameter and returns 
true if the numbers in the queue represent a palindrome (and false otherwise). 
A sequence of numbers is considered a palindrome if it is the same in reverse order. 
For example, suppose a queue called q stores these values:

front [3, 8, 17, 9, 17, 8, 3] back

Then the call of isPalindrome(q); should return true because this sequence
 is the same in reverse order. If the queue had instead stored these values:

front [3, 8, 17, 9, 4, 17, 8, 3] back

The call on isPalindrome would instead return false because this sequence 
is not the same in reverse order (the 9 and 4 in the middle don't match).
 The empty queue should be considered a palindrome. 
 You may not make any assumptions about how many elements are in the queue and
  your method must restore the queue so that it stores the same sequence of values after 
  the call as it did before. You may use one stack as auxiliary storage.

 * @author victo
 *
 */
public class IsPalindrome 
{
	
	/**
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/stacksandqueues/isPalindrome">
	 * Original question
	 * </a>
	 * @param args
	 * @return
	 */
	public static <T> boolean isPalindrome(Queue<T> args)
	{
		if(args.size()<=1)return true;
		
		// move all the element in the queue to a stack. 
		Stack<T> stack = new Stack<>();
		for(int i = args.size();i>0;i--)
		{
			T element = args.poll();
			stack.add(element);
			args.add(element);
		}
		
		// take element out from the stack, but keep the data in the queue. 
		// prepare a boolean
		boolean result = true; 
		for(int i = args.size();i>0;i--)
		{
			T element1 = args.poll(), element2 = stack.pop();
			if(!element1.equals(element2))result = false;
			args.add(element1);
		}
		return result;
	}


}
