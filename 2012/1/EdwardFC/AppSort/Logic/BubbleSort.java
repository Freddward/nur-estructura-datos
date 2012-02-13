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
public class BubbleSort extends Observable {
    
    private static Logger logger = Logger.getLogger(Controller.class);
    
    public BubbleSort() {
        super();
    }
    
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    this.setChanged();
                    this.notifyObservers();
                    
                }
            }
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        logger.info("Se ordenó el arreglo por el método de Burbuja");
        
    }
}
