package Recursive_BackTracking;

/**
 * Write a recursive method longestCommonSubsequence that accepts two strings as parameters and uses
 *  recursive backtracking to determine and return the longest common subsequence of characters that appear
 *   in both strings. A common subsequence is defined as a sequence of characters that appear in both strings in
 *    the same relative order. For example, given the two strings "ABCDEFG" and "BGCEHAF", 
 *    the longest subsequence between them is "BCEF" because those characters "B", "C", "E", and "F" appear in both strings in the same relative order. 
 *    The characters in the subsequence need not occur consecutively, and they do not have to occur at the same indexes within the two strings. 
 *    If the two strings have no characters in common, or if either string is an empty string, "" should be returned. 
 *    Here are some calls to your method and their expected return values. 
 * @author victo
 *
 */
public class LongestCommonSubsequence 
{
	
	// The solution is simple;
	//for each recursion
	//1. compare the current character
	//2. recurse and pass down the common string as parameter
	//3. get the returned string and return them back.
	//4. compare the valuses that got returned back. 

	//base case: one of the input string is empty, that means we need to return 
	// the common string...
	public static String longestCommonSubsequence(String s1, String s2)
	{
	    return longestCommonSubsequence_helper(s1,s2,"",0,0);
	}

	public static String longestCommonSubsequence_helper
	(String s1, String s2,String commonstr, int index1, int index2)
	{
	    if(index1==s1.length()||index2==s2.length())return commonstr;
	    
	    if(s1.charAt(index1)==s2.charAt(index2))
	    {

	        return longestCommonSubsequence_helper(s1,s2,commonstr+s1.charAt(index1),
	                                                                     index1+1,index2+1);
	    }
	    
	    // compare; basically the algorithm listed in the question.
	    String result1 =longestCommonSubsequence_helper(s1,s2,commonstr,index1+1,index2);
	    String result2 =longestCommonSubsequence_helper(s1,s2,commonstr,index1,index2+1);
	    
	   if(result1.length()>result2.length())
	   {
	       return result1;
	   }
	    else
	       return result2;
	    
	    
	}

}
