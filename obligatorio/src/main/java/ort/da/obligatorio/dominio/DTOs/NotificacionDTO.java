package ort.da.obligatorio.dominio.DTOs;

import ort.da.obligatorio.dominio.Personas.Notificacion;
import java.util.List;
import java.util.stream.Collectors;

public class NotificacionDTO {
    private String notificacion;

    public NotificacionDTO() {
    }

    public NotificacionDTO(String notificacion) {
        this.notificacion = notificacion;
    }

    public static NotificacionDTO from(Notificacion notificacion) {
        String hora = notificacion.getFecha().toLocalTime().toString();
        if (hora.length() > 8) {
            hora = hora.substring(0, 8);
        }
        String notificacionCompleta = String.format("[%s %s] %s",
            notificacion.getFecha().toLocalDate().toString(),
            hora,
            notificacion.getTexto());
        
        return new NotificacionDTO(notificacionCompleta);
    }

    public static List<NotificacionDTO> fromList(List<Notificacion> notificaciones) {
        if (notificaciones == null) return List.of();
        return notificaciones.stream()
                .map(NotificacionDTO::from)
                .collect(Collectors.toList());
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }
}
