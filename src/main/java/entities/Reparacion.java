package entities;

import java.time.LocalDate;

public class Reparacion {
	public int getIdReparacion() {
		return idReparacion;
	}

	public void setIdReparacion(int idReparacion) {
		this.idReparacion = idReparacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Double getCosteEstimado() {
		return costeEstimado;
	}

	public void setCosteEstimado(Double costeEstimado) {
		this.costeEstimado = costeEstimado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getVehiculoId() {
		return vehiculoId;
	}

	public void setVehiculoId(int vehiculoId) {
		this.vehiculoId = vehiculoId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	private int idReparacion;
	private String descripcion;
	private LocalDate fechaEntrada;
	private Double costeEstimado;
	private String estado;
	private int vehiculoId;
	private int usuarioId;

	public Reparacion(int idReparacion, String descripcion, LocalDate fechaEntrada, Double costeEstimado, String estado,
			int vehiculoId, int usuarioId) {
		super();
		this.idReparacion = idReparacion;
		this.descripcion = descripcion;
		this.fechaEntrada = fechaEntrada;
		this.costeEstimado = costeEstimado;
		this.estado = estado;
		this.vehiculoId = vehiculoId;
		this.usuarioId = usuarioId;
	}

}


