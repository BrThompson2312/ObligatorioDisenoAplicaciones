package ort.da.obligatorio.dominio;

// Libraries
import java.util.List;

// Dominio
import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;

public class Propietario extends Persona implements EstadoPropietario {
    
    private double saldoActual;
    private double saldoMinimo;
    private EstadoPropietario estado;
    private List<Vehiculo> listVehiculos;
    private List<AsignacionDeBonificacion> listBonificaciones;
    private List<Notificacion> listNotificacions;
    private boolean puedeLogin;
    private boolean puedeTransitar;

    public double getSaldoActual() {
        return saldoActual;
    }
    
    public double getSaldoMinimo() {
        return saldoMinimo;
    }

    public boolean isPuedeLogin() {
        return puedeLogin;
    }

    public boolean isPuedeTransitar() {
        return puedeTransitar;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public void setSaldoMinimo(double saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
    }

    public void setPuedeLogin(boolean puedeLogin) {
        this.puedeLogin = puedeLogin;
    }

    public void setPuedeTransitar(boolean puedeTransitar) {
        this.puedeTransitar = puedeTransitar;
    }

    @Override
    public void agregarBonificacion(Propietario p, AsignacionDeBonificacion ab) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void agregarNotificacion(Propietario p, Notificacion n) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void puedeLogin(Propietario p) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String registraTransito() {
        // TODO Auto-generated method stub
        return null;
    }

}
