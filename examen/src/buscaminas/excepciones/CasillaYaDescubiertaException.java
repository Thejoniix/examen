package buscaminas.excepciones;

//Excepcion para casillas que ya fueron descubiertas
public class CasillaYaDescubiertaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CasillaYaDescubiertaException(String mensaje) {
        super(mensaje);
    }
}