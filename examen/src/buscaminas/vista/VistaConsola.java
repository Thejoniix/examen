package buscaminas.vista;

import buscaminas.modelo.Casilla;
import buscaminas.modelo.Tablero;

//Clase encargada de mostrar informacion en consola
public class VistaConsola {

	
    // Muestra las instrucciones iniciales
    public void mostrarInstrucciones() {
        System.out.println("===== BUSCAMINAS =====");
        System.out.println("BIENVENIDOS AL SISTEMA");
        System.out.println("Descubra las casillas seguras y evite las minas para ganar.");
        System.out.println("Ejemplos de coordenadas: B2, E6 o H9.");
        System.out.println("Símbolos: ? cubierta | M marcada | . vacía | X mina");
        System.out.println();
    }

    
    // Muestra el menu de opciones
    public void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Descubrir");
        System.out.println("2. Marcar");
        System.out.println("3. Guardar");
        System.out.println("4. Cargar");
        System.out.println("5. Salir");
        System.out.print("Elija una opción: ");
    }

    
    // Muestra el tablero ocultando las minas
    public void mostrarTablero(Tablero tablero) {
        System.out.println();
        System.out.println("    1 2 3 4 5 6 7 8 9 10");
        System.out.println("   ----------------------");

        for (int fila = 0; fila < Tablero.FILAS; fila++) {
            char letra = (char) ('A' + fila);
            System.out.print(letra + " | ");

            for (int columna = 0; columna < Tablero.COLUMNAS; columna++) {
                Casilla casilla = tablero.getCasilla(fila, columna);
                System.out.print(obtenerSimboloCasilla(casilla) + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    
    // Devuelve el simbolo que representa una casilla
    private String obtenerSimboloCasilla(Casilla casilla) {
        if (casilla.isMarcada()) {
            return "M";
        }

        if (!casilla.isDescubierta()) {
            return "?";
        }

        if (casilla.tieneMina()) {
            return "X";
        }

        if (casilla.getMinasAlrededor() == 0) {
            return ".";
        }

        return String.valueOf(casilla.getMinasAlrededor());
    }

    // Muestra todo el tablero al finalizar el juego
    public void mostrarTableroCompleto(Tablero tablero) {
        System.out.println();
        System.out.println("Tablero final:");
        System.out.println("    1 2 3 4 5 6 7 8 9 10");
        System.out.println("   ----------------------");

        for (int fila = 0; fila < Tablero.FILAS; fila++) {
            char letra = (char) ('A' + fila);
            System.out.print(letra + " | ");

            for (int columna = 0; columna < Tablero.COLUMNAS; columna++) {
                Casilla casilla = tablero.getCasilla(fila, columna);

                if (casilla.tieneMina()) {
                    System.out.print("X ");
                } else if (casilla.getMinasAlrededor() == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(casilla.getMinasAlrededor() + " ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void solicitarCoordenada() {
        System.out.print("Ingrese coordenada: ");
    }
}