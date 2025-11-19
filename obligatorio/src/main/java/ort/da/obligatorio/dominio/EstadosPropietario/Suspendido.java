package ort.da.obligatorio.dominio.EstadosPropietario;

import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.dominio.Personas.Notificacion;
import ort.da.obligatorio.dominio.Personas.Propietario;

public class Suspendido extends EstadoPropietarioAbstracto {
    public Suspendido() {
        super("Suspendido");
    }
    @Override
    public void registraTransito(Propietario p) {
        p.setPuedeTransitar(false);
    }
}
