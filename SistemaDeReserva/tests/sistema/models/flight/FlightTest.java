package sistema.models.flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FlightTest {
	
	Flight testFlight;
	
	@Before
	public void createTestFlight() {
		testFlight = new Flight("João Pessoa","Recife","01/01/2023","13:00",true,35.00,48);
	}
	
	@Test
	public void basicFlight() {	  
		assertEquals(testFlight.getOrigin(),"João Pessoa");
		assertEquals(testFlight.getDestination(),"Recife");
		assertEquals(testFlight.getDate(),"01/01/2023");
		assertEquals(testFlight.getTime(),"13:00");
		assertTrue(testFlight.isAvailable());
		assertEquals(testFlight.getPrice(),35.00,0);
		assertEquals(testFlight.getMaxPassengers(),48);
		assertEquals(testFlight.getCurrentPassengers(),0);
		assertEquals(testFlight.getAvailableSeats(),48);
	}
}
