package sistema.models.flight;

public class Flight {
	
	String origin;
	String destination;
	String date;
	String time;
	boolean available;
	double price;
	int maxPassengers;
	int currentPassengers;
	
	public Flight(String origin, String destination, String date, String time, boolean available, 
			double price, int maxPassengers) {
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.available = available;
		this.price = price;
		this.maxPassengers = maxPassengers;
		this.currentPassengers = 0;
	}
	
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getMaxPassengers() {
		return maxPassengers;
	}
	public void setMaxPassengers(int maxPassengers) {
		this.maxPassengers = maxPassengers;
	}

	public int getAvailableSeats() {
		return maxPassengers-currentPassengers;
	}


	public int getCurrentPassengers() {
		return currentPassengers;
	}


	public void setCurrentPassengers(int currentPassengers) {
		this.currentPassengers = currentPassengers;
	}

}
