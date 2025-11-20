package ort.da.obligatorio.dominio.Bonificaciones;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;

import java.time.LocalDateTime;

import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.Personas.Vehiculo;

public class AsignacionDeBonificacion {
    
    private Propietario propietario;
    private Puesto puesto;
    private EstrategiaBonificacion eBonificacion;
    private LocalDateTime fechaAsignacion;

    public AsignacionDeBonificacion(Propietario propietario, Puesto puesto, EstrategiaBonificacion eBonificacion) {
        this.propietario = propietario;
        this.puesto = puesto;
        this.eBonificacion = eBonificacion;
        this.fechaAsignacion = LocalDateTime.now();
    }

    public double calcularMonto(double monto, Puesto puesto, Propietario propietario, Vehiculo vehiculo) {
        return eBonificacion.calcularMonto(monto, puesto, propietario, vehiculo);
    }

    public Puesto getPuesto() { 
        return puesto;
    }

    public EstrategiaBonificacion getEstrategia() {
        return eBonificacion;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public Propietario getPropietario() {
        return propietario;
    }

}
