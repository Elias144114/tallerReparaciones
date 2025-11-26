package controlador;

import java.util.ArrayList;

import dao.mysql.*;
import entities.*;



public class ControladorTaller {

	// *
	private static ControladorTaller instance;

	
	private Usuario usuarioLogueado;

	
	private UsuarioDAOMySQL usuarioDAO;
	private ClienteDAOMySQL clienteDAO;
	private VehiculoDAOMySQL vehiculoDAO;
	private ReparacionDAOMySQL reparacionDAO;

	
	private ControladorTaller() {
		
		usuarioDAO = new UsuarioDAOMySQL();
		clienteDAO = new ClienteDAOMySQL();
		vehiculoDAO = new VehiculoDAOMySQL();
		reparacionDAO = new ReparacionDAOMySQL();
	}

	//Esto es para recordarme que este codigo y el de el * lo saque de la pagina Web que nos enseño Gonzalo, recuerda preguntarle que te lo explique un poco mejor
	public static ControladorTaller getInstance() {
	    if (instance == null) {
	        instance = new ControladorTaller();
	    }
	    return instance;
	}
	
	//INICIAR SESION 
	
	public boolean login(String nombreUsuario, String password) {
		Usuario u = usuarioDAO.findByNombre(nombreUsuario);
		if (u == null) {
			 System.out.println("El usuario NO existe"); 
		} else if (u.getPassword().equals(password)) {
			this.usuarioLogueado=u;
		    System.out.println("Nombre de usuario y contraseña correctas");
		    return true;
		} 		
		return false;

	}
	
	 public void logout() {
	        this.usuarioLogueado = null;
	    }

	    public boolean sesionActiva() {
	        return usuarioLogueado != null;
	    }

	    public Usuario getUsuarioLogueado() {
	        return usuarioLogueado;
	    }
	    
	    
	    // VER REPARACIONES
	
	public ArrayList<Reparacion> ReparacionesFinalizadas() {

		ArrayList<Reparacion> reparaciones = reparacionDAO.findall();

		System.out.println(" ");
		System.out.println("           LISTADO DE REPARACIONES              ");
		System.out.println(" ");

		if (reparaciones.isEmpty()) {
			System.out.println("No hay reparaciones en la base de datos.");

		}

		for (Reparacion r : reparaciones) {
			System.out.println("ID: " + r.getIdReparacion());
			System.out.println("  Descripción: " + r.getDescripcion());
			System.out.println("  Fecha de entrada: " + r.getFechaEntrada());
			System.out.println("  Coste estimado: " + r.getCosteEstimado());
			System.out.println("  Estado: " + r.getEstado());
			System.out.println("  Id del Vehiculo: " + r.getVehiculoId());
			System.out.println("  Id del Usuario: " + r.getUsuarioId());
			System.out.println("");
		}
		return reparaciones;
	}
	
	 public boolean registrarReparacion(Reparacion r) {
		//Aqui me lie y me fui a dormir para preguntar la duda mañana, por si no me acuerdo, es lo de Vista-Fachada y pedir por consola o si no es necesario
		 
		 
	        return false;
	    }

	    //Prueba Pull Request
	
}