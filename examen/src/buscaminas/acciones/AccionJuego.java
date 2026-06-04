package buscaminas.acciones;

import buscaminas.modelo.Juego;

public interface AccionJuego {

    void ejecutar(Juego juego, int fila, int columna) throws Exception;
}