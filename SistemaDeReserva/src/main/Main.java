package main;

import java.util.Scanner;

import sistema.models.reservation.Reservation;
import sistema.models.user.User;
import sistema.services.FlightService;
import sistema.services.ReservationService;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	static User user = new User();
	
	static FlightService flightService;
	static ReservationService reservationService;
	
	public static void main(String[] args) {
		
		flightService = new FlightService();
		reservationService = new ReservationService(flightService);
		
		while(true) {
			Menu();
			
			String input = sc.nextLine();
			
			switch(input) {
			case "1":
				SubMenuUsuario();
				break;
			case "2":
				SubMenuListarVoos();
				break;
			case "3":
				SubMenuProcurarVoos();
				break;
			case "4":
				SubMenuReservarVoo();
				break;
			case "5":
				SubMenuCancelarReserva();
				break;
			case "6":
				SubMenuVerReservas();
				break;
			case "7":
				System.exit(0);
				break;
			default:
				System.out.println("Comando n�o reconhecido, tente novamente");
				break;
			}
		}
	}
	
	
	static void Menu() {
		System.out.println(
				"MENU\n" +
				"1) Definir usu�rio\n" +
				"2) Listar voos\n" +
				"3) Procurar por voos\n" +
				"4) Reservar voo\n" +
				"5) Cancelar reserva\n" +
				"6) Ver minhas reservas\n" + 
				"7) Sair\n"
		);
	}
	
	static void SubMenuVerReservas() {
		String text = reservationService.seeReserveConfirmations(user);
		System.out.println(text);	
	}
	
	static void SubMenuCancelarReserva() {
		System.out.println(
				"M�todos de cancelar reserva\n" +
				"1) Cancelar pelo id da reserva\n" +
				"2) Cancelar todas as reservas do usu�rio\n" +
				"3) Voltar\n"
		);
		
		String input = sc.nextLine();
		
		switch(input) {
		case "1":
			System.out.println("Digite o id da reserva que deseja cancelar: ");
			int intInput= sc.nextInt();
			boolean result = reservationService.cancelReserveId(intInput);
			
			if (result) {
				System.out.println("Reserva cancelada com sucesso!\n");
			} else {
				System.out.println("ERRO: Reserva n�o encontrada.\n");
			}
			
			break;
		case "2":
			result = reservationService.cancelReservesOfUser(user);
			
			if (result) {
				System.out.println("Reservas canceladas com sucesso!\n");
			} else {
				System.out.println("ERRO: Esse usu�rio n�o possui nenhuma reserva.\n");
			}
			break;
		case "3":
			break;
		default:
			System.out.println("Comando n�o reconhecido, tente novamente");
			break;
		}
	}
	
	static void SubMenuReservarVoo() {
		String text = flightService.showFlights();
		System.out.println(text);
		
		System.out.println("Digite o id do voo que voc� deseja reservar:");
		
		int flightId = sc.nextInt();
		
		System.out.println("Digite o n�mero de passagens a reservar:");
		
		int passagensNum = sc.nextInt();
		
		Reservation newReservation = reservationService.reserveFlight(flightId, user, passagensNum);
		
		System.out.println("Reserva criada:\n" + newReservation);
	}
	
	static void SubMenuProcurarVoos() {
		
		System.out.println(
				"Op��es de pesquisa de voo\n" +
				"1) Buscar por origem\n" +
				"2) Buscar por destino\n" +
				"3) Buscar por pre�o\n" +
				"4) Buscar por data\n" +
				"5) Buscar por assentos livres\n" +
				"6) Buscar por disponibilidade\n" + 
				"7) Voltar\n"
		);
		
		String input = sc.nextLine();
		
		String text;
		
		switch(input) {
		case "1":
			System.out.println("Digite o local de origem: ");
			input = sc.nextLine();
			text = flightService.searchFlightByOrigin(input);
			System.out.println(text);
			break;
		case "2":
			System.out.println("Digite o local de destino: ");
			input = sc.nextLine();
			text = flightService.searchFlightByDestination(input);
			System.out.println(text);
			break;
		case "3":
			System.out.println("Digite o pre�o m�ximo da passagem: ");
			double doubleInput = sc.nextDouble();
			text = flightService.searchFlightByPrice(doubleInput);
			System.out.println(text);
			break;
		case "4":
			System.out.println("Digite a data do voo (DD/MM/YYYY): ");
			input = sc.nextLine();
			text = flightService.searchFlightByDate(input);
			System.out.println(text);
			break;
		case "5":
			System.out.println("Digite o n�mero m�nimo de assentos livres: ");
			int intInput = sc.nextInt();
			text = flightService.searchFlightBySeatCount(intInput);
			System.out.println(text);
			break;
		case "6":
			text = flightService.searchFlightByAvailability();
			System.out.println(text);
			break;
		case "7":
			break;
		default:
			System.out.println("Comando n�o reconhecido, tente novamente");
			break;
		}
		
	}
	
	static void SubMenuListarVoos() {
		String text = flightService.showFlights();
		System.out.println(text);
	}
	
	static void SubMenuUsuario() {
		User creatingUser = new User();
		
		System.out.println("Digite o seu nome: ");
		String input = sc.nextLine();
		creatingUser.setName(input);
		
		System.out.println("Digite o seu cpf: ");
		input = sc.nextLine();
		creatingUser.setCpf(input);
		
		System.out.println("Digite o seu telefone de contato: ");
		input = sc.nextLine();
		creatingUser.setContato(input);
		
		user = creatingUser;
	}
	
	
}
