package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.Personas.Vehiculo;
import java.util.List;
import java.util.stream.Collectors;

public class VehiculoDTO {
    private String matricula;
    private String color;
    private String modelo;
    private String categoria;
    private int totalTransitos;
    private double totalPagado;

    public VehiculoDTO() {
    }

    public VehiculoDTO(String matricula, String color, String modelo, String categoria, int totalTransitos, double totalPagado) {
        this.matricula = matricula;
        this.color = color;
        this.modelo = modelo;
        this.categoria = categoria;
        this.totalTransitos = totalTransitos;
        this.totalPagado = totalPagado;
    }

    public static VehiculoDTO from(Vehiculo vehiculo) {
        return new VehiculoDTO(
            vehiculo.getMatricula(),
            vehiculo.getColor(),
            vehiculo.getModelo(),
            vehiculo.getCategoriaDeVehiculo().getNombre().toString(),
            vehiculo.getTotalTransitos(),
            vehiculo.getTotalPagado()
        );
    }

    public static List<VehiculoDTO> fromList(List<Vehiculo> vehiculos) {
        if (vehiculos == null) return List.of();
        return vehiculos.stream()
                .map(VehiculoDTO::from)
                .collect(Collectors.toList());
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTotalTransitos() {
        return totalTransitos;
    }

    public void setTotalTransitos(int totalTransitos) {
        this.totalTransitos = totalTransitos;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }
}