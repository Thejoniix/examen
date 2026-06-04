package buscaminas.acciones;

import buscaminas.modelo.Juego;


//Interfaz para definir acciones del juego
public interface AccionJuego {

    void ejecutar(Juego juego, int fila, int columna) throws Exception;
}