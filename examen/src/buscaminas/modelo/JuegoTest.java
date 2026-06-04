package buscaminas.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import buscaminas.excepciones.CasillaMarcadaException;
import buscaminas.excepciones.CasillaYaDescubiertaException;

//Pruebas unitarias de la clase Juego
class JuegoTest {

    @Test
    void juegoDebeIniciarSinTerminarYSinVictoria() {
        Juego juego = new Juego();

        assertFalse(juego.isJuegoTerminado());
        assertFalse(juego.isVictoria());
        assertNotNull(juego.getTablero());
    }

    @Test
    void marcarCasillaDebeCambiarEstadoAMarcada() throws CasillaYaDescubiertaException {
        Juego juego = new Juego();

        juego.marcarCasilla(0, 0);

        assertTrue(juego.getTablero().getCasilla(0, 0).isMarcada());
    }

    @Test
    void marcarDosVecesDebeDesmarcarLaCasilla() throws CasillaYaDescubiertaException {
        Juego juego = new Juego();

        juego.marcarCasilla(0, 0);
        juego.marcarCasilla(0, 0);

        assertFalse(juego.getTablero().getCasilla(0, 0).isMarcada());
    }

    @Test
    void noDebePermitirDescubrirCasillaMarcada() throws CasillaYaDescubiertaException {
        Juego juego = new Juego();

        juego.marcarCasilla(0, 0);

        assertThrows(CasillaMarcadaException.class, () -> {
            juego.descubrirCasilla(0, 0);
        });
    }

    @Test
    void noDebePermitirMarcarCasillaDescubierta() throws Exception {
        Juego juego = new Juego();

        int[] coordenadaSegura = buscarCasillaSinMina(juego.getTablero());

        juego.descubrirCasilla(coordenadaSegura[0], coordenadaSegura[1]);

        assertThrows(CasillaYaDescubiertaException.class, () -> {
            juego.marcarCasilla(coordenadaSegura[0], coordenadaSegura[1]);
        });
    }

    @Test
    void descubrirMinaDebeTerminarElJuegoConDerrota() throws Exception {
        Juego juego = new Juego();

        int[] coordenadaMina = buscarCasillaConMina(juego.getTablero());

        boolean resultado = juego.descubrirCasilla(coordenadaMina[0], coordenadaMina[1]);

        assertFalse(resultado);
        assertTrue(juego.isJuegoTerminado());
        assertFalse(juego.isVictoria());
    }

    private int[] buscarCasillaSinMina(Tablero tablero) {
        for (int fila = 0; fila < Tablero.FILAS; fila++) {
            for (int columna = 0; columna < Tablero.COLUMNAS; columna++) {
                if (!tablero.getCasilla(fila, columna).tieneMina()) {
                    return new int[] { fila, columna };
                }
            }
        }

        throw new IllegalStateException("No se encontró una casilla sin mina.");
    }

    private int[] buscarCasillaConMina(Tablero tablero) {
        for (int fila = 0; fila < Tablero.FILAS; fila++) {
            for (int columna = 0; columna < Tablero.COLUMNAS; columna++) {
                if (tablero.getCasilla(fila, columna).tieneMina()) {
                    return new int[] { fila, columna };
                }
            }
        }

        throw new IllegalStateException("No se encontró una casilla con mina.");
    }
}