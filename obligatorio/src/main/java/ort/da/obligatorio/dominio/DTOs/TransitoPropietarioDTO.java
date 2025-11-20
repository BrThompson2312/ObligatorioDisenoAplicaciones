package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.Puestos.Transito;
import java.util.List;
import java.util.stream.Collectors;

public class TransitoPropietarioDTO {
    private String puesto;
    private String matricula;
    private String categoria;
    private double montoTarifa;
    private String bonificacion;
    private double montoBonificacion;
    private double montoPagado;
    private String fecha;
    private String hora;

    public TransitoPropietarioDTO() {
    }

    public TransitoPropietarioDTO(String puesto, String matricula, String categoria, 
                                  double montoTarifa, String bonificacion, 
                                  double montoBonificacion, double montoPagado, 
                                  String fecha, String hora) {
        this.puesto = puesto;
        this.matricula = matricula;
        this.categoria = categoria;
        this.montoTarifa = montoTarifa;
        this.bonificacion = bonificacion;
        this.montoBonificacion = montoBonificacion;
        this.montoPagado = montoPagado;
        this.fecha = fecha;
        this.hora = hora;
    }

    public static TransitoPropietarioDTO from(Transito transito) {
        String bonificacion = transito.getBonificacionAplicada() != null ? 
            transito.getBonificacionAplicada().getClass().getSimpleName() : "Ninguna";
        
        double montoBonificacion = transito.getBonificacionAplicada() != null ? 
            transito.getTarifa().getMonto() - transito.getMontoCobrado() : 0.0;

        return new TransitoPropietarioDTO(
            transito.getPuesto().getNombre(),
            transito.getVehiculo().getMatricula(),
            transito.getVehiculo().getCategoriaDeVehiculo().getNombre().name(),
            transito.getTarifa().getMonto(),
            bonificacion,
            montoBonificacion,
            transito.getMontoCobrado(),
            transito.getFecha().toLocalDate().toString(),
            transito.getFecha().toLocalTime().toString()
        );
    }

    public static List<TransitoPropietarioDTO> fromList(List<Transito> transitos) {
        if (transitos == null) return List.of();
        return transitos.stream()
                .map(TransitoPropietarioDTO::from)
                .collect(Collectors.toList());
    }

    // Getters y Setters
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getMontoTarifa() {
        return montoTarifa;
    }

    public void setMontoTarifa(double montoTarifa) {
        this.montoTarifa = montoTarifa;
    }

    public String getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(String bonificacion) {
        this.bonificacion = bonificacion;
    }

    public double getMontoBonificacion() {
        return montoBonificacion;
    }

    public void setMontoBonificacion(double montoBonificacion) {
        this.montoBonificacion = montoBonificacion;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
