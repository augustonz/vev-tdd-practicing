package junit5Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import sistema.models.exceptions.InvalidDateException;
import sistema.models.exceptions.InvalidNonNegativeException;
import sistema.models.flight.Flight;
import sistema.models.user.User;
import sistema.repositories.FlightRepository;
import sistema.services.FlightService;
import sistema.services.ReservationService;

public class ParticaoEquivalenciaTests {
	
	ReservationService reservationService;
	
	FlightService flightService;
	
	final User MOCK_USER = new User("Fulano","123.456.789-00","+55(83)99982-6571");
	
	@BeforeEach
	public void createReservationService() {
		flightService = new FlightService();
		
		reservationService = new ReservationService(flightService);
	}
	
	@SearchFlightsTest
	@DisplayName("Busca voos por local de origem")
	public void searchFlightsByOriginTest() {
		assertTrue(
			searchFlightUtil(
				flightService.flightRepository.searchFlightsByOrigin("Jo�o Pessoa"), 
				Map.of(1,flightService.getFlight(1),4,flightService.getFlight(4))
			)
		);
	}
	
	@SearchFlightsTest
	@DisplayName("Busca voos por local de destino")
	public void searchFlightsByDestinationTest() {
		assertTrue(
			searchFlightUtil(
				flightService.flightRepository.searchFlightsByDestination("Recife"), 
				Map.of(1,flightService.getFlight(1))
			)
		);
	}
	
	@SearchFlightsTest
	@DisplayName("Busca voos por data")
	public void searchFlightsByDateTest() {
		assertTrue(
			searchFlightUtil(
				flightService.flightRepository.searchFlightsByDate("01/01/2023"), 
				Map.of(1,flightService.getFlight(1))
			)
		);
	}
	
	@SearchFlightsTest
	@DisplayName("Busca voos por data - Data inv�lida")
	public void searchFlightsByDateInvalidTest() {
		assertThrows(InvalidDateException.class,()->{
			flightService.flightRepository.searchFlightsByDate("12345");
		});
	}
	
	@SearchFlightsTest
	@DisplayName("Busca voos por quantidade de assentos dispon�veis")
	public void searchFlightsBySeatCountTest() {
		assertTrue(
			searchFlightUtil(
				flightService.flightRepository.searchFlightsBySeatCount(4), 
				Map.of(1,flightService.getFlight(1),2,flightService.getFlight(2),3,flightService.getFlight(3),4,flightService.getFlight(4))
			)
		);
	}
	
	@SearchFlightsTest
	@DisplayName("Busca voos por quantidade de assentos dispon�veis - Quantidade inv�lida")
	public void searchFlightsBySeatCountTestInvalid() {
		assertThrows(InvalidNonNegativeException.class,()->{
			flightService.flightRepository.searchFlightsBySeatCount(-5);
		});
	}
	
	@ListFlightsTest
	@DisplayName("Lista todos os voos")
	public void listFlightsTest() {
		assertEquals(flightService.flightRepository.listAllFlights(),
				"1: Voo de Jo�o Pessoa para Recife, data: 01/01/2023 �s 13:00.\n"
				+ "Voo dispon�vel? Sim, pre�o: R$35.0\n"
				+ "Passageiros: 10/48\n"
				+ "2: Voo de Bras�lia para S�o Paulo, data: 21/02/2015 �s 22:00.\n"
				+ "Voo dispon�vel? Sim, pre�o: R$120.0\n"
				+ "Passageiros: 1/60\n"
				+ "3: Voo de S�o Paulo para Rio de Janeiro, data: 11/07/2002 �s 00:00.\n"
				+ "Voo dispon�vel? Sim, pre�o: R$40.0\n"
				+ "Passageiros: 6/12\n"
				+ "4: Voo de Jo�o Pessoa para Rio de Janeiro, data: 21/02/2015 �s 07:00.\n"
				+ "Voo dispon�vel? Sim, pre�o: R$500.0\n"
				+ "Passageiros: 0/42");
	}
	
	@ListFlightsTest
	@DisplayName("Lista todos os voos - Nenhum voo dispon�vel")
	public void listEmptyFlightsTest() {
		FlightRepository emptyFlightRepository = new FlightRepository();
		
		assertEquals(emptyFlightRepository.listAllFlights(),"Nenhum voo foi cadastrado at� agora\n");
	}
	
	@CancelReservationTest
	@DisplayName("Cancela todas as reservas de um usu�rio")
	public void cancelReservesByPersonTest() {
		assertEquals(3,reservationService.reservationRepository.count());
		
		assertTrue(reservationService.cancelReservesUser(MOCK_USER));
		
		assertEquals(0,reservationService.reservationRepository.count());;
	}
	
	@CancelReservationTest
	@DisplayName("Cancela todas as reservas de um usu�rio - Usu�rio n�o possui reservas")
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
