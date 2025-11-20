package ort.da.obligatorio.dominio.Personas;

import ort.da.obligatorio.dominio.Puestos.Tarifa;

public class CategoriaDeVehiculo {
    
    public enum TipoVehiculo {
        AUTO,
        MOTO,
        CAMION,
        CAMIONETA
    }

    private TipoVehiculo nombre;
    
    public CategoriaDeVehiculo(TipoVehiculo nombre) {
        this.nombre = nombre;
    }

    public TipoVehiculo getNombre() {
        return nombre;
    }
}