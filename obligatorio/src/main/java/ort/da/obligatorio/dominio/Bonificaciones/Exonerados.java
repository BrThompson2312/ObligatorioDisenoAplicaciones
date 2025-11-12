package ort.da.obligatorio.dominio.Bonificaciones;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;


public class Exonerados implements EstrategiaBonificacion {
    @Override
    public double calcularMonto(double monto, Puesto puesto) {
        return 0;
    }
}