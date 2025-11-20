package ort.da.obligatorio.servicios;

import java.util.List;

import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.Personas.Persona;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import ort.da.obligatorio.dominio.Personas.Vehiculo;
import jakarta.servlet.http.HttpSession;
import ort.da.obligatorio.dominio.Puestos.Transito;

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

    public Sesion loginAdministrador(String ci, String c, HttpSession httpSession) throws PeajeException{
        return sPersonas.loginAdministrador(ci, c, httpSession);
    }

    public Sesion loginPropietario(String ci, String c, HttpSession httpSession) throws PeajeException{
        return sPersonas.loginPropietario(ci, c, httpSession);
    }

    public void logout(Sesion s) {
        sPersonas.logout(s);
    }

    public void menuAsignarBonificacion(List<EstrategiaBonificacion> listBonificaciones, List<Puesto> listPuestos) {

    }

    public void asignarBonificacion(EstrategiaBonificacion eb, Puesto p, Propietario propietario) throws PeajeException{
        sPersonas.asignarBonificacion(eb, p, propietario);
    }

    public void menuAdministrador() {

    }

    public void emularTransito(Propietario propietario, Puesto puesto, Vehiculo vehiculo) throws PeajeException {
        sPuestos.registrarTransito(propietario, vehiculo, puesto);
    }

    public void CambioEstadoPropietarios(List<Persona> propietarios, List<EstadoPropietario> estadoPropietarios) {
        
    }

    public void agregarEstado(EstadoPropietario estado) {
        sPersonas.agregarEstado(estado);
    }

    public void agregarEstrategiaBonificacion(EstrategiaBonificacion eb) {
        sPuestos.agregarEstrategiaBonificacion(eb);
    }

    public void agregarPersona(Persona p) {
        sPersonas.agregarPersona(p);
    }

    public void agregarPuesto(Puesto p) {
        sPuestos.agregarPuesto(p);
    }

    public List<Transito> getTransitosPorPropietario(Propietario propietario){
        return sPuestos.getTransitosPorPropietario(propietario);
    }
}
