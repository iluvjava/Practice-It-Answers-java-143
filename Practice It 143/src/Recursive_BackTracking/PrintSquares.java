package Recursive_BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a method printSquares that uses recursive backtracking to find all ways to express an integer as a sum of squares of unique positive integers. For example, the call of printSquares(200); should produce the following output: 
1^2 + 2^2 + 3^2 + 4^2 + 5^2 + 8^2 + 9^2
1^2 + 2^2 + 3^2 + 4^2 + 7^2 + 11^2
1^2 + 2^2 + 5^2 + 7^2 + 11^2
1^2 + 3^2 + 4^2 + 5^2 + 6^2 + 7^2 + 8^2
1^2 + 3^2 + 4^2 + 5^2 + 7^2 + 10^2
2^2 + 4^2 + 6^2 + 12^2
2^2 + 14^2
3^2 + 5^2 + 6^2 + 7^2 + 9^2
6^2 + 8^2 + 10^2
<br>
<a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/recursivebacktracking/printSquares">Click here</a>
 * @author victo
 *
 */
public class PrintSquares 
{
	//Tips: 
	// each recursion should know where it is in the stack so that we won't call the 
	// same number and try to square them over and over again.

	//If the input parameter is 0, that means we have somewhat found the solution, and the 
	// method should have a way to notify the method that calls it. (base case! )
	// The other base case if that the remainder becomes zero, and in that 
	// case, the method should just does nothing at all. 

	//We use one arraylist as the parameter that is passed through the lovely recursion. 


	public static void printSquares(int n)
	{
	    if(n ==0)return;
	    printSquares_helper(new ArrayList<Integer>(),n,1);
	}

	                                                                           // level is the choice we made.
	private static void printSquares_helper(List<Integer> trials, int remainder,int level)
	{
	    int upperbound =(int) Math.sqrt(remainder);
	    if(remainder ==0)
	    {
	        printResult(trials);
	        if(!trials.isEmpty())trials.remove(trials.size()-1); 
	        return;
	    }// the successful base case; 
	    
	       for
	       (
	           int i= level; 
	           i<=upperbound;          //important!
	           trials.add(i), printSquares_helper(trials, remainder-i*i,++i)
	       );
	        
	   
	    if(!trials.isEmpty())trials.remove(trials.size()-1); //Bacause the number is already considered
	    // so we need to remove it while retrieving a space for the method that calls this method.
	    // prevent duplicates of numbers in the collection. 

	}

	/**
	Given an array and it will print out the result using recursion. 
	*/
	private static void printResult(List<Integer> arg)
	{
	    printResult(arg, 0 );
	}

	private static void printResult(List<Integer> arg, int index)
	{
	    if(index == arg.size()-1){System.out.println(arg.get(index)+"^2");return;}
	    System.out.print(arg.get(index)+"^2 + ");
	    printResult(arg,index+1);
	}

	// you should use a for loop to print out the list....

}
