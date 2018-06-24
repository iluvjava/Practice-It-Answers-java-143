package Recursive_BackTracking;

import java.util.List;

/**
 * Write a method partitionable that accepts a list of integers as a parameter and uses recursive backtracking to discover whether the list can be partitioned into two sub-lists of equal sum. Your method should return true if the given list can be partitioned equally, and false if not. The table below indicates various possible values for a variable named list and the value that would be returned by the call of partitionable(list): 


List Contents

Value Returned

[] true 
[42] false 
[1, 2, 3] true 
[1, 2, 3, 4, 6] true 
[2, 1, 8, 3] false 
[8, 8] true 
[-3, 14, 3, 8] true 
[-4, 5, 7, 2, 9] false 
<br>
<a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/recursivebacktracking/partitionable">
click here</a>
 * @author victo
 *
 */
public class Partionable 
{
	public static boolean partitionable(List<Integer> list) 
	{
		return partitionable_help(list,0, 0, 0);
	}

	/**

	Use recursion to explore all possible combination and return the value 
	and if anyone of them is true, them it should bubble up that static call. 
	*/

	private static boolean partitionable_help(List<Integer> list,int partialsum1,int partialsum2,int index)
	{
	    if(index == list.size())return partialsum1==partialsum2; // this is the base case. 
	    boolean result = false;
	    result |= partitionable_help(list,partialsum1+list.get(index), partialsum2,index+1);
	    result |= partitionable_help(list,partialsum1, partialsum2+list.get(index),index+1);
	    return result; 
	}
}
