package ort.da.obligatorio.controladores;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.DTOs.PuestoDTO;
import ort.da.obligatorio.dominio.DTOs.TransitoAdminDTO;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.servicios.Fachada;
import ort.da.obligatorio.utils.Respuesta;
import ort.da.obligatorio.dominio.DTOs.BonificacionDTO;
import ort.da.obligatorio.dominio.DTOs.PropietarioDTO;
import ort.da.obligatorio.dominio.DTOs.EstadoPropietarioDTO;
import ort.da.obligatorio.observador.Observador;
import ort.da.obligatorio.observador.Observable;
import ort.da.obligatorio.utils.ConexionNavegador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ort.da.obligatorio.dominio.Personas.Administrador;

@RestController
@RequestMapping("/menuAdministrador")
@Scope("session")
public class ControladorMenuAdministrador implements Observador {

    private Administrador personaObs;
    private final ConexionNavegador conexionNavegador;

    public ControladorMenuAdministrador(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE(); 
    }

    @GetMapping("vistaConectadaAdministrador")
    public List<Respuesta> iniciarVista(@SessionAttribute(name = "sesion", required = false) Sesion sesion) {
        if(sesion == null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        personaObs = (Administrador) sesion.getPersona();
        personaObs.agregar(this);

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

        TransitoAdminDTO transitoAdminDTO = Fachada.getInstancia().emularTransitoDTO(puesto, matricula, fechaHora);

        return Respuesta.lista(
            new Respuesta("resultadoEmulacion", transitoAdminDTO)
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

    @Override
    public void actualizar(Object evento, Observable origen) {
        if (evento.equals(Fachada.Eventos.borrarNotificaciones)) {
            conexionNavegador.enviarJSON(Respuesta.lista(new Respuesta("notificaciones", null)));  
        }
    }
    
}