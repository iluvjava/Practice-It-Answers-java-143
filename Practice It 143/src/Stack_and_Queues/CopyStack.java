package Stack_and_Queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 

Write a method copyStack that takes a stack of integers as a parameter and returns a copy of 
the original stack (i.e., a new stack with the same values as the original, stored in the same 
order as the original). Your method should create the new stack and fill it up with the same
 values that are stored in the original stack. It is not acceptable to return the same stack passed
  to the method; you must create, fill, and return a new stack.
You will be removing values from the original stack to make the copy, but you have to be sure
 to put them back into the original stack in the same order before you are done. In other words, 
 when your method is done executing, the original stack must be restored to its original state and 
 you will return the new independent stack that is in the same state. You may use one queue as auxiliary storage.

 * @author victo
 *
 */
public class CopyStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	/**
	 * Use recursion might be a better option.<br>
	 * <a href ="https://practiceit.cs.washington.edu/problem/view/cs2/sections/stacksandqueues/copyStack">
	 * Original Question</a>
	 * @param arg
	 * @return
	 */
	public static <T> Stack<T> copyStack(Stack<T> arg)
	{
		Stack<T> result = new Stack<>();
		 __copyStack__(arg, result);
		 return result; 
	}
	
	private static <T> void __copyStack__(Stack<T> arg1, Stack<T> copy)
	{
		if(arg1.isEmpty())return;
		T element = arg1.pop();
		__copyStack__(arg1, copy);
		copy.add(element);
		arg1.add(element);
	}
	
	

}
