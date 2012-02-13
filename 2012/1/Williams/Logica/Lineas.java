/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;


/**
 *
 * @author Williams
 */
public class Lineas extends Observable {
     static Vector<MoldeLinea> lineas;

    public Lineas() {
        this.lineas = new Vector();
        
    }
    public Vector<MoldeLinea> getLineas() {
         this.setChanged();
          this.notifyObservers();  	
        return lineas;
    }
     public void io(Vector<MoldeLinea> li) {
        this.lineas=li;
          this.setChanged();
          this.notifyObservers();
    }
   
    public void agregarLineas(MoldeLinea li) {
        this.lineas.add(li);
        this.setChanged();
          this.notifyObservers();
    }  

}
