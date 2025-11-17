package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		try {
			ResultSet resultado = null;
			conexion.setAutoCommit(false);
			String sql = "SELECT idEmpleado, nombre, edad, numTelefono FROM persona WHERE edad > ?";

			PreparedStatement pst = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, // Sensible a
																									// cambios
					ResultSet.CONCUR_UPDATABLE); // Permite modificar

			pst.setInt(1, c.getIdCliente());
			resultado = pst.executeQuery();

			while (resultado.next()) {

				pst.setString(1, c.getDni());
		        pst.setString(2, c.getNombre());
		        pst.setString(3, c.getTelefono());
		        pst.setString(4, c.getEmail());
		        pst.setInt(5, c.getIdCliente());
				resultado.updateRow();

			}

			conexion.commit();
			System.out.println("> Cambios confirmados correctamente");

		} catch (SQLException e) {
			if (conexion != null) {
				try {
					conexion.rollback();
					System.out.println("> Cambios confirmados correctamente");
				} catch (SQLException e1) {
					System.out.println("> NOK:" + e.getMessage());
				}

			}
		} finally {
			if (conexion != null) {
				try {
					conexion.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;

	}
	
	@Override
	public int delete(String dni) {
		String sqlDelete = "DELETE FROM cliente WHERE dni = ?;";
		try {
			PreparedStatement pst = conexion.prepareStatement(sqlDelete);
			pst.setString(1, dni); 
			int filas = pst.executeUpdate();
			
			if (filas > 0) {
				System.out.println("> OK. Persona con dni " + dni + " eliminada correctamente.");
			} else {
				System.out.println("> NOK. Persona con dni " + dni + " no se encuentra en la base de datos.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
