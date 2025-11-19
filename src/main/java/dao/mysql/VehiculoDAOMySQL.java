package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBCconnection;
import dao.interfaces.VehiculoDAO;
import entities.Vehiculo;

public class VehiculoDAOMySQL implements VehiculoDAO{
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
				System.out.println("> NOK. Persona con id " + v.getIdVehiculo() + " no se encuentra en la base de datos.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Vehiculo> findall() {
		//Para listar todos los vehiculos
		return null;
	}

	@Override
	public Vehiculo findByMatricula(String Matricula) {
		// Para buscar en base a la matricula
		return null;
	}

}
