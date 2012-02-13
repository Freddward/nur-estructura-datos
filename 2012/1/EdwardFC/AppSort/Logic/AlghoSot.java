/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Main.Controller;
import java.util.Observable;
import org.apache.log4j.Logger;

/**
 *
 * @author EdwardFC
 */
public class AlghoSot extends Observable {

    private int[] array;
    public static final int cant = 200;
    private static Logger logger = Logger.getLogger(Controller.class);

    public AlghoSot() {
        array = new int[cant];

    }

    public void start() {
        logger.info("Se inicializó el arreglo");
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        disorder();
    }

    public int[] getArray() {
        return array;
    }

    public void disorder() {
        for (int i = getArray().length - 1; i >= 0; i--) {
            int x = (int) (Math.random() * (i + 1));
            int aux = getArray()[i];
            getArray()[i] = getArray()[x];
            this.setChanged();
            this.notifyObservers();
            getArray()[x] = aux;
        }
        logger.info("Se desordenó el arreglo");
    }
}
