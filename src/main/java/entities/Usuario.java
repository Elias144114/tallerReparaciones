package entities;

public class Usuario {
	public Usuario(int idUsuario, String nombreUsuario, String dni, String password, String rol) {
		this.idUsuario = idUsuario;
		this.dni = dni;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.rol = rol;
	}
	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}


	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public void setDni(String dni) {
		this.dni = dni;
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
