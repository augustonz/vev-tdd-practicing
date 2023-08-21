package sistema.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import sistema.models.reservation.Reservation;
import sistema.models.user.User;

public class ReservationServiceTest {
	
	ReservationService reservationService;
	
	FlightService flightService;
	
	final User MOCK_USER = new User("Fulano","123.456.789-00","+55(83)99982-6571");
	
	@Before
	public void createReservationService() {
		flightService = new FlightService();
		
		reservationService = new ReservationService(flightService);
	}
	
	@Test
	public void reserveFlightTest() {
		assertEquals(3,reservationService.reservationRepository.count());
		Reservation newReservation = reservationService.reserveFlight(4,MOCK_USER,3);
		assertEquals(4,reservationService.reservationRepository.count());
		assertEquals(new Reservation(MOCK_USER,3,flightService.getFlight(4)),newReservation);
		
	}
	
	@Test
	public void cancelReserveByIdTest() {
		assertTrue(reservationService.cancelReserveId(1));
		assertFalse(reservationService.cancelReserveId(-1));
		assertFalse(reservationService.cancelReserveId(1));
	}
	
	@Test
	public void cancelReserveByPersonTest() {
		assertEquals(3,reservationService.reservationRepository.count());
		assertTrue(reservationService.cancelReservesUser(MOCK_USER));
		assertEquals(0,reservationService.reservationRepository.count());
		assertFalse(reservationService.cancelReservesUser(new User("123","123","123")));
		assertFalse(reservationService.cancelReservesUser(MOCK_USER));
	}
	
}
