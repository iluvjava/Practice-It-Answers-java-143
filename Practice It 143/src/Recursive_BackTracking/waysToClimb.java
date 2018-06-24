package Recursive_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class waysToClimb {
	
	// Oupput looks like printing a list of list that contains the integer. 

	public static void waysToClimb(int steps)
	{
	   if(steps == 0 )return;
	    waysToClimb_helper(new ArrayList<Integer>(), steps);
	}



	//This method takes in two objects and modify them, and the arrayList of list will be 
	// the final result that got ereturn to the top of the resursion. 

	// The base case is adding the complete list of steps to the result (ref alpaths)
	// 
	private static void waysToClimb_helper(ArrayList<Integer> pathtaken, int stairs)
	{
	    // base case: adding the list of solutions all the paths
	    if(stairs ==0){ System.out.println(pathtaken);return;}
	    
	    // make a clone of the path, we don't wnat the paths that taken to be all 
	    // referencing the same path. 
	    ArrayList<Integer> pathtaken_clone = new ArrayList<Integer>();
	    pathtaken_clone.addAll(pathtaken);
	    
	    
	    if(stairs>0)
	    {  
	        pathtaken.add(1);
	        waysToClimb_helper(pathtaken,stairs-1);
	    }
	    if(stairs>1)
	    {
	        pathtaken_clone.add(2);
	        waysToClimb_helper(pathtaken_clone,stairs-2);
	    }
	    
	}

}
