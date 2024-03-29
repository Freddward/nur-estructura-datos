package TC;

import java.util.Observable;

import org.apache.log4j.Logger;

public class Arreglos extends Observable {

    private Logger logger = Logger.getRootLogger();
    private int[] arreglo; 
    private int tamMax = 500;

    public Arreglos(int tamaņoArreglo) {
    
        if (tamaņoArreglo < 1) {
            logger.error("El tamaņo del arreglo es menor a 1");
            logger.info("El tamaņo del arreglo no es correcto ");
        }
        if (tamaņoArreglo > tamMax) {
            logger.error("El tamaņo del arreglo es muy grande");
            tamaņoArreglo = tamMax;
            logger.info("Tamaņo se cambio al maximo posible");            
        }
        arreglo = new int[tamaņoArreglo];

        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = i + 1;   
        }
        desordenar(); 
       
    }

    public void desordenar() {
        if (getArreglo() != null) {
            for (int i = 0; i < arreglo.length; i++) {

                int nuevoValor = (int)(Math.random() * arreglo.length);
                int aux = arreglo[i];
                arreglo[i] = arreglo[nuevoValor];
                arreglo[nuevoValor] = aux;
                logger.info("se desordeno correctamente");
            }
        } else {
            logger.info("se ingreso un valor nulo");
        }
    }


    public void notificacion() {
        this.setChanged();
        this.notifyObservers();
    }


    public void setArreglo(int[] arreglo) {
        this.arreglo = arreglo;
    }

    public int[] getArreglo() {
        return arreglo;
    }
}
