/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Observable;

/**
 *
 * @author EdwardFC
 */
public class QuickSort extends Observable {

  
    public QuickSort() {
        super();
    }

    public void sort(int arr[], int low, int high) {
        int i = low;
        int j = high;
        int pivot = arr[(low + high) / 2];
        do {

            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int aux = arr[i];
                arr[i] = arr[j];
                arr[j] = aux;
                this.setChanged();
                this.notifyObservers();
                i++;
                j--;
            }
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                ;
            }

        } while (i <= j);

        if (low < j) {
            sort(arr, low, j);
        }
        if (high > i) {
            sort(arr, i, high);
        }

    }
}
