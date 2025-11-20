package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDeVehiculoDTO {
    private String nombre;

    public CategoriaDeVehiculoDTO() {
    }

    public CategoriaDeVehiculoDTO(CategoriaDeVehiculo categoria) {
        this.nombre = categoria.getNombre().name();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
