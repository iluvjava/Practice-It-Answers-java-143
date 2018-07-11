package Comparable;


/**
 * Suppose that a class Location has been defined for 
 * storing information about global map locations. Each 
 * location keeps track of its name (a string), its latitude (a real number),
 * and its longitude (a real number). The class includes the following members: 
 * <br><br>
 *  Your task is to modify the class to be Comparable by adding an
 *   appropriate compareTo method. Locations should be ordered first by
 *    latitude with locations closer to the equator considered less than
 *     locations farther from the equator. When the latitudes are equal,
 *      you should examine longitudes with locations closer to the prime 
 *      meridian considered less than locations farther from the prime meridian.
<br><br>
Latitudes are expressed relative to the equator with negative latitudes 
considered to be in the southern hemisphere and positive latitudes considered
 to be in the northern hemisphere. Thus, 0 represents the equator, -90 represents
 the South Pole and 90 represents the North Pole. Longitudes are expressed relative 
 to what is known as the prime meridian (the line of longitude that runs through 
 Greenwich, England), with positive longitudes considered to be in the western 
 hemisphere and negative longitudes considered to be in the eastern hemisphere. 
 Longitudes can range from -180 to 180.
<br><br>
You may assume that your constructor is passed legal values for latitude and longitude. 
You are allowed to use the Math.abs method to find the absolute value of a number. 
 * @author victo
 *
 */
public class Location implements Comparable<Location> {
    private String name;
    private double latitude;
    private double longitude;
    
    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public String getName() {
        return name;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public String toString() {
        String result = name + " " + Math.abs(latitude);
        if (latitude < 0) {
            result += "S";
        } else {
            result += "N";
        }
        result += " " + Math.abs(longitude);
        if (longitude < 0) {
            result += "E";
        } else {
            result += "W";
        }
        return result;
    }

	

// YOUR CODE GOES HERE
    
    /**
     * Order of importance: <br>
     * 
     * Latitude(distance from equator)->Longtitute(Distance from prime meridian)-> Name
     */
    @Override
	public int compareTo(Location arg0) 
    {
    	
    	if(null == arg0)return -1;
    	if(this == arg0)return 0;
    	 
    	if(Math.abs(this.getLatitude()-arg0.getLatitude())==0)
    		if(Math.abs(this.longitude-arg0.longitude)==0)
    			return 0;
    		else return (int)Math.signum(Math.abs(this.longitude)- Math.abs(arg0.longitude));
    	else return (int)Math.signum(Math.abs(this.latitude)- Math.abs(arg0.latitude));
    	
	}

}

