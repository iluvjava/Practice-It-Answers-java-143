package Comparable;


/**
 * Suppose that a class Point2D has been defined for storing a 2-dimensional
 *  point with x and y coordinates (both as doubles). (In our section handout,
 *   the class was called Point, but we have renamed it here because Practice-It uses
 *    a class named Point for other purposes.) The class includes the following members: 
 * @author victo
 *
 */

	public class Point2D implements Comparable<Point2D> {
	    private double x;
	    private double y;
	    
	    public Point2D() {
	        this(0.0, 0.0);
	    }
	    
	    public Point2D(double x, double y) {
	        setLocation(x, y);
	    }
	    
	    public double getX() {
	        return x;
	    }
	    
	    public double getY() {
	        return y;
	    }
	    
	    public String toString() {
	        return "(" + x + ", " + y + ")";
	    }
	    
	    public void setLocation(double x, double y) {
	        this.x = x;
	        this.y = y;
	    }
	    
	    public double distance(Point2D other) {
	        double xDiff = x - other.x;
	        double yDiff = y - other.y;
	        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	    }

		

	// YOUR CODE GOES HERE    
	    /**
	     * Closer to the origin is smaller. 
	     */
	    @Override
		public int compareTo(Point2D o) 
	    {
	    	
	    	if(this == o)return 0;
	    	if(null ==o)return -1;
	    	
	    	double thisdistance = this.x*this.x+this.y*this.y,
	    			otherdistance = o.x*o.x+o.y*o.y;
	    	return (int)Math.signum(thisdistance - otherdistance);
		}
		
		
	}



