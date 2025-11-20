package ort.da.obligatorio.dominio.Personas;
import ort.da.obligatorio.observador.Observable;

public abstract class Persona extends Observable {
    
    private String ci;
    private String password;
    private String nombre;

    public Persona(String ci, String password, String nombre) {
        this.ci = ci;
        this.password = password;
        this.nombre = nombre;
    }

    public String getCi() {
        return ci;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean validarCredenciales() {
        return true;
    }

}