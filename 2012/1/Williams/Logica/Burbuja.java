/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Williams
 */
public class Burbuja extends Observable {
       // private static Logger logger = Logger.getLogger(Pantalla.class);
        private Lineas lin;
	public Burbuja(Lineas lo) {
         // this.logger.info("Se inicio metodo burbuja");
          lin=lo;
          this.ord();
	}
         public void ord() {
       
             for (int i = 0; i < lin.getLineas().size() ; i++)
		{
                     
			for (int j = i; j < lin.getLineas().size() - 1 ; j++)
			{
				if (lin.getLineas().get(i).getX2() > lin.getLineas().get(j + 1).getX2())
				{
					int temp = lin.getLineas().get(i).getX2();
					lin.getLineas().get(i).setX2(lin.getLineas().get(j + 1).getX2());
					lin.getLineas().get(j + 1).setX2(temp);
                               
                         	}
                                        
        
                                
			}
                       /* try{
                             Thread.sleep(50);
                              
             
                         }
                          catch(Exception e){
                                 
                             }*/
           
		}

          
    }
}