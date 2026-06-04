package buscaminas.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TableroTest {

    @Test
    void tableroDebeTenerDiezFilasYDiezColumnas() {
        Tablero tablero = new Tablero();

        assertEquals(Tablero.FILAS, tablero.getCasillas().length);
        assertEquals(Tablero.COLUMNAS, tablero.getCasillas()[0].length);
    }

    @Test
    void tableroDebeTenerDiezMinas() {
        Tablero tablero = new Tablero();

        assertEquals(Tablero.TOTAL_MINAS, tablero.contarMinasTotales());
    }

    @Test
    void todasLasCasillasDebenEstarInicializadas() {
        Tablero tablero = new Tablero();

        for (int fila = 0; fila < Tablero.FILAS; fila++) {
            for (int columna = 0; columna < Tablero.COLUMNAS; columna++) {
                assertNotNull(tablero.getCasilla(fila, columna));
            }
        }
    }

    @Test
    void coordenadaDentroDelTableroDebeSerValida() {
        Tablero tablero = new Tablero();

        assertTrue(tablero.estaDentroDelTablero(0, 0));
        assertTrue(tablero.estaDentroDelTablero(9, 9));
        assertTrue(tablero.estaDentroDelTablero(5, 5));
    }

    @Test
    void coordenadaFueraDelTableroDebeSerInvalida() {
        Tablero tablero = new Tablero();

        assertFalse(tablero.estaDentroDelTablero(-1, 0));
        assertFalse(tablero.estaDentroDelTablero(0, -1));
        assertFalse(tablero.estaDentroDelTablero(10, 0));
        assertFalse(tablero.estaDentroDelTablero(0, 10));
    }

    @Test
    void casillasSinMinaDebenTenerNumeroValidoDeMinasAlrededor() {
        Tablero tablero = new Tablero();

        for (int fila = 0; fila < Tablero.FILAS; fila++) {
            for (int columna = 0; columna < Tablero.COLUMNAS; columna++) {
                Casilla casilla = tablero.getCasilla(fila, columna);

                if (!casilla.tieneMina()) {
                    assertTrue(casilla.getMinasAlrededor() >= 0);
                    assertTrue(casilla.getMinasAlrededor() <= 8);
                }
            }
        }
    }
}