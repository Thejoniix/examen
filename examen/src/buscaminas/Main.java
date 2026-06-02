package buscaminas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Juego juego = new Juego();
        VistaConsola vista = new VistaConsola();
        
        vista.mostrarInstrucciones();
        
        while (!juego.isJuegoTerminado()) {
            vista.mostrarTablero(juego.getTablero());
            
            System.out.print("Ingresa coordenada (ejemplo: A1): ");
            String entrada = scanner.next().toUpperCase();
            
            if (entrada.length() < 2) {
                vista.mostrarMensaje("Entrada inválida. Usa formato como A1\n");
                continue;
            }
            
            char letra = entrada.charAt(0);
            String numeroStr = entrada.substring(1);
            
            try {
                int fila = letra - 'A';
                int columna = Integer.parseInt(numeroStr) - 1;
                
                if (fila < 0 || fila >= 10 || columna < 0 || columna >= 10) {
                    vista.mostrarMensaje("Coordenada fuera de rango (A-J, 1-10)\n");
                    continue;
                }
                
                boolean resultado = juego.descubrirCasilla(fila, columna);
                
                if (!resultado) {
                    vista.mostrarMensaje("\nEncontraste una mina\n");
                    vista.mostrarTableroCompleto(juego.getTablero());
                    break;
                }
                
                if (juego.isVictoria()) {
                    vista.mostrarMensaje("\nescubriste todas las minas \n");
                    vista.mostrarTableroCompleto(juego.getTablero());
                    break;
                }
                
            } catch (NumberFormatException e) {
                vista.mostrarMensaje("Número inválido. Ejemplo: A1, B5\n");
            } catch (CasillaYaDescubiertaException e) {
                vista.mostrarMensaje(e.getMessage() + "\n");
            }
        }
        
        scanner.close();
    }
}