package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBCconnection;
import dao.interfaces.UsuarioDAO;
import entities.Cliente;
import entities.Reparacion;
import entities.Usuario;
import passwordUtils.PasswordUtils;


public class UsuarioDAOMySQL implements UsuarioDAO {
	private Connection conexion;
	
	public UsuarioDAOMySQL() {
		conexion = DBCconnection.getInstance().getConnection();
	}

	

	@Override
	public boolean login(String dni, String password) {
	    String sql = "SELECT password FROM usuario WHERE dni = ?";
	    
	    try (PreparedStatement pst = conexion.prepareStatement(sql)) {
	        
	        pst.setString(1, dni);
	        
	        
	        try (ResultSet resultado = pst.executeQuery()) { 

	           
	            if (resultado.next()) { 
	                
	                String passwordHashead = resultado.getString("password");
	                
	                if (PasswordUtils.verifyPassword(password, passwordHashead)) {
	                    System.out.println("> Password correcta. Adelante");
	                    return true; 
	                } else {
	                    System.out.println(">Contraseña incorrecta");
	                    return false;
	                }
	            } else {
	                System.out.println(">Usuario no encontrado");
	                return false;
	            }
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	        return false;
	    } catch (Exception e) {
	        System.out.println("> Error: " + e.getMessage());
	        return false;
	    }
	}

	

    
	@Override
	public int insert(Usuario u) {
		int resul = 0;
		try {

			String sql = "INSERT INTO usuario (idUsuario, nombreUsuario, dni, password, rol) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement pst = conexion.prepareStatement(sql);

			pst.setInt(1, u.getIdUsuario());
			pst.setString(2, u.getNombreUsuario());
			pst.setString(3, u.getDni());
			pst.setString(4, PasswordUtils.hashPassword(u.getPassword()));
			pst.setString(5, u.getRol());

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
    public ArrayList<Usuario> findall() {
    	 ArrayList<Usuario> usuarios = new ArrayList<>();
		    String sql = "SELECT idUsuario, dni, nombreUsuario, password, rol FROM usuario;";
		    try (PreparedStatement pst = conexion.prepareStatement(sql);
		         ResultSet resul = pst.executeQuery()) {

		        
		        while (resul.next()) {
		            Usuario u = new Usuario();
		            u.setIdUsuario(resul.getInt("idUsuario"));
		            u.setDni(resul.getString("dni"));
		            u.setNombreUsuario(resul.getString("nombreUsuario"));
		            u.setPassword(resul.getString("password"));
		            u.setRol(resul.getString("rol"));
		            
		            
		            usuarios.add(u);
		        }
		    } catch (SQLException e) {
		        System.out.println("> NOK en findall: " + e.getMessage());
		        e.printStackTrace();
		    }
		    return usuarios;
		}

		@Override
		public Usuario findByDni(String dni) {
			Usuario usuario = null;
	        String sql = "SELECT idUsuario, nombreUsuario, dni, password, rol FROM usuario WHERE dni = ?;";
	        
	        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
	            
	            pst.setString(1, dni);
	            
	            try (ResultSet resul = pst.executeQuery()) {
	                if (resul.next()) {
	                    usuario = new Usuario();
	                    
	                    
	                    usuario.setIdUsuario(resul.getInt("idUsuario"));
	                    usuario.setDni(resul.getString("dni"));
	                    usuario.setNombreUsuario(resul.getString("nombreUsuario"));
	                    usuario.setPassword(resul.getString("password"));
	                    usuario.setRol(resul.getString("rol"));
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("> NOK en findByDni: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return usuario;
	    }
		@Override
		public Usuario findById(int id) {
			Usuario usuario = null;
	        String sql = "SELECT idUsuario, nombreUsuario, dni, password, rol FROM usuario WHERE dni = ?;";
	        
	        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
	            
	            pst.setInt(1, id);
	            
	            try (ResultSet resul = pst.executeQuery()) {
	                if (resul.next()) {
	                    usuario = new Usuario();
	                    
	                    
	                    usuario.setIdUsuario(resul.getInt("idUsuario"));
	                    usuario.setDni(resul.getString("dni"));
	                    usuario.setNombreUsuario(resul.getString("nombreUsuario"));
	                    usuario.setPassword(resul.getString("password"));
	                    usuario.setRol(resul.getString("rol"));
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("> NOK en findById: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return usuario;
	    }
	}