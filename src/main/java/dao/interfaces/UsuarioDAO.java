package dao.interfaces;

import java.util.ArrayList;

import entities.Reparacion;
import entities.Usuario;
import java.sql.Connection;

public interface UsuarioDAO {
	boolean login(String dni, String password);
	int insert(Usuario u);
	ArrayList<Usuario> findall();
	Usuario findByDni(String dni);
	Usuario findById(int id);
	int delete(String dni);
	int update(Usuario u);

}
