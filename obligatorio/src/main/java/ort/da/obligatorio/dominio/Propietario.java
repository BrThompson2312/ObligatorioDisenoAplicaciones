package ort.da.obligatorio.dominio;

public class Propietario extends Persona {
    
    private double saldoActual;
    private double saldoMinimo;
    private boolean puedeLogin;
    private boolean puedeTransitar;

    public double getSaldoActual() {
        return saldoActual;
    }
    
    public double getSaldoMinimo() {
        return saldoMinimo;
    }

    public boolean isPuedeLogin() {
        return puedeLogin;
    }

    public boolean isPuedeTransitar() {
        return puedeTransitar;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public void setSaldoMinimo(double saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
    }

    public void setPuedeLogin(boolean puedeLogin) {
        this.puedeLogin = puedeLogin;
    }

    public void setPuedeTransitar(boolean puedeTransitar) {
        this.puedeTransitar = puedeTransitar;
    }

}
