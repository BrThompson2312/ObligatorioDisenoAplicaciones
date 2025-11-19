package ort.da.obligatorio.controladores;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import ort.da.obligatorio.dominio.Personas.CategoriaDeVehiculo;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Personas.Vehiculo;
import ort.da.obligatorio.dominio.Puestos.Tarifa;
import ort.da.obligatorio.dominio.Puestos.Transito;
import ort.da.obligatorio.servicios.Fachada;
import ort.da.obligatorio.dominio.Personas.Notificacion;


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

        List<Map<String, Object>> vehiculosData = new ArrayList<>();
        List<Vehiculo> vehiculos = propietario.getListVehiculos();
        if(vehiculos != null) {
            for(Vehiculo veh : vehiculos) {
                Map<String, Object> vehMap = new HashMap<>();
                vehMap.put("matricula", veh.getMatricula());
                vehMap.put("color", veh.getColor());
                vehMap.put("modelo", veh.getModelo());
                vehMap.put("categoria", veh.getCategoriaDeVehiculo().getNombre());
                vehMap.put("totalTransitos", veh.getTotalTransitos());
                vehMap.put("totalPagado", veh.getTotalPagado());
                vehiculosData.add(vehMap);
            }
        }

        List<Map<String, Object>> transitosData = new ArrayList<>();
        List<Transito> transitos = Fachada.getInstancia().getTransitosPorPropietario(propietario);

        if(transitos != null) {
            for(Transito tran : transitos) {
                Map<String, Object> tranMap = new HashMap<>();
                tranMap.put("puesto", tran.getPuesto().getNombre());
                tranMap.put("matricula", tran.getVehiculo().getMatricula());
                tranMap.put("categoria", tran.getVehiculo().getCategoriaDeVehiculo().getNombre());
                tranMap.put("montoTarifa", tran.getTarifa().getMonto());
                tranMap.put("bonificacion", tran.getBonificacionAplicada() != null ? 
                    tran.getBonificacionAplicada().getClass().getSimpleName() : "Ninguna");
                tranMap.put("montoBonificacion", tran.getBonificacionAplicada() != null ? 
                    tran.getTarifa().getMonto() - tran.getMontoCobrado() : 0.0);
                tranMap.put("montoPagado", tran.getMontoCobrado());
                tranMap.put("fecha", tran.getFecha().toLocalDate().toString());
                tranMap.put("hora", tran.getFecha().toLocalTime().toString());
                transitosData.add(tranMap);
            }
        }

        List<Map<String, Object>> notificacionesData = new ArrayList<>();
        List<Notificacion> notificaciones = propietario.getListNotificaciones();
        if(notificaciones != null) {
            for(Notificacion not : notificaciones) {
                Map<String, Object> notMap = new HashMap<>();
                String hora = not.getFecha().toLocalTime().toString();
                if (hora.length() > 8) {
                    hora = hora.substring(0, 8);
                }
                String notificacionCompleta = String.format("[%s %s] %s",
                    not.getFecha().toLocalDate().toString(),
                    hora,
                    not.getTexto());
                notMap.put("notificacion", notificacionCompleta);
                notificacionesData.add(notMap);
            }
        }
        

        return Respuesta.lista(new Respuesta("nombrePropietario",propietario.getNombre()),
                              new Respuesta("estadoPropietario", propietario.getEstado().getNombre()),
                              new Respuesta("saldoActual", propietario.getSaldoActual()),
                              new Respuesta("bonificaciones", bonificacionesData),
                              new Respuesta("vehiculos", vehiculosData),
                              new Respuesta("transitos", transitosData),
                              new Respuesta("notificaciones", notificacionesData));
    }

        @PostMapping("borrarNotificacionesPropietario")
        public List<Respuesta> borrarNotificaciones(@SessionAttribute(name = "sesion", required = false) Sesion sesion) {
            if(sesion == null) {
                return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
            }
            
            Propietario propietario = (Propietario) sesion.getPersona();
            propietario.eliminarNotifiaciones();
            
            return iniciarVista(sesion);
        }
    }