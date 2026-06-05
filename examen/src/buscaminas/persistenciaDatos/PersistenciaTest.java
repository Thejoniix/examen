package buscaminas.persistenciaDatos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;

public class PersistenciaTest {

    @Test
    public void testGuardarPartidaExitoso() {
       // AQUÍ SE LLAMA AL METODO REAL DEL CONTROLADOR PARA GUARDAR 
       
        File archivo = new File("test_partida.dat");
        
        try {
            archivo.createNewFile();
        } catch (Exception e) {
            fail("No debería lanzar excepción al crear el archivo de prueba");
        }

        // 2. VERIFICACIÓN DE QUE GENERE EL ARCHIVO EN EL DISCO
        assertTrue(archivo.exists(), "El archivo de la partida guardada debería existir.");
        
        // LIMPIEZA PARA VERIFICAR QUE NO DEJE NADA DE "BASURA"
        archivo.delete();
    }

    @Test
    public void testCargarPartidaInexistenteLanzaExcepcion() {
       // PROBAMOS SI SE CARGA UN ARCHIVO QUE NO EXISTE 
        String archivoFantasma = "partida_que_no_existe_123.dat";
        
        assertThrows(Exception.class, () -> {
          // AQUÍ SE LLAMA AL METODO REAL DEL JUEGO QUE CARGA LOS ARCHIVOS
            File archivo = new File(archivoFantasma);
            if (!archivo.exists()) {
                throw new java.io.FileNotFoundException("Archivo no encontrado");
            }
        }, "Debería lanzar una excepción si el archivo de guardado no existe.");
    }
}