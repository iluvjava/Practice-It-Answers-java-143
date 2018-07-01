package ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This is a class that contrains all the solution to the chapter arraylist. 
 * @author victo
 *
 */
public class AllSolutions 
{
	
	
	/**
	 * This is a method the returns the maximum lenghth of string in a list. 
	 *<br>
	 *It's a solution to a problem on practice it. 
	 *<br>
	 *<a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arraylist/maxLength">Linked<a>
	 * @return
	 */
	public static int maxLength(List<? extends String> arg)
	{
		if(arg.size()==0)return 0;
		int len = arg.get(0).length();
		for(String s: arg)
		{
			len = Math.max(len, s.length());
		}
		return len;
	}

	
	
	/**
	 * This is a method that returns  the differences between the biggest and the smallest element. 
	 * <a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arraylist/range">Link</a>
	 * @param arg
	 * @return
	 */
	public static int range(List<? extends Integer> arg)
	{
		if(arg.size()==0)return 0; 
		int max = arg.get(0), min = max;
		for(Integer i : arg)
		{
			max = Math.max(i, max);
			min = Math.min(i, min);
		}
		return max - min+1;
	}
	
	
	/**
	 * This method moves the min value in the list to the front of the list.<br>
	 * It solves a problem on practice it. <br>
	 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/arraylist/minToFront">Link</a>
	 */
	public static <T extends Integer> void  minToFront(List<T> arg)
	{
		int min =arg.get(0), index =0, counting = 0 ;
		for(Iterator<Integer> itr = (Iterator<Integer>) arg.iterator(); itr.hasNext(); counting++ )
		{
			int current = itr.next();
			if(current<min)
			{
				index = counting; 
				min = current;
			}
		}
		arg.add(0, arg.remove(index));
	}
	
	/**
	 * This is a method that removes all the string with an even length in the list. 
	 * <br>
	 * It solve <a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arraylist/removeEvenLength">
	 * this</a> problem on practice it. 
	 * @param arg
	 */
	public static <T extends String> void removeEvenLength(List<T> arg)
	{
		for(Iterator<T> itr = arg.iterator();itr.hasNext();)
			if(itr.next().length()%2==0)itr.remove();
	}
	
	
	
	/**
	 * This method repeats each element in the list. 
	 * <br>
	 * It is a solution to <a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arraylist/stutter">
	 * this</a> problem on practice it. 
	 * @param arg
	 */
	public static <T> void stutter(List<T> arg)
	{
		for(ListIterator<T> itr = arg.listIterator();itr.hasNext();itr.add(itr.next()));
	}
	
	
	/**
	 * This function really does some fancy things. It solves 
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arraylist/removeShorterStrings">
	 * this</a> problem on practice it. 
	 */
	public static <T extends String> void removeShorterStrings(List<T> arg)
	{
		for(ListIterator<T> itr= arg.listIterator(); itr.hasNext(); )
		{
			T first =  itr.next();
			if(!itr.hasNext())break;
			itr.remove();
			T second =  itr.next();
			itr.remove();
			itr.add(first.length()<=second.length()?second:first);
		}
	}
	
	
	/**
	 * Yelp! Its name tells you what it does. <br>
	 * It solves this problem on practice it. 
	 * @param arg
	 */
	public static <T> void switchPairs(List<T> arg)
	{
		for(ListIterator<T> itr= arg.listIterator(); itr.hasNext(); )
		{
			T first =  itr.next();
			if(!itr.hasNext())break;
			itr.remove();
			T second = itr.next();
			itr.remove();
			itr.add(second);
			itr.add(first);
		}
	}
	
	/**
	 * This method removes duplicated elements in an sorted array. 
	 * <br>
	 * It solves <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arraylist/removeDuplicates">
	 * this</a> problem in practice it.
	 */
	public static <T extends Comparable> void removeDuplicates(List<T> arg)
	{
		if(arg.size() <=1)return;
		
		Iterator<T> itr = arg.iterator();
		T lastelement = itr.next();
		while(itr.hasNext())
		{
			T current = itr.next();
			if(current.equals(lastelement))
				itr.remove();
			lastelement = current;
		}
	}
	
	
	/**
	 * This method will add "****" in front of evey element which has a length of 4. 
	 * <br>
	 * It solves <a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arraylist/markLength4">this</a> problem on practice it. 
	 */
	public static <T extends String> void 	markLength4(List<T> arg)
	{
		for(ListIterator<T> itr =arg.listIterator();itr.hasNext(); )
		{
			if(itr.next().length()==4)
			{
				itr.previous();
				itr.add((T) "****");
				itr.next();
			};
		}
	}
	
	
	
	
	
	
	
	
	
	
}
















