package sistema.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sistema.models.flight.Flight;

public class FlightRepositoryTest {
	
	FlightRepository flightRepository;
	
	final Flight MOCK_FLIGHT = new Flight("João Pessoa","Recife","01/01/2023","13:00",true,35.00,48);
	
	@Before
	public void createFlightRepository() {
		flightRepository = new FlightRepository();
	}
	
	@Test
	public void checkEmptyTest() {
		assertEquals(flightRepository.count(),0);
	}
	
	@Test
	public void addFlightTest() {
		assertEquals(flightRepository.count(),0);
		flightRepository.addFlight(MOCK_FLIGHT);
		assertEquals(flightRepository.count(),1);
	}
	
	@Test
	public void removeFlightTest() {
		assertEquals(flightRepository.count(),0);
		flightRepository.addFlight(MOCK_FLIGHT);
		assertEquals(flightRepository.count(),1);
		flightRepository.removeFlightById(1);
		assertEquals(flightRepository.count(),0);
	}
	
//	@Test
//	public void searchFlightTest() {
//		flightRepository.addFlight(MOCK_FLIGHT);
//		Flight returnedFlight = flightRepository.getFlightById(1);
//		assertEquals(returnedFlight,MOCK_FLIGHT);
//	}
	
}
