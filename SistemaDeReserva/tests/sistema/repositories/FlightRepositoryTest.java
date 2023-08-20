package sistema.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sistema.models.flight.Flight;

public class FlightRepositoryTest {
	
	FlightRepository flightRepository;
	
	final Flight MOCK_FLIGHT1 = new Flight("Jo�o Pessoa","Recife","01/01/2023","13:00",true,35.00,48);
	final Flight MOCK_FLIGHT2 = new Flight("Bras�lia","S�o Paulo","21/02/2015","22:00",true,120.00,60);
	final Flight MOCK_FLIGHT3 = new Flight("S�o Paulo","Rio de Janeiro","11/07/2002","00:00",true,40.00,12);
	
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
		assertEquals(flightRepository.listAllFlights(),"");
		
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
	
//	@Test
//	public void searchFlightsTest() {
//		flightRepository.addFlight(MOCK_FLIGHT1);
//		Flight returnedFlight = flightRepository.getFlightById(1);
//		assertEquals(returnedFlight,MOCK_FLIGHT1);
//	}
	
}
