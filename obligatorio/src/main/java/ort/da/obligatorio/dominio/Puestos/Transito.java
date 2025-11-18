package ort.da.obligatorio.dominio.Puestos;

import java.util.Date;

import ort.da.obligatorio.dominio.Personas.Propietario;

public class Transito {
    
    private Propietario propietario;
    private String puesto;
    private double tarifa;
    private Date fecha; 

    public Transito(int id, Propietario propietario, String puesto, double tarifa, Date fecha) {
        this.propietario = propietario;
        this.puesto = puesto;
        this.tarifa = tarifa;
        this.fecha = fecha;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   
}
