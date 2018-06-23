package Recursive_BackTracking;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/recursivebacktracking/travel2">Practice it link
 * </a>
 */
public class Tranvel2
{
	
	public static void main(String[] args)
	{
		travel2(2,1);
	}
	/**
	 * Method that will called. 
	 * @param x
	 * @param y
	 */
	public static void travel2(int x, int y)
	{
		travel2(x,y,new LinkedList<>());
	}
	
	/**
	 * Helper method that will recurse. 
	 * @param x
	 * @param y
	 * @param pathtaken
	 */
	private static void travel2(int x, int y, List<String> pathtaken)
	{
		// base case: 

		if(x==0&y ==0)
		{
			System.out.println(pathtaken);
			return;
		}
		
		// look around and decide what to do....
		boolean goN, goE, goNE; 
		goN = y!=0; goE = x!=0;goNE = goN&goE;
		
		//recurse. 
		if(goN)
		{
			pathtaken.add("N");
			travel2(x, y-1, pathtaken);
			pathtaken.remove(pathtaken.size()-1); 
		}
		if(goE)
		{
			pathtaken.add("E");
			travel2(x-1, y, pathtaken);
			pathtaken.remove(pathtaken.size()-1); 
		}
		if(goNE)
		{
			pathtaken.add("NE");
			travel2(x-1, y-1, pathtaken);
			pathtaken.remove(pathtaken.size()-1); 
		}
	}

}
