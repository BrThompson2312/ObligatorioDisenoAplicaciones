package ort.da.obligatorio.observador;

public interface Observador {
    void actualizar(Object evento, Observable origen);
}