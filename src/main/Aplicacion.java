package main;

import busquedas.Busqueda;
import busquedas.ciegas.BusquedaAmplitud;
import busquedas.ciegas.BusquedaBidireccional;
import busquedas.ciegas.BusquedaCostoUniforme;
import busquedas.ciegas.BusquedaProfundidad;
import busquedas.ciegas.BusquedaProfundidadIterativa;
import busquedas.heuristicas.BusquedaAStar;
import busquedas.heuristicas.BusquedaAvara;
import busquedas.heuristicas.BusquedaGradiente;
import busquedas.heuristicas.BusquedaPrimeroElMejor;
import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.Util;

public class Aplicacion {

    private static ArrayList<Busqueda> busquedasEjecutadas;

    public static Grafo getGrafoControlado() {
        return Util.getGrafoControladoRevisionTrabajoCiegas();
        //return Util.getGrafoControladoRevisionTrabajoFinal();
    }

    public static Grafo getGrafoAleatorio(int totalNodos, int numMaxAdyacentesPorNodo) {
        System.out.print("\nGenerando grafo aleatorio... ");
        Grafo grafo = new Grafo();
        //grafo.generar(numMaxAdyacentesPorNodo, totalNodos);
        grafo.generar2(numMaxAdyacentesPorNodo, totalNodos);
        System.out.println("Completado!");
        return grafo;
    }

    public static Grafo getGrafoLeidoDeFichero() throws Exception {
        String nombreFichero = "roadNet-CA.csv";
        System.out.print("\nLeyendo grafo del fichero " + nombreFichero + "... ");
        Grafo grafo = Util.leerGrafoDeFichero2(nombreFichero);
        System.out.println("Completado!");
        //grafo.imprimir();
        return grafo;
    }

    public static void ejecutarTodasLasBusquedas(Grafo grafo, Nodo origen, Nodo destino) {
        Busqueda amplitud = new BusquedaAmplitud(grafo);
        Busqueda profundidad = new BusquedaProfundidad(grafo);
        Busqueda p_iterativa = new BusquedaProfundidadIterativa(grafo);
        Busqueda bidireccional = new BusquedaBidireccional(grafo);
        Busqueda c_uniforme = new BusquedaCostoUniforme(grafo);
        Busqueda gradiente = new BusquedaGradiente(grafo);
        Busqueda primeroElMejor = new BusquedaPrimeroElMejor(grafo);
        Busqueda a_estrella = new BusquedaAStar(grafo);
        Busqueda avara = new BusquedaAvara(grafo);

        busquedasEjecutadas = new ArrayList<>();

        // BUSQUEDAS 
        ejecutarBusqueda(amplitud, origen, destino);
        ejecutarBusqueda(profundidad, origen, destino);
        ejecutarBusqueda(p_iterativa, origen, destino);
        ejecutarBusqueda(bidireccional, origen, destino);
        ejecutarBusqueda(c_uniforme, origen, destino);
        ejecutarBusqueda(gradiente, origen, destino);
        ejecutarBusqueda(primeroElMejor, origen, destino);
        ejecutarBusqueda(a_estrella, origen, destino);
        ejecutarBusqueda(avara, origen, destino);

        // SE MUESTRA LA TABLA DE RESULTADOS 
        mostrarResultados();
    }

    public static void ejecutarBusquedasCiegas(Grafo grafo, Nodo origen, Nodo destino) {
        Busqueda amplitud = new BusquedaAmplitud(grafo);
        Busqueda profundidad = new BusquedaProfundidad(grafo);
        Busqueda p_iterativa = new BusquedaProfundidadIterativa(grafo);
        Busqueda bidireccional = new BusquedaBidireccional(grafo);
        Busqueda c_uniforme = new BusquedaCostoUniforme(grafo);

        busquedasEjecutadas = new ArrayList<>();

        // BUSQUEDAS 
        ejecutarBusqueda(amplitud, origen, destino);
        ejecutarBusqueda(profundidad, origen, destino);
        ejecutarBusqueda(p_iterativa, origen, destino);
        ejecutarBusqueda(bidireccional, origen, destino);
        ejecutarBusqueda(c_uniforme, origen, destino);

        // SE MUESTRA LA TABLA DE RESULTADOS 
        mostrarResultados();
    }

    public static void ejecutarBusquedasHeuristicas(Grafo grafo, Nodo origen, Nodo destino) {
        Busqueda gradiente = new BusquedaGradiente(grafo);
        Busqueda primeroElMejor = new BusquedaPrimeroElMejor(grafo);
        Busqueda a_estrella = new BusquedaAStar(grafo);
        Busqueda avara = new BusquedaAvara(grafo);

        busquedasEjecutadas = new ArrayList<>();

        // BUSQUEDAS 
        ejecutarBusqueda(gradiente, origen, destino);
        ejecutarBusqueda(primeroElMejor, origen, destino);
        ejecutarBusqueda(a_estrella, origen, destino);
        ejecutarBusqueda(avara, origen, destino);

        // SE MUESTRA LA TABLA DE RESULTADOS 
        mostrarResultados();
    }

    public static void ejecutarVariasBusquedas(Grafo grafo, Nodo[][] busquedas) {

        Busqueda amplitud = new BusquedaAmplitud(grafo);
        Busqueda profundidad = new BusquedaProfundidad(grafo);
        Busqueda p_iterativa = new BusquedaProfundidadIterativa(grafo);
        Busqueda bidireccional = new BusquedaBidireccional(grafo);
        Busqueda c_uniforme = new BusquedaCostoUniforme(grafo);
        Busqueda gradiente = new BusquedaGradiente(grafo);
        Busqueda primeroElMejor = new BusquedaPrimeroElMejor(grafo);
        Busqueda a_estrella = new BusquedaAStar(grafo);
        Busqueda avara = new BusquedaAvara(grafo);

        // BUSQUEDAS 
        Util.mostrarCabeceraTablaResultados();
        amplitud.buscar(busquedas);
        profundidad.buscar(busquedas);
        p_iterativa.buscar(busquedas);
        //bidireccional.buscar(busquedas);
        c_uniforme.buscar(busquedas);
        gradiente.buscar(busquedas);
        //primeroElMejor.buscar(busquedas);
        //avara.buscar(busquedas);
        //a_estrella.buscar(busquedas);
    }

    private static void ejecutarBusqueda(Busqueda busqueda, Nodo origen, Nodo destino) {
        try {
            System.out.print("Ejecutando búsqueda: " + busqueda.getNombre() + "... ");
            busqueda.buscar(origen, destino);
            busquedasEjecutadas.add(busqueda);
            System.out.println("Completado!");
        } catch (StackOverflowError ex) {
            //ex.printStackTrace();
            System.out.println("Error!");
            JOptionPane.showMessageDialog(null, "No se completó la búsqueda" + "\"" + busqueda.getNombre() + "\": StackOverflowError");
        } catch (OutOfMemoryError ex) {
            System.out.println("Error!");
            JOptionPane.showMessageDialog(null, "No se completó la búsqueda" + "\"" + busqueda.getNombre() + "\": OutOfMemoryError");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void mostrarResultados() {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("TABLA");
        System.out.println("-------------------------------------------------------------------");
        Util.mostrarCabeceraTablaResultados();
        for (Busqueda b : busquedasEjecutadas) {
            b.mostrarResultadosEnTabla();
        }
    }

}
