

package Comparable;


/**
 * Suppose that a class CalendarDate has been defined for storing a calendar
 *  date with month, day and year components. (In our section handout,
 *   the class was called Date, but we have renamed it here because Practice-It uses 
 *   a class named Date for other purposes.) The class includes the following members: 
 *   Your task is to modify the class to be Comparable by adding an appropriate compareTo method. Dates that occur chronologically earlier should be considered "less" than dates that occur later.


You may assume that dates are constructed with appropriate values: 
Months will be between 1 and 12, days will be between 1 and 31 and years will
 be four-digit numbers. Assume that the toString method produces a standard format
  with two digits for the month and two digits for the day, including leading zeros
   if necessary. For example, January 7th, 2005, would return the string "2005/01/07". 
 * @author victo
 *
 */
public class CalendarDate implements Comparable<CalendarDate> {
    private int year;
    private int month;
    private int day;
    
    public CalendarDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public int getYear() {
        return year;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getDay() {
        return day;
    }
    
    public String toString() {
        String result = year + "/";
        if (month < 10) {
            result += "0";
        }
        result += month + "/";
        if (day < 10) {
            result += "0";
        }
        result += day;
        return result;
    }

	

 // YOUR CODE GOES HERE
     @Override
 	public int compareTo(CalendarDate arg0) 
     {
 		if(null== arg0)return -1;
 		if(arg0==this)return 0;
 		
 		// convert everything to be measured in days. 
 		if(this.getYear()==arg0.getYear())
 		{
 			if(this.getMonth()==arg0.getMonth())
 			{
 				if(this.getDay()==arg0.getDay())
 					return 0;
 				else 
 					return (int) Math.signum(this.getDay()-arg0.getDay());
 			}
 			else
 				return (int) Math.signum(this.getMonth()-arg0.getMonth());
 		}
 		else
 		return (int) Math.signum(this.getYear()-arg0.getYear());
 		
 	}
    

}
