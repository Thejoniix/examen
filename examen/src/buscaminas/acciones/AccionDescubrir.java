package buscaminas.acciones;

import buscaminas.modelo.Juego;

public class AccionDescubrir implements AccionJuego {

    @Override
    public void ejecutar(Juego juego, int fila, int columna) throws Exception {
        juego.descubrirCasilla(fila, columna);
    }
}