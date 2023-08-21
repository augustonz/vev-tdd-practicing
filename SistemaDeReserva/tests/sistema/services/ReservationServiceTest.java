package sistema.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import sistema.models.flight.Flight;
import sistema.models.reservation.Reservation;
import sistema.models.user.User;
import sistema.repositories.ReservationRepository;

public class ReservationServiceTest {
	
	ReservationService reservationService;
	
	FlightService flightService;
	
	@Before
	public void createReservationService() {
		flightService = new FlightService();
		
		reservationService = new ReservationService(flightService);
	}
	
	@Test
	public void reserveFlightTest() {
		assertEquals(3,reservationService.reservationRepository.count());
		Reservation newReservation = reservationService.reserveFlight(4,new User(),3);
		assertEquals(4,reservationService.reservationRepository.count());
		assertEquals(new Reservation(new User(),3,flightService.getFlight(4)),newReservation);
		
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
		assertTrue(reservationService.cancelReservesOfUser(new User()));
		assertEquals(0,reservationService.reservationRepository.count());
		assertFalse(reservationService.cancelReservesOfUser(new User("123","123","123")));
		assertFalse(reservationService.cancelReservesOfUser(new User()));
	}
	
}
