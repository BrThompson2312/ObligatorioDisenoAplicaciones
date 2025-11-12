package ort.da.obligatorio.dominio.EstadosPropietario;
import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
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
        
    }
    @Override
    public void agregarNotificacion(Propietario p, Notificacion n) {
    
    }
    @Override
    public void agregarBonificacion(Propietario p, AsignacionDeBonificacion ab) {
        
    }
}