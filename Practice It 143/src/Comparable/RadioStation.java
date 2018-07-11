package Comparable;
/**
 * Suppose that a class RadioStation has been defined for 
 * storing information about radio stations.
 * Each station object keeps track of its name (a string), 
 * its broadcast band (a string) and its station number (a real number).
 * For example, there is a local station called KUOW that is an FM 
 * station broadcast on 94.9. The class includes the following members: 
 * @author victo
 * 
 *  Your task is to modify the class to be Comparable by 
 *  adding an appropriate compareTo method. 
 *  Radio stations should be grouped together by band 
 *  (e.g., all AM stations grouped together and all FM stations grouped together).
 *   Within a given band, the stations should be sorted by
 *    station number (e.g., FM 94.9 less than FM 96.5).The broadcast 
 *    band can be any arbitrary string. For example, it might be "AM" versus "FM" 
 *    or might include subdivisions like "FM 1" and "FM 2" or might include other 
 *    text like "XM" for satellite radio. 
 *
 */
public class RadioStation implements Comparable<RadioStation>
{
	

    private String name;
    private String band;
    private double station;
    private RadioStation link;
    
    public RadioStation(String name, String band, double station) {
        this.name = name;
        this.band = band;
        this.station = station;
    }
    
    public String getName() {
        return name;
    }
    
    public String getBand() {
        return band;
    }
    
    public double getStation() {
        return station;
    }
    
    public void simulcast(RadioStation other) {
        link = other;
        other.link = this;
    }
    
    public String toString() {
        String result = name + " " + band + " " + station;
        if (link != null) {
            result += " (simulcast on " + link.band + " " + link.station + ")";
        }
        return result;
    }

	@Override
	/**
	 * Radio band, station and then name. 
	 */
	public int compareTo(RadioStation o)
	{
		if(o==null)return -1; 
		if(this == o)return 0;
		if(this.getBand().equals(o.getBand()))
			if(this.getStation()==o.getStation())
				if(this.name.equals(o.name))
					return 0;
				else return this.name.compareTo(o.name);
			else
				return (int)Math.signum(this.getStation()-o.getStation());
		else
		return this.getBand().compareTo(o.getBand());
	}
    
// YOUR CODE GOES HERE


}
