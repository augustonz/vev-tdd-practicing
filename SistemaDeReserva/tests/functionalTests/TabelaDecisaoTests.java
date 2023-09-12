package functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import sistema.models.reservation.Reservation;
import sistema.models.user.User;
import sistema.services.FlightService;
import sistema.services.ReservationService;

public class TabelaDecisaoTests {
	ReservationService reservationService;
	
	FlightService flightService;
	
	final User MOCK_USER = new User("Fulano","123.456.789-00","+55(83)99982-6571");
	
	@Before
	public void createReservationService() {
		flightService = new FlightService();
		
		reservationService = new ReservationService(flightService);
	}
	
	@Test(expected = InvalidPositiveException.class)
	public void reserveFlightInvalidIdTest() {
		reservationService.reserveFlight(-1,MOCK_USER,3);
	}
	
	@Test(expected = UnknownFlightIdException.class)
	public void reserveFlightUnknownIdTest() {
		reservationService.reserveFlight(100,MOCK_USER,3);
	}
	
	@Test(expected = InvalidPositiveException.class)
	public void reserveFlightInvalidSeatsTest() {
		reservationService.reserveFlight(4,MOCK_USER,-1);
	}
	
	@Test(expected = InsuficientSeatsException.class)
	public void reserveFlightInsuficientSeatsTest() {
		reservationService.reserveFlight(4,MOCK_USER,1000);
	}
	
	@Test
	public void reserveFlightTest() {
		assertEquals(3,reservationService.reservationRepository.count());
		
		Reservation newReservation = reservationService.reserveFlight(4,MOCK_USER,3);
		
		assertEquals(4,reservationService.reservationRepository.count());
		
		assertEquals(new Reservation(MOCK_USER,3,flightService.getFlight(4)),newReservation);
	}
	
	@Test(expected = InvalidPositiveException.class)
	public void cancelReserveByIdInvalidTest() {
		reservationService.cancelReserveId(-1);
	}
	
	@Test(expected = UnknownReserveIdException.class)
	public void cancelReserveByIdUnkownTest() {
		reservationService.cancelReserveId(1000);
	}
	
	@Test
	public void cancelReserveByIdTest() {
		assertTrue(reservationService.cancelReserveId(1));
	}
	
	@Test(expected = InvalidPositiveException.class)
	public void confirmReserveInvalidTest() {
		reservationService.reservationRepository.getReservationById(-11);
	}
	
	@Test(expected = UnknownReserveIdException.class)
	public void confirmReserveUnknownTest() {
		reservationService.reservationRepository.getReservationById(1000);
	}
	
	@Test
	public void confirmReserveTest() {
		Reservation reservation = reservationService.reservationRepository.getReservationById(1);
		assertEquals(reservation.toString(),
				"Informações do Voo:\n"
				+ "Voo de João Pessoa para Recife, data: 01/01/2023 às 13:00.\n"
				+ "Voo disponível? Sim, preço: R$35.0\n"
				+ "Passageiros: 10/48\n"
				+ "\n"
				+ "Usuário emissor - Nome: Fulano, cpf: 123.456.789-00, telefone para contato: +55(83)99982-6571\n"
				+ "\n"
				+ "Passagens compradas: 10, Preço total da reserva: R$350.0");
	}
}
