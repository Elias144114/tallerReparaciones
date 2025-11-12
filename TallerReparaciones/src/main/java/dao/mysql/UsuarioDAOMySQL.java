package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import dao.DBCconnection;
import dao.interfaces.UsuarioDAO;
import dwes.maven.mysql.PasswordUtils;
import entities.Usuario;


public class UsuarioDAOMySQL implements UsuarioDAO {
	private Connection conexion;
	
	public UsuarioDAOMySQL() {
		conexion = DBCconnection.getInstance().getConnection();
	}

	
    @Override
    public boolean login(String dni, String password) {
        // Aquí irá el código JDBC para comprobar usuario y contraseña
        return false; // temporal
    }

	@Override
	public int insert(Usuario u) {
		int resul = 0;
		try {

			String sql = "INSERT INTO usuario (idUsuario, nombreUsuario, dni, password, rol) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setInt(1, u.getIdUsuario());
			pst.setString(2, u.getNombreUsuario());
			pst.setString(3, u.getDni());
			pst.setString(4, PasswordUtils.hashPassword(u.getPassword()));
			pst.setString(5, u.getRol());

			resul = pst.executeUpdate();
			System.out.println("Resultado de inserción: " + resul);
		} catch (SQLException e) {
			System.out.println("> NOK: " + e.getMessage());
			resul = -1;
		} catch (Exception e) {
			System.out.println("> Error: " + e.getMessage());
			resul = -2;
		}
		return resul;
	}
	

    @Override
    public ArrayList<Usuario> findall() {
        // Código JDBC para listar todos los usuarios
        return new ArrayList<>(); // temporal
    }

    @Override
    public Usuario findByNombre(String nombre) {
        // Código JDBC para buscar usuario por nombre
        return null; // temporal
    }


	
}