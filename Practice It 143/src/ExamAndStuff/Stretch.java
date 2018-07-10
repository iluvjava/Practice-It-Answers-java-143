package ExamAndStuff;

import java.util.List;
import java.util.ListIterator;

public class Stretch {

	public static <T> void stretch(List<T> l, int i)
	{
	    if(l.size()==0||i<=1)
        {
            if(i==1)return;
            l.clear();
        }
        
	    for(ListIterator<T> itr = l.listIterator();itr.hasNext();)
	    {
	        T element = itr.next();
	        for(int j =i-1; j>0;j--)
	        {
	            itr.add(element);
	        }
	    }
	}
}
