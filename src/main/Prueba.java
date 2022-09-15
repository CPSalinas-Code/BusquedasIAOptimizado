package main;

import busquedas.Busqueda;
import busquedas.ciegas.BusquedaAmplitud;
import busquedas.ciegas.BusquedaProfundidad;
import grafo.Grafo;
import grafo.Nodo;
import javax.swing.JOptionPane;

public class Prueba {

    public static void main(String[] args) {
        try {
            Grafo grafo = Aplicacion.getGrafoControlado();
            Nodo origen = grafo.getNodo("A");
            Nodo destino = grafo.getNodo("L");

            Aplicacion.ejecutarTodasLasBusquedas(grafo, origen, destino);
            //Aplicacion.ejecutarBusquedasCiegas(grafo, origen, destino);
        } catch (Exception ex) {
            System.out.println("error!");
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (StackOverflowError so) {
            System.out.println("stack overflow!");
            JOptionPane.showMessageDialog(null, so.getMessage());
        }
    }

}
