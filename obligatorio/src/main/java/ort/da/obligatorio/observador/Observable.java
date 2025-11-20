package ort.da.obligatorio.observador;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observable {
    private final Collection<Observador> observadores = new ArrayList<>();

    public void agregar(Observador observador){
        this.observadores.add(observador);
    }

    public void remover(Observador observador){
        this.observadores.remove(observador);
    }

    public void avisar(Object evento){
        ArrayList<Observador> copia = new ArrayList<>(observadores);
        for(Observador obs:copia){
            obs.actualizar(evento, this);
        }
    }
}