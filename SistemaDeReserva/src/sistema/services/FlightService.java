package sistema.services;

import sistema.models.flight.Flight;
import sistema.repositories.FlightRepository;

public class FlightService {

	FlightRepository flightRepository;
	
	public FlightService() {
		flightRepository = new FlightRepository();
		createMockFlights();
	}
	
	public void createMockFlights() {
		flightRepository.addFlight(new Flight("João Pessoa","Recife","01/01/2023","13:00",true,35.00,48));
		flightRepository.addFlight(new Flight("Brasília","São Paulo","21/02/2015","22:00",true,120.00,60));
		flightRepository.addFlight(new Flight("São Paulo","Rio de Janeiro","11/07/2002","00:00",true,40.00,12));
		flightRepository.addFlight(new Flight("João Pessoa","Rio de Janeiro","21/02/2015","07:00",true,500.00,42));
	}

	public Flight getFlight(int flightId) {
		return flightRepository.getFlightById(flightId);
	}
	
	public String showFlights() {
		return flightRepository.listAllFlights();
	}

	public String searchFlightByOrigin(String origin) {
		return flightRepository.listFlights(flightRepository.searchFlightsByOrigin(origin));
	}

	public String searchFlightByDestination(String destination) {
		return flightRepository.listFlights(flightRepository.searchFlightsByDestination(destination));
	}

	public String searchFlightByPrice(double price) {
		return flightRepository.listFlights(flightRepository.searchFlightsByPrice(price));
	}

	public String searchFlightBySeatCount(int seatCount) {
		return flightRepository.listFlights(flightRepository.searchFlightsBySeatCount(seatCount));
	}

	public String searchFlightByDate(String string) {
		return flightRepository.listFlights(flightRepository.searchFlightsByDate(string));
	}

	public String searchFlightByAvailability() {
		return flightRepository.listFlights(flightRepository.searchFlightsByAvailability());
	}

}
