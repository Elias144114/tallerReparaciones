package entities;

public class Usuario {
	public Usuario(int idUsuario, String dni, String usuario, String password, String rol) {
		super();
		this.idUsuario = idUsuario;
		this.dni = dni;
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	private String usuario;
	private String password;
	private String rol;
}
