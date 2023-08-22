package sistema.models.reservation;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sistema.models.flight.Flight;
import sistema.models.user.User;

public class ReservationTest {

	Reservation testReservation;
	User testUser;
	Flight testFlight;
	
	@Before
	public void createTestFlightTest() {
		testUser = new User("Fulano","123.456.789-00","+55(83)99982-6571");
		testFlight = new Flight("João Pessoa","Recife","01/01/2023","13:00",true,35.00,48);
		testReservation = new Reservation(testUser,4,testFlight);
	}
	
	@Test
	public void basicReservationTest() {	  
		assertEquals(testReservation.getUser(),testUser);
		assertEquals(testReservation.getTicketCount(),4);
		assertEquals(testReservation.getFlight(),testFlight);
	}
	
	@Test
	public void toStringTest() {
		System.out.println(testReservation);
		assertEquals(testReservation.toString(),
				"Informações do Voo:\n"
				+ "Voo de João Pessoa para Recife, data: 01/01/2023 às 13:00.\n"
				+ "Voo disponível? Sim, preço: R$35.0\n"
				+ "Passageiros: 0/48\n"
				+ "\n"
				+ "Usuário emissor - Nome: Fulano, cpf: 123.456.789-00, telefone para contato: +55(83)99982-6571\n"
				+ "\n"
				+ "Passagens compradas: 4, Preço total da reserva: R$140.0");
	}
}
