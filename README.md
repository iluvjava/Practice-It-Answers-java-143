# GitHub-Project Practice It Answers


Write a method printSquares that uses recursive backtracking to find all ways to express an integer as a sum of squares of unique positive integers. For example, the call of printSquares(200); should produce the following output:

1^2 + 2^2 + 3^2 + 4^2 + 5^2 + 8^2 + 9^2<br>
1^2 + 2^2 + 3^2 + 4^2 + 7^2 + 11^2<br>
1^2 + 2^2 + 5^2 + 7^2 + 11^2<br>
1^2 + 3^2 + 4^2 + 5^2 + 6^2 + 7^2 + 8^2<br>
1^2 + 3^2 + 4^2 + 5^2 + 7^2 + 10^2<br>
2^2 + 4^2 + 6^2 + 12^2<br>
2^2 + 14^2<br>
3^2 + 5^2 + 6^2 + 7^2 + 9^2<br>
6^2 + 8^2 + 10^2<br>

Some numbers (such as 128 or 0) cannot be represented as a sum of squares, in which case your method should produce no output. Keep in mind that the sum has to be formed with unique integers. Otherwise you could always find a solution by adding 1^2 together until you got to whatever number you are working with.

As with any backtracking problem, this one amounts to a set of choices, one for each integer whose square might or might not be part of your sum. In many of our backtracking problems we store the choices in some kind of collection. In this problem you can instead generate the choices by doing a for loop over an appropriate range of numbers. Note that the maximum possible integer that can be part of a sum of squares for an integer n is the square root of n.

Like with other backtracking problems, you still need to keep track of which choices you have made at any given moment. In this case, the choices you have made consist of some group of integers whose squares may be part of a sum that will add up to n. Represent these chosen integers as an appropriate collection where you add the integer i to the collection to consider it as part of an answer. If you ever create such a collection whose values squared add up to n, you have found a sum that should be printed.

To help you solve this problem, assume there already exists a method printHelper that accepts any Java collection of integers (such as a list, set, stack, queue, etc.) and prints the collection's elements in order. For example, if a set s stores the elements [1, 4, 8, 11], the call of printHelper(s); would produce the following output:

1^2 + 4^2 + 8^2 + 11^2
<pre>
//Tips: 
// each recursion should know where it is in the stack so that we won't call the 
// same number and try to square them over and over again.

//If the input parameter is 0, that means we have somewhat found the solution, and the 
// method should have a way to notify the method that calls it. (base case! )
// The other base case if that the remainder becomes zero, and in that 
// case, the method should just does nothing at all. 

//We use one arraylist as the parameter that is passed through the lovely recursion. 


public static void printSquares(int n)
{
    if(n ==0)return;
    printSquares_helper(new ArrayList<Integer>(),n,1);
}

                                                                          // level is the choice we made.
private static void printSquares_helper(List<Integer> trials, int remainder,int level)
{
    int upperbound =(int) Math.sqrt(remainder);
    if(remainder ==0)
    {
        printResult(trials);
        if(!trials.isEmpty())trials.remove(trials.size()-1); 
        return;
    }// the successful base case; 
    
       for
       (
           int i= level; 
           i<=upperbound;          //important!
           trials.add(i), printSquares_helper(trials, remainder-i*i,++i)
       );
        
   
    if(!trials.isEmpty())trials.remove(trials.size()-1); //Bacause the number is already considered
    // so we need to remove it while retrieving a space for the method that calls this method.
    // prevent duplicates of numbers in the collection. 

}

/**
Given an array and it will print out the result using recursion. 
*/
private static void printResult(List<Integer> arg)
{
    printResult(arg, 0 );
}

private static void printResult(List<Integer> arg, int index)
{
    if(index == arg.size()-1){System.out.println(arg.get(index)+"^2");return;}
    System.out.print(arg.get(index)+"^2 + ");
    printResult(arg,index+1);
}

// you should use a for loop to print out the list....

</pre>


PLEASE GO TO READ THE CODE ABOVE FOR MORE COOL SOLUTION LIKE THIS. 
