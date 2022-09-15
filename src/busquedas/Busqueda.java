package busquedas;

import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;

public abstract class Busqueda {

    protected String nombre;
    
    protected Grafo grafo;
    protected Nodo[][] destinos;
    protected Nodo origen;
    protected Nodo destino;

    protected String nodosVisitados;
    protected String iteraciones;
    protected boolean isEncontrado;

    protected long tiempoEjecucionInicial;
    protected long tiempoEjecucionFinal;
    protected int numNodosVisitados;
    protected int numIteraciones;

    public Busqueda(Grafo grafo) {
        this.grafo = grafo;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public Nodo[][] getDestinos() {
        return destinos;
    }

    public void setDestinos(Nodo[][] destinos) {
        this.destinos = destinos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    protected void imprimirLista(ArrayList<Nodo> lista) {
        for (Nodo n : lista) {
            System.out.print(n.getNombre() + " ");
        }
        System.out.println("");
    }
    
    protected String toStringLista(ArrayList<Nodo> lista) {
        String str = "";
        for (Nodo n : lista) {
            str += n.getNombre() + " ";
        }
        return str;
    }

    public abstract void buscar(Nodo[][] busquedas);

    public abstract void buscar(Nodo origen, Nodo destino);

    protected abstract void inicializarVariables();

    protected abstract void guardarInfoDeNodoVisitado(Nodo nodo);

    protected abstract void guardarInfoDeIteracion(ArrayList<Nodo> nodos);

    protected abstract void guardarInfoDeIteracion(Nodo nodo);

    public abstract void mostrarResultados(Nodo origen, Nodo destino);

    public abstract void mostrarResultadosEnTabla();

    public abstract void mostrarResultadosEspecificos();

}
