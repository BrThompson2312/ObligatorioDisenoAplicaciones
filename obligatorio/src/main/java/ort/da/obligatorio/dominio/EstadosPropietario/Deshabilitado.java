package ort.da.obligatorio.dominio.EstadosPropietario;
import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import ort.da.obligatorio.dominio.Personas.Propietario;



public class Deshabilitado extends EstadoPropietarioAbstracto {
    public Deshabilitado() {
        super("Deshabilitado");
    }
    @Override
    public void registraTransito(Propietario p) {
        p.setPuedeTransitar(false);
    }
    @Override
    public void puedeLogin(Propietario p) {
        p.setPuedeLogin(false);
    }
    @Override
    public void agregarBonificacion(Propietario p, AsignacionDeBonificacion ab) throws PeajeException{
        throw new PeajeException("El propietario esta deshabilitado. No se pueden asignar bonificaciones");
    }
}