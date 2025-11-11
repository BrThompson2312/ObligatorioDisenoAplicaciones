package ort.da.obligatorio.dominio;

public abstract class Persona {
    
    private int id;
    private String ci;
    private String password;
    private String nombre;

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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