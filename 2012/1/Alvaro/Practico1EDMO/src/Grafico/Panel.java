/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import logica1.Arreglo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author AlvaroPasquierTufiño
 */
public class Panel extends JPanel implements Observer
{
    public int aux;
    private Arreglo objarreglo;

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }
    
    public Panel(Arreglo objArr)
    {
        objarreglo=objArr;
      
    }
    
   public void paintComponent(Graphics g)
   {
      
       super.paintComponent(g);
       this.setBackground(Color.black);
       this.setSize(600,600);
       int i=0;
      /* for(int x=0;x<150;x++)
       {
           g.drawLine(0, i, x, i);
           i+=4;
       }*/
       for (int j:objarreglo.getArr())
       {
           g.drawLine(0, i, j, i);
           g.setColor(Color.yellow);
           i+=2;
       }
       repaint();
               
   }
    public void update(Observable o, Object arg)
    {
        this.repaint();
    }
}
