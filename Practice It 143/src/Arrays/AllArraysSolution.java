package Arrays;

/**
 * This class contain all the solution for the arrays chapter. 
 * @author victo
 *
 */
public class AllArraysSolution 
{
	
	/**
	 * return the index of the first occurence of the value. 
	 * @param array
	 * @param num
	 * @return
	 */
	public static int indexOf(int[] array, int num)
	{
		for(int i =0; i<array.length;i++)
		{
			if(array[i]==num)return i;
		}
		return -1;
	}
	
	
	/**
	 * It prints out an in array<br>
	 * <a =href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrays/print">Link</a>
	 */
	public static void print(int[] arg)
	{
		StringBuilder s  = new StringBuilder("[");
		for(Integer i : arg)
		{
			s.append(i+", ");
		}
		System.out.println( s.charAt(s.length()-1)==' '?(s.toString().substring(0, s.length()-2)+"]"):s.toString()+"]");
	}
	
	
	/**
	 * This method returns the minimum values in an array. It's a solution to a problem on practice it. 
	 * <br>
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrays/findMin"></a>
	 * @param array
	 * @return
	 */
	public static int findMin(int[] array)
	{
		for(int i =0, min = array[0]; i< array.length; i++)
		{
			min = Math.min(array[i], min);
			if(i+1==array.length)return min;
		}
		return array[0];
	}
	
	
	/**
	 * This is a method that solves at problem on practice it. <br>
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrays/numUnique">link</a>
	 * @return
	 */
	public static int numUnique(int[] arg)
	{
		if(arg.length==0)return 0;
		int unique =1;
		for(int i =1; i<arg.length; i++)
			unique+=arg[i]-arg[i-1]==0?0:1;
		return unique;
	}
	
	
	/**
	 * This method's sole purpose is to solve a problem on practice it. 
	 * <br>
	 * <a href="https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrays/stretch"> link </a>
	 * @parm arg
	 * @return
	 */
	public static int[] stretch(int[] arg)
	{
		if(arg.length==0)return new int[0];
		
		int[] result = new int[arg.length*2];
		for(int i =0, j=0 ; i< result.length; i+=2,j++)
		{
			result[i+1] = arg[j]/2;
			result[i]=arg[j]-arg[j]/2;
		}
		return result; 
	}
	
	
	/**
	 * Oh boy isn't this classic? <br>
	 * This solve this problem on practice it. <br>
	 * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrays/rotateRight">Link </a>
	 */
	public static void rotateRight(int[] arg)
	{
		if(arg.length==0)return; 
		for(int i = 1 ; i< arg.length; i++)
		{
			int temp = arg[i];
			arg[i] = arg[0];
			arg[0] = temp;
		}
		
	}

}
