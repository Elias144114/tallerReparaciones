package dao;

import org.junit.jupiter.api.Test;

import dao.mysql.VehiculoDAOMySQL;

import entities.Vehiculo;

class VehiculoDAOTest {

	@Test
	void test() {
		MySQLDAOFactory factory = new MySQLDAOFactory();
		VehiculoDAOMySQL vehiculoDAO = (VehiculoDAOMySQL) factory.getVehiculoDAO();
		
		
//		Vehiculo v1 = new Vehiculo(1, "1111 FGG", "Toyota", "Corolla", 1);
//		vehiculoDAO.insert(v1);
//		Vehiculo v2 = new Vehiculo(2, "4596 JSE", "Opel", "Astra", 2);
//		vehiculoDAO.insert(v2);
//		Vehiculo v3 = new Vehiculo(3, "9876 ABC", "Ford", "Fiesta", 3);
//		vehiculoDAO.insert(v3);
//		Vehiculo v4 = new Vehiculo(4, "1122 DFG", "Audi", "A4", 4);
//		vehiculoDAO.insert(v4);
//		Vehiculo v5 = new Vehiculo(5, "7733 XYZ", "BMW", "X3", 5);
//		vehiculoDAO.insert(v5);
		
		Vehiculo v5Moficar = new Vehiculo(5, "7733 XYZ", "Peugot", "Mp5", 5);
		vehiculoDAO.update(v5Moficar);
		//vehiculoDAO.delete(v3);
	}

}
