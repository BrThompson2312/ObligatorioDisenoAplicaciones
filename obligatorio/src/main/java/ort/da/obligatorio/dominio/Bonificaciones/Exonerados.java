package ort.da.obligatorio.dominio.Bonificaciones;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Personas.Vehiculo;


public class Exonerados implements EstrategiaBonificacion {
    @Override
    public double calcularMonto(double monto, Puesto puesto, Propietario propietario, Vehiculo vehiculo) {
        return 0;
    }
}