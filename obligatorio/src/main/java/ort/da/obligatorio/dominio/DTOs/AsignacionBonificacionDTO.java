package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import java.util.List;
import java.util.stream.Collectors;

public class AsignacionBonificacionDTO {
    private String nombreBonificacion;
    private String nombrePuesto;

    public AsignacionBonificacionDTO(String nombreBonificacion, String nombrePuesto) {
        this.nombreBonificacion = nombreBonificacion;
        this.nombrePuesto = nombrePuesto;
    }

    public static AsignacionBonificacionDTO from(AsignacionDeBonificacion asignacion) {
        String nombreBonificacion = asignacion.getEstrategia().getClass().getSimpleName();
        String nombrePuesto = asignacion.getPuesto().getNombre();
        return new AsignacionBonificacionDTO(nombreBonificacion, nombrePuesto);
    }

    public static List<AsignacionBonificacionDTO> fromList(List<AsignacionDeBonificacion> asignaciones) {
        return asignaciones.stream()
                .map(AsignacionBonificacionDTO::from)
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
}
