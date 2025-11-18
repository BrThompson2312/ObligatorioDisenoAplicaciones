package ort.da.obligatorio.dominio.Bonificaciones;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Puestos.Puesto;

public class AsignacionDeBonificacion {
    
    private Propietario propietario;
    private Puesto puesto;
    private EstrategiaBonificacion eBonificacion;

    public AsignacionDeBonificacion(Propietario propietario, Puesto puesto, EstrategiaBonificacion eBonificacion) {
        this.propietario = propietario;
        this.puesto = puesto;
        this.eBonificacion = eBonificacion;
    }

    public double calcularMonto(double monto, Puesto puesto) {
        return eBonificacion.calcularMonto(monto, puesto);
    }

}
