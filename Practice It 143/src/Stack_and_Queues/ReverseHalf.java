package Stack_and_Queues;

import java.util.Queue;
import java.util.Stack;

/**
 * 
 * 

Write a method reverseHalf that reverses the order of half of the elements of a
 Queue of integers. Your method should reverse the order of all the elements in 
 odd-numbered positions (position 1, 3, 5, etc.) assuming that the first value in 
 the queue has position 0. For example, if the queue originally stores this sequence 
 of numbers when the method is called:

index: 0  1  2  3  4  5   6   7
front [1, 8, 7, 2, 9, 18, 12, 0] back

- it should store the following values after the method finishes executing:

index: 0  1  2  3   4  5  6   7
front [1, 0, 7, 18, 9, 2, 12, 8] back

Notice that numbers in even positions (positions 0, 2, 4, 6) have not moved. 
That sub-sequence of numbers is still: (1, 7, 9, 12). But notice that the numbers 
in odd positions (positions 1, 3, 5, 7) are now in reverse order relative to the original. 
In other words, the original sub-sequence: (8, 2, 18, 0) - has become: (0, 18, 2, 8). 
You may use a single stack as auxiliary storage.
 * @author victo
 *
 */
public class ReverseHalf {

	/**
	 * It's simple if you think carefully. <br>
	 * 
	 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/stacksandqueues/reverseHalf"></a>
	 * @param arg
	 */
	public static <T> void reverseHalf(final Queue<T> arg)
	{
		// take all the element at odd position out into a stack. 
		Stack<T> stack = new Stack<>();
		for(int i = arg.size(), j=0; i>0 ; i--,j++)
		{
			T element = arg.poll();
			if(j%2==1){stack.add(element); continue;}
			arg.add(element);
		}
		
		// now, we are going to add all the odd index element back. 
		boolean b = stack.size()<arg.size(); // There is an extra step i fthe size of the stack is smaller than the queue. 
		while(!stack.isEmpty())
		{
			T element1 = arg.poll(), element2 = stack.pop();
			arg.add(element1);arg.add(element2);
		}
		
		if(b)arg.add(arg.poll());
		
	}

}
