package ort.da.obligatorio.dominio.Puestos;

import ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo;

public class Tarifa {
 
    private double monto;
    private CategoriaDeVehiculo categoriaDeVehiculo;

    public Tarifa(double monto, CategoriaDeVehiculo categoriaDeVehiculo) {
        this.monto = monto;
        this.categoriaDeVehiculo = categoriaDeVehiculo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public CategoriaDeVehiculo getCategoriaDeVehiculo() {
        return categoriaDeVehiculo;
    }

    public void setCategoriaDeVehiculo(CategoriaDeVehiculo categoriaDeVehiculo) {
        this.categoriaDeVehiculo = categoriaDeVehiculo;
    }
}
