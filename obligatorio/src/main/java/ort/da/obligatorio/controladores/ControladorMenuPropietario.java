package ort.da.obligatorio.controladores;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.DTOs.AsignacionBonificacionDTO;
import ort.da.obligatorio.dominio.DTOs.BonificacionPropietarioDTO;
import ort.da.obligatorio.dominio.DTOs.NotificacionDTO;
import ort.da.obligatorio.dominio.DTOs.TransitoPropietarioDTO;
import ort.da.obligatorio.dominio.DTOs.VehiculoDTO;
import ort.da.obligatorio.dominio.DTOs.EstadoPropietarioDTO;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.observador.Observable;
import ort.da.obligatorio.observador.Observador;
import ort.da.obligatorio.servicios.Fachada;
import ort.da.obligatorio.utils.ConexionNavegador;
import ort.da.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/menuPropietario")
@Scope("session")
public class ControladorMenuPropietario implements Observador {

    private Propietario personaObs;
    private final ConexionNavegador conexionNavegador;

    public ControladorMenuPropietario(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE(); 
    }

    @GetMapping("vistaConectadaPropietario")
    public List<Respuesta> iniciarVista(@SessionAttribute(name = "sesion", required = false) Sesion sesion) {
        if(sesion==null){
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }

        personaObs = (Propietario) sesion.getPersona();
        personaObs.agregar(this);

        Fachada.getInstancia().agregar(this);
        
        Propietario propietario = (Propietario) sesion.getPersona();

        List<BonificacionPropietarioDTO> bonificacionPropietarioDTO =
            BonificacionPropietarioDTO.fromList(propietario.getListBonificaciones());

        List<VehiculoDTO> vehiculosDTO = 
            VehiculoDTO.fromList(propietario.getListVehiculos());

        List<TransitoPropietarioDTO> transitosDTO = 
            Fachada.getInstancia().getTransitosPropietarioDTOs(propietario);

        List<NotificacionDTO> notificacionesDTO = 
            NotificacionDTO.fromList(propietario.getListNotificaciones());
        
        return Respuesta.lista(
            new Respuesta("nombrePropietario",propietario.getNombre()),
            new Respuesta("estadoPropietario", propietario.getEstado().getNombre()),
            new Respuesta("saldoActual", propietario.getSaldoActual()),
            new Respuesta("bonificaciones", bonificacionPropietarioDTO),
            new Respuesta("vehiculos", vehiculosDTO),
            new Respuesta("transitos", transitosDTO),
            new Respuesta("notificaciones", notificacionesDTO)
        );
    }

    

    @PostMapping("borrarNotificacionesPropietario")
    public List<Respuesta> borrarNotificaciones(@SessionAttribute(name = "sesion", required = false) Sesion sesion) {
        if(sesion == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
        }
        Propietario propietario = (Propietario) sesion.getPersona();
        propietario.eliminarNotificaciones();
        return Respuesta.lista(new Respuesta("notificaciones", null));
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        if (evento.equals(Fachada.Eventos.borrarNotificaciones) || 
            evento.equals(Fachada.Eventos.nuevaNotificacion)) {
            List<NotificacionDTO> notificacionesDTO = 
                NotificacionDTO.fromList(personaObs.getListNotificaciones());
            conexionNavegador.enviarJSON(Respuesta.lista(
                new Respuesta("notificaciones", notificacionesDTO)
            ));
        } else if (evento.equals(Fachada.Eventos.nuevoTransito)) {
            List<TransitoPropietarioDTO> transitosDTO = 
                Fachada.getInstancia().getTransitosPropietarioDTOs(personaObs);
            conexionNavegador.enviarJSON(Respuesta.lista(
                new Respuesta("transitos", transitosDTO)
            ));
        } else if (evento.equals(Fachada.Eventos.nuevaBonificacion)) {
            List<BonificacionPropietarioDTO> BonificacionesPropietarioDTO = 
                BonificacionPropietarioDTO.fromList(personaObs.getListBonificaciones());
            conexionNavegador.enviarJSON(Respuesta.lista(
                new Respuesta("bonificaciones", BonificacionesPropietarioDTO)
            ));
        } else if (evento.equals(Fachada.Eventos.nuevoEstado)) {
            conexionNavegador.enviarJSON(Respuesta.lista(new Respuesta("estadoPropietario", personaObs.getEstado().getNombre())
            ));
        }
    }
}