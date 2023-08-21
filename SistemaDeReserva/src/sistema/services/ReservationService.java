package sistema.services;

import java.util.Map;
import java.util.Map.Entry;

import sistema.models.flight.Flight;
import sistema.models.reservation.Reservation;
import sistema.models.user.User;
import sistema.repositories.ReservationRepository;

public class ReservationService {

	ReservationRepository reservationRepository;
	
	FlightService flightService;
	
	public ReservationService(FlightService flightService) {
		reservationRepository = new ReservationRepository();
		this.flightService = flightService;
		CreateMockReservations();
	}
	
	void CreateMockReservations() {
		reserveFlight(1,new User(),10);
		reserveFlight(2,new User(),1);
		reserveFlight(3,new User(),6);
	}

	public boolean cancelReserveId(int reservationId) {
		if (reservationRepository.hasReservation(reservationId)) {	
			Reservation reservation = reservationRepository.getReservationById(reservationId);
			Flight flight = reservation.getFlight();
			flight.unoccupySeats(reservation.getTicketCount());
			reservationRepository.removeReservationById(reservationId);
			return true;
		}
		return false;
	}

	public Reservation reserveFlight(int flightId, User user, int passengerCount) {
		Flight flight = flightService.getFlight(flightId);
		flight.occupySeats(passengerCount);
		Reservation newReservation = new Reservation(user,passengerCount,flight);
		reservationRepository.addReservation(newReservation);
		return newReservation;
	}

	public boolean cancelReservesOfUser(User user) {
		Map<Integer,Reservation> reservationsofUser = reservationRepository.searchReservationsByUser(user);
		if (reservationsofUser.size()==0) return false;
		
		
		for (Entry<Integer, Reservation> entry : reservationsofUser.entrySet()) {
			Reservation reservation = entry.getValue();
			Flight flight = reservation.getFlight();
			flight.unoccupySeats(reservation.getTicketCount());
			reservationRepository.removeReservationById(entry.getKey());
	    }
		
		return true;
	}

	public String seeReserveConfirmations(User user) {
		return reservationRepository.listReservations(reservationRepository.searchReservationsByUser(user));
		
	}

}
