package ort.da.obligatorio.dominio;
import java.util.Date;

import ort.da.obligatorio.dominio.Personas.Persona;

public class Sesion {
    private Date fechaIngreso = new Date();
    private Persona persona;

    public Sesion(Persona p) {
        this.persona = p;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Persona getPersona() {
        return persona;
    }
}
