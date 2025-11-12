package ort.da.obligatorio.dominio.interfaces;

// Dominio
import ort.da.obligatorio.dominio.Propietario;
import ort.da.obligatorio.dominio.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.Notificacion;

public interface EstadoPropietario {

    void agregarNotificacion(Propietario p, Notificacion n);

    void puedeLogin(Propietario p);

    void agregarBonificacion(Propietario p, AsignacionDeBonificacion ab);

    String registraTransito();
}