package ort.da.obligatorio.dominio.EstadosPropietario;
import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import ort.da.obligatorio.dominio.Personas.Notificacion;
import ort.da.obligatorio.dominio.Personas.Propietario;



public class Deshabilitado extends EstadoPropietarioAbstracto {
    public Deshabilitado() {
        super("Deshabilitado");
    }
    @Override
    public String registraTransito() {
        return null;
    }
    @Override
    public void puedeLogin(Propietario p) {
        p.setPuedeLogin(false);
    }
    @Override
    public void agregarNotificacion(Propietario p, Notificacion n) {
    
    }
    @Override
    public void agregarBonificacion(Propietario p, AsignacionDeBonificacion ab) throws PeajeException{
        throw new PeajeException("El propietario esta deshabilitado. No se pueden asignar bonificaciones");
    }
}