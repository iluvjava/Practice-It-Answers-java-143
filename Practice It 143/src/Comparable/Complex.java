package Comparable;


/**
 * Suppose that a class Complex has been defined for storing complex numbers.
 *In mathematics, complex numbers are those that can be written as (x + y i) where
 *x and y are real numbers and i is the square root of -1. In other words, complex 
 * numbers have a real part (x) and an imaginary part (y). The class includes the following members:
 *   
 *Your task is to modify the class to be Comparable by adding an appropriate compareTo
 * method. Complex objects should be compared using absolute value with smaller absolute
 *values considered "less" than numbers of higher absolute value.
The absolute value of a complex number is defined to be the square root of the sum of the
 squares of x and y. 
 * @author victo
 *
 */
public class Complex implements Comparable<Complex> {
    private double x, y;
    
    public Complex(double real, double imaginary) {
        x = real;
        y = imaginary;
    }
    
    public double getReal() {
        return x;
    }
    
    public double getImaginary() {
        return y;
    }
    
    public double abs() {
        return Math.sqrt(x * x  +  y * y);
    }
    
    public String toString() {
        if (y == 0) {
            return "" + x;
        } else if (x == 0) {
            return y + "i";
        } else if (y < 0) {
            return x  + " - " +  -y  +  "i";
        } else {
            return x  + " + " +  y  +  "i";
        }
    }
    
    public Complex add(Complex other) {
        return new Complex(x  +  other.x, y + other.y);
    }
    
    public Complex subtract(Complex other) {
        return new Complex(x  -  other.x, y - other.y);
    }

// YOUR CODE GOES HERE
    
    @Override
	public int compareTo(Complex o) 
    {
    	if(o==null)return -1;
		return (int) Math.signum(this.x*this.x+this.y*this.y-o.x*o.x-o.y*o.y);
	}

}
