package entities;

public class Usuario {
	public Usuario(int idUsuario, String dni, String nombreUsuario, String password, String rol) {
		this.idUsuario = idUsuario;
		this.dni = dni;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.rol = rol;
	}
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public String getDni() {
		return dni;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String usuario) {
		this.nombreUsuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	private int idUsuario;
	private String dni;
	private String nombreUsuario;
	private String password;
	private String rol;
}
