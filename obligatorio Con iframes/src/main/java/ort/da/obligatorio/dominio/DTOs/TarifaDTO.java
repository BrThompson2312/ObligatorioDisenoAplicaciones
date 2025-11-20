package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.Puestos.Tarifa;

public class TarifaDTO {
    private double monto;
    private CategoriaDeVehiculoDTO categoriaDeVehiculo;

    public TarifaDTO() {
    }

    public TarifaDTO(Tarifa tarifa) {
        this.monto = tarifa.getMonto();
        this.categoriaDeVehiculo = new CategoriaDeVehiculoDTO(tarifa.getCategoriaDeVehiculo());
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public CategoriaDeVehiculoDTO getCategoriaDeVehiculo() {
        return categoriaDeVehiculo;
    }

    public void setCategoriaDeVehiculo(CategoriaDeVehiculoDTO categoriaDeVehiculo) {
        this.categoriaDeVehiculo = categoriaDeVehiculo;
    }
}
