package buscaminas.modelo;

import java.io.Serializable;

//Representa una casilla del tablero
public class Casilla implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean mina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAlrededor;

    // Constructor de la casilla
    public Casilla() {
        this.mina = false;
        this.descubierta = false;
        this.marcada = false;
        this.minasAlrededor = 0;
    }

    // Devuelve true si la casilla tiene mina
    public boolean tieneMina() {
        return mina;
    }

 // Asigna si la casilla tiene mina o no
    public void setMina(boolean mina) {
        this.mina = mina;
    }

 // Devuelve true si la casilla ya fue descubierta 
    public boolean isDescubierta() {
        return descubierta;
    }

    // Cambia la casilla a estado descubierto
    public void descubrir() {
        this.descubierta = true;
        this.marcada = false;
    }

    // Devuelve true si la casilla esta marcada
    public boolean isMarcada() {
        return marcada;
    }

    // Marca o desmarca la casilla si no esta descubierta
    public void cambiarMarca() {
        if (!descubierta) {
            this.marcada = !this.marcada;
        }
    }

    // Devuelve la cantidad de minas alrededor
    public int getMinasAlrededor() {
        return minasAlrededor;
    }

    // Asigna la cantidad de minas alrededor
    public void setMinasAlrededor(int minasAlrededor) {
        this.minasAlrededor = minasAlrededor;
    }

    // Devuelve true si no tiene mina ni minas alrededor
    public boolean estaVacia() {
        return !mina && minasAlrededor == 0;
    }
}