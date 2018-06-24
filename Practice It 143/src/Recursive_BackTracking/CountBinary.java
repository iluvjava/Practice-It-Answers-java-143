package Recursive_BackTracking;



/**
 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/recursivebacktracking/countBinary">
 * ORIGINAL PROBLEM</a>
 * @author victo
 *
 */
public class CountBinary {
	
	//By looking at the output, we can conclude that the base case is n==0;
	// and the method should start a new line when the base case is reached. 

	public static void countBinary(int n)
	{
	    if(n==0)return;
	    counBinary("",n);
	}
	private static void counBinary(String line, int n)
	{
	    // base case
	    if(n==1){System.out.println(line+"0");System.out.println(line+"1");return;}
	    counBinary(line+"0",  n-1);
	    counBinary(line+"1",  n-1);
	    
	}

}
