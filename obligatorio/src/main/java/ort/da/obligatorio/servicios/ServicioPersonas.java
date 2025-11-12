package ort.da.obligatorio.servicios;
import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.Personas.Persona;
import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;

import java.util.List;

import java.util.ArrayList;

public class ServicioPersonas {
    private List<Persona> personas;
    private List<EstadoPropietario> estados;
    private ArrayList<Sesion> sesiones;

    public ServicioPersonas() {
        this.personas = new ArrayList<>();
        this.estados = new ArrayList<>();
        this.sesiones = new ArrayList<>();
    }

    public void agregarPersona(Persona p) {
        personas.add(p);
    }

    public void agregarEstado(EstadoPropietario e) {
        estados.add(e);
    }
    /**/
    public void loginPropietario(String ci, String pwd) {

    }
    public void loginAdministrador(String ci, String pwd) {
        
    }

    public void logout(Sesion s) {
        sesiones.remove(s);
    }
    
    public ArrayList<Sesion> getSesiones() {
        return sesiones;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public List<EstadoPropietario> getEstados() {
        return estados;
    }

    public boolean puedeLogIn(Persona p) {
        return true;
    }


}
