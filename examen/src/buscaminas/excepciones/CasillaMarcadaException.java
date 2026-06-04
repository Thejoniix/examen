package buscaminas.excepciones;

//Excepcion para evitar descubrir una casilla marcada
public class CasillaMarcadaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CasillaMarcadaException(String mensaje) {
        super(mensaje);
    }
}