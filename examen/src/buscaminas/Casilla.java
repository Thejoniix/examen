package buscaminas;

import java.io.Serializable;

public class Casilla implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private boolean mina;
    private boolean descubierta;
    private int minasAlrededor;
    
    public Casilla() {
        this.mina = false;
        this.descubierta = false;
        this.minasAlrededor = 0;
    }
    
    public boolean tieneMina() {
        return mina;
    }
    
    public void setMina(boolean mina) {
        this.mina = mina;
    }
    
    public boolean isDescubierta() {
        return descubierta;
    }
    
    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
    }
    
    public int getMinasAlrededor() {
        return minasAlrededor;
    }
    
    public void setMinasAlrededor(int minasAlrededor) {
        this.minasAlrededor = minasAlrededor;
    }
}