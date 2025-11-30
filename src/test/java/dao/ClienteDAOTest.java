package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dao.mysql.ClienteDAOMySQL;
import entities.Cliente;

class ClienteDAOTest {
	MySQLDAOFactory factory = new MySQLDAOFactory();
	ClienteDAOMySQL clienteDAO = (ClienteDAOMySQL) factory.getClienteDAO();

	@Test
	void test() {

//		Cliente c1 = new Cliente(1, "45485547X", "Alberto", "625649727", "Cliente1@gmail.com");
//		clienteDAO.insert(c1);
		Cliente c2 = new Cliente(2, "96528367W", "Miguel", "625452485", "Cliente2@gmail.com");
		clienteDAO.insert(c2);
		Cliente c3 = new Cliente(3, "78945612Y", "Bea", "666555444", "Cliente3@gmail.com");
		clienteDAO.insert(c3);
		Cliente c4 = new Cliente(4, "12345678Z", "Carlos", "600123456", "Cliente4@gmail.com");
		clienteDAO.insert(c4);
		Cliente c5 = new Cliente(5, "90123456A", "Diana", "611789012", "Cliente5@gmail.com");
		clienteDAO.insert(c5);
//		
//		Cliente pruebaUpdate = new Cliente(6, "46134275E", "Virginia", "461342756", "ClienteParaProbar@gmail.com");
//		clienteDAO.insert(pruebaUpdate);
//		
//		Cliente clienteCambiado = new Cliente(6, "46134275E", "Alicia", "7898475147", "ClienteParamodificar@gmail.com");
//		int res = clienteDAO.update(clienteCambiado);
//		assertEquals(res, 1);
//		
		// Probando el eliminar un cliente partiendo de su Dni
		// clienteDAO.delete("12345678Z");

	}

	@Test
	void testListar() {

//		ArrayList<Cliente> clientes = clienteDAO.findall();
//
//		System.out.println(" ");
//		System.out.println("           LISTADO DE CLIENTES              ");
//		System.out.println(" ");
//
//		if (clientes.isEmpty()) {
//			System.out.println("No hay clientes en la base de datos.");
//			return;
//		}
//
//		for (Cliente c : clientes) {
//			System.out.println("ID: " + c.getIdCliente());
//			System.out.println("  DNI: " + c.getDni());
//			System.out.println("  Nombre: " + c.getNombre());
//			System.out.println("  Teléfono: " + c.getTelefono());
//			System.out.println("  Email: " + c.getEmail());
//			System.out.println("");
//		}
	}

	@Test
	void testFindBy() {
//		String dniExistente = "45485547X";
//		System.out.println("Buscando DNI: " + dniExistente);
//
//		Cliente clienteEncontrado = clienteDAO.findByDni(dniExistente);
//
//		if (clienteEncontrado != null) {
//			System.out.println("Cliente encontrado:");
//			System.out.println("   Nombre: " + clienteEncontrado.getNombre());
//			System.out.println("   Teléfono: " + clienteEncontrado.getTelefono());
//		} else {
//			System.out.println("Cliente con DNI " + dniExistente + " no encontrado.");
//		}
//
//		System.out.println("");
//
//
	}
}

