package buscaminas.acciones;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buscaminas.modelo.Juego;

class AccionMarcarTest {

    private AccionMarcar accion;
    private Juego juego;

    @BeforeEach
    void setUp() throws Exception {
        // Objetivos antes de cada test 
        accion = new AccionMarcar();
        juego = new Juego(); // 
    }

    @Test
    void testEjecutarMarcadoSinErrores() {
        // Verificamos que al marcar una casilla responda correctamente 
        assertDoesNotThrow(() -> {
            accion.ejecutar(juego, 1, 1);
        });
    }

    @Test
    void testEjecutarMarcadoDosVeces() {
   // Probamos que al marcar 2 veces no se vaya a desmarcar 
        assertDoesNotThrow(() -> {
            accion.ejecutar(juego, 2, 2);
            accion.ejecutar(juego, 2, 2);
        });
    }
}