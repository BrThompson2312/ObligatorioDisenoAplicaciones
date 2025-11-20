package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BonificacionPropietarioDTO {
    private String nombreBonificacion;
    private String nombrePuesto;
    private String fechaAsignacion; // Cambiar de LocalDateTime a String

    public BonificacionPropietarioDTO() {
    }

    public BonificacionPropietarioDTO(String nombreBonificacion, String nombrePuesto, String fechaAsignacion) {
        this.nombreBonificacion = nombreBonificacion;
        this.nombrePuesto = nombrePuesto;
        this.fechaAsignacion = fechaAsignacion != null ? fechaAsignacion.toString() : null;
    }

    public static BonificacionPropietarioDTO from(AsignacionDeBonificacion asignacion) {
        String nombreBonificacion = asignacion.getEstrategia().getClass().getSimpleName();
        String nombrePuesto = asignacion.getPuesto().getNombre();
        String fechaAsignacion = asignacion.getFechaAsignacion().toString();
        
        return new BonificacionPropietarioDTO(nombreBonificacion, nombrePuesto, fechaAsignacion);
    }

    public static List<BonificacionPropietarioDTO> fromList(List<AsignacionDeBonificacion> asignaciones) {
        if (asignaciones == null) return List.of();
        return asignaciones.stream()
                .map(BonificacionPropietarioDTO::from)
                .collect(Collectors.toList());
    }

    public String getNombreBonificacion() {
        return nombreBonificacion;
    }

    public void setNombreBonificacion(String nombreBonificacion) {
        this.nombreBonificacion = nombreBonificacion;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}
