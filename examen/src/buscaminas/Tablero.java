package buscaminas;

import java.util.Random;
import java.io.Serializable;

public class Tablero implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;
    private static final int MINAS = 10;
    
    private Casilla[][] casillas;
    
    public Tablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
        inicializarTablero();
        colocarMinas();
        calcularNumeros();
    }
    
    private void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }
    
    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;
        
        while (minasColocadas < MINAS) {
            int fila = random.nextInt(FILAS);
            int columna = random.nextInt(COLUMNAS);
            
            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].setMina(true);
                minasColocadas++;
            }
        }
    }
    
    private void calcularNumeros() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!casillas[i][j].tieneMina()) {
                    casillas[i][j].setMinasAlrededor(contarMinasAlrededor(i, j));
                }
            }
        }
    }
    
    private int contarMinasAlrededor(int fila, int columna) {
        int contador = 0;
        
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS) {
                    if (casillas[i][j].tieneMina()) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }
    
    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }
    
    public Casilla[][] getCasillas() {
        return casillas;
    }
    
    public int getFilas() {
        return FILAS;
    }
    
    public int getColumnas() {
        return COLUMNAS;
    }
    
    public void descubrirCasilla(int fila, int columna) {
        casillas[fila][columna].setDescubierta(true);
    }
    
    public boolean hayMina(int fila, int columna) {
        return casillas[fila][columna].tieneMina();
    }
    
    public boolean estaDescubierta(int fila, int columna) {
        return casillas[fila][columna].isDescubierta();
    }
    
    public int getMinasAlrededor(int fila, int columna) {
        return casillas[fila][columna].getMinasAlrededor();
    }
}