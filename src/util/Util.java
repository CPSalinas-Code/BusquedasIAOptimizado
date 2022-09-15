package util;

import grafo.Camino;
import grafo.Grafo;
import grafo.Nodo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Util {

    public int numMaxIteraciones = 10000;
    public static String FORMATO_TABLA = "%-20s%-20s%-20s%-20s%-20s%-50s%-20s\n";

    public static void mostrarCabeceraTablaResultados() {
        System.out.printf(FORMATO_TABLA, "ALGORITMO", "ORIGEN-DESTINO", "NODOS VISITADOS", "ITERACIONES", "TIEMPO", "CAMINO","RECORRIDO");
    }
    
    public static int getNumMaxIteraciones(){
        return 10000;
    }

    public static int aleatorio(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    public static void respaldarGrafoSerializadoEnFichero(Grafo grafo, String nombreFichero) throws IOException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreFichero));
            out.writeObject(grafo);
        } catch (IOException ex) {
            throw new IOException("!Error al intentar respaldar el grafo: IOException!");
        }
    }
    
    public static Grafo leerGrafoSerializadoDeFichero(String nombreFichero) throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreFichero));
            return (Grafo) in.readObject();
        } catch (IOException ex) {
            throw new Exception("!Error al intentar recuperar el grafo: IOException!");
        } catch (ClassNotFoundException ex) {
            throw new Exception("!Error al intentar respaldar el grafo: ClassNotFoundException!");
        }
    }

    public static Grafo leerGrafoDeFichero(String nombreFichero) throws Exception {
        //Lee ficheros con formato H:20,A:14:0,C:16:0
        Grafo grafo = new Grafo();
        File f = new File(nombreFichero);
        Scanner s;
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                String infoNodos[] = linea.split(",");
                if (infoNodos.length > 0) {
                    String datos[] = infoNodos[0].split(":");
                    Nodo cabecera;
                    if (!grafo.contiene(datos[0])) {
                        cabecera = new Nodo(datos[0], Integer.parseInt(datos[1]));
                        grafo.agregarNodos(cabecera);
                    } else {
                        cabecera = grafo.getNodo(datos[0]);
                    }
                    for (int i = 1; i < infoNodos.length; i++) {
                        Nodo nodo = null;
                        datos = infoNodos[i].split(":");
                        if (!grafo.contiene(datos[0])) {
                            nodo = new Nodo(datos[0], Integer.parseInt(datos[1]));
                            grafo.agregarNodos(nodo);
                        } else {
                            nodo = grafo.getNodo(datos[0]);
                        }
                        cabecera.agregarCaminos(new Camino(nodo, Integer.parseInt(datos[2])));
                    }
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new Exception("No se ha enconrado el fichero \""+nombreFichero+"\"");
        }
        return grafo;
    }

    
    public static Grafo leerGrafoDeFichero2(String nombreFichero) throws Exception {
        //Lee ficheros con formato Padre,Hijo
        Grafo grafo = new Grafo();
        File f = new File(nombreFichero);
        Scanner s;
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                String datos[] = linea.split(",");
                if (datos.length > 0) {
                    Nodo cabecera;
                    if (!grafo.contiene(datos[0])) {
                        cabecera = new Nodo(datos[0], 0);
                        grafo.agregarNodos(cabecera);
                    } else {
                        cabecera = grafo.getNodo(datos[0]);
                    }
                    Nodo hijo = null;
                    if (!grafo.contiene(datos[1])) {
                        hijo = new Nodo(datos[1], 0);
                        grafo.agregarNodos(hijo);
                    } else {
                        hijo = grafo.getNodo(datos[1]);
                    }
                    cabecera.agregarCaminos(new Camino(hijo, 0));
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new Exception("No se ha enconrado el fichero \""+nombreFichero+"\"");
        }
        return grafo;
    }
    
    public static Grafo getGrafoControlado() {
        Nodo H = new Nodo("H");
        Nodo A = new Nodo("A");
        Nodo B = new Nodo("B");
        Nodo C = new Nodo("C");
        Nodo D = new Nodo("D");
        Nodo E = new Nodo("E");
        Nodo F = new Nodo("F");
        Nodo G = new Nodo("G");
        Nodo J = new Nodo("J");
        Nodo K = new Nodo("K");
        Nodo L = new Nodo("L");

        H.agregarCaminos(new Camino(A), new Camino(B), new Camino(C));
        A.agregarCaminos(new Camino(D), new Camino(E));
        B.agregarCaminos(new Camino(F));
        C.agregarCaminos(new Camino(G), new Camino(J));
        D.agregarCaminos(new Camino(K), new Camino(L));

        Grafo grafo = new Grafo();
        grafo.agregarNodos(H, A, B, C, D, E, F, G, J, K, L);
        return grafo;
    }

    public static Grafo getGrafoControlado2() {        
        Nodo H = new Nodo("H", 20);
        Nodo A = new Nodo("A", 14);
        Nodo C = new Nodo("C", 16);
        Nodo D = new Nodo("D", 9);
        Nodo E = new Nodo("E", 5);
        Nodo M = new Nodo("M", 10);
        Nodo G = new Nodo("G", 4);
        Nodo J = new Nodo("J", 8);
        Nodo K = new Nodo("K", 3);
        Nodo L = new Nodo("L", 6);
        Nodo N = new Nodo("N", 1);
        Nodo B = new Nodo("B", 3);
        Nodo F = new Nodo("F", 0);

        H.agregarCaminos(new Camino(A, 0), new Camino(C, 0));
        A.agregarCaminos(new Camino(D, 1), new Camino(E, 1), new Camino(M, 1));
        C.agregarCaminos(new Camino(G, 1), new Camino(J, 1));
        D.agregarCaminos(new Camino(K, 2), new Camino(L, 2));
        M.agregarCaminos(new Camino(N, 2));
        G.agregarCaminos(new Camino(B, 2));
        L.agregarCaminos(new Camino(F, 3));
        N.agregarCaminos(new Camino(F, 2));
        B.agregarCaminos(new Camino(F, 1));
        
        Grafo grafo = new Grafo();
        grafo.agregarNodos(H, A, C, D, E, M, G, J, K, L, N, B, F);
        return grafo;
    }

    public static Grafo getGrafoExamen() {        
        Nodo A = new Nodo("A", 40);
        Nodo B = new Nodo("B", 20);
        Nodo C = new Nodo("C", 40);
        Nodo D = new Nodo("D", 100);
        Nodo E = new Nodo("E", 110);
        Nodo F = new Nodo("F", 20);
        Nodo G = new Nodo("G", 0);
        Nodo H = new Nodo("H", 10);
        Nodo I = new Nodo("I", 0);

        A.agregarCaminos(new Camino(B, 10), new Camino(D, 20), new Camino(E, 20));
        B.agregarCaminos(new Camino(F, 100), new Camino(C, 20));
        C.agregarCaminos(new Camino(F, 80));
        D.agregarCaminos(new Camino(H, 20));
        E.agregarCaminos(new Camino(G, 1));
        F.agregarCaminos(new Camino(H, 25));
        H.agregarCaminos(new Camino(I, 5));
        
        Grafo grafo = new Grafo();
        grafo.agregarNodos(A,B,C,D,E,F,G,H,I);
        return grafo;
    }
    
    public static Grafo getGrafoControladoRevisionTrabajoCiegas(){
        Nodo A = new Nodo("A");
        Nodo D = new Nodo("D");
        Nodo F = new Nodo("F");
        Nodo G = new Nodo("G");
        Nodo J = new Nodo("J");
        Nodo H = new Nodo("H");
        Nodo C = new Nodo("C");
        Nodo E = new Nodo("E");
        Nodo K = new Nodo("K");
        Nodo B = new Nodo("B");
        Nodo Z = new Nodo("Z");
        Nodo W = new Nodo("W");
        Nodo L = new Nodo("L");
           
        A.agregarCaminos(new Camino(D,1),new Camino(F,1),new Camino(G,1));
        D.agregarCaminos(new Camino(J,2),new Camino(H,2));
        F.agregarCaminos(new Camino(C,2),new Camino(E,2));
        J.agregarCaminos(new Camino(K,3));
        H.agregarCaminos(new Camino(B,3));
        E.agregarCaminos(new Camino(Z,3),new Camino(W,3));
        K.agregarCaminos(new Camino(L,4));
        
        Grafo grafo = new Grafo();
        grafo.agregarNodos(A,D,F,G,J,H,C,E,K,B,Z,W,L);
        return grafo;
    }
    
    public static Grafo getGrafoControladoRevisionTrabajoFinal(){
        Nodo S = new Nodo("S",8);
        Nodo C = new Nodo("C",5);
        Nodo E = new Nodo("E",6);
        Nodo F = new Nodo("F",6);
        Nodo B = new Nodo("B",3);
        Nodo D = new Nodo("D",4);
        Nodo A = new Nodo("A",2);
        Nodo Z = new Nodo("Z",1);
        Nodo G = new Nodo("G",0);
        
        S.agregarCaminos(new Camino(C,3),new Camino(E,2),new Camino(F,1));
        C.agregarCaminos(new Camino(A,1),new Camino(B,3),new Camino(E,5));
        F.agregarCaminos(new Camino(B,5),new Camino(D,4));
        B.agregarCaminos(new Camino(A,1),new Camino(Z,3),new Camino(D,2));
        D.agregarCaminos(new Camino(Z,4));
        A.agregarCaminos(new Camino(G,7));
        Z.agregarCaminos(new Camino(G,1));
        
        Grafo grafo = new Grafo();
        grafo.agregarNodos(S,C,E,F,B,D,A,Z,G);
        
        return grafo;
    }
}
