package buscaminas.persistenciaDatos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import buscaminas.modelo.Juego;

//GUARDAR Y CARGAR PARTIDAS
public class GestorPartidaArchivo implements GestorPartida {

    @Override
    public void guardar(Juego juego, String rutaArchivo) throws IOException {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            salida.writeObject(juego);
        }
    }

    @Override
    public Juego cargar(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Juego) entrada.readObject();
        }
    }
}