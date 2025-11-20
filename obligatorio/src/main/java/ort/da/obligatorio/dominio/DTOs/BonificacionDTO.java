package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import java.util.List;
import java.util.stream.Collectors;

public class BonificacionDTO {
    private String nombre;

    public BonificacionDTO(String nombre) {
        this.nombre = nombre;
    }

    public static BonificacionDTO from(EstrategiaBonificacion estrategia) {
        return new BonificacionDTO(estrategia.getClass().getSimpleName());
    }

    public static List<BonificacionDTO> fromList(List<EstrategiaBonificacion> estrategias) {
        return estrategias.stream()
                .map(BonificacionDTO::from)
                .collect(Collectors.toList());
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
