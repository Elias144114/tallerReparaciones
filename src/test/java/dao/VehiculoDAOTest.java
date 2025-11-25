package dao;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dao.mysql.VehiculoDAOMySQL;
import entities.Reparacion;
import entities.Vehiculo;

class VehiculoDAOTest {
	MySQLDAOFactory factory = new MySQLDAOFactory();
	VehiculoDAOMySQL vehiculoDAO = (VehiculoDAOMySQL) factory.getVehiculoDAO();

	@Test
	void test() {

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

//		Vehiculo v5Moficar = new Vehiculo(5, "7733 XYZ", "Peugot", "Mp5", 5);
//		vehiculoDAO.update(v5Moficar);
		// vehiculoDAO.delete(v3);
	}

	@Test
	void testListar() {

		ArrayList<Vehiculo> vehiculos = vehiculoDAO.findall();

		System.out.println(" ");
		System.out.println("           LISTADO DE VEHICULOS              ");
		System.out.println(" ");

		if (vehiculos.isEmpty()) {
			System.out.println("No hay Vehiculos en la base de datos.");
			return;
		}

		for (Vehiculo v : vehiculos) {
			System.out.println("ID: " + v.getIdVehiculo());
			System.out.println("  Matricula: " + v.getMatricula());
			System.out.println("  Marca: " + v.getMarca());
			System.out.println("  Modelo: " + v.getModelo());
			System.out.println("  Id del cliente: " + v.getClienteId());
			System.out.println("");
		}
	}

}
