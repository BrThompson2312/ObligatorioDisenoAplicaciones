package ort.da.obligatorio.servicios;

import java.util.List;

import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.Personas.Persona;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;

public class Fachada {
    
    private static Fachada instance;
    private ServicioPersonas sPersonas;
    private ServicioPuestos sPuestos;

    private Fachada() {
        sPersonas = new ServicioPersonas();
        sPuestos = new ServicioPuestos();
    }

    public static Fachada getInstancia() {
        if (instance == null) {
            instance = new Fachada();
        } 
        return instance;
    }

    public List<Sesion> getSesiones() {
        return sPersonas.getSesiones();
    }

    public void precarga() {}

    public List<Persona> getPersonas() {
        return null;
    }

    public List<Puesto> getPuestos() {
        return null;
    }

    public Sesion loginAdministrador(String ci, String c) throws PeajeException{
        return sPersonas.loginAdministrador(ci, c);
    }

    public Sesion loginPropietario(String ci, String c) throws PeajeException{
        return sPersonas.loginPropietario(ci, c);
    }

    public void logout(Sesion s) {
        sPersonas.logout(s);
    }

    public void asignarBonificacion(List<EstrategiaBonificacion> listBonificaciones, List<Puesto> listPuestos) {

    }

    public void menuAdministrador() {

    }

    public void emularTransito(List<Puesto> listPuestos, List<Persona> propietarios) {

    }

    public void CambioEstadoPropietarios(List<Persona> propietarios, List<EstadoPropietario> estadoPropietarios) {
        
    }

    public void agregarEstado(EstadoPropietario estado) {
        sPersonas.agregarEstado(estado);
    }

    public void agregarPersona(Persona p) {
        sPersonas.agregarPersona(p);
    }

}
