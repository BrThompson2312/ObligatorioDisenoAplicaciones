package ort.da.obligatorio.dominio.Personas;

public class Vehiculo {
    
    private String matricula;
    private String color;
    private String modelo;
    private CategoriaDeVehiculo categoriaDeVehiculo;
    
    public Vehiculo(String matricula, String color, String modelo, CategoriaDeVehiculo categoriaDeVehiculo) {
        this.matricula = matricula;
        this.color = color;
        this.modelo = modelo;
        this.categoriaDeVehiculo = categoriaDeVehiculo;
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

    public CategoriaDeVehiculo getCategoriaDeVehiculo() {
        return categoriaDeVehiculo;
    }

    public void setCategoriaDeVehiculo(CategoriaDeVehiculo categoriaDeVehiculo) {
        this.categoriaDeVehiculo = categoriaDeVehiculo;
    }
}
