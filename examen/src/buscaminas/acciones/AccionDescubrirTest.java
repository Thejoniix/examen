package buscaminas.acciones;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Necesitamos importar el Juego para poder probar la acción
import buscaminas.modelo.Juego; 

class AccionDescubrirTest {

    private AccionDescubrir accion;
    private Juego juego;

    @BeforeEach
    void setUp() throws Exception {
       
        accion = new AccionDescubrir();
        juego = new Juego(); 
    }

    @Test
    void testEjecutarAccionSinErrores() {
        //  Probamos la acción en una casilla válida no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            accion.ejecutar(juego, 0, 0);
        });
    }
}