package buscaminas.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//Pruebas unitarias de la clase Casilla
class CasillaTest {

    @Test
    void casillaDebeIniciarSinMinaSinDescubrirYSinMarcar() {
        Casilla casilla = new Casilla();

        assertFalse(casilla.tieneMina());
        assertFalse(casilla.isDescubierta());
        assertFalse(casilla.isMarcada());
        assertEquals(0, casilla.getMinasAlrededor());
    }

    @Test
    void cambiarMarcaDebeMarcarYDesmarcarCasilla() {
        Casilla casilla = new Casilla();

        casilla.cambiarMarca();
        assertTrue(casilla.isMarcada());

        casilla.cambiarMarca();
        assertFalse(casilla.isMarcada());
    }

    @Test
    void descubrirDebeCambiarEstadoADescubiertaYQuitarMarca() {
        Casilla casilla = new Casilla();

        casilla.cambiarMarca();
        casilla.descubrir();

        assertTrue(casilla.isDescubierta());
        assertFalse(casilla.isMarcada());
    }

    @Test
    void estaVaciaDebeSerVerdaderoSiNoTieneMinaYNiMinasAlrededor() {
        Casilla casilla = new Casilla();

        assertTrue(casilla.estaVacia());
    }

    @Test
    void estaVaciaDebeSerFalsoSiTieneMina() {
        Casilla casilla = new Casilla();

        casilla.setMina(true);

        assertFalse(casilla.estaVacia());
    }

    @Test
    void estaVaciaDebeSerFalsoSiTieneMinasAlrededor() {
        Casilla casilla = new Casilla();

        casilla.setMinasAlrededor(2);

        assertFalse(casilla.estaVacia());
    }
}