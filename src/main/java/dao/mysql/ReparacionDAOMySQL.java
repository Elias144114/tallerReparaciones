package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBCconnection;
import dao.interfaces.ReparacionDAO;
import entities.Reparacion;

public class ReparacionDAOMySQL implements ReparacionDAO {
private Connection conexion;
	
	public ReparacionDAOMySQL() {
		conexion = DBCconnection.getInstance().getConnection();
	}
	
	@Override
	public int insert(Reparacion r) {
		int resul = 0;
		try {

			String sql = "INSERT INTO reparacion (idReparacion, descripcion, fechaEntrada, costeEstimado, estado, vehiculoId, usuarioId) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setInt(1, r.getIdReparacion());
			pst.setString(2, r.getDescripcion());
			pst.setObject(3, r.getFechaEntrada());
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
	

	@Override
	public int update(Reparacion r) {
			int resul = 0;
			try {

				String sql = "UPDATE reparacion SET descripcion = ?, fechaEntrada = ?, costeEstimado = ?, estado = ? WHERE idReparacion = ?;";
				PreparedStatement pst = conexion.prepareStatement(sql);
					
				
				pst.setString(1, r.getDescripcion());
				pst.setObject(2, r.getFechaEntrada());
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

	@Override
	public int delete(Reparacion r) {
			String sqlDelete = "DELETE FROM reparacion WHERE idReparacion = ?;";
			try {
				PreparedStatement pst = conexion.prepareStatement(sqlDelete);
				pst.setInt(1, r.getIdReparacion()); 
				int filas = pst.executeUpdate();
				
				if (filas > 0) {
					System.out.println("> OK. Reparacion con " + r.getIdReparacion() + " eliminada correctamente.");
				} else {
					System.out.println("> NOK. Persona con " + r.getIdReparacion() + " no se encuentra en la base de datos.");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
	

	@Override
	public ArrayList<Reparacion> findall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reparacion findByidReparacion(String idReparacion) {
		// TODO Auto-generated method stub
		return null;
	}

}
