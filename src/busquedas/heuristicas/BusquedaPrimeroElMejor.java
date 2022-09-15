package busquedas.heuristicas;

import busquedas.BusquedaHeuristica;
import grafo.Grafo;
import grafo.Nodo;
import grafo.NodoInformado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import util.Comparador;
import util.Util;

public class BusquedaPrimeroElMejor extends BusquedaHeuristica {

    private ArrayList<NodoInformado> nodosAbiertos;              // Cola o pila de nodos por visitar
    private ArrayList<NodoInformado> nodosCerrados;              // Nodos que ya fueron visitados
    private ArrayList<Nodo> camino;                     // Camino encontrado

    public BusquedaPrimeroElMejor(Grafo grafo) {
        super(grafo);
        super.nombre = "Primero el mejor";
    }

    @Override
    public void buscar(Nodo[][] busquedas) {
        for (int i = 0; i < destinos.length; i++) {
            buscar(destinos[i][0], destinos[i][1]);
            mostrarResultadosEnTabla();
        }
    }

    @Override
    public void buscar(Nodo origen, Nodo destino) {
        super.origen = origen;
        super.destino = destino;
        inicializarVariables();
        tiempoEjecucionInicial = System.currentTimeMillis();

        nodosAbiertos.add(new NodoInformado(origen));
        NodoInformado actual = nodosAbiertos.get(0);
        //guardarInfoDeIteracion(actual);

        while (!isEncontrado) {
            numIteraciones++;
            guardarInfoDeNodoVisitado(actual);
            if (actual.getNodo().getNombre().equals(destino.getNombre())) {
                actual.agregarACamino(actual.getNodo());
                nodosCerrados.add(actual);
                isEncontrado = true;
            } else {
                nodosCerrados.add(actual);
                ArrayList<Nodo> nodosVisitables = new ArrayList<>(getNodosAdyacentesVisitables(actual.getNodo()));
                for(Nodo n : nodosVisitables){
                    NodoInformado nodoNuevo = new NodoInformado(n, actual.getCamino());
                    nodoNuevo.agregarACamino(actual.getNodo());                             // Guarda el camino por el que viene
                    nodosAbiertos.add(nodoNuevo);
                }
                
                nodosAbiertos.remove(0);
                if (!nodosVisitables.isEmpty()) {
                    Collections.sort(nodosAbiertos, Comparador.porHeuristicaNodoInformado());
                }
                
                //guardarInfoDeIteracion(nodosAbiertos);
                if (nodosAbiertos.size() > 0) {
                    actual = nodosAbiertos.get(0);
                } else {
                    break;
                }
            }
        }

        tiempoEjecucionFinal = System.currentTimeMillis();
        numNodosVisitados = nodosCerrados.size();
        camino = isEncontrado ? actual.getCamino() : null;
        heuristicaTotal = getHeuristicaTotal();
        //mostrarResultados(origen, destino);
    }

    private ArrayList<Nodo> getNodosAdyacentesVisitables(Nodo actual) {     // Devuelve una lista de nodos adyacentes al actual que aun no han sido visitados
        ArrayList<Nodo> nodosVisitables = new ArrayList<>();
        HashSet<Nodo> cerradosYabiertos = new HashSet<>();
        for (NodoInformado ni : nodosCerrados) {
            cerradosYabiertos.add(ni.getNodo());
        }
        for (NodoInformado ni : nodosAbiertos) {
            cerradosYabiertos.add(ni.getNodo());
        }
        for (Nodo n : actual.getNodosAdyacentes()) {
            if (!cerradosYabiertos.contains(n)) {
                nodosVisitables.add(n);
            }
        }
        return nodosVisitables;
    }

    private int getHeuristicaTotal() {
        int heuristicaTotal = 0;
        if (camino != null) {
            for (int i = 0; i < camino.size() - 1; i++) {  // No se considera la heuristica del nodo destino
                heuristicaTotal += camino.get(i).getHeuristica();
            }
        }
        return heuristicaTotal;
    }
    
    protected void guardarInfoIteracion(ArrayList<NodoInformado> lista) {
        for (NodoInformado n : lista) {
            iteraciones += n.getNodo().getNombre() + "(" + n.getNodo().getHeuristica() + ")" + " ";
        }
        iteraciones += "\n";
    }

    protected void guardarInfoDeNodoVisitado(NodoInformado nodo) {
        nodosVisitados += nodo.getNodo().getNombre() + "(" + nodo.getNodo().getHeuristica() + ")" + " ";
    }

    @Override
    protected void inicializarVariables() {
        iteraciones = nodosVisitados = "";
        heuristicaTotal = 0;
        isEncontrado = false;
        nodosAbiertos = new ArrayList<>();
        nodosCerrados = new ArrayList<>();
        camino = new ArrayList<>();
        tiempoEjecucionInicial = 0;
        tiempoEjecucionFinal = 0;
        numIteraciones = 0;
        numNodosVisitados = 0;
    }

    @Override
    public void mostrarResultados(Nodo origen, Nodo destino) {}

    @Override
    public void mostrarResultadosEnTabla() {
        String str_camino = isEncontrado ? toStringLista(camino) : "!No existe camino!";
        String str_recorrido = isEncontrado ? nodosVisitados : nodosVisitados+" (-)";
        String str_tiempo = (tiempoEjecucionFinal - tiempoEjecucionInicial) + "ms"; 
        System.out.printf(Util.FORMATO_TABLA, "Primero el mejor", origen.getNombre() + "-" + destino.getNombre(), numNodosVisitados, numIteraciones, str_tiempo, str_camino, str_recorrido);
    }

    @Override
    public void mostrarResultadosEspecificos() {
        System.out.println("\n\n|BUSQUEDA PRIMERO EL MEJOR|");
        System.out.println("Nodo(Heuristica)");
        String str_camino = isEncontrado ? toStringLista(camino) : "No existe camino!";
        String str_heuristica_total = isEncontrado ? heuristicaTotal + "" : "-";
        String origen_destino = origen.getNombre() + "|" + destino.getNombre();
        System.out.println("Camino encontrado " + origen_destino + "    : " + toStringLista(camino));
        System.out.println("Heuristica total          : " + heuristicaTotal);
        System.out.println("Nodos Visitados           : " + nodosVisitados);
        System.out.println("\n***Iteraciones***\n" + iteraciones);
    }

}
