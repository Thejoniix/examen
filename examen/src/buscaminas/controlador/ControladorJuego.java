package buscaminas.controlador;

import java.io.IOException;
import java.util.Scanner;

import buscaminas.acciones.AccionDescubrir;
import buscaminas.acciones.AccionJuego;
import buscaminas.acciones.AccionMarcar;
import buscaminas.excepciones.CasillaMarcadaException;
import buscaminas.excepciones.CasillaYaDescubiertaException;
import buscaminas.modelo.Juego;
import buscaminas.modelo.Tablero;
import buscaminas.persistenciaDatos.GestorPartida;
import buscaminas.persistenciaDatos.GestorPartidaArchivo;
import buscaminas.vista.VistaConsola;

public class ControladorJuego {

    private static final String ARCHIVO_PARTIDA = "partida_buscaminas.dat";

    private Juego juego;
    private VistaConsola vista;
    private Scanner scanner;
    private GestorPartida repositorio;

    public ControladorJuego() {
        this.juego = new Juego();
        this.vista = new VistaConsola();
        this.scanner = new Scanner(System.in);
        this.repositorio = new GestorPartidaArchivo();
    }

    public void iniciar() {
        vista.mostrarInstrucciones();

        while (!juego.isJuegoTerminado()) {
            vista.mostrarTablero(juego.getTablero());
            vista.mostrarMenu();

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ejecutarAccion(new AccionDescubrir());
                    break;

                case "2":
                    ejecutarAccion(new AccionMarcar());
                    break;

                case "3":
                    guardarPartida();
                    break;

                case "4":
                    cargarPartida();
                    break;

                case "5":
                    vista.mostrarMensaje("Saliendo del juego.");
                    return;

                default:
                    vista.mostrarMensaje("Opción no válida.");
                    break;
            }
        }

        finalizarJuego();
    }

    private void ejecutarAccion(AccionJuego accion) {
        try {
            int[] coordenada = leerCoordenada();

            accion.ejecutar(juego, coordenada[0], coordenada[1]);

            if (accion instanceof AccionDescubrir) {
                verificarResultadoDescubrimiento();
            } else if (accion instanceof AccionMarcar) {
                vista.mostrarMensaje("Marca actualizada.");
            }

        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Coordenada inválida. Use B2, E6 o H9.");
        } catch (ArrayIndexOutOfBoundsException e) {
            vista.mostrarMensaje("Coordenada fuera del tablero.");
        } catch (CasillaYaDescubiertaException | CasillaMarcadaException e) {
            vista.mostrarMensaje(e.getMessage());
        } catch (Exception e) {
            vista.mostrarMensaje("No se pudo realizar la acción.");
        }
    }

    private void verificarResultadoDescubrimiento() {
        if (juego.isJuegoTerminado() && juego.isVictoria()) {
            vista.mostrarMensaje("Victoria. Todas las casillas seguras fueron descubiertas.");
        } else if (juego.isJuegoTerminado()) {
            vista.mostrarMensaje("Derrota. Se descubrió una mina.");
        }
    }

    private int[] leerCoordenada() {
        vista.solicitarCoordenada();
        String entrada = scanner.nextLine().trim().toUpperCase();

        if (entrada.length() < 2) {
            throw new NumberFormatException();
        }

        char letraFila = entrada.charAt(0);
        int fila = letraFila - 'A';
        int columna = Integer.parseInt(entrada.substring(1)) - 1;

        if (fila < 0 || fila >= Tablero.FILAS || columna < 0 || columna >= Tablero.COLUMNAS) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return new int[] { fila, columna };
    }

    private void guardarPartida() {
        try {
            repositorio.guardar(juego, ARCHIVO_PARTIDA);
            vista.mostrarMensaje("Partida guardada.");

        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar partida.");
        }
    }

    private void cargarPartida() {
        try {
            juego = repositorio.cargar(ARCHIVO_PARTIDA);
            vista.mostrarMensaje("Partida cargada.");

        } catch (IOException | ClassNotFoundException e) {
            vista.mostrarMensaje("Error al cargar partida.");
        }
    }

    private void finalizarJuego() {
        vista.mostrarTableroCompleto(juego.getTablero());

        if (juego.isVictoria()) {
            vista.mostrarMensaje("Resultado: VICTORIA,DESCUBRISTE TODAS LAS MINAS.");
            vista.mostrarMensaje("GRACIAS POR UTILIZAR EL SISTEMA.");
        } else {
            vista.mostrarMensaje("Resultado: DERROTA ENCONTRASTE UNA MINA.");
            vista.mostrarMensaje("VUELVA PRONTO");
        }
    }
}