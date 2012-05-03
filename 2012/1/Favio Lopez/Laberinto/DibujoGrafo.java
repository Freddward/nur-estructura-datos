package logica;

import java.awt.Color;
import java.awt.Graphics;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;

import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class DibujoGrafo {

    private int anchoNodo = 30;
    private Grafo<DibujoNodo> grafo;
    private Pila<Grafo.Nodo<DibujoNodo>> ultimoCamino = null;
    private int[][] matrizgrafo;
    private int[] actual = {1, 1};

    private static final Logger logger = Logger.getLogger(DibujoGrafo.class);

    private int maximaFila = 1;
    private int maximaColumna = 1;

    public DibujoGrafo(Grafo<DibujoNodo> elGrafo) {
        grafo = elGrafo;
    }

    public void tamano() {
        maximaFila = getMaximaFila();
        maximaColumna = getMaximaColumna();
        logger.info("se obtuvo tamaño maximo");
    }

    public void dibujar(Graphics gc) {

        maximaFila = getMaximaFila();
        maximaColumna = getMaximaColumna();
        gc.setColor(Color.BLACK);
        gc.fillRect(0, 0, (maximaColumna * anchoNodo)+1, maximaFila * anchoNodo);
        logger.info("se pinto la base de nuestro");

        Iterator<Grafo.Nodo<DibujoNodo>> iter = grafo.getNodos().iterator();
        while (iter.hasNext()) {
            Grafo.Nodo<DibujoNodo> nodo = iter.next();
            nodo.getValor().dibujar(gc, this.anchoNodo);
        }
        logger.info("dihbujamos nhuestros nodos");
    }

    public int getMaximaFila() {
        int maxima = 0;
        Iterator<Grafo.Nodo<DibujoNodo>> iter = grafo.getNodos().iterator();
        while (iter.hasNext()) {
            Grafo.Nodo<DibujoNodo> nodo = iter.next();
            int filaNodo = nodo.getValor().getFila();
            if (filaNodo > maxima)
                maxima = filaNodo;
        }
        return maxima+1;
        
    }

    public int getMaximaColumna() {
        int maxima = 0;
        Iterator<Grafo.Nodo<DibujoNodo>> iter = grafo.getNodos().iterator();
        while (iter.hasNext()) {
            Grafo.Nodo<DibujoNodo> nodo = iter.next();

            int columnaNodo = nodo.getValor().getColumna();

            if (columnaNodo > maxima)
                maxima = columnaNodo;
        }

       //logger.info("Se obtuvo el tamaño de alto de nuestra matriz");
       return maxima+1;
        
    }

    public void setAnchoNodo(int newanchoNodo) {
        this.anchoNodo = newanchoNodo;
    }

    public int getAnchoNodo() {
        return anchoNodo;
    }

    public void llenarMatriz(BufferedReader aFile) throws Exception {
        BufferedReader theReader = null;
        try {
            theReader = aFile;
            String linea = theReader.readLine();
            logger.info("Se lee el archivo txt");
            while (theReader.ready()) {
                Llenarmatriz(linea);
                linea = theReader.readLine();
            }
        } catch (Exception err) {
            logger.error("Error al leer el archivo", err);
            throw new Exception("Error al leer el archivo de arcos, ver logs");
        } finally {
            if (theReader != null)
                theReader.close();
                logger.info("Termino la lectura del archivo");
        }
        logger.info("matriz llenada con valores y sin problemas");
    }

    public void leerNodosLaberinto(BufferedReader aFile) throws Exception {
        BufferedReader theReader = null;
        matrizgrafo = new int[maximaFila][maximaColumna];
        try {
            theReader = aFile;
            String linea = theReader.readLine();
            logger.info("Se lee de archivo txt para los nodos");
            while (theReader.ready()) {
                DibujoNodo unNodoDibujo = new DibujoNodo(linea);
                logger.debug("Cargando nodo " + unNodoDibujo.getNombre() + 
                             " en el grafo");
                grafo.nuevoNodo(unNodoDibujo.getNombre(), unNodoDibujo);
                linea = theReader.readLine();
            }
        } catch (Exception err) {
            logger.error("Error al leer el archivo", err);
            throw new Exception("Error al leer el archivo de arcos, ver logs");
        } finally {
            if (theReader != null)
                theReader.close();
                logger.info("Lectura vacia");
            matrizgrafo = new int[getMaximaFila()][getMaximaColumna()];
            vaciar();
            logger.info("valores de matriz en cero");
        }
        logger.info("Se termino de llenar los nodos");
    }

    private void vaciar() {
        for (int i = 0; i < matrizgrafo.length; i++) {
            for (int j = 0; j < matrizgrafo[0].length; j++) {
                matrizgrafo[i][j] = 0;
            }
        }
        logger.info("valores de matriz en 0");
    }
    private void Mostrar() {
        for (int i = 0; i < matrizgrafo.length; i++) {
            for (int j = 0; j < matrizgrafo[0].length; j++) {
                System.out.print(matrizgrafo[i][j]+" ");
            }
            System.out.println("");
        }
    }
    private void Llenarmatriz(String cadena) {
        String total = "";
        int valor = 0;
        int fila = 0;
        int columna = 0;
        int estado = 0;
        for (int i = 0; i < cadena.length(); i++) {
            String a = "";
            a = "" + cadena.charAt(i);
            if (!a.equals(" ")) {
                total = total + a;
            } else {
            logger.info("el dato ingresado es vacio");
                if (estado == 0) {
                    valor = Integer.parseInt(total);
                    estado++;
                    total = "";
                    logger.info("obtuvimos valor del nodo");
                } else {
                    if (estado == 1) {
                        fila = Integer.parseInt(total);
                        estado++;
                        total = "";
                        logger.info("se obtuvo psicion de la fila de ubicacion del nodo");
                    } else {
                        if (estado == 2) {
                            columna = Integer.parseInt(total);
                            matrizgrafo[fila - 1][columna - 1] = valor;
                            logger.info("se obtuvo posicion de la columna del nodo...matriz complete");
                        }
                    }
                }
            }
        }
        logger.info("Matriz con valores");
    }

    /*private void tamanoMatriz(String cadena) {
        System.out.println(matrizgrafo.length);
        String total = "";
        int valor = 0;
        int fila = 0;
        int columna = 0;
        int estado = 0;
        for (int i = 0; i < cadena.length(); i++) {
            String a = "";
            a = "" + cadena.charAt(i);
            if (!a.equals(" ")) {
                total = total + a;
            } else {
                if (estado == 0) {
                    valor = Integer.parseInt(total);
                    estado++;
                    total = "";
                } else {
                    if (estado == 1) {
                        valor = Integer.parseInt(total);
                        estado++;
                        total = "";
                    } else {
                        if (estado == 2) {
                            valor = Integer.parseInt(total);
                            System.out.println(valor + " " + fila + " " + 
                                               columna);
                            matrizgrafo[fila][columna] = valor;
                        }
                    }
                }
            }
        }
    }*/


    public Grafo<DibujoNodo> getGrafo() {
        return grafo;
    }

    public void leerArcosLaberinto(BufferedReader aFile) throws Exception {
        BufferedReader theReader = null;
        try {
            theReader = aFile;
            String linea = theReader.readLine();
            logger.info("Se lee archivo txt para arcos");
            while (theReader.ready()) {

                StringTokenizer tokens = new StringTokenizer(linea);
                String desde = tokens.nextToken();
                String hasta = tokens.nextToken();

                logger.debug("Lee una arista que va de " + desde + " a " + 
                             hasta);
                grafo.arista(desde, hasta);

                linea = theReader.readLine();
            }
        } catch (Exception err) {
            logger.error("Error al leer el archivo de arcos", err);
            throw new Exception("Error al leer el archivo de arcos, ver logs");
        } finally {
            if (theReader != null)
                theReader.close();
                logger.warn("lectura de archivo vacia");
        }
        logger.info("hCargado de arcos llenado sin problemas");
    }

    /**
     * Revisa todos los nodos del grafo y dependiendo de sus vecinos va colocando
     * los valores de las paredes en el nodo bidujo de cada nodo
     */
    public void calcularParedes() {
        logger.info("Coloca todas las paredes a 0, quiere decir que hay paredes en todo lado");
        Iterator<Grafo.Nodo<DibujoNodo>> iter = 
            this.grafo.getNodos().iterator();
        while (iter.hasNext()) {
            Grafo.Nodo<DibujoNodo> nodo = iter.next();
            nodo.getValor().hayVecinoEn(0);
        }

        logger.info("Coloca las paredes dependiendo de los vecinos");
        iter = this.grafo.getNodos().iterator();
        while (iter.hasNext()) {
            Grafo.Nodo<DibujoNodo> nodo = iter.next();
            nodo.getValor().hayVecinoEn(0);

            Cadena<Grafo.Nodo<DibujoNodo>> vecinos = nodo.getVecinos();
            Iterator<Grafo.Nodo<DibujoNodo>> iterVecino = vecinos.iterator();
            while (iterVecino.hasNext()) {
                Grafo.Nodo<DibujoNodo> vecino = iterVecino.next();
                calcularParedDeVecino(nodo, vecino);
            }
        }
        logger.info("se calcularon las paredes ");
    }

    public void calcularParedDeVecino(Grafo.Nodo<DibujoNodo> nodo, 
                                      Grafo.Nodo<DibujoNodo> vecino) {
        // Si esta a la derecha
        if (nodo.getValor().getFila() == vecino.getValor().getFila() && 
            nodo.getValor().getColumna() < vecino.getValor().getColumna())
            nodo.getValor().hayVecinoEn(DibujoNodo.RIGHT);

        if (nodo.getValor().getFila() == vecino.getValor().getFila() && 
            nodo.getValor().getColumna() > vecino.getValor().getColumna())
            nodo.getValor().hayVecinoEn(DibujoNodo.LEFT);

        if (nodo.getValor().getColumna() == vecino.getValor().getColumna() && 
            nodo.getValor().getFila() < vecino.getValor().getFila())
            nodo.getValor().hayVecinoEn(DibujoNodo.BOTTOM);

        if (nodo.getValor().getColumna() == vecino.getValor().getColumna() && 
            nodo.getValor().getFila() > vecino.getValor().getFila())
            nodo.getValor().hayVecinoEn(DibujoNodo.TOP);
    }

    /**
     * Marca el ultimo camino calculado con el algoritmo. En cada un ode los nodos
     * dibujo se coloca la direccion que toma cada uno para que se recorra por el 
     * buen camino y se puedan ver las flechas correspondientes.
     * @param newultimoCamino
     */
    public void setUltimoCamino(Pila<Grafo.Nodo<DibujoNodo>> newultimoCamino) {
        this.ultimoCamino = newultimoCamino;

        logger.info("Borrando el valor de cualquier camino anterior que habia");
        Iterator<Grafo.Nodo<DibujoNodo>> iter = 
            this.grafo.getNodos().iterator();
        while (iter.hasNext()) {
            Grafo.Nodo<DibujoNodo> nodo = iter.next();
            nodo.getValor().setDireccion(0);
        }

        if (newultimoCamino.tamano() <= 1) {
            logger.warn("No existen al menos dos nodos (inicio y fin) en el camino pasado en parametro");
            return;
        }

        logger.info("Coloca el camino nuevo en el grafo");
        Grafo.Nodo<DibujoNodo> actual = newultimoCamino.pop();
        Grafo.Nodo<DibujoNodo> siguiente = null;
        while (!newultimoCamino.estaVacia()) {
            siguiente = newultimoCamino.pop();

            logger.debug("Haciendo camino entre: " + 
                         actual.getValor().getNombre() + " y " + 
                         siguiente.getValor().getNombre());

            if (actual.getValor().getFila() == 
                siguiente.getValor().getFila() && 
                actual.getValor().getColumna() < 
                siguiente.getValor().getColumna())
                actual.getValor().setDireccion(DibujoNodo.RIGHT);

            if (actual.getValor().getFila() == 
                siguiente.getValor().getFila() && 
                actual.getValor().getColumna() > 
                siguiente.getValor().getColumna())
                actual.getValor().setDireccion(DibujoNodo.LEFT);

            if (actual.getValor().getColumna() == 
                siguiente.getValor().getColumna() && 
                actual.getValor().getFila() < siguiente.getValor().getFila())
                actual.getValor().setDireccion(DibujoNodo.BOTTOM);

            if (actual.getValor().getColumna() == 
                siguiente.getValor().getColumna() && 
                actual.getValor().getFila() > siguiente.getValor().getFila())
                actual.getValor().setDireccion(DibujoNodo.TOP);

            actual = siguiente;
        }
        logger.info("direccion dibujada");
        siguiente.getValor().setDireccion(DibujoNodo.END);
        
    }

    public Pila<Grafo.Nodo<DibujoNodo>> getUltimoCamino() {
        return ultimoCamino;
    }


    public int getMaximaFila1() {
        return maximaFila * anchoNodo + 15;
    }

    public int getMaximaColumna1() {
        return maximaColumna * anchoNodo + 40;
    }

    public void setActual(int[] newactual) {
        this.actual = newactual;
    }

    public boolean setActualPos(int x, int y) {
        int[] aux = { actual[0], actual[1] };
        if (Verificar(x, y)) {
            if (actual[0] == 0 && x == -1) {
                logger.error("choco con una pared");
                x = 0;
            }
            if (actual[1] == 0 && y == -1) {
              logger.error("choco con una pared");
                y = 0;
            }
            if (actual[0] == getMaximaColumna()-1 && x == getMaximaColumna()-1) {
                x = getMaximaColumna()-1 ;
              logger.error("choco con una pared");
            }
            if (actual[1] == getMaximaColumna()-1 && y == getMaximaColumna()-1) {
                    y = getMaximaColumna()-1 ;
              logger.error("No hay camino por ahi");
            }
            actual[0] = x;
            actual[1] = y;
            if (Mover()) {
                return true;
            } else {
                actual = aux;
               logger.info("No se pudo momer");
               return false;
                
            }
        } else {
            actual = aux;
           logger.info("no se pudo mover");
           return false;
            
        }
    }

    private boolean Verificar(int x, int y) {
        if ((actual[0] + x) > -1) {
        logger.info("movida posible");
            if ((actual[1] + y) > -1) {
                   logger.info("movida posible");
                   return true;
                    
                    }else{
               logger.warn("Se acabo el camino enh esa direccion");
               return false;
                
                }
            } else {
               logger.warn("Se acabo el camino enh esa direccion");
               return false;
              
            }
    }

    private boolean Mover() {
        if (matrizgrafo[actual[0]][actual[1]] != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int[] getActual() {
        return actual;
    }

    public int ObtenerValorJugador() {
        return matrizgrafo[actual[0]][actual[1]];
    }
}
