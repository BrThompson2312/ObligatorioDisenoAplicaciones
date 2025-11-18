package ort.da.obligatorio.controladores;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.Bonificaciones.AsignacionDeBonificacion;
import ort.da.obligatorio.utils.Respuesta;
import ort.da.obligatorio.dominio.Personas.Propietario;

@RestController
@RequestMapping("/menuPropietario")
@Scope("session")
public class ControladorMenuPropietario {
    @GetMapping("vistaConectadaPropietario")
    public List<Respuesta> iniciarVista(@SessionAttribute(name = "sesion", required = false) Sesion sesion) {
        if(sesion==null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }
        Propietario propietario = (Propietario) sesion.getPersona();

        List<Map<String, Object>> bonificacionesData = new ArrayList<>();
        List<AsignacionDeBonificacion> bonificaciones = propietario.getListBonificaciones();
        
        if(bonificaciones != null) {
            for(AsignacionDeBonificacion bonif : bonificaciones) {
                Map<String, Object> bonifMap = new HashMap<>();
                bonifMap.put("tipo", bonif.getEstrategia().getClass().getSimpleName());
                bonifMap.put("puesto", bonif.getPuesto().getNombre());
                bonifMap.put("fechaAsignacion", bonif.getFechaAsignacion().toString());
                bonificacionesData.add(bonifMap);
            }
        }


        System.out.println(bonificacionesData);
        return Respuesta.lista(new Respuesta("nombrePropietario",propietario.getNombre()),
                              new Respuesta("estadoPropietario", propietario.getEstado().getNombre()),
                              new Respuesta("saldoActual", propietario.getSaldoActual()),
                              new Respuesta("bonificaciones", bonificacionesData));
    }
    
}
