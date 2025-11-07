package dao.mysql;

import java.util.ArrayList;

import dao.interfaces.ClienteDAO;
import entities.Cliente;

public class ClienteDAOMySQL implements ClienteDAO{
	
	@Override
	public int insert(Cliente c) {
		//Aqui irá el metodo para añadir a un cliente
		return 0;
		}
	
	@Override
	public int update(Cliente c) {
		//Este servira para actualizarlos
	return 0;
	}
	
	@Override
	public int delete(String dni) {
		//Este servira para borrarlos
	return 0;
	}
	
	  @Override
	    public ArrayList<Cliente> findall() {
	        // Código JDBC para listar todos los Clientes
	        return new ArrayList<>(); // temporal
	    }
	
	  @Override
	    public Cliente findByDni(String dni) {
	        // Código JDBC para buscar usuario por nombre
	        return null; // temporal
	    }

}
