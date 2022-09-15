package busquedas;

import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;

public abstract class BusquedaHeuristica extends Busqueda {
    
    protected int heuristicaTotal;
    
    public BusquedaHeuristica(Grafo grafo) {
        super(grafo);
    }
    
    @Override
    protected void guardarInfoDeNodoVisitado(Nodo nodo) {
        nodosVisitados += nodo.getNombre() + "(" + nodo.getHeuristica() + ") ";
    }

    @Override
    protected void guardarInfoDeIteracion(ArrayList<Nodo> nodos) {
        for (Nodo n : nodos) {
            iteraciones += n.getNombre() + "(" + n.getHeuristica() + ") ";
        }
        iteraciones += "\n";
    }

    @Override
    protected void guardarInfoDeIteracion(Nodo nodo) {
        iteraciones += nodo.getNombre() + "(" + nodo.getHeuristica() + ")\n";
    }
}
