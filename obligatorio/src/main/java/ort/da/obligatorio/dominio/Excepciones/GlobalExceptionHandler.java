package ort.da.obligatorio.dominio.Excepciones;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice

public class GlobalExceptionHandler {
    private final int errorCodeStatus = 299; //cambiar si se quiere otro codigo para indicar excepcion de aplicacion

    @ExceptionHandler(PeajeException.class)
    public ResponseEntity<String> manejarException(PeajeException ex) {
       // Usamos un código de estado no estándar (299) para indicar una excepción de aplicación 
       return ResponseEntity.status(errorCodeStatus).body(ex.getMessage());
    }
}
