package dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBCconnection;
import dao.interfaces.ReparacionDAO;
import entities.Cliente;
import entities.Reparacion;

public class ReparacionDAOMySQL implements ReparacionDAO {
	private Connection conexion;

	public ReparacionDAOMySQL() {
		conexion = DBCconnection.getInstance().getConnection();
	}
/**
 * Sirve para insertar reparaciones en la base de datos
 */
	@Override
	public int insert(Reparacion r) {
		int resul = 0;
		try {

			String sql = "INSERT INTO reparacion (idReparacion, descripcion, fechaEntrada, costeEstimado, estado, vehiculoId, usuarioId) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setInt(1, r.getIdReparacion());
			pst.setString(2, r.getDescripcion());
			pst.setDate(3, java.sql.Date.valueOf(r.getFechaEntrada()));
			pst.setDouble(4, r.getCosteEstimado());
			pst.setString(5, r.getEstado());
			pst.setInt(6, r.getVehiculoId());
			pst.setInt(7, r.getUsuarioId());

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
/**
 * Sirve para actualizar los datos del reparaciones de la base de datos
 */
	@Override
	public int update(Reparacion r) {
		int resul = 0;
		try {

			String sql = "UPDATE reparacion SET descripcion = ?, fechaEntrada = ?, costeEstimado = ?, estado = ? WHERE idReparacion = ?;";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setString(1, r.getDescripcion());
			pst.setDate(2, java.sql.Date.valueOf(r.getFechaEntrada()));
			pst.setDouble(3, r.getCosteEstimado());
			pst.setString(4, r.getEstado());
			pst.setInt(5, r.getIdReparacion());

			resul = pst.executeUpdate();
			System.out.println("Resultado de inserción: " + resul);
		} catch (SQLException e) {
			System.out.println("> NOK: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("> Error: " + e.getMessage());
		}
		return resul;
	}

	/**
	 * Sirve para borrar la base de datos
	 */
	@Override
	public int delete(int idReparacion) {
		String sqlDelete = "DELETE FROM reparacion WHERE idReparacion = ?;";
		try {
			PreparedStatement pst = conexion.prepareStatement(sqlDelete);
			pst.setInt(1, idReparacion);
			int filas = pst.executeUpdate();

			if (filas > 0) {
				System.out.println("> OK. Reparacion con " + idReparacion + " eliminada correctamente.");
			} else {
				System.out
						.println("> NOK. Persona con " + idReparacion + " no se encuentra en la base de datos.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * Sirve para encontrar TODAS las reparaciones 
	 */
	@Override
	public ArrayList<Reparacion> findall() {
		ArrayList<Reparacion> reparaciones = new ArrayList<>();
		String sql = "SELECT idReparacion, descripcion, fechaEntrada, costeEstimado, estado, vehiculoId, usuarioId FROM reparacion;";

		try (PreparedStatement pst = conexion.prepareStatement(sql); ResultSet resul = pst.executeQuery()) {

			while (resul.next()) {

				Reparacion r = new Reparacion();
				r.setIdReparacion(resul.getInt("idReparacion"));
				r.setDescripcion(resul.getString("descripcion"));
				r.setFechaEntrada(resul.getDate("fechaEntrada").toLocalDate());
				r.setCosteEstimado(resul.getDouble("costeEstimado"));
				r.setEstado(resul.getString("estado"));
				r.setVehiculoId(resul.getInt("vehiculoId"));
				r.setUsuarioId(resul.getInt("usuarioId"));

				reparaciones.add(r);
			}
		} catch (SQLException e) {
			System.out.println("> NOK en findall: " + e.getMessage());
			e.printStackTrace();
		}
		return reparaciones;
	}
	/**
	 * Sirve para encontrar reparaciones por id
	 */
	@Override
	public Reparacion findByIdReparacion(int idReparacion) {
		 Reparacion r = null;
		    String sql = "SELECT idReparacion, descripcion, fechaEntrada, costeEstimado, estado, vehiculoId, usuarioId FROM reparacion WHERE idReparacion = ?;";
		    
		    try (PreparedStatement pst = conexion.prepareStatement(sql)) {
		        
		        pst.setInt(1, idReparacion);
		        
		        try (ResultSet resul = pst.executeQuery()) {
		            if (resul.next()) {
		            	r = new Reparacion();
		            	r.setIdReparacion(resul.getInt("idReparacion"));
		            	r.setDescripcion(resul.getString("descripcion"));
		            	r.setFechaEntrada(resul.getDate("fechaEntrada").toLocalDate());
		            	r.setCosteEstimado(resul.getDouble("costeEstimado"));
		            	r.setEstado(resul.getString("estado"));
		            	r.setVehiculoId(resul.getInt("vehiculoId"));
		            	r.setVehiculoId(resul.getInt("usuarioId"));
		            }
		        }
		    } catch (SQLException e) {
		        System.out.println("> NOK en findByDni: " + e.getMessage());
		        e.printStackTrace();
		    }
		    return r;
	}

	/**
	 * Sirve para encontrar reparaciones por estado
	 */
	@Override
	public ArrayList<Reparacion> findByEstado(String estado) {
	    ArrayList<Reparacion> reparaciones = new ArrayList<>();
	    String sql = "SELECT * FROM reparacion WHERE estado = ? ORDER BY fechaEntrada DESC;";

	    try (PreparedStatement pst = conexion.prepareStatement(sql)) {
	        
	        pst.setString(1, estado);
	        
	        try (ResultSet resul = pst.executeQuery()) {
	            while (resul.next()) {
	                Reparacion r = new Reparacion();
	                r.setIdReparacion(resul.getInt("idReparacion"));
	                r.setDescripcion(resul.getString("descripcion"));
	                r.setFechaEntrada(resul.getDate("fechaEntrada").toLocalDate());
	                r.setCosteEstimado(resul.getDouble("costeEstimado"));
	                r.setEstado(resul.getString("estado"));
	                r.setVehiculoId(resul.getInt("vehiculoId"));
	                r.setUsuarioId(resul.getInt("usuarioId"));
	                reparaciones.add(r);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("> NOK en findByEstado: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return reparaciones;
	}
}
