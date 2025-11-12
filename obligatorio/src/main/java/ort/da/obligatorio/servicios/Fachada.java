package ort.da.obligatorio.servicios;

import java.util.List;

import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.Personas.Persona;
import ort.da.obligatorio.dominio.Puestos.Puesto;

public class Fachada {
    
    private Fachada instance;
    private ServicioPersonas sPersonas;
    private ServicioPuestos sPuestos;

    private Fachada() {
        sPersonas = new ServicioPersonas();
        sPuestos = new ServicioPuestos();
    }

    public Fachada getInstancia() {
        if (instance == null) {
            return new Fachada();
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

    public void loginAdmin(String ci, String c, List<Persona> propietarios) {

    }

    public void loginPropietario(String ci, String c, List<Persona> propietarios) {
        
    }

    public void asignarBonificacion(List<EstrategiaBonificacion> listBonificaciones, List<Puesto> listPuestos) {

    }

    public void menuAdministrador() {

    }

    public void emularTransito(List<Puesto> listPuestos, List<Persona> propietarios) {

    }

    public void CambioEstadoPropietarios(List<Persona> propietarios, List<EstadoPropietario> estadoPropietarios) {
        
    }

}
