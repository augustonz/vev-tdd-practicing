package sistema.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import sistema.models.flight.Flight;

public class FlightRepositoryTest {
	
	FlightRepository flightRepository;
	
	final Flight MOCK_FLIGHT1 = new Flight("Jo�o Pessoa","Recife","01/01/2023","13:00",true,35.00,48);
	final Flight MOCK_FLIGHT2 = new Flight("Bras�lia","S�o Paulo","21/02/2015","22:00",true,120.00,60);
	final Flight MOCK_FLIGHT3 = new Flight("S�o Paulo","Rio de Janeiro","11/07/2002","00:00",true,40.00,12);
	final Flight MOCK_FLIGHT4 = new Flight("Jo�o Pessoa","Rio de Janeiro","21/02/2015","07:00",true,500.00,42);
	
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
		flightRepository.addFlight(MOCK_FLIGHT1);
		assertEquals(flightRepository.count(),1);
	}
	
	@Test
	public void removeFlightTest() {
		assertEquals(flightRepository.count(),0);
		flightRepository.addFlight(MOCK_FLIGHT1);
		assertEquals(flightRepository.count(),1);
		flightRepository.removeFlightById(1);
		assertEquals(flightRepository.count(),0);
	}
	
	@Test
	public void searchFlightTest() {
		flightRepository.addFlight(MOCK_FLIGHT1);
		flightRepository.addFlight(MOCK_FLIGHT2);
		flightRepository.addFlight(MOCK_FLIGHT3);
		Flight returnedFlight = flightRepository.getFlightById(1);
		assertEquals(returnedFlight,MOCK_FLIGHT1);
		returnedFlight = flightRepository.getFlightById(2);
		assertEquals(returnedFlight,MOCK_FLIGHT2);
		returnedFlight = flightRepository.getFlightById(3);
		assertEquals(returnedFlight,MOCK_FLIGHT3);
	}
	
	@Test
	public void listFlightsTest() {
		assertEquals(flightRepository.listAllFlights(),"Nenhum voo foi cadastrado at� agora\n");
		
		flightRepository.addFlight(MOCK_FLIGHT1);
		flightRepository.addFlight(MOCK_FLIGHT2);
		flightRepository.addFlight(MOCK_FLIGHT3);
		
		assertEquals(flightRepository.listAllFlights(),
				"1: Voo de Jo�o Pessoa para Recife, data: 01/01/2023 �s 13:00.\n"
				+ "Voo dispon�vel? Sim, pre�o: R$35.0\n"
				+ "Passageiros: 0/48\n"
				+ "2: Voo de Bras�lia para S�o Paulo, data: 21/02/2015 �s 22:00.\n"
				+ "Voo dispon�vel? Sim, pre�o: R$120.0\n"
				+ "Passageiros: 0/60\n"
				+ "3: Voo de S�o Paulo para Rio de Janeiro, data: 11/07/2002 �s 00:00.\n"
				+ "Voo dispon�vel? Sim, pre�o: R$40.0\n"
				+ "Passageiros: 0/12");
		
		flightRepository.removeFlightById(2);
		
		assertEquals(flightRepository.listAllFlights(),
				"1: Voo de Jo�o Pessoa para Recife, data: 01/01/2023 �s 13:00.\n"
				+ "Voo dispon�vel? Sim, pre�o: R$35.0\n"
				+ "Passageiros: 0/48\n"
				+ "3: Voo de S�o Paulo para Rio de Janeiro, data: 11/07/2002 �s 00:00.\n"
				+ "Voo dispon�vel? Sim, pre�o: R$40.0\n"
				+ "Passageiros: 0/12");
	}

	
	@Test
	public void searchFlightsByOriginTest() {
		flightRepository.addFlight(MOCK_FLIGHT1);
		flightRepository.addFlight(MOCK_FLIGHT2);
		flightRepository.addFlight(MOCK_FLIGHT3);
		flightRepository.addFlight(MOCK_FLIGHT4);
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByOrigin("Recife"), Map.of()));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByOrigin("Bras�lia"), Map.of(2,MOCK_FLIGHT2)));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByOrigin("Jo�o Pessoa"), Map.of(1,MOCK_FLIGHT1,4,MOCK_FLIGHT4)));
	}
	
	@Test
	public void searchFlightsByDestinationTest() {
		flightRepository.addFlight(MOCK_FLIGHT1);
		flightRepository.addFlight(MOCK_FLIGHT2);
		flightRepository.addFlight(MOCK_FLIGHT3);
		flightRepository.addFlight(MOCK_FLIGHT4);
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByDestination("Jo�o Pessoa"), Map.of()));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByDestination("Recife"), Map.of(1,MOCK_FLIGHT1)));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByDestination("Rio de Janeiro"), Map.of(4,MOCK_FLIGHT4,3,MOCK_FLIGHT3)));
	}
	
	@Test
	public void searchFlightsByPriceTest() {
		flightRepository.addFlight(MOCK_FLIGHT1);
		flightRepository.addFlight(MOCK_FLIGHT2);
		flightRepository.addFlight(MOCK_FLIGHT3);
		flightRepository.addFlight(MOCK_FLIGHT4);
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByPrice(20), Map.of()));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByPrice(35), Map.of(1,MOCK_FLIGHT1)));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByPrice(420), Map.of(1,MOCK_FLIGHT1,2,MOCK_FLIGHT2,3,MOCK_FLIGHT3)));
	}
	
	@Test
	public void searchFlightsBySeatCountTest() {
		flightRepository.addFlight(MOCK_FLIGHT1);
		flightRepository.addFlight(MOCK_FLIGHT2);
		flightRepository.addFlight(MOCK_FLIGHT3);
		flightRepository.addFlight(MOCK_FLIGHT4);
		assertTrue(searchFlightUtil(flightRepository.searchFlightsBySeatCount(1), Map.of(1,MOCK_FLIGHT1,2,MOCK_FLIGHT2,3,MOCK_FLIGHT3,4,MOCK_FLIGHT4)));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsBySeatCount(45), Map.of(1,MOCK_FLIGHT1,2,MOCK_FLIGHT2)));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsBySeatCount(100), Map.of()));
	}
	
	@Test
	public void searchFlightsByDateTest() {
		flightRepository.addFlight(MOCK_FLIGHT1);
		flightRepository.addFlight(MOCK_FLIGHT2);
		flightRepository.addFlight(MOCK_FLIGHT3);
		flightRepository.addFlight(MOCK_FLIGHT4);
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByDate("25/12/2022"), Map.of()));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByDate("01/01/2023"), Map.of(1,MOCK_FLIGHT1)));
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByDate("21/02/2015"), Map.of(2,MOCK_FLIGHT2,4,MOCK_FLIGHT4)));
	}
	
	@Test
	public void searchFlightsByAvailabilityTest() {
		flightRepository.addFlight(MOCK_FLIGHT1);
		flightRepository.addFlight(MOCK_FLIGHT2);
		flightRepository.addFlight(MOCK_FLIGHT3);
		flightRepository.addFlight(MOCK_FLIGHT4);
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByAvailability(), Map.of(1,MOCK_FLIGHT1,2,MOCK_FLIGHT2,3,MOCK_FLIGHT3,4,MOCK_FLIGHT4)));
		flightRepository.setAvailability(1,false);
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByAvailability(), Map.of(2,MOCK_FLIGHT2,3,MOCK_FLIGHT3,4,MOCK_FLIGHT4)));
		flightRepository.setAvailability(2,false);
		flightRepository.setAvailability(3,false);
		flightRepository.setAvailability(4,false);
		assertTrue(searchFlightUtil(flightRepository.searchFlightsByAvailability(), Map.of()));
	}
	
	boolean searchFlightUtil(Map<Integer,Flight> responseMap,Map<Integer,Flight> expectedFlights) {
		
		for (Entry<Integer, Flight> entry : responseMap.entrySet()) {
			if (!expectedFlights.containsKey(entry.getKey())) return false;
			if (!expectedFlights.get(entry.getKey()).equals(entry.getValue())) return false;
	    }
		
		if (responseMap.size()!=expectedFlights.size()) return false;
		
		return true;
	}
	
}
