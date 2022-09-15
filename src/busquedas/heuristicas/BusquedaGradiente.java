package busquedas.heuristicas;

import busquedas.BusquedaHeuristica;
import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;
import util.Util;

public class BusquedaGradiente extends BusquedaHeuristica {

    private ArrayList<Nodo> nodosAbiertos;
    private ArrayList<Nodo> nodosCerrados;
    private ArrayList<Nodo> camino;

    public BusquedaGradiente(Grafo grafo) {
        super(grafo);
        super.nombre = "Gradiente";
    }

    @Override
    public void buscar(Nodo[][] destinos) {
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

        ArrayList<Nodo> nodosAdyacentes;
        Nodo nodoActual = origen;
        nodosAbiertos.add(nodoActual);
        //guardarInfoDeIteracion(nodoActual);                                                     // Respaldo de datos

        while (!isEncontrado) {
            numIteraciones++;
            numNodosVisitados++;
            guardarInfoDeNodoVisitado(nodoActual);
            if (isNodoVisitado(nodoActual)) {
                break;
            } else {
                if (nodoActual.getNombre().equals(destino.getNombre())) {
                    isEncontrado = true;
                } else {
                    nodosAdyacentes = new ArrayList<>(getNodosAdyacentesVisitables(nodoActual));
                    if (!nodosAdyacentes.isEmpty()) {
                        nodosCerrados.add(nodoActual);                        
                        heuristicaTotal += nodoActual.getHeuristica();                              // Respaldo de datos
                        nodoActual = obtenerNodoConMenorHeuristica(nodosAdyacentes);
                        nodosAbiertos.add(nodoActual);
                        //guardarInfoDeIteracion(nodoActual);                                         // Respaldo de datos
                    } else {
                        break;
                    }
                }
            }
        }

        tiempoEjecucionFinal = System.currentTimeMillis();
        camino = nodosAbiertos;
        //mostrarResultados(origen, destino);
    }

    private Nodo obtenerNodoConMenorHeuristica(ArrayList<Nodo> nodos) {
        Nodo menor = nodos.get(0);
        for (int i = 1; i < nodos.size(); i++) {
            if (!(menor.getHeuristica() <= nodos.get(i).getHeuristica())) {
                menor = nodos.get(i);
            }
        }
        return menor;
    }

    private ArrayList<Nodo> getNodosAdyacentesVisitables(Nodo actual) {     // Devuelve una lista de nodos adyacentes al actual que aun no han sido visitados
        ArrayList<Nodo> nodosVisitables = new ArrayList<>();
        for (Nodo n : actual.getNodosAdyacentes()) {
            if (!nodosCerrados.contains(n) && !nodosAbiertos.contains(n)) {
                nodosVisitables.add(n);
            }
        }
        return nodosVisitables;
    }
    
    private boolean isNodoVisitado(Nodo nodo) {
        if (nodosCerrados.contains(nodo)) {
            return true;
        }
        return false;
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
        System.out.printf(Util.FORMATO_TABLA, "Gradiente", origen.getNombre() + "-" + destino.getNombre(), numNodosVisitados, numIteraciones, str_tiempo, str_camino, str_recorrido);
    }

    @Override
    public void mostrarResultadosEspecificos() {
        System.out.println("\n|BUSQUEDA GRADIENTE|");
        System.out.println("Nodo(Heuristica)");
        String str_camino = isEncontrado ? nodosVisitados : "No existe camino!";
        String str_heuristica_total = isEncontrado ? heuristicaTotal + "" : "-";
        String origen_destino = origen.getNombre() + "|" + destino.getNombre();
        System.out.println("Camino encontrado " + origen_destino + "    : " + str_camino);
        System.out.println("Heuristica total          : " + str_heuristica_total);
        System.out.println("Nodos Visitados           : " + nodosVisitados);
        System.out.println("\n***Iteraciones***\n" + iteraciones);
    }
}
