package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dao.mysql.ReparacionDAOMySQL;
import entities.Cliente;
import entities.Reparacion;

class ReparacionDAOTest {
	MySQLDAOFactory factory = new MySQLDAOFactory();
	LocalDate fechaEntrada = LocalDate.of(2025, 11, 15);
	ReparacionDAOMySQL reparacionDAO = (ReparacionDAOMySQL) factory.getReparacionDAO();
	
	
	@Test
	void test() {
		
//		Reparacion r1 = new Reparacion(1, "Cambio de aceite", fechaEntrada, 356.00, "En curso", 1, 1);
//		reparacionDAO.insert(r1);
		Reparacion r2 = new Reparacion(2, "Cambio pastillas de freno", fechaEntrada, 180.50, "Finalizada", 2, 2);
		reparacionDAO.insert(r2);
		Reparacion r3 = new Reparacion(3, "Fallo luces intermitentes", fechaEntrada, 75.20, "En curso", 2, 3);
		reparacionDAO.insert(r3);
		Reparacion r4 = new Reparacion(4, "Revisión 50.000km", fechaEntrada, 250.00, "En curso", 3, 4);
		reparacionDAO.insert(r4);
		Reparacion r5 = new Reparacion(5, "Sustitución 2 neumáticos", fechaEntrada, 210.75, "No iniciada", 2, 4);
		reparacionDAO.insert(r5);
//		
//		Reparacion r5cambio = new Reparacion(5, "Cambio de aceite y cambio de filtro de aceite", fechaEntrada, 346.9, "En curso", 2, 4);
//		reparacionDAO.update(r5cambio);
		//reparacionDAO.delete(r3);
	}
	
	@Test
	void testListar() {

//		ArrayList<Reparacion> reparaciones = reparacionDAO.findall();
//
//		System.out.println(" ");
//		System.out.println("           LISTADO DE REPARACIONES              ");
//		System.out.println(" ");
//
//		if (reparaciones.isEmpty()) {
//			System.out.println("No hay reparaciones en la base de datos.");
//			return;
//		}
//
//		for (Reparacion r : reparaciones) {
//			System.out.println("ID: " + r.getIdReparacion());
//			System.out.println("  Descripción: " + r.getDescripcion());
//			System.out.println("  Fecha de entrada: " + r.getFechaEntrada());
//			System.out.println("  Coste estimado: " + r.getCosteEstimado());
//			System.out.println("  Estado: " + r.getEstado());
//			System.out.println("  Id del Vehiculo: " + r.getVehiculoId());
//			System.out.println("  Id del Usuario: " + r.getUsuarioId());
//			System.out.println("");
//		}
	}

  }



