package ort.da.obligatorio.dominio.interfaces;

import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.EstadosPropietario.EstadoPropietarioAbstracto;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import ort.da.obligatorio.dominio.Personas.Notificacion;
import ort.da.obligatorio.dominio.Personas.Propietario;

public interface EstadoPropietario {

    void agregarNotificacion(Propietario p, Notificacion n);

    void puedeLogin(Propietario p);

    void agregarBonificacion(Propietario p, AsignacionDeBonificacion ab) throws PeajeException;

    void registraTransito(Propietario p);

    void puedeRecibirBonificacion(Propietario p);

    EstadoPropietarioAbstracto getEstado();
    
}