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
	
	public void occupySeats(int seatNum) {
		currentPassengers+=seatNum;
		if (currentPassengers>=maxPassengers) {
			currentPassengers=maxPassengers;
			available=false;
		}
	}
	
	public void unoccupySeats(int seatNum) {
		currentPassengers-=seatNum;
		if (currentPassengers<maxPassengers) {
			available=true;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Voo de " + origin + " para " + destination + ", data: " + date + " às " + time
				+ ".\n" + "Voo disponível? " + (available? "Sim":"Não") + ", preço: R$" + price + "\n" + "Passageiros: " + currentPassengers + "/" + maxPassengers;
	}

}
