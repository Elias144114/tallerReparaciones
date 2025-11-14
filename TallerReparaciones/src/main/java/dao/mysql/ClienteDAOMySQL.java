package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.DBCconnection;
import dao.interfaces.ClienteDAO;
import entities.Cliente;

public class ClienteDAOMySQL implements ClienteDAO{
private Connection conexion;
	
	public ClienteDAOMySQL() {
		conexion = DBCconnection.getInstance().getConnection();
	}
	
	@Override
	public int insert(Cliente c) {
		int resul = 0;
		try {

			String sql = "INSERT INTO cliente (idCliente, dni, nombre, telefono, email) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setInt(1, c.getIdCliente());
			pst.setString(2, c.getDni());
			pst.setString(3, c.getNombre());
			pst.setString(4, c.getTelefono());
			pst.setString(5, c.getEmail());
			
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
	public int update(Cliente c) {
		//Este servira para actualizarlos
	return 0;
	}
	
	@Override
	public int delete(String dni) {
		//Este servira para borrarlos
	return 0;
	}
	
	  @Override
	    public ArrayList<Cliente> findall() {
	        // Código JDBC para listar todos los Clientes
	        return new ArrayList<>(); // temporal
	    }
	
	  @Override
	    public Cliente findByDni(String dni) {
	        // Código JDBC para buscar usuario por nombre
	        return null; // temporal
	    }

}
