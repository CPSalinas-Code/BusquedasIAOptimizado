
package grafo;

import java.io.Serializable;
import java.util.ArrayList;

public class Nodo implements Serializable{
    
    private String nombre;
    private int heuristica;
    private ArrayList<Camino> caminos;      // Caminos hacia sus vecinos
    
    private ArrayList<Nodo> predecesores;   // Nodos anteriores a este

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.caminos = new ArrayList<>();
        this.predecesores = new ArrayList<>();
        this.heuristica = 0;
    }

    public Nodo(String nombre, int heuristica) {
        this.nombre = nombre;
        this.heuristica = heuristica;
        this.caminos = new ArrayList<>();
        this.predecesores = new ArrayList<>();
    }
    
    public Nodo(String nombre, int heuristica, ArrayList<Camino> caminos) {
        this.nombre = nombre;
        this.heuristica = heuristica;
        this.caminos = new ArrayList<>(caminos);
        this.predecesores = new ArrayList<>();
    }
        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Camino> getCaminos() {
        return caminos;
    }
    
    public ArrayList<Nodo> getNodosAdyacentes() {
        ArrayList<Nodo> nodos = new ArrayList<>();
        for(Camino c : caminos){
            nodos.add(c.getNodo());
        }
        return nodos;
    }

    public void setNodosAdyacentes(ArrayList<Camino> caminos) {        
        this.caminos = new ArrayList<>(caminos);
    }

    public int getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }
    
    public void agregarCaminos(Camino... caminos) {
        for (Camino c : caminos) {
            this.caminos.add(c);
            c.getNodo().addPredecesor(this);
        }
    }
    
    public ArrayList<Nodo> getPredecesores() {
        return predecesores;
    }

    public void addPredecesor(Nodo nodo) {
        predecesores.add(nodo);
    }
}
