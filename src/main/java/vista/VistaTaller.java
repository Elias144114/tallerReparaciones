package vista;

import java.util.Scanner;
import controlador.ControladorTaller;
import entities.*;

/**
 * Clase Main, la vista
 * 
 * @author Elias
 */

public class VistaTaller {

	private static Scanner sc = new Scanner(System.in);
	private static ControladorTaller controlador = ControladorTaller.getInstance();

	/**
	 * La clase Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) {
			try {
				switch (controlador.getRol()) {
				case "invitado":
					menuInvitado();
					break;
				case "mecanico":
					menuMecanico();
					break;
				case "administrador":
					menuAdministrador();
					break;
				}
			} catch (Exception e) {
				System.err.println("Error inesperado en el sistema: " + e.getMessage());
				System.out.println("");
			}
		}
	}

	/**
	 * El menu del invitado
	 */
	// MENÚ INVITADO
	private static void menuInvitado() {
		while (true) {
			System.out.println("");
			System.out.println("  MENÚ INVITADO ");
			System.out.println("");
			System.out.println("1. Ver reparaciones finalizadas");
			System.out.println("2. Iniciar sesión");
			System.out.println("3. Salir del programa");
			System.out.print("Elige una opción: ");
			String opcion = sc.nextLine();

			try {
				switch (opcion) {
				case "1":
					controlador.ReparacionesFinalizadas();
					break;
				case "2":
					iniciarSesion();
					return;
				case "3":
					System.out.println("Saliendo");
					System.exit(0);
					break;
				default:
					System.err.println("Opción incorrecta.");
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
				System.out.println("");
			}
		}
	}

	/**
	 * Sirve para usar el metodo "iniciar sesion"
	 */
	private static void iniciarSesion() {
		System.out.println("");
		System.out.print("DNI: ");
		String dni = sc.nextLine();
		System.out.print("Contraseña: ");
		String password = sc.nextLine();

		if (controlador.login(dni, password)) {
			System.out.println("");
			System.out.println("Sesión iniciada. Rol: " + controlador.getRol());
		} else {
			System.err.println("Error iniciando sesión. Volviendo al menú de invitado");
			System.out.println("");
		}
	}

	/**
	 * Sirve para ver lo que puede hacer el mecanico
	 */
	// MENÚ MECÁNICO
	private static void menuMecanico() {
		while (true) {
			System.out.println("");
			System.out.println("    MENÚ MECÁNICO    ");
			System.out.println(
					"(NOTA IMPORTANTE: Para crear una reparación debe existir un Vehículo y un Mecánico para añadirles el id)");
			System.out.println("");
			System.out.println("1. Ver todas las reparaciones");
			System.out.println("2. Registrar reparación");
			System.out.println("3. Actualizar reparación");
			System.out.println("4. Borrar reparación");
			System.out.println("5. Cerrar sesión");
			System.out.println("6. Salir del programa");
			System.out.print("Elige una opción: ");
			String opcion = sc.nextLine();

			try {
				switch (opcion) {
				case "1":
					controlador.Reparaciones();
					break;
				case "2":
					controlador.registrarReparacion(null);
					break;
				case "3":
					controlador.actualizarReparacion(null);
					break;
				case "4":
					controlador.borrarReparacion(null);
					break;
				case "5":
					controlador.logout();
					return;
				case "6":
					System.exit(0);
					break;
				default:
					System.err.println("Opción incorrecta.");
				}
			} catch (Exception e) {
				System.err.println("Error en la operación: " + e.getMessage());
				System.out.println("");
			}
		}
	}

	/**
	 * Sirve para saber que puede hacer el administrador
	 */
	// MENÚ ADMINISTRADOR
	private static void menuAdministrador() {
		while (true) {
			System.out.println("");
			System.out.println("    MENÚ ADMINISTRADOR    ");
			System.out.println(
					"(NOTA IMPORTANTE: No se puede crear Vehículo sin Cliente, ni una Reparación sin Vehículo y un Usuario Mecánico para añadirles los id)");
			System.out.println("");
			System.out.println("1. Gestión de Clientes");
			System.out.println("2. Gestión de Vehículos");
			System.out.println("3. Gestión de Usuarios");
			System.out.println("4. Gestión de Reparaciones");
			System.out.println("5. Estadísticas");
			System.out.println("6. Cerrar sesión");
			System.out.println("7. Salir del programa");
			System.out.print("Elige una opción: ");
			String opcion = sc.nextLine();

			try {
				switch (opcion) {
				case "1":
					menuClientes();
					break;
				case "2":
					menuVehiculos();
					break;
				case "3":
					menuUsuarios();
					break;
				case "4":
					menuReparaciones();
					break;
				case "5":
					menuEstadisticas();
					break;
				case "6":
					controlador.logout();
					return;
				case "7":
					System.exit(0);
					break;
				default:
					System.err.println("Opción incorrecta.");
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
				System.out.println("");
			}
		}
	}
/**
 * Sirve para implementar todos los metodos del cliente
 */
	// SUBMENÚ CLIENTES
	private static void menuClientes() {
		while (true) {
			System.out.println("");
			System.out.println("    Menu Clientes    ");
			System.out.println("");
			System.out.println("1. Registrar cliente");
			System.out.println("2. Actualizar cliente");
			System.out.println("3. Borrar cliente");
			System.out.println("4. Volver");
			System.out.print("Opción: ");
			String op = sc.nextLine();

			switch (op) {
			case "1":
				controlador.registrarCliente(new Cliente());
				break;
			case "2":
				controlador.actualizarCliente(new Cliente());
				break;
			case "3":
				controlador.borrarCliente();
				break;
			case "4":
				return;
			default:
				System.err.println("Opción incorrecta.");
			}
		}
	}
	/**
	 * Sirve para implementar todos los metodos del vehiculos
	 */
	// SUBMENÚ VEHÍCULOS
	private static void menuVehiculos() {
		while (true) {
			System.out.println("");
			System.out.println("    Menu Vehículos    ");
			System.out.println("");
			System.out.println("1. Registrar vehículo");
			System.out.println("2. Actualizar vehículo");
			System.out.println("3. Borrar vehículo");
			System.out.println("4. Volver");
			System.out.print("Opción: ");
			String op = sc.nextLine();

			switch (op) {
			case "1":
				controlador.registrarVehiculo(new Vehiculo());
				break;
			case "2":
				controlador.actualizarVehiculo(new Vehiculo());
				break;
			case "3":
				controlador.borrarVehiculo();
				break;
			case "4":
				return;
			default:
				System.err.println("Opción incorrecta.");
			}
		}
	}

	/**
	 * Sirve para implementar todos los metodos del usuarios
	 */
	// SUBMENÚ USUARIOS
	private static void menuUsuarios() {
		while (true) {
			System.out.println("");
			System.out.println("    Menu Usuarios    ");
			System.out.println("");
			System.out.println("1. Registrar usuario");
			System.out.println("2. Actualizar usuario");
			System.out.println("3. Borrar usuario");
			System.out.println("4. Volver");
			System.out.print("Opción: ");
			String op = sc.nextLine();

			switch (op) {
			case "1":
				controlador.registrarUsuario(new Usuario());
				break;
			case "2":
				controlador.actualizarUsuario(new Usuario());
				break;
			case "3":
				controlador.borrarUsuario(new Usuario());
				break;
			case "4":
				return;
			default:
				System.err.println("Opción incorrecta.");
			}
		}
	}

	/**
	 * Sirve para implementar todos los metodos del reparaciones
	 */
	// SUBMENÚ REPARACIONES
	private static void menuReparaciones() {
		while (true) {
			System.out.println("");
			System.out.println("    Menu Reparaciones    ");
			System.out.println("");
			System.out.println("1. Ver todas las reparaciones");
			System.out.println("2. Registrar reparación");
			System.out.println("3. Actualizar reparación");
			System.out.println("4. Borrar reparación");
			System.out.println("5. Volver");
			System.out.print("Opción: ");
			String op = sc.nextLine();

			switch (op) {
			case "1":
				controlador.Reparaciones();
				break;
			case "2":
				controlador.registrarReparacion(new Reparacion());
				break;
			case "3":
				controlador.actualizarReparacion(new Reparacion());
				break;
			case "4":
				controlador.borrarReparacion(new Reparacion());
				break;
			case "5":
				return;
			default:
				System.err.println("Opción incorrecta.");
			}
		}
	}
	
	/**
	 * Sirve para ver las estadisticas
	 */

	private static void menuEstadisticas() {
		while (true) {
			System.out.println("");
			System.out.println("    Estadísticas    ");
			System.out.println("");
			System.out.println("1. Reparaciones por estado");
			System.out.println("2. Coste medio de reparaciones");
			System.out.println("3. Volver");
			System.out.print("Opción: ");
			String op = sc.nextLine();

			switch (op) {
			case "1":
				controlador.estadisticaPorEstado();
				break;
			case "2":
				controlador.estadisticaCosteMedioReparaciones();
				break;
			case "3":
				return;
			default:
				System.err.println("Opción incorrecta.");
			}
		}
	}
}