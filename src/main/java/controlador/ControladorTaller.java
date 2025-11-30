package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.MySQLDAOFactory;
import dao.interfaces.ClienteDAO;
import dao.interfaces.ReparacionDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VehiculoDAO;
import entities.Cliente;
import entities.Reparacion;
import entities.Usuario;
import entities.Vehiculo;
import passwordUtils.PasswordUtils;

/**
 * Clase principal del controlador del taller.
 * @author Elias
 */

/**
 * Controlador principal del proyecto taller de reparaciones. Implementa el
 * patrón Singleton para controlar el acceso a la lógica de negocio. Gestiona
 * usuarios, clientes, vehículos y reparaciones y muestra estadísticas.
 *
 */

public class ControladorTaller {

	private static ControladorTaller instancia = null;

	/**
	 * Consigue una instancia unica
	 */

	public static ControladorTaller getInstance() {
		if (instancia == null) {
			instancia = new ControladorTaller();
		}
		return instancia;
	}

	/**
	 * Sirve para que el usuario se mantenga hasta que se cierre sesion
	 */
	private Usuario usuarioLogueado = null;

	private final UsuarioDAO usuarioDAO;
	private final ReparacionDAO reparacionDAO;
	private final ClienteDAO clienteDAO;
	private final VehiculoDAO vehiculoDAO;

	/**
	 * Podemos acceder a los metodos de los DAO
	 */
	private ControladorTaller() {
		MySQLDAOFactory factory = new MySQLDAOFactory();
		this.usuarioDAO = factory.getUsuarioDAO();
		this.reparacionDAO = factory.getReparacionDAO();
		this.clienteDAO = factory.getClienteDAO();
		this.vehiculoDAO = factory.getVehiculoDAO();
	}

	/**
	 * Puedo Iniciar sesion y verificar la contraseña encriptada
	 * 
	 * @param dni      Sirve para añadir el Dni del Usuario
	 * @param password La contraseña del usuario
	 * @return un inicio de sesion correcto
	 */
	// INICIO DE SESION
	public boolean login(String dni, String password) {
		try {
			Usuario usuario = usuarioDAO.findByDni(dni);

			if (usuario == null) {
				System.out.println("El usuario NO existe");
				return false;
			} else if (PasswordUtils.verifyPassword(password, usuario.getPassword())) {
				this.usuarioLogueado = usuario;
				System.out.println("Nombre de usuario y contraseña correctas");
				return true;
			} else {
				String comprobarRol = getRol();
				if (comprobarRol.equals("invitado")) {
					System.out.println("Lo siento, no puedes iniciar sesion con tus permisos");
					System.out.println("");
					return false;
				}
				return false;
			}

		} catch (Exception e) {
			System.out.println("> Error en login: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Mira si la sesion se cierra
	 */

	public void logout() {

		this.usuarioLogueado = null;

	}

	/**
	 * Mira si la sesion esta activa
	 */

	public boolean sesionActiva() {

		return usuarioLogueado != null;

	}

	/**
	 * Te devuelve el Usuario logueado
	 */
	public Usuario getUsuarioLogueado() {
		try {
			return usuarioLogueado;
		} catch (Exception e) {
			System.err.println("Error obteniendo usuario logueado: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Pilla los roles posibles
	 * 
	 * @return el rol del usuario que se esta usando
	 */
	public String getRol() {
		if (usuarioLogueado == null) {
			return "invitado";
		} else if (usuarioLogueado.getRol().equalsIgnoreCase("mecanico")) {
			return "mecanico";
		} else if (usuarioLogueado.getRol().equalsIgnoreCase("administrador")) {
			return "administrador";
		}
		return usuarioLogueado.getRol().toLowerCase().trim();
	}

	/**
	 * Sirve para listar
	 * 
	 * @return las lista de todas las reparaciones finalizadas
	 */
//REPARACIONES
	public ArrayList<Reparacion> ReparacionesFinalizadas() {
		try {
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

		} catch (Exception e) {
			System.err.println(">Error mostrando reparaciones finalizadas: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * Sirve para registrar la reparacion y pedir los datos por consola
	 * 
	 * @param r la reparacion registrada
	 * @return la reparacion registrada
	 */
	public boolean registrarReparacion(Reparacion r) {
		try {
			Scanner sc = new Scanner(System.in);

			if (r == null) {
				r = new Reparacion();
			}

			String comprobarRol = getRol();
			if (comprobarRol.equals("invitado")) {
				System.out.println("Lo siento, no puedes registrar nada con tus permisos");
				return false;
			}

			System.out.println("Id de la reparacion");
			int idReparacion = sc.nextInt();
			sc.nextLine();

			if (this.reparacionDAO.findByIdReparacion(idReparacion) != null) {
				System.err.println("Ya existe una reparación con el id " + idReparacion);
				return false;
			}

			System.out.println("Descripcion de la reparacion");
			String descripcion = sc.nextLine();

			LocalDate fechaEntrada = null;
			boolean fechaValida = false;

			while (!fechaValida) {
				System.out.println("Fecha de entrada (formato YYYY-MM-DD, ej: 2025-11-28):");
				String input = sc.nextLine();
				try {
					fechaEntrada = LocalDate.parse(input);
					fechaValida = true;
				} catch (DateTimeParseException e) {
					System.err.println(
							"Formato de fecha incorrecto. Por favor, introduce una fecha válida en formato YYYY-MM-DD.");
					System.out.println("");
				}
			}

			System.out.println("Coste estimado de la reparacion");
			Double costeEstimado = sc.nextDouble();

			String estado = "";
			boolean estadoValido = false;
			while (!estadoValido) {
				System.out.println("Estado (no iniciada, en curso, finalizada):");
				estado = sc.nextLine().toLowerCase().trim();

				if (estado.equals("no iniciada") || estado.equals("en curso") || estado.equals("finalizada")) {
					estadoValido = true;
				} else {
					System.err.println("El estado " + estado + " no es válido");
					System.out.println("Por favor, introduce uno de los permitidos");
					System.out.println("");
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
					System.out.println("Vehículo con matricula " + vehiculoExistente.getMatricula() + " encontrado");
				} else {
					System.err.println("No existe ningún vehículo con el ID " + vehiculoId);
				}
			}

			int usuarioId = 0;
			boolean usuarioValido = false;
			while (!usuarioValido) {
				System.out.println("Id del usuario al que se asigna la reparación:");

				int idUsuarioLeido = sc.nextInt();
				sc.nextLine();

				Usuario usuarioExistente = this.usuarioDAO.findById(idUsuarioLeido);

				// Esto lo hice así por que diria yo que es raro que un administrador tenga una
				// reparacion asignada
				if (usuarioExistente != null && usuarioExistente.getRol().equalsIgnoreCase("mecanico")) {
					usuarioId = idUsuarioLeido;
					usuarioValido = true;
					System.out.println("Reparación asignada a: " + usuarioExistente.getNombreUsuario());
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
			return resultado > 0;

		} catch (Exception e) {
			System.err.println("Error registrando reparación: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Se pueden actualizar las reparaciones
	 * 
	 * @param r
	 * @return
	 */
//CAMBIAR ESTADO DE REPARACIONES
	public boolean actualizarReparacion(Reparacion r) {
		try {
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
					System.err.println("El estado " + estado + " no es válido");
					System.out.println("Por favor, introduce uno de los permitidos");
				}
			}

			r.setIdReparacion(idReparacion);
			r.setDescripcion(descripcion);
			r.setCosteEstimado(costeEstimado);
			r.setFechaEntrada(fechaEntrada);
			r.setEstado(estado);

			int resultado = this.reparacionDAO.update(r);

			return resultado > 0;

		} catch (Exception e) {
			System.err.println("Error actualizando reparación: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Se registran los clientes como se registran las reparaciones
	 * 
	 * @param c
	 * @return
	 */
//GESTION DE CLIENTES Y VEHICULOS POR EL ADMINISTRADOR	
	public boolean registrarCliente(Cliente c) {
		try {
			Scanner sc = new Scanner(System.in);

			System.out.println("Id del cliente:");
			int idCliente = sc.nextInt();
			sc.nextLine();

			if (clienteDAO.findById(idCliente) != null) {
				System.out.println("Ya existe un cliente con el id " + idCliente);
				return false;
			}

			System.out.println("DNI del cliente:");
			String dni = sc.nextLine();

			if (clienteDAO.findByDni(dni) != null) {
				System.out.println("Ya existe un cliente con el DNI " + dni);
				return false;
			}

			System.out.println("Nombre:");
			String nombre = sc.nextLine();

			System.out.println("Teléfono:");
			String telefono = sc.nextLine();

			System.out.println("Email:");
			String email = sc.nextLine();

			c.setIdCliente(idCliente);
			c.setDni(dni);
			c.setNombre(nombre);
			c.setTelefono(telefono);
			c.setEmail(email);

			int resultado = clienteDAO.insert(c);
			return resultado > 0;

		} catch (Exception e) {
			System.err.println("Error registrando cliente: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Sirve para actualizar los clientes
	 * 
	 * @param c
	 * @return
	 */
	public boolean actualizarCliente(Cliente c) {
		try {
			Scanner sc = new Scanner(System.in);
			String comprobarRol = getRol();

			if (comprobarRol.equals("invitado")) {
				System.out.println("Lo siento, no puedes actualizar nada con tus permisos");
				return false;
			}

			String dni = "";
			boolean clienteValido = false;
			Cliente clienteExistente = null;

			while (!clienteValido) {
				System.out.println("DNI del cliente a actualizar:");
				dni = sc.nextLine();

				clienteExistente = clienteDAO.findByDni(dni);

				if (clienteExistente != null) {
					clienteValido = true;
					System.out.println("Cliente encontrado: " + clienteExistente.getNombre());
				} else {
					System.err.println("No existe ningún cliente con el DNI " + dni);
				}
			}

			System.out.println("Nuevo nombre:");
			String nombre = sc.nextLine();

			System.out.println("Nuevo teléfono:");
			String telefono = sc.nextLine();

			System.out.println("Nuevo email:");
			String email = sc.nextLine();

			c.setIdCliente(clienteExistente.getIdCliente());
			c.setDni(dni);
			c.setNombre(nombre);
			c.setTelefono(telefono);
			c.setEmail(email);

			int resultado = clienteDAO.update(c);

			return resultado > 0;

		} catch (Exception e) {
			System.err.println("Error actualizando cliente: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Sirve para actualizar clientes
	 * 
	 * @return
	 */
	public boolean borrarCliente() {
		try {
			Scanner sc = new Scanner(System.in);
			String comprobarRol = getRol();

			if (comprobarRol.equals("invitado")) {
				System.out.println("Lo siento, no puedes borrar nada con tus permisos");
				return false;
			}

			System.out.println("DNI del cliente:");
			String dni = sc.nextLine();
			boolean dniValido = false;

			while (!dniValido) {
				Cliente clienteExistente = this.clienteDAO.findByDni(dni);

				if (clienteExistente != null) {
					dniValido = true;

					if (comprobarRol.equalsIgnoreCase("administrador")) {
						System.out.println("Cliente encontrado: " + clienteExistente.getNombre() + ", borrando...");
						return this.clienteDAO.delete(dni) > 0;
					} else {
						System.out.println("No tienes permisos para borrar clientes.");
						return false;
					}

				} else {
					System.err.println("No existe ningún cliente con el DNI " + dni);
					System.out.println("Por favor, introduce un DNI diferente:");
					dni = sc.nextLine();
				}
			}

			return false;

		} catch (Exception e) {
			System.err.println("Error al borrar cliente: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Sirve para registrar un Vehiculo
	 * 
	 * @param v
	 * @return
	 */
	public boolean registrarVehiculo(Vehiculo v) {
		try {
			Scanner sc = new Scanner(System.in);

			System.out.println("Id del vehículo:");
			int idVehiculo = sc.nextInt();
			sc.nextLine();

			if (vehiculoDAO.findByid(idVehiculo) != null) {
				System.out.println("Ya existe un vehículo con el id " + idVehiculo);
				return false;
			}

			System.out.println("Matrícula del vehículo:");
			String matricula = sc.nextLine();

			if (vehiculoDAO.findByMatricula(matricula) != null) {
				System.out.println("Ya existe un vehículo con la matrícula " + matricula);
				return false;
			}

			System.out.println("Marca del Coche:");
			String marca = sc.nextLine();

			System.out.println("Modelo del Coche:");
			String modelo = sc.nextLine();

			System.out.println("Id del cliente:");
			int clienteId = sc.nextInt();
			sc.nextLine();

			if (clienteDAO.findById(clienteId) == null) {
				System.out.println("No existe ningún cliente con el id " + clienteId);
				return false;
			}

			v.setIdVehiculo(idVehiculo);
			v.setMatricula(matricula);
			v.setMarca(marca);
			v.setModelo(modelo);
			v.setClienteId(clienteId);

			int resultado = vehiculoDAO.insert(v);
			return resultado > 0;

		} catch (Exception e) {
			System.err.println("Error registrando vehículo: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Sirve para actualizar un vehiculo
	 * 
	 * @param v
	 * @return
	 */
	public boolean actualizarVehiculo(Vehiculo v) {
		try {
			Scanner sc = new Scanner(System.in);
			String comprobarRol = getRol();

			if (comprobarRol.equals("invitado")) {
				System.out.println("Lo siento, no puedes actualizar nada con tus permisos");
				return false;
			}

			int idVehiculo = 0;
			boolean vehiculoValido = false;
			Vehiculo vehiculoExistente = null;

			while (!vehiculoValido) {
				System.out.println("Id del vehículo a actualizar:");
				idVehiculo = sc.nextInt();
				sc.nextLine();

				vehiculoExistente = vehiculoDAO.findByid(idVehiculo);

				if (vehiculoExistente != null) {
					vehiculoValido = true;
					System.out.println("Vehículo encontrado: " + vehiculoExistente.getMatricula());
				} else {
					System.err.println("No existe ningún vehículo con el ID " + idVehiculo);
				}
			}

			System.out.println("Nueva matrícula:");
			String matricula = sc.nextLine();

			System.out.println("Nueva marca:");
			String marca = sc.nextLine();

			System.out.println("Nuevo modelo:");
			String modelo = sc.nextLine();

			System.out.println("Id del cliente propietario:");
			int clienteId = sc.nextInt();
			sc.nextLine();

			if (clienteDAO.findById(clienteId) == null) {
				System.out.println("No existe ningún cliente con el id " + clienteId);
				return false;
			}

			v.setIdVehiculo(idVehiculo);
			v.setMatricula(matricula);
			v.setMarca(marca);
			v.setModelo(modelo);
			v.setClienteId(clienteId);

			int resultado = vehiculoDAO.update(v);

			return resultado > 0;

		} catch (Exception e) {
			System.err.println("Error actualizando vehículo: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Sirve para borrar un vehiculo
	 * 
	 * @return
	 */
	public boolean borrarVehiculo() {
		try {
			Scanner sc = new Scanner(System.in);
			String comprobarRol = getRol();

			if (comprobarRol.equals("invitado")) {
				System.out.println("Lo siento, no puedes borrar nada con tus permisos");
				return false;
			}

			System.out.println("Id del vehículo:");
			int idVehiculo = sc.nextInt();
			sc.nextLine();
			boolean idValido = false;

			while (!idValido) {
				Vehiculo vehiculoExistente = this.vehiculoDAO.findByid(idVehiculo);

				if (vehiculoExistente != null) {
					idValido = true;

					if (comprobarRol.equalsIgnoreCase("administrador")) {
						System.out
								.println("Vehículo encontrado: " + vehiculoExistente.getMatricula() + ", borrando...");
						return this.vehiculoDAO.delete(vehiculoExistente) > 0;
					} else {
						System.out.println("No tienes permisos para borrar vehículos.");
						return false;
					}

				} else {
					System.err.println("No existe ningún vehículo con el ID " + idVehiculo);
					System.out.println("Por favor, introduce un ID diferente:");
					idVehiculo = sc.nextInt();
					sc.nextLine();
				}
			}

			return false;

		} catch (Exception e) {
			System.err.println("Error al borrar vehículo: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Sirve para listar todas las reparaciones
	 * 
	 * @return
	 */
	public ArrayList<Reparacion> Reparaciones() {
		try {
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

		} catch (Exception e) {
			System.err.println("Error mostrando reparaciones: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * Sirve para borrar la reparacion
	 * 
	 * @param r
	 * @return
	 */
	public boolean borrarReparacion(Reparacion r) {
		try {
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
						System.out.println("Id existente: " + idReparacion);
						return this.reparacionDAO.delete(idReparacion) > 0;
					} else if (comprobarRol.equalsIgnoreCase("administrador")) {
						System.out.println("No se puede borrar a un admin");
					}
				} else {
					System.err.println("No existe un usuario con el DNI " + idReparacion);
					System.out.println("Por favor, introduce un DNI diferente:");
					idReparacion = sc.nextInt();
					sc.nextLine();

				}
			}

			return false;

		} catch (Exception e) {
			System.err.println("Error al borrar reparación: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Sirve para registrar los usuarios
	 * 
	 * @param u
	 * @return
	 */
	public boolean registrarUsuario(Usuario u) {
		try {
			Scanner sc = new Scanner(System.in);
			String comprobarRol = getRol();
			if (comprobarRol.equals("invitado") || comprobarRol.equals("mecanico")) {
				System.out.println("Lo siento, no puedes hacer nada de esto con tus permisos");
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
			return resultado > 0;

		} catch (Exception e) {
			System.err.println("Error registrando usuario: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Sirve para actualizar usuarios
	 * 
	 * @param u
	 * @return
	 */
	public boolean actualizarUsuario(Usuario u) {
		try {
			Scanner sc = new Scanner(System.in);
			String comprobarRol = getRol();
			if (comprobarRol.equals("invitado") || comprobarRol.equals("mecanico")) {
				System.out.println("Lo siento, no puedes hacer nada de esto con tus permisos");
				return false;
			}

			String dni = "";
			boolean usuarioValido = false;
			Usuario usuarioExistente = null;

			while (!usuarioValido) {
				System.out.println("DNI del usuario a actualizar:");
				dni = sc.nextLine();

				usuarioExistente = usuarioDAO.findByDni(dni);

				if (usuarioExistente != null) {
					usuarioValido = true;
					System.out.println("Usuario encontrado: " + usuarioExistente.getNombreUsuario());
				} else {
					System.err.println("No existe ningún usuario con el DNI " + dni);
				}
			}

			System.out.println("Nuevo nombre del usuario:");
			String nombreUsuario = sc.nextLine();

			System.out.println("Nueva contraseña del usuario:");
			String password = sc.nextLine();

			String rol = "";
			boolean rolValido = false;
			while (!rolValido) {
				System.out.println("Nuevo rol (mecanico, administrador):");
				rol = sc.nextLine().toLowerCase().trim();

				if (rol.equals("mecanico") || rol.equals("administrador")) {
					rolValido = true;
				} else {
					System.err.println("El rol '" + rol + "' no es válido");
					System.out.println("Por favor, introduce uno de los permitidos");
				}
			}

			u.setIdUsuario(usuarioExistente.getIdUsuario());
			u.setDni(dni);
			u.setNombreUsuario(nombreUsuario);
			u.setPassword(password);
			u.setRol(rol);

			int resultado = usuarioDAO.update(u);

			return resultado > 0;

		} catch (Exception e) {
			System.err.println("Error actualizando usuario: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Sirve para borrar un usuario
	 * 
	 * @param u El usuario a borrar
	 * @return Un delete para borrar al usuario
	 */
	public boolean borrarUsuario(Usuario u) {
		try {
			Scanner sc = new Scanner(System.in);
			String comprobarRol = getRol();
			if (comprobarRol.equals("invitado") || comprobarRol.equals("mecanico")) {
				System.out.println("Lo siento, no puedes hacer nada de esto con tus permisos");
				return false;
			}

			System.out.println("Dni del usuario");
			String dni = sc.nextLine();
			boolean dniValido = false;
			while (!dniValido) {

				Usuario usuarioExistente = this.usuarioDAO.findByDni(dni);

				if (usuarioExistente != null) {
					dniValido = true;
					if (comprobarRol.equalsIgnoreCase("mecanico") || comprobarRol.equalsIgnoreCase("Administrador ")) {
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

		} catch (Exception e) {
			System.err.println("Error borrando usuario: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Estadisticas por estado
	 */
	public void estadisticaPorEstado() {
		try {
			ArrayList<Reparacion> reparaciones = reparacionDAO.findall();

			int noIniciadas = 0;
			int enCurso = 0;
			int finalizadas = 0;

			for (Reparacion r : reparaciones) {
				String estado = r.getEstado().toLowerCase().trim();
				if (estado.equals("no iniciada"))
					noIniciadas++;
				else if (estado.equals("en curso"))
					enCurso++;
				else if (estado.equals("finalizada"))
					finalizadas++;
			}

			int total = noIniciadas + enCurso + finalizadas; // ← TODAS

			System.out.println("    ESTADO DE REPARACIONES   ");
			System.out.println("");
			System.out.println(" Todas       : " + total);
			System.out.println(" No iniciadas: " + noIniciadas);
			System.out.println(" En curso    : " + enCurso);
			System.out.println(" Finalizadas : " + finalizadas);
			System.out.println("");

		} catch (Exception e) {
			System.err.println("Error dando reparaciones por estado: " + e.getMessage());
		}
	}

	/**
	 * Estadisticas por coste medio
	 */
	public void estadisticaCosteMedioReparaciones() {
		try {
			ArrayList<Reparacion> reparaciones = reparacionDAO.findall();
			if (reparaciones.isEmpty()) {
				System.out.println("No hay reparaciones registradas.");
				return;
			}
			double suma = 0;
			for (Reparacion r : reparaciones) {
				if (r.getCosteEstimado() != null)
					suma += r.getCosteEstimado();
			}

			double media = suma / reparaciones.size();
			System.out.println("    COSTE MEDIO DE REPARACIONES    ");
			System.out.println("Coste medio: " + media + " €");
		} catch (Exception e) {
			System.err.println("Error calculando coste medio: " + e.getMessage());
		}
	}

}
