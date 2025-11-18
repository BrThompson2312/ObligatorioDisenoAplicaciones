package ort.da.obligatorio.dominio.EstadosPropietario;
import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.Personas.Notificacion;
import ort.da.obligatorio.dominio.Personas.Propietario;

public class Habilitado extends EstadoPropietarioAbstracto {
    public Habilitado() {
        super("Habilitado");
    }
    @Override
    public String registraTransito() {
        return null;
    }
    @Override
    public void agregarNotificacion(Propietario p, Notificacion n) {
    
    }


}
