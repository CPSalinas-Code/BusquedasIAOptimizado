package busquedas.ciegas;

import busquedas.*;
import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;
import java.util.Collections;
import util.Comparador;
import grafo.NodoInformado;
import util.Util;

public class BusquedaCostoUniforme extends BusquedaCiegas {

    private ArrayList<NodoInformado> nodosAbiertos;
    private ArrayList<Nodo> nodosCerrados;
    private NodoInformado camino;
    
    private int numMaxDeIteraciones;
    private boolean limiteIteracionesAlcanzado;

    public BusquedaCostoUniforme(Grafo grafo) {
        super(grafo);
        super.nombre = "Costo uniforme";
        this.numMaxDeIteraciones = Util.getNumMaxIteraciones();
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

        nodosAbiertos.add(new NodoInformado(origen, new ArrayList<Nodo>(), 0));
        NodoInformado actual = nodosAbiertos.get(0);

        while (!isEncontrado && (numIteraciones <= numMaxDeIteraciones)) {  //Limite de iteraciones en caso de bucle
            numNodosVisitados++;
            numIteraciones++;
            //guardarInfoIteracion(nodosAbiertos);//--
            guardarInfoDeNodoVisitado(actual);

            if (actual.getNodo().getNombre().equals(destino.getNombre())) {
                actual.agregarACamino(actual.getNodo());
                nodosCerrados.add(actual.getNodo());
                isEncontrado = true;
            } else if (!nodosAbiertos.isEmpty()) {
                for (Nodo n : actual.getNodo().getNodosAdyacentes()) {
                    int costoTotal = grafo.getCostoCamino(actual.getNodo(), n) + actual.getCosto();
                    NodoInformado nuevo = new NodoInformado(n, actual.getCamino(), costoTotal);
                    nuevo.agregarACamino(actual.getNodo());
                    nodosAbiertos.add(nuevo);
                }

                nodosCerrados.add(actual.getNodo());
                nodosAbiertos.remove(0);
                Collections.sort(nodosAbiertos, Comparador.porCostoNodoInformado());

                if (nodosAbiertos.size() > 0) {
                    actual = nodosAbiertos.get(0);
                } else {
                    break;
                }
            }
        }
        camino = actual;
        tiempoEjecucionFinal = System.currentTimeMillis();

        if(numIteraciones >= numMaxDeIteraciones){
            limiteIteracionesAlcanzado = true;
        }
        //mostrarResultadosEspecificos();//--
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
        numNodosVisitados = numIteraciones = 0;
        nodosVisitados = iteraciones = "";
        nodosAbiertos = new ArrayList<>();
        nodosCerrados = new ArrayList<>();
        isEncontrado = false;
        limiteIteracionesAlcanzado = false;
    }

    @Override
    public void mostrarResultados(Nodo origen, Nodo destino) {}

    @Override
    public void mostrarResultadosEnTabla() {
        String str_camino = isEncontrado ? toStringLista(camino.getCamino()) : "!No existe camino!";
        str_camino = (limiteIteracionesAlcanzado) ? "!"+numMaxDeIteraciones+" iteraciones alcanzadas!" : str_camino;
        String str_recorrido = isEncontrado ? nodosVisitados : nodosVisitados+" (-)";
        String str_tiempo = (tiempoEjecucionFinal - tiempoEjecucionInicial) + "ms"; 
        System.out.printf(Util.FORMATO_TABLA, "Costo Uniforme", origen.getNombre() + "-" + destino.getNombre(), numNodosVisitados, numIteraciones, str_tiempo, str_camino, str_recorrido);
    }

    @Override
    public void mostrarResultadosEspecificos() {
        String str_camino = isEncontrado ? toStringLista(camino.getCamino()) : "No existe camino!";
        str_camino = (limiteIteracionesAlcanzado) ? "!"+numMaxDeIteraciones+" iteraciones alcanzadas!" : str_camino;
        String str_costo = isEncontrado ? camino.getCosto() + "" : "-";
        System.out.println("\n\n|BUSQUEDA COSTO UNIFORME|");
        System.out.println("Nodo(Costo hasta el nodo)");
        System.out.println("Camino Costo Uniforme     : " + str_camino);
        System.out.println("Costo                     : " + str_costo);
        System.out.println("Nodos visitados           : " + nodosVisitados);
        System.out.println("\n***Iteraciones***\n" + iteraciones);
    }

}
