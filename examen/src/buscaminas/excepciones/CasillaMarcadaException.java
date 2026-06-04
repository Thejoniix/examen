package buscaminas.excepciones;

public class CasillaMarcadaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CasillaMarcadaException(String mensaje) {
        super(mensaje);
    }
}