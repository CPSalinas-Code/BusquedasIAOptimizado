package busquedas.ciegas;

import busquedas.*;
import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;
import util.Util;

public class BusquedaProfundidadIterativa extends BusquedaCiegas {

    private ArrayList<Nodo> nodosAbiertos;
    private ArrayList<Nodo> nodosCerrados;
    
    private int profundidad;
    private String infoRecorrido;
    
    private int limiteProfundidad;
    private boolean profundidadMaxAlcanzada;                      // Registra si se ha alcanzado el limite de profundidad
    
    public BusquedaProfundidadIterativa(Grafo grafo) {
        super(grafo);
        super.nombre = "Profundidad iterativa";
        this.limiteProfundidad = 20;
    }

    @Override
    public void buscar(Nodo[][] busquedas) {
        buscar(busquedas, limiteProfundidad);
    }    
    
    public void buscar(Nodo[][] busquedas, int profundidad) {
        for (int i = 0; i < busquedas.length; i++) {
            buscar(busquedas[i][0], busquedas[i][1], profundidad);
            mostrarResultadosEnTabla();
        }
    }

    @Override
    public void buscar(Nodo origen, Nodo destino) {
        buscar(origen, destino, limiteProfundidad);
    }
    
    public void buscar(Nodo origen, Nodo destino, int limiteProfundidad) {
        super.origen = origen;
        super.destino = destino;
        inicializarVariables();
        tiempoEjecucionInicial = System.currentTimeMillis();
        profundidad = 0;
        while (profundidad <= limiteProfundidad) {
            nodosAbiertos = new ArrayList<>();
            nodosCerrados = new ArrayList<>();
            
            //iteraciones += "\nProfundidad: "+profundidad+"\n";
            nodosVisitados = origen.getNombre() + " ";
            //iteraciones += nodosVisitados;            
            
            nodosAbiertos.add(origen);
            Nodo resultado = busquedaProfundidadLimitada(origen, destino, profundidad);
            infoRecorrido += "Nivel "+profundidad+": "+nodosVisitados+"  ";                 // Para presentar en tabla
            if (resultado != null && resultado.getNombre().equals(destino.getNombre())) {
                break;
            }            
            profundidad += 1;    
        }
        tiempoEjecucionFinal = System.currentTimeMillis();
        
        if(profundidad >= limiteProfundidad){
            profundidadMaxAlcanzada = true;
        }
        //mostrarResultados(origen, destino);        
    }

    private Nodo busquedaProfundidadLimitada(Nodo nodo, Nodo destino, int profundidad) {
        numNodosVisitados++;
        numIteraciones++;
        if (profundidad == 0 && nodo.getNombre().equals(destino.getNombre())) {
            nodosCerrados.add(nodo);
            isEncontrado = true;
            return nodo;
        } else if (profundidad > 0) {
            nodosCerrados.add(nodo);
            for (Nodo hijo : getNodosAdyacentesVisitables(nodo)) {
                nodosVisitados += hijo.getNombre() + " ";
                //iteraciones += "\n"+nodosVisitados; 
                
                nodosAbiertos.add(hijo);
                Nodo resultado = busquedaProfundidadLimitada(hijo, destino, profundidad - 1);   
                if (resultado != null) {
                    return resultado;
                }
            }
            return null;
        } else {
            return null;
        }
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
    
    @Override
    protected void inicializarVariables() {
        nodosAbiertos = new ArrayList<>();
        nodosCerrados = new ArrayList<>();
        iteraciones = "";
        nodosVisitados = "";
        infoRecorrido = "";
        numNodosVisitados = 0;
        numIteraciones = 0;
        tiempoEjecucionFinal = 0;
        tiempoEjecucionInicial = 0;
        isEncontrado = false;
        profundidadMaxAlcanzada = false;
    }

    @Override
    public void mostrarResultados(Nodo origen, Nodo destino) {}
    
    @Override
    public void mostrarResultadosEnTabla(){        
        String str_camino = isEncontrado ? "" : "!No existe camino!";
        str_camino = (profundidadMaxAlcanzada) ? "!Nivel "+limiteProfundidad+" max alcanzado!" : str_camino;
        String str_recorrido = isEncontrado ? infoRecorrido : infoRecorrido+"(-)";
        String str_tiempo = (tiempoEjecucionFinal - tiempoEjecucionInicial) + "ms"; 
        System.out.printf(Util.FORMATO_TABLA, "P. Iterativa", origen.getNombre() + "-" + destino.getNombre(), numNodosVisitados, numIteraciones, str_tiempo, str_camino, str_recorrido);
    }

    @Override
    public void mostrarResultadosEspecificos() {
        nodosVisitados = (isEncontrado) ? nodosVisitados : "No existe camino!";
        System.out.println("\n\n|BUSQUEDA PROFUNDIDAD ITERATIVA|");
        System.out.println("Recorrido Iterativo " + origen.getNombre() + "|" + destino.getNombre() + " : " + nodosVisitados);
        System.out.println("Profundidad          : " + profundidad);
        System.out.println("\n***Iteraciones***\n" + iteraciones);
    }

}
