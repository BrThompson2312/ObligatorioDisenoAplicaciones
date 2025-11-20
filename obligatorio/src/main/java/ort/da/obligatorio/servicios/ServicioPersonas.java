package ort.da.obligatorio.servicios;
import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.Personas.Administrador;
import ort.da.obligatorio.dominio.Personas.Notificacion;
import ort.da.obligatorio.dominio.Personas.Persona;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import jakarta.servlet.http.HttpSession;
import ort.da.obligatorio.dominio.Personas.Vehiculo;
import ort.da.obligatorio.dominio.EstadosPropietario.Habilitado;
import ort.da.obligatorio.dominio.EstadosPropietario.Deshabilitado;
import ort.da.obligatorio.dominio.EstadosPropietario.Suspendido;
import ort.da.obligatorio.dominio.EstadosPropietario.Penalizado;

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

    public boolean puestoSinMismaBonificacion(Propietario p, Puesto puesto) throws PeajeException {
        for (AsignacionDeBonificacion ab : p.getListBonificaciones()) {
            if(ab.getPuesto().equals(puesto)) {
                return false;
            }
        }
        return true;
    }

    public void asignarBonificacion(EstrategiaBonificacion eb, Puesto p, Propietario propietario) throws PeajeException {
        AsignacionDeBonificacion ab = new AsignacionDeBonificacion(propietario, p, eb);
        if(!puestoSinMismaBonificacion(propietario, p)){
            throw new PeajeException("Ya tiene una bonifiacion asignada para este puesto");
        }
        propietario.getEstado().agregarBonificacion(propietario, ab);
    }

    public Vehiculo getVehiculoPorMatricula(String matricula) throws PeajeException {
        for (Persona persona : personas) {
            if (persona instanceof Propietario) {
                Propietario propietario = (Propietario) persona;
                for (Vehiculo vehiculo : propietario.getListVehiculos()) {
                    if (vehiculo.getMatricula().equals(matricula)) {
                        return vehiculo;
                    }
                }
            }
        }
        throw new PeajeException("No existe el vehiculo");
    }

    public Propietario getPropietarioPorVehiculo(Vehiculo vehiculo) {
        for (Persona persona : personas) {
            if (persona instanceof Propietario) {
                Propietario propietario = (Propietario) persona;
                if (propietario.getListVehiculos().contains(vehiculo)) {
                    return propietario;
                }
            }
        }
        return null;
    }

    public void borrarNotificacionesPropietario(Propietario propietario) {
        propietario.eliminarNotificaciones();
    }

    public Propietario getPropietarioPorCi(String ci) {
        for (Persona persona : personas) {
            if (persona instanceof Propietario && persona.getCi().equals(ci)) {
                return (Propietario) persona;
            }
        }
        return null;
    }

    public void cambiarEstadoPropietario(Persona persona, String estadoPropietario) throws PeajeException {
        EstadoPropietario getEstadoConcreto = getEstadoPropietarioPorNombre(estadoPropietario);
        Propietario propietario = (Propietario) persona;
        try {
            propietario.setEstado(getEstadoConcreto);
        } catch (Exception e) {
            throw new PeajeException(e.getMessage());
        }
        //No pasa por el estado de propietario ya que no depende de el
        propietario.agregarNotificacion(new Notificacion("Se ha cambiado tu estado en el sistema. Tu estado actual es " + propietario.getEstado().getNombre()));
    }

    private EstadoPropietario getEstadoPropietarioPorNombre(String estadoPropietario) {
        EstadoPropietario ep = null;
        switch (estadoPropietario) {
            case "Habilitado":
                ep = new Habilitado(); break;
            case "Deshabilitado":
                ep = new Deshabilitado(); break;
            case "Suspendido":
                ep = new Suspendido(); break;
            case "Penalizado":
                ep = new Penalizado(); break;
        }
        return ep;
    }
}