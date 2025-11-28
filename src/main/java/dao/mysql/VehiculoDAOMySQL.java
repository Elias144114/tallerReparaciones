package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBCconnection;
import dao.interfaces.VehiculoDAO;
import entities.Cliente;
import entities.Vehiculo;

public class VehiculoDAOMySQL implements VehiculoDAO {
	private Connection conexion;

	public VehiculoDAOMySQL() {
		conexion = DBCconnection.getInstance().getConnection();
	}

	@Override
	public int insert(Vehiculo v) {
		int resul = 0;
		try {

			String sql = "INSERT INTO vehiculo (idVehiculo, matricula, marca, modelo, clienteId) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setInt(1, v.getIdVehiculo());
			pst.setString(2, v.getMatricula());
			pst.setString(3, v.getMarca());
			pst.setString(4, v.getModelo());
			pst.setInt(5, v.getClienteId());

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
	public int update(Vehiculo v) {
		int resul = 0;
		try {

			String sql = "UPDATE vehiculo SET marca = ?, modelo = ? WHERE matricula = ?;";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setString(1, v.getMarca());
			pst.setObject(2, v.getModelo());
			pst.setString(3, v.getMatricula());

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
	public int delete(Vehiculo v) {
		String sqlDelete = "DELETE FROM vehiculo WHERE idVehiculo = ?;";
		try {
			PreparedStatement pst = conexion.prepareStatement(sqlDelete);
			pst.setInt(1, v.getIdVehiculo());
			int filas = pst.executeUpdate();

			if (filas > 0) {
				System.out.println("> OK. Reparacion con  id " + v.getIdVehiculo() + " eliminada correctamente.");
			} else {
				System.out.println(
						"> NOK. Persona con id " + v.getIdVehiculo() + " no se encuentra en la base de datos.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Vehiculo> findall() {
		ArrayList<Vehiculo> vehiculos = new ArrayList<>();
		String sql = "SELECT idVehiculo, matricula, marca, modelo, clienteId FROM Vehiculo;";
		try (PreparedStatement pst = conexion.prepareStatement(sql); ResultSet resul = pst.executeQuery()) {

			while (resul.next()) {

				Vehiculo v = new Vehiculo();
				v.setIdVehiculo(resul.getInt("idVehiculo"));
				v.setMatricula(resul.getString("matricula"));
				v.setMarca(resul.getString("marca"));
				v.setModelo(resul.getString("modelo"));
				v.setClienteId(resul.getInt("clienteId"));

				vehiculos.add(v);
			}
		} catch (SQLException e) {
			System.out.println("> NOK en findall: " + e.getMessage());
			e.printStackTrace();
		}
		return vehiculos;
	}

	@Override
	public Vehiculo findByMatricula(String matricula) {
		Vehiculo vehiculo = null;
		String sql = "SELECT idVehiculo, matricula, marca, modelo, clienteId FROM vehiculo WHERE matricula = ?;";

		try (PreparedStatement pst = conexion.prepareStatement(sql)) {

			pst.setString(1, matricula);

			try (ResultSet resul = pst.executeQuery()) {
				if (resul.next()) {
					vehiculo = new Vehiculo();

					vehiculo.setIdVehiculo(resul.getInt("idVehiculo"));
					vehiculo.setMatricula(resul.getString("matricula"));
					vehiculo.setMarca(resul.getString("marca"));
					vehiculo.setModelo(resul.getString("modelo"));
					vehiculo.setClienteId(resul.getInt("clienteId"));
				}
			}
		} catch (SQLException e) {
			System.out.println("> ERROR en findByMatricula: " + e.getMessage());
			e.printStackTrace();
		}
		return vehiculo;
	}

	@Override
	public Vehiculo findByid(int id) {
		Vehiculo vehiculo = null;
		String sql = "SELECT idVehiculo, matricula, marca, modelo, clienteId FROM vehiculo WHERE idVehiculo = ?;";

		try (PreparedStatement pst = conexion.prepareStatement(sql)) {

			pst.setInt(1, id); 

			try (ResultSet resul = pst.executeQuery()) {
				if (resul.next()) {
					vehiculo = new Vehiculo();
					
					vehiculo.setIdVehiculo(resul.getInt("idVehiculo"));
					vehiculo.setMatricula(resul.getString("matricula"));
					vehiculo.setMarca(resul.getString("marca"));
					vehiculo.setModelo(resul.getString("modelo"));
					vehiculo.setClienteId(resul.getInt("clienteId"));
				}
			}
		} catch (SQLException e) {
			System.out.println("> NOK en findByid: " + e.getMessage());
			e.printStackTrace();
		}
		return vehiculo;
	}

}
