package busquedas.ciegas;

import busquedas.*;
import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;
import util.Util;

public class BusquedaAmplitud extends BusquedaCiegas {

    private ArrayList<Nodo> cola;
    private ArrayList<Nodo> nodosCerrados;

    public BusquedaAmplitud(Grafo grafo) {
        super(grafo);
        super.nombre = "Amplitud";
        cola = new ArrayList<>();
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

        cola.add(origen);
        Nodo actual = origen;
        //guardarInfoDeIteracion(cola);

        while (!isEncontrado) {
            numNodosVisitados++;
            numIteraciones++;
            guardarInfoDeNodoVisitado(actual);
            if (actual.getNombre().equals(destino.getNombre())) {
                nodosCerrados.add(actual);
                isEncontrado = true;
            } else {
                nodosCerrados.add(actual);
                cola.addAll(getNodosAdyacentesVisitables(actual));
                cola.remove(0);
                //guardarInfoDeIteracion(cola);

                if (cola.size() > 0) {
                    actual = cola.get(0);
                } else {
                    break;
                }
            }
        }
        tiempoEjecucionFinal = System.currentTimeMillis();
        //mostrarResultados(origen, destino);
    }

    private ArrayList<Nodo> getNodosAdyacentesVisitables(Nodo actual) {     // Devuelve una lista de nodos adyacentes al actual que aun no han sido visitados y que aun no han sido considerados para la cola
        ArrayList<Nodo> nodosVisitables = new ArrayList<>();
        for (Nodo n : actual.getNodosAdyacentes()) {
            if (!nodosCerrados.contains(n) && !cola.contains(n)) {
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
        cola = new ArrayList<>();
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
        System.out.printf(Util.FORMATO_TABLA, "Amplitud", origen.getNombre() + "-" + destino.getNombre(), numNodosVisitados, numIteraciones, str_tiempo, str_camino, str_recorrido);
    }

    @Override
    public void mostrarResultadosEspecificos() {
        nodosVisitados = (isEncontrado) ? nodosVisitados : "No existe camino!";
        System.out.println("\n|BUSQUEDA AMPLITUD|");
        System.out.println("Recorrido Amplitud " + origen.getNombre() + "|" + destino.getNombre() + " : " + nodosVisitados);
        System.out.println("\n***Iteraciones***\n" + iteraciones);
    }

}
