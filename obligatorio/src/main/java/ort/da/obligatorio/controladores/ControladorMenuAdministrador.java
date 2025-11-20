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
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.Puestos.Transito;
import ort.da.obligatorio.servicios.Fachada;
import ort.da.obligatorio.utils.Respuesta;
import ort.da.obligatorio.dominio.DTOs.BonificacionDTO;
import ort.da.obligatorio.dominio.DTOs.PropietarioDTO;
import ort.da.obligatorio.dominio.DTOs.EstadoPropietarioDTO;
import org.springframework.web.bind.annotation.RequestBody;



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
        List<EstadoPropietarioDTO> estadosDTO = Fachada.getInstancia().getEstadosPropietarioDTOs();
        List<BonificacionDTO> bonificacionesDTO = Fachada.getInstancia().getBonificacionesDTOs();

        return Respuesta.lista(
            new Respuesta("puestos", puestosDTO),
            new Respuesta("puestosBonificaciones", puestosDTO),
            new Respuesta("bonificaciones", bonificacionesDTO),
            new Respuesta("estados",estadosDTO)
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

    @PostMapping("cambiarEstadoPropietario")
    public List<Respuesta> cambiaEstadoPropietario(
        @RequestParam String ci,
        @RequestParam String nuevoEstado,
        @SessionAttribute(name = "sesion", required = false) Sesion sesion) throws PeajeException {
        
        if(sesion == null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        Propietario propietario = Fachada.getInstancia().getPropietarioPorCi(ci);

        if(propietario == null) {
            throw new PeajeException("no existe el propietario");
        }

        Fachada.getInstancia().cambiarEstadoPropietario(propietario, nuevoEstado);

        PropietarioDTO propietarioDTO = PropietarioDTO.from(propietario);
        return Respuesta.lista(
            new Respuesta("estadoCambiado", "Estado cambiado exitosamente"),
            new Respuesta("propietarioEncontradoEstado", propietarioDTO)
        );
    }
    

    @PostMapping("buscarPropietario")
    public List<Respuesta> buscarPropietario(
            @RequestParam String ci,
            @SessionAttribute(name = "sesion", required = false) Sesion sesion) throws PeajeException {
        
        if(sesion == null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        Propietario propietario = Fachada.getInstancia().getPropietarioPorCi(ci);
        
        if(propietario == null) {
            throw new PeajeException("No se encontró el propietario con cédula: " + ci);
        }

        PropietarioDTO propietarioDTO = PropietarioDTO.from(propietario);

        return Respuesta.lista(
            new Respuesta("propietarioEncontrado", propietarioDTO)
        );
    }

    @PostMapping("buscarPropietarioEstado")
    public List<Respuesta> buscarPropietarioEstado(
            @RequestParam String ci,
            @SessionAttribute(name = "sesion", required = false) Sesion sesion) throws PeajeException {
        
        if(sesion == null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        Propietario propietario = Fachada.getInstancia().getPropietarioPorCi(ci);
        
        if(propietario == null) {
            throw new PeajeException("No se encontró el propietario con cédula: " + ci);
        }

        PropietarioDTO propietarioDTO = PropietarioDTO.from(propietario);

        return Respuesta.lista(
            new Respuesta("propietarioEncontradoEstado", propietarioDTO)
        );
    }

    @PostMapping("asignarBonificacion")
    public List<Respuesta> asignarBonificacion(
        @RequestParam String ci,
        @RequestParam String nombreBonificacion,
        @RequestParam String nombrePuesto,
        @SessionAttribute(name = "sesion", required = false) Sesion sesion) throws PeajeException {
        
        if(sesion == null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        Fachada.getInstancia().asignarBonificacionPorNombres(ci, nombreBonificacion, nombrePuesto);

        Propietario propietario = Fachada.getInstancia().getPropietarioPorCi(ci);
        PropietarioDTO propietarioDTO = PropietarioDTO.from(propietario);

        return Respuesta.lista(
            new Respuesta("bonificacionAsignada", "Bonificación asignada exitosamente"),
            new Respuesta("propietarioEncontrado", propietarioDTO)
        );
    }
    
}