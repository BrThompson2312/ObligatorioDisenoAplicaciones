package ort.da.obligatorio.dominio.Puestos;

import java.util.List;

import ort.da.obligatorio.dominio.Personas.Propietario;

public class Puesto {
    
    private String nombre;
    private String direccion;
    private List<Transito> listTransito;
    private List<Tarifa> listTarifas;

    public Puesto(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public List<Transito> getTransitos() {
        return listTransito;
    }

    public List<Tarifa> getTarifas() {
        return listTarifas;
    }

    public void agregarTransito(Transito t) {
        listTransito.add(t);
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
    
}