package dao.interfaces;

import java.util.ArrayList;

import entities.Reparacion;
import java.sql.Connection;

public interface ReparacionDAO {
	int insert(Reparacion r);
	int update(Reparacion r);
	int delete(Reparacion r);
	ArrayList<Reparacion> findall();
	Reparacion findByidReparacion(String idReparacion);
}

