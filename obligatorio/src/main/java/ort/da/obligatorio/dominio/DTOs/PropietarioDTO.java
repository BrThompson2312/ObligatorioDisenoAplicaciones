package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.Personas.Propietario;
import java.util.List;

public class PropietarioDTO {
    private String nombre;
    private String estado;
    private List<AsignacionBonificacionDTO> bonificaciones;

    public PropietarioDTO() {
    }

    public PropietarioDTO(String nombre, String estado, List<AsignacionBonificacionDTO> bonificaciones) {
        this.nombre = nombre;
        this.estado = estado;
        this.bonificaciones = bonificaciones;
    }

    public static PropietarioDTO from(Propietario propietario) {
        String nombre = propietario.getNombre();
        String estado = propietario.getEstado().getNombre();
        List<AsignacionBonificacionDTO> bonificaciones = 
            AsignacionBonificacionDTO.fromList(propietario.getListBonificaciones());
        
        return new PropietarioDTO(nombre, estado, bonificaciones);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<AsignacionBonificacionDTO> getBonificaciones() {
        return bonificaciones;
    }

    public void setBonificaciones(List<AsignacionBonificacionDTO> bonificaciones) {
        this.bonificaciones = bonificaciones;
    }
}