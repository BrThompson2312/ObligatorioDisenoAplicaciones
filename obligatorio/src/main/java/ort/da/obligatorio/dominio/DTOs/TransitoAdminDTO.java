package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.Puestos.Transito;
import java.time.LocalDateTime;

public class TransitoAdminDTO {
    private String nombrePropietario;
    private String ciPropietario;
    private String matriculaVehiculo;
    private String categoria;
    private String nombrePuesto;
    private String bonificacion;
    private double montoCobrado;
    private double saldoDespues;
    private LocalDateTime fechaHora;

    public TransitoAdminDTO() {
    }

    public TransitoAdminDTO(String nombrePropietario, String ciPropietario, String matriculaVehiculo, 
                       String categoria, String nombrePuesto, String bonificacion, 
                       double montoCobrado, double saldoDespues, LocalDateTime fechaHora) {
        this.nombrePropietario = nombrePropietario;
        this.ciPropietario = ciPropietario;
        this.matriculaVehiculo = matriculaVehiculo;
        this.categoria = categoria;
        this.nombrePuesto = nombrePuesto;
        this.bonificacion = bonificacion;
        this.montoCobrado = montoCobrado;
        this.saldoDespues = saldoDespues;
        this.fechaHora = fechaHora;
    }

    public static TransitoAdminDTO from(Transito transito) {
        String nombrePropietario = transito.getPropietario().getNombre();
        String ciPropietario = transito.getPropietario().getCi();
        String matriculaVehiculo = transito.getVehiculo().getMatricula();
        String categoria = transito.getVehiculo().getCategoriaDeVehiculo().getNombre().name();
        String nombrePuesto = transito.getPuesto().getNombre();
        String bonificacion = transito.getBonificacionAplicada() != null ? 
            transito.getBonificacionAplicada().getClass().getSimpleName() : "Ninguna";
        double montoCobrado = transito.getMontoCobrado();
        double saldoDespues = transito.getPropietario().getSaldoActual();
        LocalDateTime fechaHora = transito.getFecha();

        return new TransitoAdminDTO(nombrePropietario, ciPropietario, matriculaVehiculo, 
                              categoria, nombrePuesto, bonificacion, montoCobrado, 
                              saldoDespues, fechaHora);
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getCiPropietario() {
        return ciPropietario;
    }

    public void setCiPropietario(String ciPropietario) {
        this.ciPropietario = ciPropietario;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(String bonificacion) {
        this.bonificacion = bonificacion;
    }

    public double getMontoCobrado() {
        return montoCobrado;
    }

    public void setMontoCobrado(double montoCobrado) {
        this.montoCobrado = montoCobrado;
    }

    public double getSaldoDespues() {
        return saldoDespues;
    }

    public void setSaldoDespues(double saldoDespues) {
        this.saldoDespues = saldoDespues;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
