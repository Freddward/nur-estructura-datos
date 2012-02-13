/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.Observable;
import org.apache.log4j.Logger;

/**
 *
 * @author Sistemas
 */
public class Arreglo extends Observable {

    private static Logger logger = Logger.getLogger(Arreglo.class);
    private int[] arreglo;
    private int[] arregloAux;

    public Arreglo(int n) {
       
        try {
            logger.info("El arreglo recibe el tamaño, para lo cuan debe cumplir con la sigte condicion:");
        if (n >= 10 && n <= 300) {
            arreglo = new int[n];
            arregloAux = new int[arreglo.length];
            this.desordenar();
        }

        } catch (Exception e) {
           if(n < 10)
            logger.error("El tamaño que se paso no cumple el rango minimo permitido (MIN:10 )",e);
            if (n > 300)
              logger.error("El tamaño que se paso no cumple el rango maximo permitido (MAX: 300)",e);
        }
       }

    public int[] getArreglo() {
        return arreglo;
    }

    public void setArreglo(int[] arreglo) {
        this.arreglo = arreglo;
    }

    private void inicializar() {
       logger.info("Se esta llenando el arregloAuxiliar de la siguiente manera :");
        for (int i = 0; i < arreglo.length; i++) {
            //arreglo[i] = i + 1;
            arregloAux[i] = i + 1;
            logger.info("ArregloAuxiliar en pos [" + " ]" + " = " + i);



        }
        this.setChanged();
        this.notifyObservers();
    }

    public void desordenar() {
        logger.info("Se esta llamando al metodo privado inicializar, desde el metodo desordenar");
        inicializar();
        for (int j = 0; j < arreglo.length; j++) {
            int x = (int) (Math.random() * arreglo.length);
            if (arregloAux[x] == 0) {
               logger.error("El Numero aleatorio: " + x +" que se recibio ya existe");
                j = j - 1;
            }
            if (arregloAux[x] != 0) {
                arreglo[j] = arregloAux[x];
                arregloAux[x] = 0;
                logger.info("Arreglo [" + " ]" + " = " + arregloAux[x] );
            }
        }
        this.setChanged();
        this.notifyObservers();
    }
    /* public void desordenar() {
    for (int i = 0; i < arreglo.length; i++) {
    int nuevaPosicion =
    (int) (Math.random() * (double) arreglo.length);
    int swap = arreglo[i];
    arreglo[i] = arreglo[nuevaPosicion];
    arreglo[nuevaPosicion] = swap;
    }
    }*/

    public void imprimirArreglo() {
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print("[ " + arreglo[i] + " ]");

        }
    }

    public String toString() {
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < arreglo.length; i++) {
            resultado.append(arreglo[i] + "  ");
        }
        return resultado.toString();
    }

    public void ordenarQuickSort() {

        int primero = 0;
        int ultimo = arreglo.length - 1;
        logger.info("Este metodo llama al metodo Ordenar Quicksort");
        this.ordenarQuicksort(arreglo, primero, ultimo);
    }

    private void ordenarQuicksort(int[] elarreglo, int primero, int ultimo) {
        int i = primero;
        int j = ultimo;
        int pivote = elarreglo[(primero + ultimo) / 2];
        int auxiliar;

        do {
            while (elarreglo[i] < pivote) {
                i++;
            }
            while (elarreglo[j] > pivote) {
                j--;
            }

            if (i <= j) {
                auxiliar = elarreglo[j];
                elarreglo[j] = elarreglo[i];
                elarreglo[i] = auxiliar;
                i++;
                j--;

                this.setChanged();
                this.notifyObservers();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    ;
                }
            }
        } while (i <= j);

        if (primero < j) {
            ordenarQuicksort(elarreglo, primero, j);
        }
        if (ultimo > i) {
            ordenarQuicksort(elarreglo, i, ultimo);
        }
    }

    public void ordenarSeleccion() {
        logger.info("Se acaba de llamar al metodo de Ordenar Seleccion");
        for (int i = 0; i < arreglo.length; i++) {
            int menor = i;
            for (int j = i + 1; j < arreglo.length; j++) {
                if (arreglo[j] < arreglo[menor]) {
                    menor = j;
                }
            }
            int aux = arreglo[i];
            arreglo[i] = arreglo[menor];
            arreglo[menor] = aux;

            this.setChanged();
            this.notifyObservers();

            try {
                logger.info("Se le esta asignando un tiempo a los Hilos");
                Thread.sleep(100);
            } catch (Exception e) {
                ;
            }
        }
      }

   

}
