package ort.da.obligatorio.dominio.interfaces;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Personas.Vehiculo;
import ort.da.obligatorio.dominio.Puestos.Puesto;

public interface EstrategiaBonificacion {

    public double calcularMonto(double monto, Puesto puesto, Propietario propietario,Vehiculo vehiculo);
}
