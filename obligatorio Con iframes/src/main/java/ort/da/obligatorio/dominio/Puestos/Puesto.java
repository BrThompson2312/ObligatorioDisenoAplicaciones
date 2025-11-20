package ort.da.obligatorio.dominio.Puestos;

import java.util.ArrayList;
import java.util.List;

import ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Personas.Vehiculo;

public class Puesto {
    
    private String nombre;
    private String direccion;
    private List<Transito> listTransitos;
    private List<Tarifa> listTarifas;

    public Puesto(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listTransitos = new ArrayList<>();
        this.listTarifas = new ArrayList<>();
    }

    public List<Transito> getTransitos() {
        return listTransitos;
    }

    public List<Tarifa> getTarifas() {
        return listTarifas;
    }

    public void agregarTransito(Transito t) {
        listTransitos.add(t);
        System.out.println("Transito agregado:");
        System.out.println(listTransitos);
    }
    
    public void agregarTarifa(Tarifa ta) {
        listTarifas.add(ta);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Tarifa getTarifaPorCategoria(CategoriaDeVehiculo categoria) {
        for (Tarifa t : listTarifas) {
            if (t.getCategoriaDeVehiculo().getNombre() == categoria.getNombre()) {
                return t;
            }
        }
        return null;
    }

    public boolean segundoTransitoHoy(Propietario propietario, Vehiculo vehiculo) {
        int transitos = 0;
        for (Transito t : listTransitos) {
            if (t.getPropietario().getCi().equals(propietario.getCi()) && t.getFecha().toLocalDate().equals(java.time.LocalDate.now())
                && t.getVehiculo().getMatricula().equals(vehiculo.getMatricula())) {
                transitos++;
            }
        }
        return transitos == 1;
    }

}