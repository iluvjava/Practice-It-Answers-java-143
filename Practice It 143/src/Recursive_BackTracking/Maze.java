package Recursive_BackTracking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Maze 
{
	
	
	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println(new File("Maze1.txt").exists());
		String content = new String();
		for(Scanner sc = new Scanner(new File("Maze1.txt"));sc.hasNextLine();)
		{
			content+=sc.nextLine()+"\n";
		}
		
		Maze m = new Maze(content);
		System.out.println(m);
		System.out.println("Testing explorer.");
		explorer(6, 1, 3, 5,  m);
		System.out.println(m);
		System.out.println("Testing search exist. ");
		System.out.println(Arrays.toString(Maze.searchForExist(m,0,0)));
		System.out.println("The following Should be a solved maze. ");
		m = new Maze(content);
		Maze.solve(m, 6, 1);
		System.out.println(m);
		
	}
	
    private char[][] squares;
    private boolean[][] explored;
    
    public Maze(String text) 
    {
        this(text.split("[\r]?\n"));
    }
    
    public Maze(String[] lines) {
        if (lines == null || lines.length == 0 || lines[0].length() == 0) {
            throw new IllegalArgumentException("empty lines data");
        }
        
        squares = new char[lines.length][lines[0].length()];
        explored = new boolean[lines.length][lines[0].length()];
        
        for (int row = 0; row < getHeight(); row++) {
            if (lines[row].length() != getWidth()) {
                throw new IllegalArgumentException("line " + row + " wrong length");
            }
            
            for (int col = 0; col < getWidth(); col++) {
                squares[row][col] = lines[row].charAt(col);
            }
        }
    }
    
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Maze) {
            Maze other = (Maze) o;
            if (getWidth() != other.getWidth()) {
                return false;
            }
            for (int row = 0; row < getHeight(); row++) {
                for (int col = 0; col < getWidth(); col++) {
                    if (squares[row][col] != other.squares[row][col]) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
    
    public int getHeight() {
        return squares.length;
    }
    
    public char getSquare(int row, int col) {
        checkIndexes(row, col);
        return squares[row][col];
    }
    
    public int getWidth() {
        return squares[0].length;
    }
    
    public boolean isExplored(int row, int col) {
        checkIndexes(row, col);
        return explored[row][col];
    }
    
    public void setExplored(int row, int col, boolean value) {
        checkIndexes(row, col);
        explored[row][col] = value;
    }
    
    public void setSquare(int row, int col, char value) {
        checkIndexes(row, col);
        if (squares[row][col] != ' ' && squares[row][col] != '.') {
            throw new IllegalArgumentException(
                    "Cannot set square at (" + row + ", " + col + 
                    "): cannot change " + squares[row][col] + " to " + value);
        }
        squares[row][col] = value;
    }
    
    public String toString() {
        String result = new String(squares[0]);
        for (int i = 1; i < getHeight(); i++) {
            result += "\n" + new String(squares[i]);
        }
        return result;
    }
    
    private void checkIndexes(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            throw new IllegalArgumentException("illegal indexes: (" + 
                                               row + ", " + col + ")");
        }
    }
    
    public boolean isEscaped() {
        // check left/right edges
        for (int row = 0; row < getHeight(); row++) {
            if (squares[row][0] == '.' || squares[row][getWidth() - 1] == '.') {
                return true;
            }
        }

        // check top/bottom edges
        for (int col = 0; col < getWidth(); col++) {
            if (squares[0][col] == '.' || squares[getHeight() - 1][col] == '.') {
                return true;
            }
        }
        
        return false;
    }
    
// YOUR CODE GOES HERE
    
    
    public static void solve(Maze m, int r, int c)
    {
    	int[] exist = Maze.searchForExist(m,0,0);
    	explorer( r, c, exist[0], exist[1], m);
    }
    
    /**
     * ---Casually Tested---
     * @param
     * A row and columns that gives the starting point,
     * @return
     * Whether is method has reached the destination, or has called a method 
     * that has reached the destination. 
     */
    protected static  boolean explorer(int r, int c, int destineR, int DestineC, Maze m)
    {
    	// Set the point as explored
    	m.setExplored(r, c, true);
    	
    	// look around first. where can I go? 
    	boolean up , down , left , right ;
    	
    	up = r-1>=0&&!m.isExplored(r-1, c)&&m.getSquare(r-1, c)==' ';
    	down = r+1<m.getHeight()&&!m.isExplored(r+1, c)&&m.getSquare(r+1, c)==' ';
    	left = c-1>=0&&!m.isExplored(r, c-1)&&m.getSquare(r, c-1)==' ';
    	right =c+1<m.getWidth()&& !m.isExplored(r, c+1)&&m.getSquare(r, c+1)==' ';
    	
    	if(r == destineR&& c ==DestineC )
	    {
	    		m.setSquare(r, c, '.');
	    		return true;
	    }
    	
    	
    	// base cases. We have arrived or no way to go. 
    	if(!up&&!down&&!left&&!right)
    	{
    		return false; 
    	}
    	else
    	{
	    	//recursion part. 
	    	boolean upr = false, downr  =false, leftr =false, rightr =false; 
	    	if(up)upr = explorer(r-1, c, destineR, DestineC, m);
	    	if(down)downr = explorer(r+1, c, destineR, DestineC, m);
	    	if(left)leftr = explorer(r, c-1, destineR, DestineC, m);
	    	if(right)rightr = explorer(r, c+1, destineR, DestineC, m);
	    	if(upr||downr||leftr||rightr)
	    	{
	    		m.setSquare(r, c, '.');
	    		return true;
	    	}
	    	return false;
    	}
		    	
    }
    
    /**--- Casually Tested---
     * This method will return the exist on the boarder. 
     * @return
     */
    protected static int[] searchForExist(Maze m, int startR, int startC)
    {
    	if(startR>m.getHeight()-1||startC> m.getWidth()-1)
		return null;
    	if(m.getSquare(startR, startC)==' ')
		{
			if(startR==0||startR == m.getHeight()-1)
			{
				int[] result = {startR, startC};
				return result; 
			}
			if(startC == 0||startC ==m.getWidth()-1)
			{
				int[] result = {startR, startC};
				return result;
			}
		}
    	if(startR == m.getHeight()-1)
    	return searchForExist(m,0 ,startC+1 );
    	else
    	return searchForExist(m,startR+1 ,startC );
    	
    	
    }
    
}

