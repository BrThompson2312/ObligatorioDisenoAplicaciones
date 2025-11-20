package ort.da.obligatorio.dominio.Personas;

import java.time.LocalDateTime;

public class Notificacion {
    public LocalDateTime fecha;
    public String texto;

    public Notificacion(String texto) {
        this.fecha = LocalDateTime.now();
        this.texto = texto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getTexto() {
        return texto;
    }
}
