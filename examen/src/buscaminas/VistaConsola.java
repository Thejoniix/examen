package buscaminas;

public class VistaConsola {
    
    public void mostrarTablero(Tablero tablero) {
        Casilla[][] casillas = tablero.getCasillas();
        
        System.out.println("\n   1 2 3 4 5 6 7 8 9 10");
        System.out.println("   ---------------------");
        
        for (int i = 0; i < tablero.getFilas(); i++) {
            char letra = (char) ('A' + i);
            System.out.print(letra + " |");
            
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla casilla = casillas[i][j];
                
                if (!casilla.isDescubierta()) {
                    System.out.print("? ");
                } else if (casilla.tieneMina()) {
                    System.out.print("X ");
                } else {
                    int num = casilla.getMinasAlrededor();
                    if (num == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print(num + " ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void mostrarTableroCompleto(Tablero tablero) {
        Casilla[][] casillas = tablero.getCasillas();
        
        System.out.println("\n   TABLERO COMPLETO:");
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        System.out.println("   ---------------------");
        
        for (int i = 0; i < tablero.getFilas(); i++) {
            char letra = (char) ('A' + i);
            System.out.print(letra + " |");
            
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla casilla = casillas[i][j];
                
                if (casilla.tieneMina()) {
                    System.out.print("X ");
                } else {
                    int num = casilla.getMinasAlrededor();
                    if (num == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print(num + " ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("(X = Mina)");
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void mostrarInstrucciones() {
        System.out.println("===== BUSCAMINAS =====");
        System.out.println("Instrucciones:");
        System.out.println(" - Ingresa coordenadas como A1, B5, J10");
        System.out.println(" - Las casillas con números indican minas alrededor");
        System.out.println(" - Las casillas vacías no tienen minas alrededor");
        System.out.println(" - ¡No toques las minas (X)!\n");
    }
}