/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.*;
import org.apache.log4j.Logger;
/**
 *
 * @author Williams
 */
public class Quickshort  {
       // private static Logger logger = Logger.getLogger(Pantalla.class);
        private Lineas lin;
	public Quickshort(Lineas L){
          //  this.logger.info("Se inicio metodo QuickShort");
            lin=L;
            this.Ordena();
	}
	
	public void Ordena() {
		
		for ( int increment = lin.getLineas().size()/ 2; increment > 0; increment =(increment == 2 ? 1 : (int) Math.round(increment / 2.2)))
		{
			for (int i = increment; i < lin.getLineas().size(); i++)
			{
					for (int j = i; j >= increment && lin.getLineas().get(j - increment).getX2() >lin.getLineas().get(j).getX2(); j -= increment)
					{
						int temp = lin.getLineas().get(j).getX2();
						lin.getLineas().get(j).setX2(lin.getLineas().get(j - increment).getX2());
						lin.getLineas().get(j - increment).setX2(temp);
					}
			}
		}
	}
}
