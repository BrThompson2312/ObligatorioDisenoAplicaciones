package ort.da.obligatorio.controladores;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ort.da.obligatorio.dominio.Personas.Persona;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;

@RestController
@RequestMapping("/personas")
public class ControladorPersonas {

    @GetMapping("/buscarPersona")
    public Persona buscarPersona(String ci, boolean esAdmin, List<Persona> personas) {
        return null;
    }

    @GetMapping("/menuAsignarBonificacion")
    public void menuAsignarBonificacion() {
    }

    @GetMapping("/asignarBonificacion")
    public void asignarBonificacion() {
    }

    @GetMapping("/cambiarEstado")
    public void cambiarEstado(EstadoPropietario e, Propietario p) {
    }

}