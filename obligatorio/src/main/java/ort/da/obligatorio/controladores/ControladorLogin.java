package ort.da.obligatorio.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import ort.da.obligatorio.servicios.Fachada;
import ort.da.obligatorio.dominio.Sesion;


import ort.da.obligatorio.dominio.Excepciones.PeajeException;
import ort.da.obligatorio.utils.Respuesta;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/acceso")

public class ControladorLogin {
    @PostMapping("/loginPropietario")
    public List<Respuesta> loginPropietario( @RequestParam String cedula, @RequestParam String password, HttpSession sessionHttp) throws PeajeException{
        Sesion sesion = Fachada.getInstancia().loginPropietario(cedula, password, sessionHttp);
        sessionHttp.setAttribute("sesion", sesion);
        return Respuesta.lista(new Respuesta("loginExitoso","menuPropietario.html"));
    }
    
    @PostMapping("/loginAdministrador")
    public List<Respuesta> loginAdministrador(@RequestParam String cedula, @RequestParam String password, HttpSession sessionHttp) throws PeajeException{
        Sesion sesion = Fachada.getInstancia().loginAdministrador(cedula, password, sessionHttp);
        sessionHttp.setAttribute("sesion", sesion);
        return Respuesta.lista(new Respuesta("loginExitoso","menuAdministrador.html"));
    }

    @PostMapping("/logoutPropietario")
    public List<Respuesta> logoutPropietario(HttpSession sesionHttp) {
        Sesion sesion = (Sesion) sesionHttp.getAttribute("sesion");
        if(sesion != null) {
            Fachada.getInstancia().logout(sesion);
            //sesionHttp.removeAttribute("sesion");
        }
        return Respuesta.lista(new Respuesta("usuarioNoAutenticado","index.html"));
    }

    @PostMapping("/logoutAdministrador")
    public List<Respuesta> logoutAdministrador(HttpSession sesionHttp) {
        Sesion sesion = (Sesion) sesionHttp.getAttribute("sesion");
        if(sesion != null) {
            Fachada.getInstancia().logout(sesion);
            //sesionHttp.removeAttribute("sesion");
        }
        return Respuesta.lista(new Respuesta("usuarioNoAutenticado","index.html"));
    }
    
}
