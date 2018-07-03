package Stack_and_Queues;

import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author victo
 *
 */
public class Rearrage {
	
	
	/**
	 * Oh boy isn't this classic? <br> 
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/stacksandqueues/rearrange">Original question </a>
	 * @param arg
	 */
	public static <T extends Integer> void rearrange(Queue<T> arg)
	{
		Stack<T> tempstack = new Stack<>();
		
		// put all odd numbers into the stack. 
		for(int i = arg.size(); i>0;i--)
		{
			T element = arg.poll();
			if(element%2==1||element%2==-1){tempstack.add(element); continue;}
			arg.add(element);
		}
		
		
		while(!tempstack.isEmpty())
		arg.add(tempstack.pop());
		
		// repeat the whole thing so that the order is preserved. 
		for(int i = arg.size(); i>0;i--)
		{
			T element = arg.poll();
			if(element%2==1||element%2==-1){tempstack.add(element); continue;}
			arg.add(element);
		}
		
		
		while(!tempstack.isEmpty())
		arg.add(tempstack.pop());
		
		// Whoa, I am lazy. 
	
	}

}
