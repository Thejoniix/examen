package buscaminas.modelo;

import java.io.Serializable;
import buscaminas.excepciones.CasillaMarcadaException;
import buscaminas.excepciones.CasillaYaDescubiertaException;

//Clase que contiene la lógica principal del juego Buscaminas
public class Juego implements Serializable { 

    private static final long serialVersionUID = 1L;
    private Tablero tablero; // Tablero donde se encuentran las casillas y minas
    private boolean juegoTerminado; // Indica si el juego ya terminó
    private boolean victoria; // Indica si el jugador ganó la partida

    public Juego() {
        this.tablero = new Tablero();
        this.juegoTerminado = false;
        this.victoria = false;
    }

    // Descubre una casilla seleccionada por el jugador
    public boolean descubrirCasilla(int fila, int columna)
            throws CasillaYaDescubiertaException, CasillaMarcadaException {

        Casilla casilla = tablero.getCasilla(fila, columna);

        // No permite descubrir una casilla que ya fue abierta
        if (casilla.isDescubierta()) {
            throw new CasillaYaDescubiertaException("Casilla ya descubierta.");
        }

        // No permite descubrir una casilla marcada
        if (casilla.isMarcada()) {
            throw new CasillaMarcadaException("Casilla marcada. Primero desmárquela.");
        }

        casilla.descubrir();  // Cambia el estado de la casilla a descubierta

        // Si la casilla tiene mina, el juego termina con derrota
        if (casilla.tieneMina()) {
            juegoTerminado = true;
            victoria = false; 
            return false;
        }

        // Si la casilla está vacía, se descubren automaticamente las casillas cercanas
        if (casilla.estaVacia()) {
            descubrirCasillasAdyacentes(fila, columna);
        }

        verificarVictoria(); // Después de cada jugada se verifica si el jugador ganó
        return true;
    }

    // Metodo recursivo para descubrir casillas vecinas cuando una casilla está vacía
    private void descubrirCasillasAdyacentes(int fila, int columna) {
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {

                if (tablero.estaDentroDelTablero(i, j)) {
                    Casilla casilla = tablero.getCasilla(i, j);

                    if (!casilla.isDescubierta() && !casilla.tieneMina() && !casilla.isMarcada()) {
                        casilla.descubrir();

                        if (casilla.estaVacia()) {
                            descubrirCasillasAdyacentes(i, j);
                        }
                    }
                }
            }
        }
    }

    // Marca o desmarca una casilla que el jugador cree que contiene una mina
    public void marcarCasilla(int fila, int columna) throws CasillaYaDescubiertaException {
        Casilla casilla = tablero.getCasilla(fila, columna);

        if (casilla.isDescubierta()) {
            throw new CasillaYaDescubiertaException("No se puede marcar una casilla abierta.");
        }

        casilla.cambiarMarca();
    }

    // Verifica si todas las casillas que no tienen mina ya fueron descubiertas
    private void verificarVictoria() {
        for (int fila = 0; fila < Tablero.FILAS; fila++) {
            for (int columna = 0; columna < Tablero.COLUMNAS; columna++) {
                Casilla casilla = tablero.getCasilla(fila, columna);

                if (!casilla.tieneMina() && !casilla.isDescubierta()) {
                    return;
                }
            }
        }

        juegoTerminado = true;
        victoria = true;
    }

    
    public Tablero getTablero() {
        return tablero;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public boolean isVictoria() {
        return victoria;
    }
}