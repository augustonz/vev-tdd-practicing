module sistemaDeReserva {
	requires junit;
	requires org.junit.jupiter.api;
	
	exports main;
	exports sistema.models.user;
	exports sistema.models.flight;
	exports sistema.models.reservation;
	exports sistema.repositories;
	exports sistema.services;
	exports functionalTests;
	exports junit5Tests;
}