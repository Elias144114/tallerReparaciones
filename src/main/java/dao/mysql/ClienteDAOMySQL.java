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

public class ClienteDAOMySQL implements ClienteDAO {
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
		int resul = 0;
		try {

			String sql = "UPDATE cliente SET nombre = ?, telefono = ?, email = ? WHERE dni = ?;";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setString(1, c.getNombre());
			pst.setString(2, c.getTelefono());
			pst.setString(3, c.getEmail());
			pst.setString(4, c.getDni());

			resul = pst.executeUpdate();
			System.out.println("Resultado de inserción: " + resul);
		} catch (SQLException e) {
			System.out.println("> NOK: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("> Error: " + e.getMessage());
		}
		return resul;
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
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT idCliente, dni, nombre, telefono, email FROM cliente;";
		try (PreparedStatement pst = conexion.prepareStatement(sql); 
				ResultSet resul = pst.executeQuery()) {

			// 1. Recorrer el ResultSet
			while (resul.next()) {
				
				Cliente c = new Cliente();
				c.setIdCliente(resul.getInt("idCliente"));
				c.setDni(resul.getString("dni"));
				c.setNombre(resul.getString("nombre"));
				c.setTelefono(resul.getString("telefono"));
				c.setEmail(resul.getString("email"));

				clientes.add(c);
			}
		} catch (SQLException e) {
			System.out.println("> NOK en findall: " + e.getMessage());
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public Cliente findByDni(String dni) {
		Cliente cliente = null;
		String sql = "SELECT idCliente, dni, nombre, telefono, email FROM cliente WHERE dni = ?;";

		try (PreparedStatement pst = conexion.prepareStatement(sql)) {

			pst.setString(1, dni);

			try (ResultSet resul = pst.executeQuery()) {
				if (resul.next()) {
					cliente = new Cliente();
					cliente.setIdCliente(resul.getInt("idCliente"));
					cliente.setDni(resul.getString("dni"));
					cliente.setNombre(resul.getString("nombre"));
					cliente.setTelefono(resul.getString("telefono"));
					cliente.setEmail(resul.getString("email"));
				}
			}
		} catch (SQLException e) {
			System.out.println("> NOK en findByDni: " + e.getMessage());
			e.printStackTrace();
		}
		return cliente;
	}
}
