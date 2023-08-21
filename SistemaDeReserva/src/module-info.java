module sistemaDeReserva {
	requires junit;
	
	exports main;
	exports sistema.models.user;
	exports sistema.models.flight;
	exports sistema.models.reservation;
	exports sistema.repositories;
	exports sistema.services;
}