package ort.da.obligatorio.dominio.Puestos;

import java.util.List;

import ort.da.obligatorio.dominio.Personas.Propietario;

public class Puesto {
    
    private String nombre;
    private String direccion;
    private List<Transito> listTransito;
    private List<Tarifa> listTarifas;
    public Transito transito;

    public Puesto(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public List<Transito> getTransitos() {
        return null;
    }

    public List<Tarifa> getTarifas() {
        return null;
    }

    public void agregarTransito(Transito t) {
        listTransito.add(t);
    }

    public Propietario getPropietario() {
        return transito.getPropietario();
    }
    
}