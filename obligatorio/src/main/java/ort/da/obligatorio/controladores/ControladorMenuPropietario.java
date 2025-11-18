package ort.da.obligatorio.controladores;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/menuPropietario")
@Scope("session")
public class ControladorMenuPropietario {
    @GetMapping("vistaConectadaPropietario")
    public List<Respuesta> iniciarVista(@SessionAttribute(name = "sesion", required = false) Sesion sesion) {
        if(sesion==null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }
        return Respuesta.lista(new Respuesta("nombrePropietario",sesion.getPersona().getNombre()));
    }
    
}
