package Recursive_BackTracking;


/**
 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/recursivebacktracking/countDecimal">Original problem</a>
 * @author victo
 *
 */
public class CountDecimal
{
	
	//The base case is n==1 and we need to print the last digit of the line.
	public static void countDecimal(int n)
	{
	    if(n==0)return;
	    countDecimal("",n);
	}
	private static void countDecimal (String line, int n)
	{
	    if(n==1)for(int i=0; i<10;System.out.println(line+i+""),i++);
	    else
	        for(int i =0; i<10;countDecimal(line+i+"",n-1),i++);
	}

}
