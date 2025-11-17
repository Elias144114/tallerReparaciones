package dao;

import org.junit.jupiter.api.Test;

import dao.mysql.ClienteDAOMySQL;
import entities.Cliente;

class ClienteDAOTest {

	@Test
	void test() {
		MySQLDAOFactory factory = new MySQLDAOFactory();
		ClienteDAOMySQL clienteDAO = (ClienteDAOMySQL) factory.getClienteDAO();
		
		
//		Cliente c1 = new Cliente(1, "45485547X", "Alberto", "625649727", "Cliente1@gmail.com");
//		clienteDAO.insert(c1);
//		Cliente c2 = new Cliente(2, "96528367W", "Pierna", "625452485", "Cliente2@gmail.com");
//		clienteDAO.insert(c2);
//		Cliente c3 = new Cliente(3, "78945612Y", "Bea", "666555444", "Cliente3@gmail.com");
//		clienteDAO.insert(c3);
//		Cliente c4 = new Cliente(4, "12345678Z", "Carlos", "600123456", "Cliente4@gmail.com");
//		clienteDAO.insert(c4);
//		Cliente c5 = new Cliente(5, "90123456A", "Diana", "611789012", "Cliente5@gmail.com");
//		clienteDAO.insert(c5);

		
		//Probando el eliminar un cliente partiendo de su Dni
		//clienteDAO.delete("12345678Z");

	}

}
