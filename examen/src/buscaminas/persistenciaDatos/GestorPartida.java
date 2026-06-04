package buscaminas.persistenciaDatos;

import java.io.IOException;

import buscaminas.modelo.Juego;

//Interfaz para guardar y cargar partidas
public interface GestorPartida {

    void guardar(Juego juego, String rutaArchivo) throws IOException;

    Juego cargar(String rutaArchivo) throws IOException, ClassNotFoundException;
}