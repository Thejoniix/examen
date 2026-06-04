package buscaminas.modelo;

import java.io.Serializable;
import buscaminas.excepciones.CasillaMarcadaException;
import buscaminas.excepciones.CasillaYaDescubiertaException;

//LOGICA DE JUEGO
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;
    private Tablero tablero;
    private boolean juegoTerminado;
    private boolean victoria;

    public Juego() {
        this.tablero = new Tablero();
        this.juegoTerminado = false;
        this.victoria = false;
    }

    public boolean descubrirCasilla(int fila, int columna)
            throws CasillaYaDescubiertaException, CasillaMarcadaException {

        Casilla casilla = tablero.getCasilla(fila, columna);

        if (casilla.isDescubierta()) {
            throw new CasillaYaDescubiertaException("Casilla ya descubierta.");
        }

        if (casilla.isMarcada()) {
            throw new CasillaMarcadaException("Casilla marcada. Primero desmárquela.");
        }

        casilla.descubrir();

        if (casilla.tieneMina()) {
            juegoTerminado = true;
            victoria = false;
            return false;
        }

        if (casilla.estaVacia()) {
            descubrirCasillasAdyacentes(fila, columna);
        }

        verificarVictoria();
        return true;
    }

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

    public void marcarCasilla(int fila, int columna) throws CasillaYaDescubiertaException {
        Casilla casilla = tablero.getCasilla(fila, columna);

        if (casilla.isDescubierta()) {
            throw new CasillaYaDescubiertaException("No se puede marcar una casilla abierta.");
        }

        casilla.cambiarMarca();
    }

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