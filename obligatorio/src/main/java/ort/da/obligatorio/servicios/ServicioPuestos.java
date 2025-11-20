package ort.da.obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Personas.Vehiculo;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.Puestos.Tarifa;
import ort.da.obligatorio.dominio.Puestos.Transito;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import ort.da.obligatorio.dominio.Personas.Notificacion;


public class ServicioPuestos {
    
    private List<Puesto> listPuestos;
    private List<EstrategiaBonificacion> listBonificaciones;
    
    public ServicioPuestos() {
        this.listPuestos = new ArrayList<>();
        this.listBonificaciones = new ArrayList<>();
    }
    
    public Transito registrarTransito(Propietario propietario, Vehiculo vehiculo, Puesto puesto) throws PeajeException {
        Tarifa tarifa = puesto.getTarifaPorCategoria(vehiculo.getCategoriaDeVehiculo());
        if (tarifa == null) {
            throw new PeajeException("No existe tarifa para la categoría: " 
                + vehiculo.getCategoriaDeVehiculo());
        }
        
        AsignacionDeBonificacion asignacion = propietario.getAsignacionDeBonificacionParaPuesto(puesto);
        EstrategiaBonificacion estrategia = null;
        
        if (asignacion != null&&propietario.PuedeRecibirBonificacion()) {
            estrategia = asignacion.getEstrategia();
        }
        
        double monto;
        if (estrategia != null) {
            monto = estrategia.calcularMonto(tarifa.getMonto(), puesto, propietario, vehiculo);
        } else {
            monto = tarifa.getMonto();
        }
        
        if (propietario.getSaldoActual() < monto) {
            throw new PeajeException("Saldo insuficiente. Saldo actual: $" + propietario.getSaldoActual() + ", Monto requerido: $" + monto);
        }

        if (!propietario.PuedeTransitar()) {
            throw new PeajeException("El propietario del vehiculo esta " + propietario.getEstado().getNombre() + ", no puede realizar transitos");
        }
        
        Transito transito = new Transito(propietario, vehiculo, puesto, tarifa, estrategia, monto);
        
        puesto.agregarTransito(transito);

        propietario.setSaldoActual(propietario.getSaldoActual() - monto);
        
        propietario.getEstado().agregarNotificacion(propietario, new Notificacion("Pasaste por el puesto: " + puesto.getNombre() + " con el vehiculo " + vehiculo.getMatricula()));

        verificarSaldoBajo(propietario);

        Fachada.getInstancia().avisar(Fachada.Eventos.nuevaNotificacion);
        Fachada.getInstancia().avisar(Fachada.Eventos.nuevoTransito);

        return transito;
    }
    
    private void verificarSaldoBajo(Propietario propietario) {
        if (propietario.getSaldoActual() < propietario.getSaldoMinimo()) {
            propietario.getEstado().agregarNotificacion(propietario, new Notificacion("Tu saldo actual es de $" + propietario.getSaldoActual() + " Te Recomendamos hacer una recarga"));
        }
    }

    public List<Puesto> getListPuestos() {
        return listPuestos;
    }

    public List<EstrategiaBonificacion> getListBonificaciones() {
        return listBonificaciones;
    }

    public void agregarPuesto(Puesto p) {
        listPuestos.add(p);
    }  

    public void agregarEstrategiaBonificacion(EstrategiaBonificacion eb) {
        listBonificaciones.add(eb);
    }

    public List<Transito> getTransitosPorPropietario(Propietario propietario){
        List<Transito> transitosPropietario = new ArrayList<>();
        for (Puesto puesto : listPuestos) {
            for (Transito transito : puesto.getTransitos()) {
                if (transito.getPropietario().getCi().equals(propietario.getCi())) {
                    transitosPropietario.add(transito);
                }
            }
        }
        return transitosPropietario;
    }

    public Puesto getPuestoPorNombre(String nombre) {
        for (Puesto puesto : listPuestos) {
            if (puesto.getNombre().equals(nombre)) {
                return puesto;
            }
        }
        return null;
    }

    public EstrategiaBonificacion getEstrategiaBonificacionPorNombre(String nombre){
        for (EstrategiaBonificacion eb : listBonificaciones) {
            if (eb.getClass().getSimpleName().equals(nombre)) {
                return eb;
            }
        };
        return null;
    }


}
