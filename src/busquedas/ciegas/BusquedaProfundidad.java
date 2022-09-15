package busquedas.ciegas;

import busquedas.*;
import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;
import util.Util;

public class BusquedaProfundidad extends BusquedaCiegas {

    private ArrayList<Nodo> pila;
    private ArrayList<Nodo> nodosCerrados;

    public BusquedaProfundidad(Grafo grafo) {
        super(grafo);
        super.nombre = "Profundidad";
        pila = new ArrayList<>();
        nodosCerrados = new ArrayList<>();
    }

    @Override
    public void buscar(Nodo[][] busquedas) {
        for (int i = 0; i < busquedas.length; i++) {
            buscar(busquedas[i][0], busquedas[i][1]);
            mostrarResultadosEnTabla();
        }
    }

    @Override
    public void buscar(Nodo origen, Nodo destino) {
        super.origen = origen;
        super.destino = destino;
        inicializarVariables();
        tiempoEjecucionInicial = System.currentTimeMillis();
        
        pila.add(origen);
        Nodo actual = origen;
        //guardarInfoDeIteracion(pila);
        
        while (!isEncontrado) {
            numNodosVisitados++;
            numIteraciones++;
            guardarInfoDeNodoVisitado(actual);
            if (actual.getNombre().equals(destino.getNombre())) {
                nodosCerrados.add(actual);
                isEncontrado = true;
            } else {
                nodosCerrados.add(actual);
                pila.remove(0);
                ArrayList<Nodo> aux = pila;
                pila = getNodosAdyacentesVisitables(actual);
                pila.addAll(aux);
                //guardarInfoDeIteracion(pila);

                if (pila.size() > 0) {
                    actual = pila.get(0);
                } else {
                    break;
                }
            }
        }
        tiempoEjecucionFinal = System.currentTimeMillis();

        //mostrarResultados(origen, destino);
    }

    private ArrayList<Nodo> getNodosAdyacentesVisitables(Nodo actual) {     // Devuelve una lista de nodos adyacentes al actual que aun no han sido visitados
        ArrayList<Nodo> nodosVisitables = new ArrayList<>();
        for (Nodo n : actual.getNodosAdyacentes()) {
            if (!nodosCerrados.contains(n) && !pila.contains(n)) {
                nodosVisitables.add(n);
            }
        }
        return nodosVisitables;
    }

    @Override
    protected void inicializarVariables() {
        iteraciones = "";
        nodosVisitados = "";
        numNodosVisitados = 0;
        numIteraciones = 0;
        tiempoEjecucionFinal = 0;
        tiempoEjecucionInicial = 0;
        isEncontrado = false;
        pila = new ArrayList<>();
        nodosCerrados = new ArrayList<>();
    }

    @Override
    public void mostrarResultados(Nodo origen, Nodo destino) {
    }

    @Override
    public void mostrarResultadosEnTabla() {
        String str_camino = isEncontrado ? "" : "!No existe camino!";
        String str_recorrido = isEncontrado ? nodosVisitados : nodosVisitados + " (-)";
        String str_tiempo = (tiempoEjecucionFinal - tiempoEjecucionInicial) + "ms";
        System.out.printf(Util.FORMATO_TABLA, "Profundidad", origen.getNombre() + "-" + destino.getNombre(), numNodosVisitados, numIteraciones, str_tiempo, str_camino, str_recorrido);
    }

    @Override
    public void mostrarResultadosEspecificos() {
        nodosVisitados = (isEncontrado) ? nodosVisitados : "No existe camino!";
        System.out.println("\n\n|BUSQUEDA PROFUNDIDAD|");
        System.out.println("Recorrido Profundidad " + origen.getNombre() + "|" + destino.getNombre() + " : " + nodosVisitados);
        System.out.println("\n***Iteraciones***\n" + iteraciones);
    }

}
