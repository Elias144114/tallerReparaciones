package dao.interfaces;

import java.util.ArrayList;
import java.sql.Connection;

import entities.Cliente;

public interface ClienteDAO {
	int insert(Cliente c);
	int update(Cliente c);
	int delete(String dni);
	ArrayList<Cliente> findall();
	Cliente findByDni(String dni);
	Cliente findById(int idCliente);

}
