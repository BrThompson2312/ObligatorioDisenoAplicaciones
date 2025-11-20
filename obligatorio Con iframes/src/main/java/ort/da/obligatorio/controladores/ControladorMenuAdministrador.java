package ort.da.obligatorio.controladores;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.DTOs.PuestoDTO;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.Puestos.Transito;
import ort.da.obligatorio.servicios.Fachada;
import ort.da.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/menuAdministrador")
@Scope("session")
public class ControladorMenuAdministrador {

    @GetMapping("vistaConectadaAdministrador")
    public List<Respuesta> iniciarVista(@SessionAttribute(name = "sesion", required = false) Sesion sesion) {
        if(sesion == null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        List<Puesto> puestos = Fachada.getInstancia().getPuestos();
        List<PuestoDTO> puestosDTO = PuestoDTO.fromList(puestos);

        return Respuesta.lista(
            new Respuesta("puestos", puestosDTO)
        );
    }

    @PostMapping("emularTransito")
    public List<Respuesta> emularTransito(
            @RequestParam String puesto, 
            @RequestParam String matricula, 
            @RequestParam String fecha, 
            @SessionAttribute(name = "sesion", required = false) Sesion sesion) throws PeajeException {
        
        if(sesion == null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime fechaHora = LocalDateTime.parse(fecha, formatter);

        Transito transito = Fachada.getInstancia().emularTransitoInicial(puesto, matricula, fechaHora);

        Map<String, Object> transitoData = new HashMap<>();
        transitoData.put("nombrePropietario", transito.getPropietario().getNombre());
        transitoData.put("categoria", transito.getVehiculo().getCategoriaDeVehiculo().getNombre().name());
        transitoData.put("bonificacion", transito.getBonificacionAplicada() != null ? 
            transito.getBonificacionAplicada().getClass().getSimpleName() : "Ninguna");
        transitoData.put("costoTransito", transito.getMontoCobrado());
        transitoData.put("saldoDespues", transito.getPropietario().getSaldoActual());

        return Respuesta.lista(
            new Respuesta("resultadoEmulacion", transitoData)
        );
    }
}
