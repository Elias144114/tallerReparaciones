package dao;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dao.mysql.ReparacionDAOMySQL;
import entities.Reparacion;

class ReparacionDAOTest {

	@Test
	void test() {
		MySQLDAOFactory factory = new MySQLDAOFactory();

		LocalDate fechaEntrada = LocalDate.of(2025, 11, 15);
		ReparacionDAOMySQL reparacionDAO = (ReparacionDAOMySQL) factory.getReparacionDAO();
		Reparacion r1 = new Reparacion(1, "Cambio de aceite", fechaEntrada, 356.00, "En curso", 1, 1);
		reparacionDAO.insert(r1);
		Reparacion r2 = new Reparacion(2, "Cambio pastillas de freno", fechaEntrada, 180.50, "Pendiente", 2, 1);
		reparacionDAO.insert(r2);
		Reparacion r3 = new Reparacion(3, "Fallo luces intermitentes", fechaEntrada, 75.20, "En diagnóstico", 1, 2);
		reparacionDAO.insert(r3);
		Reparacion r4 = new Reparacion(4, "Revisión 50.000km", fechaEntrada, 250.00, "En curso", 3, 3);
		reparacionDAO.insert(r4);
		Reparacion r5 = new Reparacion(5, "Sustitución 2 neumáticos", fechaEntrada, 210.75, "Finalizado", 2, 4);
		reparacionDAO.insert(r5);
		
		//reparacionDAO.delete(r3);
	}
	

}
