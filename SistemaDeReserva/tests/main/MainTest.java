package main;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class MainTest {
	
	String message="test";
	
	@Test
   public void testPrintMessage() {	  
      assertEquals(message,"test");
   }
}
