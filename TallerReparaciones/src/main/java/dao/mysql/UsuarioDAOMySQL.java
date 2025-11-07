package dao.mysql;

import java.util.ArrayList;

import dao.interfaces.UsuarioDAO;
import entities.Usuario;




public class UsuarioDAOMySQL implements UsuarioDAO {

    @Override
    public boolean login(String dni, String password) {
        // Aquí irá el código JDBC para comprobar usuario y contraseña
        return false; // temporal
    }

    @Override
    public int insert(Usuario u) {
        // Código JDBC para insertar un usuario en la BD
        return 0; // temporal
    }

    @Override
    public ArrayList<Usuario> findall() {
        // Código JDBC para listar todos los usuarios
        return new ArrayList<>(); // temporal
    }

    @Override
    public Usuario findByNombre(String nombre) {
        // Código JDBC para buscar usuario por nombre
        return null; // temporal
    }
}