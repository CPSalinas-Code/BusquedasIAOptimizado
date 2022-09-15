
package util;

import grafo.NodoInformado;
import grafo.Nodo;
import java.util.Comparator;


public class Comparador {
    
    public static Comparator porCostoNodoInformado() {
        return new Comparator() {

            @Override
            public int compare(Object nodo1, Object nodo2) {
                int costo1 = ((NodoInformado) nodo1).getCosto();
                int costo2 = ((NodoInformado) nodo2).getCosto();
                return costo1 - costo2;
            }
        };
    }
    
    public static Comparator porHeuristicaNodoInformado() {
        return new Comparator() {

            @Override
            public int compare(Object nodo1, Object nodo2) {
                int h1 = ((NodoInformado) nodo1).getNodo().getHeuristica();
                int h2 = ((NodoInformado) nodo2).getNodo().getHeuristica();
                return h1 - h2;
            }
        };
    }
    
    public static Comparator porHeuristicaNodo() {
        return new Comparator() {

            @Override
            public int compare(Object nodo1, Object nodo2) {
                int h1 = ((Nodo) nodo1).getHeuristica();
                int h2 = ((Nodo) nodo2).getHeuristica();
                return h1 - h2;
            }
        };
    }
}
