package ort.da.obligatorio.dominio.Bonificaciones;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import java.time.LocalDateTime;

import java.time.DayOfWeek;

import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Personas.Vehiculo;

public class Trabajadores implements EstrategiaBonificacion {
    @Override
    public double calcularMonto(double monto, Puesto puesto, Propietario propietario, Vehiculo vehiculo) {
        DayOfWeek diaActual = LocalDateTime.now().getDayOfWeek();
        if(diaActual != DayOfWeek.SATURDAY && diaActual != DayOfWeek.SUNDAY) {
            return monto * 0.2;
        }
        return monto;
    }
    
}
