package functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import sistema.models.flight.Flight;
import sistema.models.reservation.Reservation;
import sistema.models.user.User;
import sistema.repositories.FlightRepository;
import sistema.services.FlightService;
import sistema.services.ReservationService;

public class ParticaoEquivalenciaTests {
	
	ReservationService reservationService;
	
	FlightService flightService;
	
	final User MOCK_USER = new User("Fulano","123.456.789-00","+55(83)99982-6571");
	
	@Before
	public void createReservationService() {
		flightService = new FlightService();
		
		reservationService = new ReservationService(flightService);
	}
	
	@Test
	public void searchFlightsByOriginTest() {
		assertTrue(
				searchFlightUtil(flightService.flightRepository.searchFlightsByOrigin("João Pessoa"), 
				Map.of(1,flightService.getFlight(1),4,flightService.getFlight(4))));
	}
	
	@Test
	public void searchFlightsByDestinationTest() {
		assertTrue(
				searchFlightUtil(flightService.flightRepository.searchFlightsByDestination("Recife"), 
				Map.of(1,flightService.getFlight(1))));
	}
	
	@Test
	public void searchFlightsByDateTest() {
		assertTrue(
				searchFlightUtil(flightService.flightRepository.searchFlightsByDate("01/01/2023"), 
				Map.of(1,flightService.getFlight(1))));
	}
	
	@Test(expected = InvalidDateException.class)
	public void searchFlightsByDateInvalidTest() {
		flightService.flightRepository.searchFlightsByDate("12345");
	}
	
	@Test
	public void searchFlightsBySeatCountTest() {
		assertTrue(
				searchFlightUtil(flightService.flightRepository.searchFlightsBySeatCount(4), 
						Map.of(1,flightService.getFlight(1),2,flightService.getFlight(2),3,flightService.getFlight(3),4,flightService.getFlight(4))));
	}
	
	@Test(expected = InvalidNonNegativeException.class)
	public void searchFlightsBySeatCountTestInvalid() {
		flightService.flightRepository.searchFlightsBySeatCount(-5);
	}
	
	@Test
	public void listFlightsTest() {
		assertEquals(flightService.flightRepository.listAllFlights(),
				"1: Voo de João Pessoa para Recife, data: 01/01/2023 às 13:00.\n"
				+ "Voo disponível? Sim, preço: R$35.0\n"
				+ "Passageiros: 10/48\n"
				+ "2: Voo de Brasília para São Paulo, data: 21/02/2015 às 22:00.\n"
				+ "Voo disponível? Sim, preço: R$120.0\n"
				+ "Passageiros: 1/60\n"
				+ "3: Voo de São Paulo para Rio de Janeiro, data: 11/07/2002 às 00:00.\n"
				+ "Voo disponível? Sim, preço: R$40.0\n"
				+ "Passageiros: 6/12\n"
				+ "4: Voo de João Pessoa para Rio de Janeiro, data: 21/02/2015 às 07:00.\n"
				+ "Voo disponível? Sim, preço: R$500.0\n"
				+ "Passageiros: 0/42");
	}
	
	@Test
	public void listEmptyFlightsTest() {
		
		FlightRepository emptyFlightRepository = new FlightRepository();
		
		assertEquals(emptyFlightRepository.listAllFlights(),"Nenhum voo foi cadastrado até agora\n");
	}
	
	@Test
	public void cancelReservesByPersonTest() {
		assertEquals(3,reservationService.reservationRepository.count());
		
		assertTrue(reservationService.cancelReservesUser(MOCK_USER));
		
		assertEquals(0,reservationService.reservationRepository.count());;
	}
	
	@Test
	public void cancelEmptyReserveByPersonTest() {
		User testUser = new User("123","123","123");
		assertFalse(reservationService.cancelReservesUser(testUser));
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
