package entities;

import java.time.LocalDate;

public class Vehiculo {
	public Vehiculo(int idVehiculo, String matricula, String marca, String modelo, int clienteId) {
		super();
		this.idVehiculo = idVehiculo;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.clienteId = clienteId;
	}
	
	public int getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	
	
	
	
	private int idVehiculo;
	private String matricula;
	private String marca;
	private String modelo;
	private int clienteId;
	
}
