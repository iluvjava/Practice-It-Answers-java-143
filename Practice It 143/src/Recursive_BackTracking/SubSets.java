package Recursive_BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a method subsets that uses recursive backtracking to find every possible sub-list of a given list. A sub-list of a list L contains 0 or more of L's elements. Your method should accept a List of strings as its parameter and print every sub-list that could be created from elements of that list, one per line. For example, suppose a variable called list stores the following elements: 
[Janet, Robert, Morgan, Char]


The call of subsets(list); would produce output such as the following: 
[Janet, Robert, Morgan, Char]
[Janet, Robert, Morgan]
[Janet, Robert, Char]
[Janet, Robert]
[Janet, Morgan, Char]
[Janet, Morgan]
[Janet, Char]
[Janet]
[Robert, Morgan, Char]
[Robert, Morgan]
[Robert, Char]
[Robert]
[Morgan, Char]
[Morgan]
[Char]
[]
<br>
<a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/recursivebacktracking/subsets"> Click here</a>
 * @author victo
 *
 */
public class SubSets 
{
	//This problem is one of the best to isllutrate the idea of recursive backtracking in action.
	// By making decision in each recurstion, we have explore all the possible possibilites, isn't 
	// it awesome? 


	// One of the mathematical fact that is related to this is that there exist 2^n possible subsets
	// in a set consists of n elements. 


	// Main idea: For each elements in the set, it is either in or not in the subset. 
	// as easy as that.
	//Only one list will be used as a subset in the method for the sake of saving resources. 
	public static void subsets(List<String> elements) {
	    
	    if(elements.isEmpty())return; // we don't like array out of index error to occur.
	    
		substs_helper(elements, new ArrayList<String>(),0);
	}

	private static void substs_helper(List<String> elements, List<String> subsets,int index)
	{
	    if(index==elements.size())
	    {
	        System.out.println(subsets); // base case, the index exceed the last element index. 
	        return; // end this method. 
	    }
	    subsets.add(elements.get(index));
	    substs_helper(elements,subsets,index+1);
	    subsets.remove(subsets.size()-1); // we need to remove the element, or it will not work because 
	    // all choices we made are included in the list.... 
	    substs_helper(elements,subsets,index+1);
	    
	}

}
