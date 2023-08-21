package sistema.repositories;
//Classe muito bem feita , parabens OK
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sistema.models.reservation.Reservation;
import sistema.models.user.User;

public class ReservationRepository {

	int currentId;
	Map<Integer,Reservation> reservations;

	public ReservationRepository() {
		this.currentId = 1;
		this.reservations = new HashMap<Integer,Reservation>();
	}

	public int getCurrentId() {
		return currentId;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}
	public Map<Integer, Reservation> getReservations() {
		return reservations;
	}
	
	public void setFlights(Map<Integer, Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public int count() {
		return reservations.size();
	}
	
	public void addReservation(Reservation flight) {
		reservations.put(currentId, flight);
		currentId++;
	}
	
	public void removeReservationById(int id) {
		reservations.remove(id);
	}
	
	public Reservation getReservationById(int id) {
		return reservations.get(id);
	}
	
	public boolean hasReservation(int id) {
		return reservations.keySet().contains(id);
	}
	
	Map<Integer,Reservation> getSubMap(List<Integer> keyList) {
		Map<Integer, Reservation> newMap = new HashMap<>(reservations);
		newMap.keySet().retainAll(keyList);
		return newMap;
	}
	
	public Map<Integer,Reservation> searchReservationsByUser(User user) {
		List<Integer> keys = new ArrayList<>();
		
		for (Entry<Integer, Reservation> entry : reservations.entrySet()) {
	        if (entry.getValue().getUser().equals(user)) {
	            keys.add(entry.getKey());
	        }
	    }
		
		return getSubMap(keys);
	}
	
	public String listAllReservations() {
		return listReservations(reservations);
	}
	
	public String listReservations(Map<Integer,Reservation> reservations) {
		String resp = "";
		for (Entry<Integer, Reservation> entry : reservations.entrySet()) {
			resp += entry.getKey() + ": " + entry.getValue() + "\n";
	    }
		
		resp = resp.trim();
		
		return resp;
	}
}
