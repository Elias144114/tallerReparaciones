package dao.interfaces;

import java.util.ArrayList;

import entities.Reparacion;
import java.sql.Connection;

public interface ReparacionDAO {
	int insert(Reparacion r);
	int update(Reparacion r);
	int delete(int idReparacion);
	ArrayList<Reparacion> findall();
	ArrayList<Reparacion> findByEstado(String estado);
	Reparacion findByIdReparacion(int idReparacion);
	
}

