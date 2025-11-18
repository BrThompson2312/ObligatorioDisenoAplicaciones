package ort.da.obligatorio.servicios;

import org.springframework.stereotype.Service;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServicioPuestos {
    private List<Puesto> puestos;
    private List<EstrategiaBonificacion> estrategiasBonificacion;

    public ServicioPuestos() {
        puestos = new ArrayList<>();
        estrategiasBonificacion = new ArrayList<>();
    }
    public void agregarPuesto(Puesto p) {
        puestos.add(p);
    }
    public void agregarEstrategiaBonificacion(EstrategiaBonificacion eb) {
        estrategiasBonificacion.add(eb);
    }
}
