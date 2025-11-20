package ort.da.obligatorio.dominio.EstadosPropietario;
import ort.da.obligatorio.dominio.Personas.Notificacion;
import ort.da.obligatorio.dominio.Personas.Propietario;

public class Penalizado extends EstadoPropietarioAbstracto {
    public Penalizado() {
        super("Penalizado");
    }
    @Override
    public void agregarNotificacion(Propietario p, Notificacion n) {

    }
    @Override
    public void puedeRecibirBonificacion(Propietario p) {
        p.setPuedeRecibirBonificacion(false);
    }
}