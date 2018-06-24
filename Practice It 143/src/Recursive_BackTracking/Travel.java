package Recursive_BackTracking;

public class Travel {
	
	// A pair of methods to solve this problem
	// The complexity of the methods are exponetial, and this reflects 
	// the fact that the internal structure of the methds will call 
	// itself multiple times. 

	//The main Idea: Each time you traval from (0,0) to (n,n), you need
	// to solve the problem of traveling fom(n-1,n-1) to (n,n),which
	//  Like: which direction to go and what paramerter we should recieve and pass 
	// into the recursion? 
	// is the same problem. Thus, it is recursive in its nature. 

	public static void travel(int x, int y)
	{
	    System.out.print(travel_helper("", x,y));
	}


	private static String travel_helper(String pathtaken,int x, int y)
	{
	    // listed above: East, then North, then Northeast. 
	    if(x==0&&y==0)return pathtaken+"\n";
	    if(x==1&&y==0) return pathtaken + "E\n";
	    if(y==1&&x==0) return pathtaken+ "N\n"; // base cases;
	    
	      String s = new String();
	      if(x>0) s+=travel_helper(pathtaken+"E ",x-1, y);
	      if(y>0)s += travel_helper(pathtaken+"N ",x, y-1);
	      if(x>0&&y>0)s += travel_helper(pathtaken+"NE ",x-1, y-1);
	    return s;
	}


	// Graphical interpreation:
	// "m(x,y)" is the simplified representation of the method we did.
	// Each time the method calls itself, it will pass a parameter and 
	// return all the parameter it obtained from previous recursion.
//	         m(0,1) -- goes to N and end the line of out put
//	        /
//	       E
//	      /
	//m(1,1)-N--m(1,0)-- goes to E and end the line of out put
//	      \
//	       NE
//	        \
//	         m(0,0)-\n-end
	//
	//
	//
	//
	//

}
