package Comparable;

/**
 * Suppose that a class USCurrency has been defined for
 *  storing a currency amount in dollars and cents 
 *  (both integers) where one dollar equals 100 cents.
 *   The class includes the following members: 
 *   
 *   
 *    Your task is to modify the class to be Comparable by 
 *    adding an appropriate compareTo method. The currency 
 *    objects should be compared in the obvious way, with smaller
 *     currency amounts considered "less" than larger currency 
 *     amounts (e.g., -$13.45 < -$2.03 < $5.13 < $98.06).

A currency amount can be negative. The cents method returns 
values in the range of 0 to 99 for nonnegative amounts and returns
 values in the range of 0 to -99 for negative currency amounts. 
 * @author victo
 *
 */
public class USCurrency implements Comparable<USCurrency> {
    private int totalCents;
    
    public USCurrency(int dollars, int cents) {
        totalCents = dollars * 100 + cents;
    }
    
    public int dollars() {
        return totalCents / 100;
    }
    
    public int cents() {
        return totalCents % 100;
    }
    
    public String toString() {
        int cents = Math.abs(totalCents);
        String s = cents / 100 + "." + cents % 100 / 10 + cents % 10;
        if (totalCents < 0) {
            return "-$" + s;
        } else {
            return "$" + s;
        }
    }
    
    public USCurrency add(USCurrency other) {
        return new USCurrency(dollars() + other.dollars(),
        cents() + other.cents());
    }
    
    public USCurrency subtract(USCurrency other) {
        return new USCurrency(dollars() - other.dollars(),
        cents() - other.cents());
    }

	

// YOUR CODE GOES HERE
    @Override
	public int compareTo(USCurrency o) {
		if(null==o)return -1;
		if(this == o)return 0; 
		return (int) Math.signum(this.dollars()*100+this.cents()-o.dollars()*100-o.cents());
	}
    

}
