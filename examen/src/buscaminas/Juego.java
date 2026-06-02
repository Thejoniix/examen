package buscaminas;

public class Juego {
    private Tablero tablero;
    private boolean juegoTerminado;
    private boolean victoria;
    
    public Juego() {
        tablero = new Tablero();
        juegoTerminado = false;
        victoria = false;
    }
    
    public boolean descubrirCasilla(int fila, int columna) throws CasillaYaDescubiertaException {
        if (tablero.estaDescubierta(fila, columna)) {
            throw new CasillaYaDescubiertaException(
                "La casilla " + (char)('A' + fila) + (columna + 1) + " ya fue descubierta"
            );
        }
        
        tablero.descubrirCasilla(fila, columna);
        
        if (tablero.hayMina(fila, columna)) {
            juegoTerminado = true;
            victoria = false;
            return false;  // Perdió
        }
        
        // Si la casilla no tiene minas alrededor, descubrir las adyacentes
        if (tablero.getMinasAlrededor(fila, columna) == 0) {
            descubrirAdyacentes(fila, columna);
        }
        
        verificarVictoria();
        return true;  // Continúa
    }
    
    private void descubrirAdyacentes(int fila, int columna) {
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < tablero.getFilas() && 
                    j >= 0 && j < tablero.getColumnas()) {
                    try {
                        if (!tablero.estaDescubierta(i, j) && !tablero.hayMina(i, j)) {
                            tablero.descubrirCasilla(i, j);
                            if (tablero.getMinasAlrededor(i, j) == 0) {
                                descubrirAdyacentes(i, j);
                            }
                        }
                    } catch (Exception e) {
                        // Ignorar errores
                    }
                }
            }
        }
    }
    
    private void verificarVictoria() {
        boolean todasDescubiertas = true;
        
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (!tablero.hayMina(i, j) && !tablero.estaDescubierta(i, j)) {
                    todasDescubiertas = false;
                    break;
                }
            }
        }
        
        if (todasDescubiertas) {
            juegoTerminado = true;
            victoria = true;
        }
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