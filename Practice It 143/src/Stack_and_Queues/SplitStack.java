package Stack_and_Queues;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *Write a method splitStack that takes a stack of integers as a parameter
 * and splits it into negatives and non-negatives. The numbers in the stack 
 * should be rearranged so that all the negatives appear on the bottom of the stack
 *  and all the non-negatives appear on the top. In other words, if after this method is 
 *  called you were to pop numbers off the stack, you would first get all the nonnegative 
 *  numbers and then get all the negative numbers. It does not matter what order the numbers
 *   appear in as long as all the negatives appear lower in the stack than all the non-negatives. 
 *   You may use a single queue as auxiliary storage. 
 * @author victo
 *
 */
public class SplitStack 
{

	public static void main(String[] args)
	{
		
	}
	
	
	
	
	/**
	 * Negative number to the bottom, and positive number to the top. 
	 * <br>
	 * This problem solves <a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/stacksandqueues/splitStack">
	 * 
	 * Original Question</a>
	 * @param arg
	 */
	public static <T extends Number> void splitStack(Stack<T> arg)
	{
		// Transfer the stack into a queue: 
		Queue<T> q = new LinkedList<>();
		while(!arg.isEmpty())q.add(arg.pop());
		
		//get negative numbers in the queue
		for(Iterator<T> itr = q.iterator();itr.hasNext();)
		{
			T i = itr.next();
			if(i.doubleValue()<0){ arg.add(i);itr.remove();}
		}
		// add the ramaining positive back to the stack. 
		while(!q.isEmpty())arg.add(q.poll());
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
