package dao;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dao.mysql.ReparacionDAOMySQL;
import dao.mysql.UsuarioDAOMySQL;
import entities.Reparacion;
import entities.Usuario;

class UsuarioDAOTest {
	private MySQLDAOFactory factory = new MySQLDAOFactory();
	private UsuarioDAOMySQL usuarioDAO = (UsuarioDAOMySQL) factory.getUsuarioDAO();
	
	@Test
	void testInsert() {
		

		
//		Usuario u1 = new Usuario(1, "Alberto", "987987", "943f321je", "Cliente");
//		usuarioDAO.insert(u1);
//		Usuario u2 = new Usuario(2, "Felipe", "334455", "pqr1314stu", "Cliente");
//		usuarioDAO.insert(u2);
//		Usuario u3 = new Usuario(3, "Beatriz", "123456", "abc456def", "Cliente");
//		usuarioDAO.insert(u3);
//		Usuario u4 = new Usuario(4, "Carlos", "445566", "xyz789ijk", "Empleado");
//		usuarioDAO.insert(u4);
//		Usuario u5 = new Usuario(5, "Diana", "778899", "1011lmn12", "Empleado");
//		usuarioDAO.insert(u5);

	}
	
	@Test
	void testLogin() {
		// insert
//		Usuario probarLogin = new Usuario(7, "11111111P", "Elias", "Contraseña", "Cliente");
//		usuarioDAO.insert(probarLogin);
//		

		usuarioDAO.login("11111111P", "Contraseña");

		
	usuarioDAO.login("11111111P", "PassMala");
		// login

	}

}
