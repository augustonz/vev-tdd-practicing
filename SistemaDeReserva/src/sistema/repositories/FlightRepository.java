package sistema.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public void setAvailability(int id,boolean value) {
		flights.get(id).setAvailable(value);
	}
	
	Map<Integer,Flight> getSubMap(List<Integer> keyList) {
		Map<Integer, Flight> newMap = new HashMap<>(flights);
		newMap.keySet().retainAll(keyList);
		return newMap;
	}
	
	public Map<Integer,Flight> searchFlightsByOrigin(String origin) {
		List<Integer> keys = new ArrayList<>();
		
		for (Entry<Integer, Flight> entry : flights.entrySet()) {
	        if (entry.getValue().getOrigin().equals(origin)) {
	            keys.add(entry.getKey());
	        }
	    }
		
		return getSubMap(keys);
	}
	
	public Map<Integer,Flight> searchFlightsByDestination(String destination) {
		List<Integer> keys = new ArrayList<>();
		
		for (Entry<Integer, Flight> entry : flights.entrySet()) {
	        if (entry.getValue().getDestination().equals(destination)) {
	            keys.add(entry.getKey());
	        }
	    }
		
		return getSubMap(keys);
	}
	
	public Map<Integer,Flight> searchFlightsByPrice(double price) {
		List<Integer> keys = new ArrayList<>();
		
		for (Entry<Integer, Flight> entry : flights.entrySet()) {
	        if (entry.getValue().getPrice()<=price) {
	            keys.add(entry.getKey());
	        }
	    }
		
		return getSubMap(keys);
	}
	
	public Map<Integer,Flight> searchFlightsByDate(String date) {
		List<Integer> keys = new ArrayList<>();
		
		for (Entry<Integer, Flight> entry : flights.entrySet()) {
	        if (entry.getValue().getDate().equals(date)) {
	            keys.add(entry.getKey());
	        }
	    }
		
		return getSubMap(keys);
	}
	
	public Map<Integer,Flight> searchFlightsBySeatCount(int num) {
		List<Integer> keys = new ArrayList<>();
		
		for (Entry<Integer, Flight> entry : flights.entrySet()) {
	        if (entry.getValue().getAvailableSeats()>=num) {
	            keys.add(entry.getKey());
	        }
	    }
		
		return getSubMap(keys);
	}
	
	public Map<Integer,Flight> searchFlightsByAvailability() {
		List<Integer> keys = new ArrayList<>();
		
		for (Entry<Integer, Flight> entry : flights.entrySet()) {
	        if (entry.getValue().isAvailable()) {
	            keys.add(entry.getKey());
	        }
	    }
		
		return getSubMap(keys);
	}
	
	public String listAllFlights() {
		return listFlights(flights);
	}
	
	public String listFlights(Map<Integer,Flight> flights) {
		String resp = "";
		
		for (Entry<Integer, Flight> entry : flights.entrySet()) {
			resp += entry.getKey() + ": " + entry.getValue() + "\n";
	    }
		
		resp = resp.trim();
		
		if (flights.size()==0) return "Nenhum voo foi cadastrado até agora\n";
		
		return resp;
	}
	
}
