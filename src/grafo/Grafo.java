package grafo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import util.Util;

public class Grafo implements Serializable {

    private Map<String, Nodo> listaNodos;

    public Grafo() {
        listaNodos = new TreeMap<>();
    }

    public Map<String, Nodo> getListaNodos() {
        return listaNodos;
    }

    public void setListaNodos(Map<String, Nodo> listaNodos) {
        this.listaNodos = listaNodos;
    }

    public Nodo getNodo(String nombre) throws NullPointerException {
        Nodo n = listaNodos.get(nombre);
        if (n != null) {
            return n;
        } else {
            throw new NullPointerException("No existe el nodo " + nombre + "!");
        }
    }

    public boolean contiene(String nombre) {
        return listaNodos.containsKey(nombre);
    }

    public void agregarNodos(Nodo... nodos) {
        for (Nodo n : nodos) {
            listaNodos.put(n.getNombre(), n);
        }
    }

    public int getCostoCamino(Nodo n1, Nodo n2) {
        try {
            if (n1.getNombre().equals(n2.getNombre())) {
                return 0;
            } else {
                for (Camino c : n1.getCaminos()) {
                    if (c.getNodo().getNombre().equals(n2.getNombre())) {
                        return c.getCosto();
                    }
                }
                return -1;
            }
        } catch (Exception ex) {
            return -1;
        }
    }

    public void generar(int numMaxAdyacentesPorNodo, int totalNodos) {
        ArrayList<Nodo> cola = new ArrayList<>();
        int numNodosCreados = 0;
        int numAdyacentes;

        Nodo origen = new Nodo("1");
        listaNodos.put(origen.getNombre(), origen);
        cola.add(origen);
        numNodosCreados++;

        while (numNodosCreados <= totalNodos) {
            numAdyacentes = Util.aleatorio(1, numMaxAdyacentesPorNodo);
            for (int i = 0; i < numAdyacentes; i++) {
                if (numNodosCreados <= totalNodos) {
                    Nodo nodoNuevo = new Nodo((numNodosCreados + 1) + "", Util.aleatorio(1, 20));
                    cola.get(0).agregarCaminos(new Camino(nodoNuevo, Util.aleatorio(1, 20)));
                    listaNodos.put(nodoNuevo.getNombre(), nodoNuevo);
                    cola.add(nodoNuevo);
                    numNodosCreados++;
                }
            }
            cola.remove(0);
        }
    }

    public void generar2(int numMaxAdyacentesPorNodo, int totalNodos) {
        // Se generan todos los nodos
        for (int i = 1; i <= totalNodos; i++) {
            Nodo nodoNuevo = new Nodo(i + "", Util.aleatorio(1, 20));
            listaNodos.put(nodoNuevo.getNombre(), nodoNuevo);
        }

        ArrayList<Nodo> nodosEnGrafo = new ArrayList<>(listaNodos.values());
        Nodo nodoActual, nodoAdyacente;
        int numAdyacentes, numCaminosCreados, numIteraciones;
        String nombre;

        // Se generan los caminos entre nodos
        for (int i = 0; i < totalNodos; i++) {
            numAdyacentes = Util.aleatorio(1, numMaxAdyacentesPorNodo);
            nodoActual = nodosEnGrafo.get(i);
            numCaminosCreados = 0;
            numIteraciones = 0;
            while (numCaminosCreados < numAdyacentes && numIteraciones < 1000) {
                nombre = Util.aleatorio(1, totalNodos) + "";
                nodoAdyacente = listaNodos.get(nombre);
                if (!nodoActual.equals(nodoAdyacente)) {
                    //System.out.print("Actual: "+nodoActual.getNombre()+"   Adyacente: "+nodoAdyacente.getNombre());
                    if (!nodoAdyacente.getPredecesores().contains(nodoActual)) {
                        nodoActual.agregarCaminos(new Camino(nodoAdyacente, Util.aleatorio(1, 20)));
                        numCaminosCreados++;
                        //System.out.print(" - agregado");
                    }
                    System.out.println("");
                }
                numIteraciones++;
            }
        }
    }

    public void imprimir() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("GRAFO");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Nodo(Heuristica): NodoAdyacente(Heuristica|Costo a Nodo) ...");
        for (Nodo n : listaNodos.values()) {
            if (!n.getCaminos().isEmpty()) {
                System.out.print(n.getNombre() + "(" + n.getHeuristica() + ") " + ": ");
                for (Camino c : n.getCaminos()) {
                    System.out.print(c.getNodo().getNombre() + "(" + c.getNodo().getHeuristica() + "|" + c.getCosto() + ") ");
                }
                System.out.println("");
            }
        }
    }

}
