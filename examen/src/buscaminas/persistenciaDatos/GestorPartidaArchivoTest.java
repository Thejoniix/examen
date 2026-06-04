package buscaminas.persistenciaDatos;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import buscaminas.excepciones.CasillaMarcadaException;
import buscaminas.excepciones.CasillaYaDescubiertaException;
import buscaminas.modelo.Juego;

class GestorPartidaArchivoTest {

    @Test
    void guardarYCargarPartidaDebeMantenerEstadoDelJuego()
            throws IOException, ClassNotFoundException, CasillaYaDescubiertaException, CasillaMarcadaException {

        GestorPartidaArchivo repositorio = new GestorPartidaArchivo();
        Juego juegoOriginal = new Juego();

        juegoOriginal.marcarCasilla(0, 0);

        String rutaArchivo = "buscaminas_partida_prueba.dat";

        repositorio.guardar(juegoOriginal, rutaArchivo);
        Juego juegoCargado = repositorio.cargar(rutaArchivo);

        assertNotNull(juegoCargado);
        assertNotNull(juegoCargado.getTablero());
        assertTrue(juegoCargado.getTablero().getCasilla(0, 0).isMarcada());

        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    @Test
    void cargarArchivoInexistenteDebeLanzarIOException() {
        GestorPartidaArchivo repositorio = new GestorPartidaArchivo();

        assertThrows(IOException.class, () -> {
            repositorio.cargar("partida_no_existe.dat");
        });
    }
}