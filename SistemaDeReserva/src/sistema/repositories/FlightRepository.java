package sistema.repositories;

import java.util.HashMap;
import java.util.Map;

import sistema.models.flight.*;

public class FlightRepository {
	
	Map<Integer,Flight> flights;
	int currentId;
	
	public FlightRepository() {
		currentId=1;
		flights = new HashMap<Integer,Flight>();
	}
	
	public int count() {
		return flights.size();
	}
	
	public void addFlight(Flight flight) {
		flights.put(currentId, flight);
		currentId++;
	}
	
	public void removeFlightById(int id) {
		flights.remove(id);
	}
	
	public Flight getFlightById(int id) {
		return flights.get(id);
	}
	
	public String listAllFlights() {
		String resp = "";
		for (Map.Entry<Integer, Flight> entry : flights.entrySet()) {
			resp += entry.getKey() + ": " + entry.getValue() + "\n";
	    }
		
		resp = resp.trim();
		
		return resp;
	}
	
}
