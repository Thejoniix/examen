package buscaminas.modelo;

import java.io.Serializable;
import java.util.Random;

//Clase que genera y administra el tablero
public class Tablero implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int FILAS = 10;
    public static final int COLUMNAS = 10;
    public static final int TOTAL_MINAS = 10;

    private Casilla[][] casillas;

    // Constructor del tablero    
    public Tablero() {
        this.casillas = new Casilla[FILAS][COLUMNAS];
        inicializarCasillas();
        colocarMinas();
        calcularMinasAlrededor();
    }

    // Crea todas las casillas del tablero
    private void inicializarCasillas() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                casillas[fila][columna] = new Casilla();
            }
        }
    }

    // Coloca minas aleatorias en el tablero
    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;

        while (minasColocadas < TOTAL_MINAS) {
            int fila = random.nextInt(FILAS);
            int columna = random.nextInt(COLUMNAS);

            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].setMina(true);
                minasColocadas++;
            }
        }
    }

    // Calcula cuantas minas tiene alrededor cada casilla segura
    private void calcularMinasAlrededor() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (!casillas[fila][columna].tieneMina()) {
                    int cantidad = contarMinasAlrededor(fila, columna);
                    casillas[fila][columna].setMinasAlrededor(cantidad);
                }
            }
        }
    }

    // Cuenta las minas que rodean una casilla
    private int contarMinasAlrededor(int fila, int columna) {
        int contador = 0;

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (estaDentroDelTablero(i, j) && casillas[i][j].tieneMina()) {
                    contador++;
                }
            }
        }

        return contador;
    }

    // Verifica si una coordenada esta dentro del tablero
    public boolean estaDentroDelTablero(int fila, int columna) {
        return fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS;
    }

    // Devuelve una casilla especifica
    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    // Devuelve la matriz de casillas
    public Casilla[][] getCasillas() {
        return casillas;
    }

    // Cuenta el total de minas del tablero
    public int contarMinasTotales() {
        int contador = 0;

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (casillas[fila][columna].tieneMina()) {
                    contador++;
                }
            }
        }

        return contador;
    }
}