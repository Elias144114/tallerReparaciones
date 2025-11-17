package dao.interfaces;

import java.util.ArrayList;

import java.sql.Connection;

import entities.Vehiculo;

public interface VehiculoDAO {
	int insert(Vehiculo v);
	int update(Vehiculo v);
	int delete(Vehiculo v);
	ArrayList<Vehiculo> findall();
	Vehiculo findByMatricula(String Matricula);
}
