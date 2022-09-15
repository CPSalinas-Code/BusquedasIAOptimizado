package busquedas.heuristicas;

import busquedas.BusquedaHeuristica;
import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;
import java.util.Collections;
import util.Comparador;
import grafo.NodoInformado;
import util.Util;

public class BusquedaAStar extends BusquedaHeuristica {

    private ArrayList<NodoInformado> nodosAbiertos;
    private ArrayList<NodoInformado> nodosCerrados;
    private ArrayList<Nodo> camino;

    public BusquedaAStar(Grafo grafo) {
        super(grafo);
        super.nombre = "A*";
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
        this.origen = origen;
        this.destino = destino;
        inicializarVariables();
        tiempoEjecucionInicial = System.currentTimeMillis();

        nodosAbiertos.add(new NodoInformado(origen, origen.getHeuristica()));       //Guarda informacion del nodo actual, del camino hasta el nodo actual y el costo
        NodoInformado actual = nodosAbiertos.get(0);

        while (!isEncontrado) {
            numIteraciones++;
            //guardarInfoIteracion(nodosAbiertos);
            guardarInfoDeNodoVisitado(actual);

            if (actual.getNodo().getNombre().equals(destino.getNombre())) {
                actual.agregarACamino(actual.getNodo());
                nodosCerrados.add(actual);
                isEncontrado = true;
            } else {
                for (Nodo n : getNodosAdyacentesVisitables(actual.getNodo())) {
                    int costo = n.getHeuristica() + grafo.getCostoCamino(actual.getNodo(), n);
                    NodoInformado nodoNuevo = new NodoInformado(n, actual.getCamino(), costo);
                    nodoNuevo.agregarACamino(actual.getNodo());

                    NodoInformado nodoMejor = getMejorNodo(nodoNuevo);                              // Se busca si existe un mejor camino al nodo nuevo desde los nodos onsiderados en la lista abierta y cerrada
                    if (nodoMejor != null) {
                        nodoNuevo = nodoMejor;
                    }

                    NodoInformado nodoExistente = getNodoExistenteEnListaDeNodosAbiertos(n);        //Se verifica si el nodo ya esta en la lista de abiertos
                    if (nodoExistente != null) {                                                    //Si lo esta, se comprueba si el actual es mejor que el que esta en dicha lista para reemplazarlo
                        if (nodoExistente.getCosto() > nodoNuevo.getCosto()) {
                            nodosAbiertos.remove(nodoExistente);
                            nodosAbiertos.add(nodoNuevo);
                        }
                    } else {
                        nodosAbiertos.add(nodoNuevo);
                    }
                }
                nodosCerrados.add(actual);
                nodosAbiertos.remove(0);
                if (nodosAbiertos.size() > 0) {
                    Collections.sort(nodosAbiertos, Comparador.porCostoNodoInformado());
                    actual = nodosAbiertos.get(0);
                } else {
                    break;
                }
            }
        }

        tiempoEjecucionFinal = System.currentTimeMillis();
        numNodosVisitados = nodosCerrados.size();
        camino = isEncontrado ? actual.getCamino() : null;
        //mostrarResultados(origen, destino);
    }

    private NodoInformado getMejorNodo(NodoInformado actual) {
        ArrayList<NodoInformado> mejoresNodos = new ArrayList<>();
        ArrayList<NodoInformado> abiertosYcerrados = new ArrayList<>();
        abiertosYcerrados.addAll(nodosAbiertos);
        abiertosYcerrados.addAll(nodosCerrados);
        for (NodoInformado ni : abiertosYcerrados) {
            if (actual.getNodo().getPredecesores().contains(ni.getNodo())) {
                int costo = actual.getNodo().getHeuristica() + grafo.getCostoCamino(ni.getNodo(), actual.getNodo());
                if (costo < actual.getCosto()) {
                    NodoInformado nodoNuevo = new NodoInformado(actual.getNodo(), ni.getCamino(), costo);
                    nodoNuevo.agregarACamino(ni.getNodo());
                    mejoresNodos.add(nodoNuevo);
                }
            }
        }
        if (mejoresNodos.isEmpty()) {
            return null;
        } else {
            Collections.sort(mejoresNodos, Comparador.porCostoNodoInformado()); 
            return mejoresNodos.get(0);                                          // Se obtiene el mejor nodo de los encontrados
        }
    }

    private NodoInformado getNodoExistenteEnListaDeNodosAbiertos(Nodo nodo) {    //Obtiene el nodoInformado que tiene como atributo a "nodo"
        NodoInformado nodoAbierto = null;
        for (NodoInformado n : nodosAbiertos) {
            if (n.getNodo().getNombre().equals(nodo.getNombre())) {
                nodoAbierto = n;
                break;
            }
        }
        return nodoAbierto;
    }

    private ArrayList<Nodo> getNodosAdyacentesVisitables(Nodo actual) {     // Devuelve una lista de nodos adyacentes al actual que aun no han sido visitados
        ArrayList<Nodo> nodosVisitables = new ArrayList<>();
        ArrayList<Nodo> cerrados = new ArrayList<>();
        for (NodoInformado ni : nodosCerrados) {
            cerrados.add(ni.getNodo());
        }
        for (Nodo n : actual.getNodosAdyacentes()) {
            if (!cerrados.contains(n)) {
                nodosVisitables.add(n);
            }
        }
        return nodosVisitables;
    }

    protected void guardarInfoIteracion(ArrayList<NodoInformado> lista) {
        for (NodoInformado n : lista) {
            iteraciones += n.getNodo().getNombre() + "(" + n.getCosto() + ")" + " ";
        }
        iteraciones += "\n";
    }

    protected void guardarInfoDeNodoVisitado(NodoInformado nodo) {
        nodosVisitados += nodo.getNodo().getNombre() + "(" + nodo.getCosto() + ")" + " ";
    }

    @Override
    protected void inicializarVariables() {
        iteraciones = nodosVisitados = "";
        this.nodosAbiertos = new ArrayList<>();
        this.nodosCerrados = new ArrayList<>();
        this.isEncontrado = false;
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
        String str_recorrido = isEncontrado ? nodosVisitados : nodosVisitados + " (-)";
        String str_tiempo = (tiempoEjecucionFinal - tiempoEjecucionInicial) + "ms";
        System.out.printf(Util.FORMATO_TABLA, "A*", origen.getNombre() + "-" + destino.getNombre(), numNodosVisitados, numIteraciones, str_tiempo, str_camino, str_recorrido);
    }

    @Override
    public void mostrarResultadosEspecificos() {
        System.out.println("\n\n|BUSQUEDA A*|");
        System.out.println("Nodo(Camino+Heuristica)");
        String str_camino = isEncontrado ? toStringLista(camino) : "No existe camino!";
        String origen_destino = origen.getNombre() + "|" + destino.getNombre();
        System.out.println("Camino encontrado " + origen_destino + "    : " + toStringLista(camino));
        System.out.println("Nodos Visitados           : " + nodosVisitados);
        System.out.println("\n***Iteraciones***\n" + iteraciones);
    }
}
