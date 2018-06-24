package Recursive_BackTracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Write a method makeChange that uses recursive backtracking to 
 * find all ways to make change for a given amount of money using pennies (1 cent), 
 * nickels (5 cents), dimes (10 cents), and quarters (25 cents). For example, 
 * when making change for 37 cents, you could use: 1 quarter, 1 dime and 2 pennies; 3 dimes and 7 pennies; or other combinations. 
Your method should accept a single parameter: the amount of cents for which to make change. 
Your method's output should be a sequence of all combinations of each type of coin that add up to that amount, 
one per line. For example, if the client code contained the following call: 
System.out.println(" P  N  D  Q");
System.out.println("------------");
makeChange(28);


The overall output generated should be the following: 
 P  N  D  Q
------------
[3, 0, 0, 1]
[3, 1, 2, 0]
[3, 3, 1, 0]
[3, 5, 0, 0]
[8, 0, 2, 0]
[8, 2, 1, 0]
[8, 4, 0, 0]
[13, 1, 1, 0]
[13, 3, 0, 0]
[18, 0, 1, 0]
[18, 2, 0, 0]
[23, 1, 0, 0]
[28, 0, 0, 0]
<br>
<a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/recursivebacktracking/makeChange">
Click here</a>
 * @author victo
 *
 */
public class MakeChange 
{
	
	//1. The base case is trying divide the value into quaters.
	//2. If the amount is not a zero after it has be divided by quaters, it would mean that it is not a 
	// valid solutions, thus, the method will return;
	//3. If the index is one larger and all the amount is divided into zero, it would mean that 
	//the successful base case is reached, thus, printing the result out. 


	/**
	 * Part of insight is realize that if we take out some of the money, and them pass the remaining to
	 * recursion, then a decision tree is forming when the methods are recursing. 
	 * @param amount
	 */
	public static void makeChange(int amount) 
	{
	    List<Integer> coinValues = new LinkedList<Integer>();
	    coinValues.add(1);    // penny
	    coinValues.add(5);    // nickel
	    coinValues.add(10);   // dime
	    coinValues.add(25);   // quarter
	    makeChange_helper_2
	    (coinValues,  new int[4],0, amount);
	}
	
	/**
	 * 
	 * @param coinValues
	 * All the coin values available for using. 
	 * @param coinslot
	 * An imaginary slot where each index hold a certain type of coin. 
	 * @param index
	 * The index is pivotal because it tells where the function IS in the recursive step. 
	 * @param amount
	 * The money that method is going to divide in two different coin values. 
	 */
	private static void makeChange_helper_2(List<Integer> coinValues, int[] coinslot,int index, int amount)
	{
		// We ran out of coins.
	    if(index == coinValues.size())
	    {
	        if(amount ==0)System.out.println(Arrays.toString(coinslot));
	        coinslot[index-1]=0;
	        return;
	    }
	    
	    if(amount>=coinValues.get(index))
	    {
	        int temp = amount/coinValues.get(index);
	        for(int i =0; i<=temp;i++)
	        {
	            coinslot[index]=i;
	            makeChange_helper_2(coinValues, coinslot,index+1,amount - i*coinValues.get(index));
	        }
	    }
	    else
	    {
	        coinslot[index]= 0 ;
	        makeChange_helper_2
	        (coinValues, coinslot, index+1, amount);
	    }
	}

}
