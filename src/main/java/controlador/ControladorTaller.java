package controlador;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import dao.MySQLDAOFactory;
import dao.interfaces.*;
import entities.Reparacion;
import entities.Usuario;
import entities.Vehiculo;
import passwordUtils.PasswordUtils;

public class ControladorTaller {

	private static ControladorTaller instancia = null;

	public static ControladorTaller getInstance() {
		if (instancia == null) {
			instancia = new ControladorTaller();
		}
		return instancia;
	}

	private Usuario usuarioLogueado = null;

	private final UsuarioDAO usuarioDAO;
	private final ReparacionDAO reparacionDAO;
	private final ClienteDAO clienteDAO;
	private final VehiculoDAO vehiculoDAO;

	private ControladorTaller() {
		MySQLDAOFactory factory = new MySQLDAOFactory();

		this.usuarioDAO = factory.getUsuarioDAO();
		this.reparacionDAO = factory.getReparacionDAO();
		this.clienteDAO = factory.getClienteDAO();
		this.vehiculoDAO = factory.getVehiculoDAO();
	}

	// INICIAR SESION

	public boolean login(String dni, String password) {
		Usuario u = usuarioDAO.findByDni(dni);
		try {
			if (u == null) {
				System.out.println("El usuario NO existe");
				return false;
			} else if (PasswordUtils.verifyPassword(password, u.getPassword())) {
				this.usuarioLogueado = u;
				System.out.println("Nombre de usuario y contraseña correctas");
				return true;
			} else {
				return false;

			}
		} catch (Exception e) {
			System.out.println("> Error en login: " + e.getMessage());
			return false;
		}
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

	// VER Y REGISTRAR REPARACIONES

	public ArrayList<Reparacion> ReparacionesFinalizadas() {

		ArrayList<Reparacion> reparaciones = reparacionDAO.findByEstado("Finalizada");

		System.out.println(" ");
		System.out.println("           LISTADO DE REPARACIONES FINALIZADAS              ");
		System.out.println(" ");

		if (reparaciones.isEmpty()) {
			System.out.println("No hay reparaciones finalizadas en la base de datos.");

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

	// USARE ESTE METODO PARA QUE LOS QUE PUEDEN INICIAR SESION VEN TODAS LAS
	// REPARACIONES
	public ArrayList<Reparacion> Reparaciones() {

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

	public String getRol() {
		if (usuarioLogueado == null) {
			return "invitado";
		} else if (usuarioLogueado.getRol().equalsIgnoreCase("mecanico")) {
			return "mecanico";
		} else if (usuarioLogueado.getRol().equalsIgnoreCase("administrador")) {
			return "administrador";
		}
		return usuarioLogueado.getRol().toLowerCase().trim();
		// Esto para evitar problemas si alguien pone "MECANICo", "meCanico" o lo que
		// sea. Tambien quitamos espacios
	}

	public boolean registrarReparacion(Reparacion r) {

		Scanner sc = new Scanner(System.in);

		String comprobarRol = getRol();
		if (comprobarRol.equals("invitado")) {
			System.out.println("Lo siento, no puedes registrar nada con tus permisos");
			return false;
		}

		System.out.println("Id de la reparacion");
		int idReparacion = sc.nextInt();
		sc.nextLine();

		System.out.println("Descripcion de la reparacion");
		String descripcion = sc.nextLine();

		System.out.println("Fecha de entrada (formato YYYY-MM-DD, ej: 2025-11-28):");
		LocalDate fechaEntrada = LocalDate.parse(sc.nextLine());

		System.out.println("Coste estimado de la reparacion");
		Double costeEstimado = sc.nextDouble();
		sc.nextLine();

		String estado = "";
		boolean estadoValido = false;
		while (!estadoValido) {
			System.out.println("Estado (no iniciada, en curso, finalizada):");
			estado = sc.nextLine().toLowerCase().trim();

			if (estado.equals("no iniciada") || estado.equals("en curso") || estado.equals("finalizada")) {
				estadoValido = true;
			} else {
				System.err.println("El estado '" + estado + "' no es válido");
				System.out.println("Por favor, introduce uno de los permitidos");
			}
		}

		int vehiculoId = 0;
		boolean vehiculoValido = false;
		while (!vehiculoValido) {
			System.out.println("Id del vehiculo");

			int idLeido = sc.nextInt();
			sc.nextLine();

			Vehiculo vehiculoExistente = this.vehiculoDAO.findByid(idLeido);

			if (vehiculoExistente != null) {
				vehiculoId = idLeido;
				vehiculoValido = true;
				System.out.println("Vehículo encontrado: " + vehiculoExistente.getMatricula());
			} else {
				System.err.println("No existe ningún vehículo con el ID " + idLeido);
			}
		}

		int usuarioId = 0;
		boolean usuarioValido = false;
		while (!usuarioValido) {
			System.out.println("Id del usuario al que se asigna la reparación:");

			int idUsuarioLeido = sc.nextInt();
			sc.nextLine();

			Usuario usuarioExistente = this.usuarioDAO.findById(idUsuarioLeido);

			if (usuarioExistente != null && usuarioExistente.getRol().equalsIgnoreCase("mecanico")) {
				usuarioId = idUsuarioLeido;
				usuarioValido = true;
				System.out.println("Reparación asignada a: " + usuarioExistente.getNombreUsuario()); // Lo hice así
																										// porque seria
																										// raro que un
																										// Administrador
																										// tuviera una
																										// reparacion
																										// asignada
			} else {
				System.err.println("No existe ningún usuario con el ID " + idUsuarioLeido);
			}
		}

		r.setIdReparacion(idReparacion);
		r.setDescripcion(descripcion);
		r.setCosteEstimado(costeEstimado);
		r.setFechaEntrada(fechaEntrada);
		r.setEstado(estado);
		r.setVehiculoId(vehiculoId);
		r.setUsuarioId(usuarioId);

		int resultado = this.reparacionDAO.insert(r);
		return resultado > 1;
	}

	public boolean actualizarReparacion(Reparacion r) {

		Scanner sc = new Scanner(System.in);
		String comprobarRol = getRol();

		if (comprobarRol.equals("invitado")) {
			System.out.println("Lo siento, no puedes actualizar nada con tus permisos");
			return false;
		}

		int idReparacion = 0;
		boolean reparacionValida = false;
		Reparacion reparacionExistente = null;

		while (!reparacionValida) {
			System.out.println("Id de la reparacion a actualizar:");

			idReparacion = sc.nextInt();
			sc.nextLine();

			reparacionExistente = this.reparacionDAO.findByIdReparacion(idReparacion);

			if (reparacionExistente != null) {
				reparacionValida = true;
				System.out.println("Reparación encontrada. Estado actual: " + reparacionExistente.getEstado());
			} else {
				System.err.println("No existe ninguna reparación con el ID " + idReparacion);
			}
		}

		System.out.println("Nueva descripcion de la reparacion");
		String descripcion = sc.nextLine();

		System.out.println("Nueva fecha de entrada (formato YYYY-MM-DD, ej: 2025-11-28):");
		LocalDate fechaEntrada = LocalDate.parse(sc.nextLine());

		System.out.println("Nuevo coste estimado de la reparacion");
		Double costeEstimado = sc.nextDouble();

		String estado = "";
		boolean estadoValido = false;
		while (!estadoValido) {
			System.out.println("Nuevo estado (no iniciada, en curso, finalizada):");
			estado = sc.nextLine().toLowerCase().trim();

			if (estado.equals("no iniciada") || estado.equals("en curso") || estado.equals("finalizada")) {
				estadoValido = true;
			} else {
				System.err.println("El estado '" + estado + "' no es válido");
				System.out.println("Por favor, introduce uno de los permitidos");
			}
		}

		r.setIdReparacion(idReparacion);
		r.setDescripcion(descripcion);
		r.setCosteEstimado(costeEstimado);
		r.setFechaEntrada(fechaEntrada);
		r.setEstado(estado);

		int resultado = this.reparacionDAO.update(r);

		return resultado > 1;
	}
	
	public boolean borrarReparacion(Reparacion r) {
		Scanner sc = new Scanner(System.in);
		String comprobarRol = getRol();
		if (comprobarRol.equals("invitado")) {
			System.out.println("Lo siento, no puedes borrar nada con tus permisos");
			return false;
		}

		System.out.println("Id de la reparacion");
		int idReparacion = sc.nextInt();
		sc.nextLine();
		boolean idValido = false;
		while (!idValido) {

			Reparacion ReparacionExistente = this.reparacionDAO.findByIdReparacion(idReparacion);

			if (ReparacionExistente != null) {
				idValido = true;
				if (comprobarRol.equalsIgnoreCase("mecanico")) {
					System.out.println("DNI existente: " + idReparacion + ", estas despedido");
				  return this.reparacionDAO.delete(idReparacion) > 1;
				} else if (comprobarRol.equalsIgnoreCase("administrador")) {
					System.out.println("No se puede borrar a un admin");
				}
				}else {
				System.err.println("No existe un usuario con el DNI " + idReparacion);
				System.out.println("Por favor, introduce un DNI diferente:");
				idReparacion = sc.nextInt();
				sc.nextLine();

			
		}
		}

		return false;
	}

	public boolean registrarUsuario(Usuario u) {

		Scanner sc = new Scanner(System.in);
		String comprobarRol = getRol();
		if (comprobarRol.equals("invitado")) {
			System.out.println("Lo siento, no puedes registrar nada con tus permisos");
			return false;
		}

		System.out.println("Id del usuario");
		int idUsuario = sc.nextInt();
		sc.nextLine();

		System.out.println("Dni del usuario");
		String dni = sc.nextLine();

		System.out.println("Nombre del usuario");
		String nombreUsuario = sc.nextLine();

		System.out.println("Password del usuario");
		String password = sc.nextLine();

		String rol = "";
		boolean rolValido = false;
		while (!rolValido) {
			System.out.println("Rol (mecanico, administrador):");
			rol = sc.nextLine().toLowerCase().trim();

			if (rol.equals("mecanico") || rol.equals("administrador")) {
				rolValido = true;
			} else {
				System.err.println("El rol '" + rol + "' no es válido");
				System.out.println("Por favor, introduce uno de los permitidos");
			}
		}

		boolean dniValido = false;
		while (!dniValido) {

			Usuario usuarioExistente = this.usuarioDAO.findByDni(dni);

			if (usuarioExistente == null) {
				dniValido = true;
				System.out.println("DNI válido: " + dni);
			} else {
				System.err.println("Ya existe un usuario con el DNI " + dni);
				System.out.println("Por favor, introduce un DNI diferente:");
				dni = sc.nextLine();
			}
		}

		u.setIdUsuario(idUsuario);
		u.setDni(dni);
		u.setNombreUsuario(nombreUsuario);
		u.setPassword(password);
		u.setRol(rol);

		int resultado = this.usuarioDAO.insert(u);
		return resultado > 1;
	}

	public boolean borrarUsuario(Usuario u) {
		Scanner sc = new Scanner(System.in);
		String comprobarRol = getRol();
		if (comprobarRol.equals("invitado")) {
			System.out.println("Lo siento, no puedes registrar nada con tus permisos");
			return false;
		}

		System.out.println("Dni del usuario");
		String dni = sc.nextLine();
		boolean dniValido = false;
		while (!dniValido) {

			Usuario usuarioExistente = this.usuarioDAO.findByDni(dni);

			if (usuarioExistente != null) {
				dniValido = true;
				if (comprobarRol.equalsIgnoreCase("mecanico")) {
					System.out.println("DNI existente: " + dni + ", estas despedido");
					return this.usuarioDAO.delete(dni) > 0;
				} else if (comprobarRol.equalsIgnoreCase("administrador")) {
					System.out.println("No se puede borrar a un admin");
				}
			} else {
				System.err.println("No existe un usuario con el DNI " + dni);
				System.out.println("Por favor, introduce un DNI diferente:");
				dni = sc.nextLine();

			}
		}

		return false;
	}

}
