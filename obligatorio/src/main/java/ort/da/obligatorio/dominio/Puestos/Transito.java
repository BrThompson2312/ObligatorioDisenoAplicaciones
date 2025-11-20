package ort.da.obligatorio.dominio.Puestos;

import java.time.LocalDateTime;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Personas.Vehiculo;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;

public class Transito {
    
    private Puesto puesto;
    private Propietario propietario;
    private Vehiculo vehiculo;
    private Tarifa tarifa;
    private EstrategiaBonificacion bonificacionAplicada;
    private double montoCobrado;
    private LocalDateTime fecha;

    public Transito(Propietario propietario, Vehiculo vehiculo, Puesto puesto, 
                    Tarifa tarifa, EstrategiaBonificacion bonificacion, double montoCobrado) {
        this.propietario = propietario;
        this.vehiculo = vehiculo;
        this.puesto = puesto;
        this.tarifa = tarifa;
        this.bonificacionAplicada = bonificacion;
        this.montoCobrado = montoCobrado;
        this.fecha = LocalDateTime.now();
    }

    public Puesto getPuesto() { return puesto; }
    public Propietario getPropietario() { return propietario; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public Tarifa getTarifa() { return tarifa; }
    public EstrategiaBonificacion getBonificacionAplicada() { return bonificacionAplicada; }
    public double getMontoCobrado() { return montoCobrado; }
    public LocalDateTime getFecha() { return fecha; }
}
