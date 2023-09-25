package junit5Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import sistema.models.exceptions.InsuficientSeatsException;
import sistema.models.exceptions.InvalidPositiveException;
import sistema.models.exceptions.UnknownIdException;
import sistema.models.reservation.Reservation;
import sistema.models.user.User;
import sistema.services.FlightService;
import sistema.services.ReservationService;

public class TabelaDecisaoTests {
	ReservationService reservationService;
	
	FlightService flightService;
	
	final User MOCK_USER = new User("Fulano","123.456.789-00","+55(83)99982-6571");
	
	@BeforeEach
	public void createReservationService() {
		flightService = new FlightService();
		
		reservationService = new ReservationService(flightService);
	}
	
	@ReserveFlightTest
	@DisplayName("Realiza reserva de um voo - Id de voo inválido")
	public void reserveFlightInvalidIdTest() {
		assertThrows(InvalidPositiveException.class,()->{
			reservationService.reserveFlight(-1,MOCK_USER,3);
		});
	}
	
	@ReserveFlightTest
	@DisplayName("Realiza reserva de um voo - Id de voo desconhecido")
	public void reserveFlightUnknownIdTest() {
		assertThrows(UnknownIdException.class,()->{
			reservationService.reserveFlight(100,MOCK_USER,3);
		});
	}
	
	@ReserveFlightTest
	@DisplayName("Realiza reserva de um voo - Quantidade de assentos inválido")
	public void reserveFlightInvalidSeatsTest() {
		assertThrows(InvalidPositiveException.class,()->{
			reservationService.reserveFlight(4,MOCK_USER,-1);
		});
	}
	
	@ReserveFlightTest
	@DisplayName("Realiza reserva de um voo - Quantidade de assentos insuficiente")
	public void reserveFlightInsuficientSeatsTest() {
		assertThrows(InsuficientSeatsException.class,()->{
			reservationService.reserveFlight(4,MOCK_USER,1000);
		});
		
	}
	
	@ReserveFlightTest
	@DisplayName("Realiza reserva de um voo")
	public void reserveFlightTest() {
		assertEquals(3,reservationService.reservationRepository.count());
		
		Reservation newReservation = reservationService.reserveFlight(4,MOCK_USER,3);
		
		assertEquals(4,reservationService.reservationRepository.count());
		
		assertEquals(new Reservation(MOCK_USER,3,flightService.getFlight(4)),newReservation);
	}
	
	@CancelReservationTest
	@DisplayName("Cancelamento de reserva - Id da reserva inválido")
	public void cancelReserveByIdInvalidTest() {
		assertThrows(InvalidPositiveException.class,()->{
			reservationService.cancelReserveId(-1);
		});
	}
	
	@CancelReservationTest
	@DisplayName("Cancelamento de reserva - Id da reserva desconhecido")
	public void cancelReserveByIdUnkownTest() {
		assertThrows(UnknownIdException.class,()->{
			reservationService.cancelReserveId(1000);
		});
	}
	
	@CancelReservationTest
	@DisplayName("Cancelamento de reserva")
	public void cancelReserveByIdTest() {
		assertTrue(reservationService.cancelReserveId(1));
	}
	
	@ConfirmReservationTest
	@DisplayName("Confirmação de reserva - Id da reserva inválido")
	public void confirmReserveInvalidTest() {
		assertThrows(InvalidPositiveException.class,()->{
			reservationService.reservationRepository.getReservationById(-1);
		});
	}
	
	@ConfirmReservationTest
	@DisplayName("Confirmação de reserva - Id da reserva desconhecido")
	public void confirmReserveUnknownTest() {
		assertThrows(UnknownIdException.class,()->{
			reservationService.reservationRepository.getReservationById(1000);
		});
		
	}
	
	@ConfirmReservationTest
	@DisplayName("Confirmação de reserva")
	public void confirmReserveTest() {
		Reservation reservation = reservationService.reservationRepository.getReservationById(1);
		assertEquals(reservation.toString(),
				"Informações do Voo:\n"
				+ "Voo de João Pessoa para Recife, data: 01/01/2023 às 13:00.\n"
				+ "Voo disponível? Sim, preço: R$35.0\n"
				+ "Passageiros: 10/48\n"
				+ "\n"
				+ "Usuário emissor - Nome: Fulano, cpf: 123.456.789-00, telefone para contato: +55(83)99982-6571\n"
				+ "\n"
				+ "Passagens compradas: 10, Preço total da reserva: R$350.0");
	}
}
