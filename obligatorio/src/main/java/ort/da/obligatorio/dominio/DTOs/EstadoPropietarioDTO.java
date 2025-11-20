package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;
import java.util.List;
import java.util.stream.Collectors;

public class EstadoPropietarioDTO {
    private String nombre;

    public EstadoPropietarioDTO(String nombre) {
        this.nombre = nombre;
    }

    public static EstadoPropietarioDTO from(EstadoPropietario estado) {
        return new EstadoPropietarioDTO(estado.getEstado().getNombre());
    }

    public static List<EstadoPropietarioDTO> fromList(List<EstadoPropietario> estados) {
        return estados.stream()
                .map(EstadoPropietarioDTO::from)
                .collect(Collectors.toList());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}