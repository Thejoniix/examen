package buscaminas;

import buscaminas.controlador.ControladorJuego;

//Clase principal que inicia el programa
public class Main {

    public static void main(String[] args) {
        ControladorJuego controlador = new ControladorJuego();
        controlador.iniciar();
    }
}