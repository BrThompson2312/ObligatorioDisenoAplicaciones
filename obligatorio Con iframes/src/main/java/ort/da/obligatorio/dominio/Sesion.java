package ort.da.obligatorio.dominio;
import java.util.Date;

import jakarta.servlet.http.HttpSession;
import ort.da.obligatorio.dominio.Personas.Persona;

public class Sesion {
    private Date fechaIngreso = new Date();
    private Persona persona;
    private HttpSession httpSession;

    public Sesion(Persona p, HttpSession httpSession) {
        this.persona = p;
        this.httpSession = httpSession;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Persona getPersona() {
        return persona;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void cerrarSesion() {
        if (httpSession != null) {
            httpSession.invalidate();
        }
    }
}
