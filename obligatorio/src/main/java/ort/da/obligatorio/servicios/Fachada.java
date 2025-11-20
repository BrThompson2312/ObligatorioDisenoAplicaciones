package ort.da.obligatorio.servicios;

import java.util.List;
import ort.da.obligatorio.dominio.interfaces.EstadoPropietario;
import ort.da.obligatorio.dominio.interfaces.EstrategiaBonificacion;
import ort.da.obligatorio.dominio.Sesion;
import ort.da.obligatorio.dominio.DTOs.BonificacionDTO;
import ort.da.obligatorio.dominio.DTOs.EstadoPropietarioDTO;
import ort.da.obligatorio.dominio.DTOs.TransitoAdminDTO;
import ort.da.obligatorio.dominio.DTOs.TransitoPropietarioDTO;
import ort.da.obligatorio.dominio.Personas.Persona;
import ort.da.obligatorio.dominio.Personas.Propietario;
import ort.da.obligatorio.dominio.Puestos.Puesto;
import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import ort.da.obligatorio.dominio.Personas.Vehiculo;
import jakarta.servlet.http.HttpSession;
import ort.da.obligatorio.dominio.Puestos.Transito;
import java.time.LocalDateTime;

public class Fachada {
    
    private static Fachada instance;
    private ServicioPersonas sPersonas;
    private ServicioPuestos sPuestos;

    public enum Eventos {
        nuevaNotificacion, 
        nuevaBotificacion, 
        borrarNotificaciones,
        emularTransito
    }

    private Fachada() {
        sPersonas = new ServicioPersonas();
        sPuestos = new ServicioPuestos();
    }

    public static Fachada getInstancia() {
        if (instance == null) {
            instance = new Fachada();
        } 
        return instance;
    }

    public List<EstadoPropietario> getEstadosPropietario() {
        return sPersonas.getEstados();
    }

    public List<EstadoPropietarioDTO> getEstadosPropietarioDTOs() {
        return EstadoPropietarioDTO.fromList(getEstadosPropietario());
    }

    public List<Sesion> getSesiones() {
        return sPersonas.getSesiones();
    }

    public List<Persona> getPersonas() {
        return sPersonas.getPersonas();
    }

    public List<Puesto> getPuestos() {
        return sPuestos.getListPuestos();
    }

    public List<TransitoPropietarioDTO> getTransitosPropietarioDTOs(Propietario propietario){
        List<Transito> transitos = sPuestos.getTransitosPorPropietario(propietario);
        return TransitoPropietarioDTO.fromList(transitos);
    }

    public List<BonificacionDTO> getBonificacionesDTOs(){
        return BonificacionDTO.fromList(getEstrategiasBonificacion());
    }

    public List<EstrategiaBonificacion> getEstrategiasBonificacion() {
        return sPuestos.getListBonificaciones();
    }

    public Sesion loginAdministrador(String ci, String c, HttpSession httpSession) throws PeajeException{
        return sPersonas.loginAdministrador(ci, c, httpSession);
    }

    public Sesion loginPropietario(String ci, String c, HttpSession httpSession) throws PeajeException{
        return sPersonas.loginPropietario(ci, c, httpSession);
    }

    public void logout(Sesion s) {
        sPersonas.logout(s);
    }

    public void asignarBonificacionPorNombres(String ci, String nombreBonificacion, String nombrePuesto) throws PeajeException {
        Propietario propietario = sPersonas.getPropietarioPorCi(ci);
        if (propietario == null) {
            throw new PeajeException("No se encontró el propietario con cédula: " + ci);
        }
        
        Puesto puesto = sPuestos.getPuestoPorNombre(nombrePuesto);
        if (puesto == null) {
            throw new PeajeException("No se encontró el puesto: " + nombrePuesto);
        }
        
        EstrategiaBonificacion estrategia = sPuestos.getEstrategiaBonificacionPorNombre(nombreBonificacion);
        if (estrategia == null) {
            throw new PeajeException("No se encontró la bonificación: " + nombreBonificacion);
        }
        
        asignarBonificacion(estrategia, puesto, propietario);
    }

    public void asignarBonificacion(EstrategiaBonificacion eb, Puesto p, Propietario propietario) throws PeajeException{
        sPersonas.asignarBonificacion(eb, p, propietario);
    }
    
    public Transito emularTransito(Propietario propietario, Puesto puesto, Vehiculo vehiculo) throws PeajeException {
        return sPuestos.registrarTransito(propietario, vehiculo, puesto);
    }

    public Transito emularTransitoInicial(String puesto, String matricula, LocalDateTime fechaHora) throws PeajeException { 
        Puesto p = sPuestos.getPuestoPorNombre(puesto);
        Vehiculo v = sPersonas.getVehiculoPorMatricula(matricula);
        Propietario propietario = sPersonas.getPropietarioPorVehiculo(v);

        return emularTransito(propietario, p, v);
    }

    public TransitoAdminDTO emularTransitoDTO(String puesto, String matricula, LocalDateTime fechaHora) throws PeajeException {
        Transito transito = emularTransitoInicial(puesto, matricula, fechaHora);
        return TransitoAdminDTO.from(transito);
    }

    public void borrarNotificacionesPropietario(Propietario propietario) {
        sPersonas.borrarNotificacionesPropietario(propietario);
    }

    public void cambiarEstadoPropietario(Persona propietario, String estadoPropietario) throws PeajeException {
        sPersonas.cambiarEstadoPropietario(propietario, estadoPropietario);
    }

    public void agregarEstado(EstadoPropietario estado) {
        sPersonas.agregarEstado(estado);
    }

    public void agregarEstrategiaBonificacion(EstrategiaBonificacion eb) {
        sPuestos.agregarEstrategiaBonificacion(eb);
    }

    public void agregarPersona(Persona p) {
        sPersonas.agregarPersona(p);
    }

    public void agregarPuesto(Puesto p) {
        sPuestos.agregarPuesto(p);
    }

    public List<Transito> getTransitosPorPropietario(Propietario propietario){
        return sPuestos.getTransitosPorPropietario(propietario);
    }

    public Propietario getPropietarioPorCi(String ci) {
        return sPersonas.getPropietarioPorCi(ci);
    }

}