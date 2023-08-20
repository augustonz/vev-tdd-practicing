package sistema.models.reservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import sistema.models.flight.Flight;
import sistema.models.user.User;

public class ReservationTest {

	Reservation testReservation;
	User testUser;
	Flight testFlight;
	
	@Before
	public void createTestFlight() {
		testUser = new User();
		testFlight = new Flight("João Pessoa","Recife","01/01/2023","13:00",true,35.00,48);
		testReservation = new Reservation(testUser,4,testFlight); //usuario, numero de passagens, voo
	}
	
	@Test
	public void basicReservation() {	  
		assertEquals(testReservation.getUser(),testUser);
		assertEquals(testReservation.getTicketCount(),4);
		assertEquals(testReservation.getFlight(),testFlight);
	}
}
