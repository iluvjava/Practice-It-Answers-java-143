package ArrayIntList;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEveryThing {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() 
	{
		testingIdeas();
	}
	
	public static void println()
	{
		System.out.println();
	}
	
	public static void println(Object o )
	{
		System.out.println(o);
	}
	
	
	public static void testingIdeas()
	{
		{
			println("Ideas for print inversion. ");
			int size = 3;
			 for(
					 int first =0, second = 1;
					 first<size&&second<size;
					 first+= second>=size-1?1:0,
					 second=second<size -1 ?second+1:first+1
				)println(first+", "+second);
		}
		
		
		{
			println("Testing the stretch idea...");
			int previoussize = 4, arg =3;
			for(int i = previoussize-1, repetation = 0; 
					 i>=0;
					 i=repetation == arg?i-1:i,
					 repetation = repetation<arg?repetation+1:0
				  )println(i);
		}
	}

}
