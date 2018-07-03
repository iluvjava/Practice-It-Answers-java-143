package Stack_and_Queues;

import java.util.Stack;

/**
 * Write a method equals that 
 * takes as parameters two stacks 
 * of integers and returns true if the 
 * two stacks are equal and that returns 
 * false otherwise. To be considered equal, 
 * the two stacks would have to store the same 
 * sequence of integer values in the same order. 
 * Your method is to examine the two stacks but 
 * must return them to their original state before terminating. 
 * You may use one stack as auxiliary storage. 
 * @author victo
 *
 */
public class Equal 
{
	
	
	public static <T,U> boolean equals(Stack<T>first, Stack<U> second)
	{
		if(first == second)return true;
		if(first.size()!= second.size())return false;
		
		Stack<Object> temp = new Stack<>();
		
		boolean result = true;
		
		// we took out two element from each stack compare them and put them 
		// into the auxiliary stack for restoration later. 
		while(!first.isEmpty())
		{
			T a = first.pop(); U b = second.pop();
			temp.add(a); temp.add(b);
			if(!a.equals(b)){result = false;break;}
		}
		
		while(!temp.isEmpty())
		{
			second.add((U) temp.pop());
			first.add((T) temp.pop());
		}
		return result;
	}

}
