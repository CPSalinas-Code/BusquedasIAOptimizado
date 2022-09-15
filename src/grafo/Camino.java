package grafo;

import java.io.Serializable;


public class Camino implements Serializable {
    private Nodo nodo;
    private int costo;

    public Camino(Nodo nodo, int costo) {
        this.nodo = nodo;
        this.costo = costo;
    }
    
    public Camino(Nodo nodo) {
        this.nodo = nodo;
        this.costo = 0;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    
}
