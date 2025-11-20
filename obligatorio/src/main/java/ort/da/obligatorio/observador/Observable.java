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

    protected void avisar(Object evento){
        observadores.forEach(o->o.actualizar(this, evento));
    }
}