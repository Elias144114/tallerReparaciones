package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import dao.DBCconnection;
import dao.interfaces.UsuarioDAO;
import dwes.maven.mysql.PasswordUtils;
import entities.Usuario;


public class UsuarioDAOMySQL implements UsuarioDAO {
	private Connection conexion;
	
	public UsuarioDAOMySQL() {
		conexion = DBCconnection.getInstance().getConnection();
	}

	
    @Override
    public boolean login(String dni, String password) {
        // Aquí irá el código JDBC para comprobar usuario y contraseña
        return false; // temporal
    }

    @Override
    public int insert(Usuario u) {
    	try (Scanner sc = new Scanner(System.in)) {
	        // Pedir datos por consola
	        System.out.print("Introduce el ID del usuario: ");
	        int idUsuario = sc.nextInt();
	        sc.nextLine(); // limpiar el salto de línea

	        System.out.print("Introduce el nombreUsuario: ");
	        String nombreUsuario = sc.nextLine();

	        System.out.print("Introduce el dni: ");
	        int dni = sc.nextInt();
	        sc.nextLine();

	        System.out.print("Introduce el rol: ");
	        String rol = sc.nextLine();

	        System.out.print("Introduce la contraseña: ");
	        String password = sc.nextLine();

	        
	        String sql = "INSERT INTO persona (idEmpleado, nombre, edad, numTelefono, fechaNacimiento, password) VALUES(?, ?, ?, ?, ?, ?);";
	        PreparedStatement pst = conexion.prepareStatement(sql);

	        pst.setInt(1, idUsuario);
	        pst.setString(2, nombreUsuario);
	        pst.setInt(3, dni);
	        pst.setString(4, rol);
	        pst.setString(6, PasswordUtils.hashPassword(password));

	        int resul = pst.executeUpdate();
	        System.out.println("Resultado de inserción: " + resul);
	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("> Error: " + e.getMessage());
	    }
		return 0;
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