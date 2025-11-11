package ort.da.obligatorio.dominio;

import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;

public abstract class EstadoPropietarioAbstracto implements EstadoPropietario {
    
    private String nombre;

    public void agregarNotificacion(Propietario p, Notificacion n) {

    }

    public void puedeLogin() {

    }

    public void agregarBonificacion(Propietario p, AsignacionDeBonificacion ab) {
        // TODO Auto-generated method stub
        
    }

    public void puedeLogin(Propietario p) {
        // TODO Auto-generated method stub
        
    }

    public String registraTransito() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
