package ort.da.obligatorio.dominio.EstadosPropietario;
import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.Personas.Notificacion;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;

public abstract class EstadoPropietarioAbstracto implements EstadoPropietario {
    
    private String nombre;

    public EstadoPropietarioAbstracto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarNotificacion(Propietario p, Notificacion n) {

    }

    public void agregarBonificacion(Propietario p, AsignacionDeBonificacion ab) {
        
    }

    public void puedeLogin(Propietario p) {
        
    }

    public String registraTransito() {
        return null;
    }
    
}