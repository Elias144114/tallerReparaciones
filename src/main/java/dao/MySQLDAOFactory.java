package dao;

import dao.mysql.ClienteDAOMySQL;
import dao.mysql.ReparacionDAOMySQL;
import dao.mysql.UsuarioDAOMySQL;
import dao.mysql.VehiculoDAOMySQL;

/**
 * Clase para implementar las clases DAO
 */
public class MySQLDAOFactory implements DAOFactory {

	@Override
	public UsuarioDAOMySQL getUsuarioDAO() {
		return new UsuarioDAOMySQL();
		
	}

	@Override
	public VehiculoDAOMySQL getVehiculoDAO() {
		return new VehiculoDAOMySQL();
	}

	@Override
	public ReparacionDAOMySQL getReparacionDAO() {
		return new ReparacionDAOMySQL();
	}

	@Override
	public ClienteDAOMySQL getClienteDAO() {
		return new ClienteDAOMySQL();
	}
	
}
