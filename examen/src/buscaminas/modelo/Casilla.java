package buscaminas.modelo;

import java.io.Serializable;

//DEFINICIÓN DE UNA CASILLA DEL TABLERO
public class Casilla implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean mina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAlrededor;

    public Casilla() {
        this.mina = false;
        this.descubierta = false;
        this.marcada = false;
        this.minasAlrededor = 0;
    }

    public boolean tieneMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void descubrir() {
        this.descubierta = true;
        this.marcada = false;
    }

    public boolean isMarcada() {
        return marcada;
    }

    public void cambiarMarca() {
        if (!descubierta) {
            this.marcada = !this.marcada;
        }
    }

    public int getMinasAlrededor() {
        return minasAlrededor;
    }

    public void setMinasAlrededor(int minasAlrededor) {
        this.minasAlrededor = minasAlrededor;
    }

    public boolean estaVacia() {
        return !mina && minasAlrededor == 0;
    }
}