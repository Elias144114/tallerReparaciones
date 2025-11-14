package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.DBCconnection;
import dao.interfaces.ReparacionDAO;
import dwes.maven.mysql.PasswordUtils;
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

			String sql = "INSERT INTO reparacion (idReparacion, descripcion, fechaEntrada, costeEstimado, vehiculoId, usuarioId) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setInt(1, r.getIdReparacion());
			pst.setString(2, r.getDescripcion());
			LocalDate fechaEntrada = LocalDate.now();
			pst.setDate(3, java.sql.Date.valueOf(r.getFechaEntrada()));
			pst.setDouble(4, r.getCosteEstimado());
			pst.setInt(5, r.getVehiculoId());
			pst.setInt(6, r.getUsuarioId());

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Reparacion r) {
		// TODO Auto-generated method stub
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
