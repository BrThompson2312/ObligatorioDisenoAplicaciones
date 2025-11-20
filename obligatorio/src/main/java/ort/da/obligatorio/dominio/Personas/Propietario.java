package ort.da.obligatorio.dominio.Personas;
import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.EstadosPropietario.EstadoPropietarioAbstracto;
import java.util.ArrayList;
import java.util.List;
import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;
import ort.da.obligatorio.dominio.Puestos.Puesto;

public class Propietario extends Persona{
    
    private double saldoActual;
    private double saldoMinimo;
    private EstadoPropietario estado;
    private List<Vehiculo> listVehiculos;
    private List<AsignacionDeBonificacion> listBonificaciones;
    private List<Notificacion> listNotificacions;
    private boolean puedeLogin;
    private boolean puedeTransitar;
    private boolean puedeRecibirBonificacion;

    public Propietario(String ci, String password, String nombre, double saldoActual, double saldoMinimo, EstadoPropietario estado) {
        super(ci, password, nombre);
        this.saldoActual = saldoActual;
        this.saldoMinimo = saldoMinimo;
        this.estado = estado;
        this.listBonificaciones = new ArrayList<>();
        this.listVehiculos = new ArrayList<>();
        this.listNotificacions = new ArrayList<>();
        estado.puedeLogin(this);
        estado.registraTransito(this);
        estado.puedeRecibirBonificacion(this);
    }

    public EstadoPropietarioAbstracto getEstado() {
        return estado.getEstado();
    }

    public double getSaldoActual() {
        return saldoActual;
    }
    
    public double getSaldoMinimo() {
        return saldoMinimo;
    }

    public boolean PuedeLogin() {
        return puedeLogin;
    }

    public boolean PuedeTransitar() {
        return puedeTransitar;
    }

    public boolean PuedeRecibirBonificacion() {
        return puedeRecibirBonificacion;
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

    public void setPuedeRecibirBonificacion(boolean puedeRecibirBonificacion) {
        this.puedeRecibirBonificacion = puedeRecibirBonificacion;
    }

    public List<AsignacionDeBonificacion> getListBonificaciones() {
        return listBonificaciones;
    }

    public void agregarBonificacion(AsignacionDeBonificacion ab) {
        getListBonificaciones().add(ab);
    }

    public List<Vehiculo> getListVehiculos() {
        return listVehiculos;
    }

    public void agregarVehiculo(Vehiculo v) {
        getListVehiculos().add(v);
    }

    public List<Notificacion> getListNotificaciones() {
        return listNotificacions;
    }

    public AsignacionDeBonificacion getAsignacionDeBonificacionParaPuesto(Puesto puesto) {
        for (AsignacionDeBonificacion ab : listBonificaciones) {
            if(ab.getPuesto().equals(puesto)) {
                return ab;
            }
        }
        return null;
    }

    public void agregarNotificacion(Notificacion n) {
        getListNotificaciones().add(0,n);
    }

    public void eliminarNotificaciones() {
        getListNotificaciones().clear();
    }
}
