package sistema.models.reservation;

import sistema.models.flight.Flight;
import sistema.models.user.User;

public class Reservation {
	Flight flight;
	User user;
	int ticketCount;
	
	public Reservation(User user, int ticketCount,Flight flight) {
		this.flight = flight;
		this.user = user;
		this.ticketCount = ticketCount;
	}
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + ticketCount;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Reservation other = (Reservation) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (ticketCount != other.ticketCount)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Informações do Voo:\n" + flight + "\n\n" + user + "\n\nPassagens compradas: " + ticketCount + ", Preço total da reserva: R$" + ticketCount*flight.getPrice();
	}
}
