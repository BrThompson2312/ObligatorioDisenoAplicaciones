package ort.da.obligatorio.servicios;
import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.Personas.Administrador;
import ort.da.obligatorio.dominio.Personas.Persona;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import jakarta.servlet.http.HttpSession;


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
    
    public Persona puedeLogin(String ci, String pwd) {
        for (Persona p : personas) {
            if(p.getCi().equals(ci)&& p.getPassword().equals(pwd)) {
                return p;
            }
        }
        return null;
    }
    
    public Sesion loginPropietario(String ci, String pwd, HttpSession httpSession) throws PeajeException {
        Persona persona = puedeLogin(ci, pwd); 

        if(persona != null && persona instanceof Propietario) {
            Propietario propietario = (Propietario) persona;

            if(!propietario.PuedeLogin()) {
                throw new PeajeException("Usuario deshabilitado, no puede ingresar al sistema");
            }
            Sesion sesion = new Sesion(propietario, httpSession);
            sesiones.add(sesion);
            httpSession.setAttribute("sesion", sesion);

            return sesion;
        }

        throw new PeajeException("Acceso denegado");
    }

    public Sesion loginAdministrador(String ci, String pwd, HttpSession httpSession) throws PeajeException {
         Persona persona = puedeLogin(ci, pwd); 
        if(persona != null && persona instanceof Administrador) {
            Administrador administrador = (Administrador) persona;
            if(mismoAdminLogueado(administrador)) {
                throw new PeajeException("Ud. Ya esta logueado");
            }
            Sesion sesion = new Sesion(administrador, httpSession);
            sesiones.add(sesion);
            httpSession.setAttribute("sesion", sesion);
            return sesion;
        }

        throw new PeajeException("Acceso denegado");
    }

    public boolean mismoAdminLogueado(Administrador a){
        for (Sesion s : sesiones) {
            if(s.getPersona() instanceof Administrador) {
                Administrador adminLogueado = (Administrador) s.getPersona();
                if(adminLogueado.getCi().equals(a.getCi())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void logout(Sesion s) {
        sesiones.remove(s);
        s.cerrarSesion();
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


}
