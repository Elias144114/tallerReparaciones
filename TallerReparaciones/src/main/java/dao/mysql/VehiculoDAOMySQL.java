package dao.mysql;

import java.util.ArrayList;

import dao.interfaces.VehiculoDAO;
import entities.Vehiculo;

public class VehiculoDAOMySQL implements VehiculoDAO{

	@Override
	public int insert(Vehiculo v) {
		//Para añadir un vehiculo
		return 0;
	}

	@Override
	public int update(Vehiculo v) {
		// Para actualizar un vehiculo
		return 0;
	}

	@Override
	public int delete(Vehiculo v) {
		// Para eliminar un vehiculo
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
