package ort.da.obligatorio.dominio.interfaces;

import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.Personas.Notificacion;
import ort.da.obligatorio.dominio.Personas.Propietario;

public interface EstadoPropietario {

    void agregarNotificacion(Propietario p, Notificacion n);

    void puedeLogin(Propietario p);

    void agregarBonificacion(Propietario p, AsignacionDeBonificacion ab);

    String registraTransito();
    
}