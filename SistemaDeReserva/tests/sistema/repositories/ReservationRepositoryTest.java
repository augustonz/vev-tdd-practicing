package sistema.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sistema.models.flight.Flight;
import sistema.models.reservation.Reservation;
import sistema.models.user.User;

public class ReservationRepositoryTest {

	final Flight MOCK_FLIGHT1 = new Flight("Jo�o Pessoa","Recife","01/01/2023","13:00",true,35.00,48);
	final Flight MOCK_FLIGHT2 = new Flight("Bras�lia","S�o Paulo","21/02/2015","22:00",true,120.00,60);
	
	final User MOCK_USER1 = new User();
	final User MOCK_USER2 = new User();
	
	final Reservation MOCK_RESERVATION1 = new Reservation(MOCK_USER1, 1, MOCK_FLIGHT1);
	final Reservation MOCK_RESERVATION2 = new Reservation(MOCK_USER1, 6, MOCK_FLIGHT2);
	final Reservation MOCK_RESERVATION3 = new Reservation(MOCK_USER2, 3, MOCK_FLIGHT2);
	
	ReservationRepository reservationRepository;
	
	@Before
	public void createReservationRepository() {
		reservationRepository = new ReservationRepository();
	}
	
	@Test
	public void checkEmptyTest() {
		assertEquals(reservationRepository.count(),0);
	}
	
	@Test
	public void addReservationTest() {
		assertEquals(reservationRepository.count(),0);
		reservationRepository.addReservation(MOCK_RESERVATION1);
		assertEquals(reservationRepository.count(),1);
	}
	
	@Test
	public void removeReservationTest() {
		assertEquals(reservationRepository.count(),0);
		reservationRepository.addReservation(MOCK_RESERVATION1);
		assertEquals(reservationRepository.count(),1);
		reservationRepository.removeReservationById(1);
		assertEquals(reservationRepository.count(),0);
	}
	
	@Test
	public void searchReservationTest() {
		reservationRepository.addReservation(MOCK_RESERVATION1);
		reservationRepository.addReservation(MOCK_RESERVATION2);
		reservationRepository.addReservation(MOCK_RESERVATION3);
		Reservation returnedReservation = reservationRepository.getReservationById(1);
		assertEquals(returnedReservation,MOCK_RESERVATION1);
		returnedReservation = reservationRepository.getReservationById(2);
		assertEquals(returnedReservation,MOCK_RESERVATION2);
		returnedReservation = reservationRepository.getReservationById(3);
		assertEquals(returnedReservation,MOCK_RESERVATION3);
	}
}
