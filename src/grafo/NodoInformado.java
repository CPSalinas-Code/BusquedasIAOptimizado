
package grafo;

import java.util.ArrayList;

/*
    guarda el nodo que se esta examinando, 
    el camino desde el origen hasta dicho nodo 
    y el costo
*/
public class NodoInformado {                
    private Nodo nodo;
    private ArrayList<Nodo> camino;
    private int costo;
    
    public NodoInformado(Nodo nodo){
        this.nodo = nodo;
        this.costo = 0;
        this.camino = new ArrayList<>();
    }
    
    public NodoInformado(Nodo nodo, int costo){
        this.nodo = nodo;
        this.costo = costo;
        this.camino = new ArrayList<>();
    }
    
    public NodoInformado(Nodo nodo, ArrayList<Nodo> camino, int costo){
        this.nodo = nodo;
        this.costo = costo;
        this.camino = new ArrayList<>(camino);
    }
    
    public NodoInformado(Nodo nodo, ArrayList<Nodo> camino){
        this.nodo = nodo;
        this.costo = 0;
        this.camino = new ArrayList<>(camino);
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public ArrayList<Nodo> getCamino() {
        return camino;
    }

    public void setCamino(ArrayList<Nodo> camino) {
        this.camino = camino;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    public void agregarACamino(Nodo n){
        this.camino.add(n);
    }
}
