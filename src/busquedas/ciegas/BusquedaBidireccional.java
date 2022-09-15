package busquedas.ciegas;

import busquedas.*;
import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;
import java.util.Collections;
import util.Util;

public class BusquedaBidireccional extends BusquedaCiegas {

    private static String recorridoBidireccional;
    private static String recorridoAbajo;
    private static String recorridoArriba;

    private static Nodo actualAbajo;
    private static Nodo actualArriba;

    private static ArrayList<Nodo> nodosCerradosHaciaAbajo;    // Nodos que ya fueron revisados en el recorrido hacia abajo
    private static ArrayList<Nodo> nodosCerradosHaciaArriba;   // Nodos que ya fueron revisados en el recorrido hacia arriba
    private static ArrayList<Nodo> nodosAbiertosHaciaAbajo;    // Guarda el recorrido hacia abajo
    private static ArrayList<Nodo> nodosAbiertosHaciaArriba;   // Guarda el recorrido hacia arriba
    private static ArrayList<Nodo> listaCamino;                // Guarda el camino encontrado

    boolean isFinalAbajo = false;                               // Controla cuando termina el recorrido hacia abajo
    boolean isFinalArriba = false;                              // Controla cuando termina el recorrido hacia arriba
    boolean isCruce = false;                                    // Verifica si existe un cruce, es decir, si se encontro un camino

    public BusquedaBidireccional(Grafo grafo) {
        super(grafo);
        super.nombre = "Bidireccional";
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
        tiempoEjecucionInicial = System.currentTimeMillis();    // Para medir el tiempo de ejecucion

        nodosAbiertosHaciaAbajo.add(origen);
        nodosAbiertosHaciaArriba.add(destino);

        actualAbajo = nodosAbiertosHaciaAbajo.get(0);
        actualArriba = nodosAbiertosHaciaArriba.get(0);
        //guardarInfoIteraciones(nodosAbiertosHaciaAbajo, "abajo");
        //guardarInfoIteraciones(nodosAbiertosHaciaArriba, "arriba");

        while (!isFinalAbajo || !isFinalArriba) {
            numIteraciones++;
            if (!isFinalAbajo) {
                busquedaHaciaAbajo(destino);                    //De arriba hacia abajo con busqueda en amplitud
            }

            if (!isFinalArriba) {
                busquedaHaciaArriba(origen);                    //Desde abajo hacia arriba con busqueda amplitud al reves
            }
        }
        tiempoEjecucionFinal = System.currentTimeMillis();      // Para calcular tiempo de ejecucion

        recorridoAbajo = generarRecorrido(nodosCerradosHaciaAbajo);   // Convierte a String la lista de nodos en el recorrido
        recorridoArriba = generarRecorrido(nodosCerradosHaciaArriba);
        if (isCruce) {
            Collections.reverse(nodosCerradosHaciaArriba);
            nodosCerradosHaciaArriba.remove(0);
            listaCamino.addAll(nodosCerradosHaciaAbajo);
            listaCamino.addAll(nodosCerradosHaciaArriba);       // Se genera la lista que tiene los caminos juntando los nodos revisados       
        }
        recorridoBidireccional = generarRecorrido(listaCamino);    // Convierte a String la lista de caminos

        //mostrarResultados(origen,destino);
    }

    private void busquedaHaciaAbajo(Nodo destino) {
        numNodosVisitados++;
        if (actualAbajo.getNombre().equals(destino.getNombre())) {
            nodosCerradosHaciaAbajo.add(actualAbajo);
            isFinalAbajo = true;
        } else if (!nodosAbiertosHaciaAbajo.isEmpty()) {
            nodosCerradosHaciaAbajo.add(actualAbajo);
            nodosAbiertosHaciaAbajo.addAll(getNodosAdyacentesVisitablesHaciaAbajo(actualAbajo));
            nodosAbiertosHaciaAbajo.remove(0);
            //guardarInfoIteraciones(nodosAbiertosHaciaAbajo, "abajo");
            if (nodosAbiertosHaciaAbajo.size() > 0) {
                actualAbajo = nodosAbiertosHaciaAbajo.get(0);
            } else {                                            // Si entra, significa que la cola ya está vacia
                isFinalAbajo = true;
            }
        }
        revisarExistenciaDeCruce();                             //Si ay un cruce, entonces hay un camino
    }

    private void busquedaHaciaArriba(Nodo origen) {
        numNodosVisitados++;
        if (actualArriba.getNombre().equals(origen.getNombre())) {
            nodosCerradosHaciaArriba.add(actualArriba);
            isFinalArriba = true;
        } else if (!nodosAbiertosHaciaArriba.isEmpty()) {
            nodosCerradosHaciaArriba.add(actualArriba);
            nodosAbiertosHaciaArriba.addAll(getNodosPredecesoresVisitablesHaciaArriba(actualArriba));
            nodosAbiertosHaciaArriba.remove(0);
            //guardarInfoIteraciones(nodosAbiertosHaciaArriba, "arriba");
            if (nodosAbiertosHaciaArriba.size() > 0) {
                actualArriba = nodosAbiertosHaciaArriba.get(0);
            } else {                                            // Si entra, significa que la cola ya está vacia
                isFinalArriba = true;
            }
        }
        revisarExistenciaDeCruce();                             //Si ay un cruce, entonces hay un camino
    }

    private void revisarExistenciaDeCruce() {
        for (Nodo nodo : nodosCerradosHaciaAbajo) {
            if (nodosCerradosHaciaArriba.contains(nodo)) {
                isFinalAbajo = true;
                isFinalArriba = true;
                isCruce = true;
                break;
            }
        }
    }

    private ArrayList<Nodo> getNodosAdyacentesVisitablesHaciaAbajo(Nodo actual) {     // Devuelve una lista de nodos adyacentes al actual que aun no han sido visitados y que aun no han sido considerados para la cola
        ArrayList<Nodo> nodosVisitables = new ArrayList<>();
        for (Nodo n : actual.getNodosAdyacentes()) {
            if (!nodosCerradosHaciaAbajo.contains(n) && !nodosAbiertosHaciaAbajo.contains(n)) {
                nodosVisitables.add(n);
            }
        }
        return nodosVisitables;
    }
    
    private ArrayList<Nodo> getNodosPredecesoresVisitablesHaciaArriba(Nodo actual) {     // Devuelve una lista de nodos predecesores al actual que aun no han sido visitados y que aun no han sido considerados para la cola
        ArrayList<Nodo> nodosVisitables = new ArrayList<>();
        for (Nodo n : actual.getPredecesores()) {
            if (!nodosCerradosHaciaArriba.contains(n) && !nodosAbiertosHaciaArriba.contains(n)) {
                nodosVisitables.add(n);
            }
        }
        return nodosVisitables;
    }
    
    private String generarRecorrido(ArrayList<Nodo> lista) {
        String camino = "";
        if (!lista.isEmpty()) {
            for (Nodo n : lista) {
                camino += n.getNombre() + " ";
            }
        } else {
            camino = "No existe camino!";
        }
        return camino;
    }

    private void guardarInfoIteraciones(ArrayList<Nodo> nodos, String direccion) {
        if (direccion.equals("abajo")) {
            iteraciones += "\nCola hacia abajo  : ";
        } else {
            iteraciones += "Cola hacia arriba : ";
        }
        guardarInfoDeIteracion(nodos);
    }

    @Override
    protected void inicializarVariables() {
        numNodosVisitados = numIteraciones = 0;
        iteraciones = "";
        recorridoBidireccional = "";
        nodosAbiertosHaciaAbajo = new ArrayList<>();
        nodosAbiertosHaciaArriba = new ArrayList<>();
        nodosCerradosHaciaAbajo = new ArrayList<>();
        nodosCerradosHaciaArriba = new ArrayList<>();
        listaCamino = new ArrayList<>();
    }

    @Override
    public void mostrarResultados(Nodo origen, Nodo destino) {}

    @Override
    public void mostrarResultadosEnTabla() {
        String str_camino = isCruce ? "" : "!No existe camino!";
        String str_recorrido = "Adelante: "+recorridoAbajo+" Atras: "+recorridoArriba;
        String str_tiempo = (tiempoEjecucionFinal - tiempoEjecucionInicial) + "ms";         
        System.out.printf(Util.FORMATO_TABLA, "Bidireccional", origen.getNombre() + "-" + destino.getNombre(), numNodosVisitados, numIteraciones, str_tiempo, str_camino, str_recorrido);
    }

    @Override
    public void mostrarResultadosEspecificos() {
        System.out.println("\n\n|BUSQUEDA BIDIRECCIONAL|");
        System.out.println("Nodos revisados hacia abajo  : " + recorridoAbajo);
        System.out.println("Nodos revisados hacia arriba : " + recorridoArriba);
        System.out.println("\n***Iteraciones***\n" + iteraciones);
    }
}
