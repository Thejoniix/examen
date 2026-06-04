package buscaminas.acciones;

import buscaminas.modelo.Juego;

public class AccionMarcar implements AccionJuego {

    @Override
    public void ejecutar(Juego juego, int fila, int columna) throws Exception {
        juego.marcarCasilla(fila, columna);
    }
}