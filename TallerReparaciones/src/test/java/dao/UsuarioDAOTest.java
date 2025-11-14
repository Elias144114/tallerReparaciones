package dao;

import org.junit.jupiter.api.Test;

import dao.mysql.UsuarioDAOMySQL;
import entities.Usuario;

class UsuarioDAOTest {

	@Test
	void test() {
		MySQLDAOFactory factory = new MySQLDAOFactory();
		int resul = 0;

		UsuarioDAOMySQL usuarioDAO = (UsuarioDAOMySQL) factory.getUsuarioDAO();
		Usuario u = new Usuario(0, "Alberto", "987987", "943f321je", "Cliente");
		
		usuarioDAO.insert(u);
	}
}


