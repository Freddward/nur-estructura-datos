
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import sun.java2d.loops.DrawRect;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Williams
 */
public class MiPanel  extends JPanel implements Observer{
   private Grafo<DibujaClase> grafo;

    public MiPanel(Grafo<DibujaClase> grafo) {
        this.grafo = grafo;
    }
     @Override
     public void paint(Graphics g) {
        super.paint(g);
         Iterator<Nodo<DibujaClase>> i = grafo.getNodos().values().iterator();
        while(i.hasNext()){ 
           Nodo<DibujaClase> n = i.next();
           int r=15;
           
       
            Iterator<Nodo<DibujaClase>> relacion =n.getConexiones().values().iterator();
            while(relacion.hasNext()){ 
                Nodo<DibujaClase> relacionesClase =relacion.next();
                g.drawLine(n.getContenido().puntoMedioX(), n.getContenido().puntoMedioY(), relacionesClase.getContenido().puntoMedioX(), relacionesClase.getContenido().puntoMedioY());
            }
           
            
           for (int j = 0; j < n.getContenido().getAtributo().size(); j++) {
                 if(r>=n.getContenido().getRectanguloAtributo().height)n.getContenido().AltoAtributo(15); 
                g.drawString(n.getContenido().getAtributo().get(j),(int)n.getContenido().getRectanguloAtributo().getX()+15,(int)n.getContenido().getRectanguloAtributo().getY()+r);
                 r+=15;
            }
            r=15;
            for (int j = 0; j < n.getContenido().getMetodos().size(); j++) {
                 if(r>=n.getContenido().getRectanguloMetodo().height)n.getContenido().AltoMetodo(15); 
                g.drawString(n.getContenido().getMetodos().get(j),(int)n.getContenido().getRectanguloMetodo().getX()+15,(int)n.getContenido().getRectanguloMetodo().getY()+r);
                 
                 r+=15;
            }
            g.drawString(n.getContenido().getNombreClase(),n.getContenido().getX1()+60,n.getContenido().getY1()+15);
            g.drawRect((int)n.getContenido().getRectanguloCabecera().getX(),(int)n.getContenido().getRectanguloCabecera().getY(),(int) n.getContenido().getRectanguloCabecera().getWidth(),(int)n.getContenido().getRectanguloCabecera().getHeight());
            g.drawRect((int)n.getContenido().getRectanguloAtributo().getX(),(int)n.getContenido().getRectanguloAtributo().getY(),(int) n.getContenido().getRectanguloAtributo().getWidth(),(int)n.getContenido().getRectanguloAtributo().getHeight());
            g.drawRect((int)n.getContenido().getRectanguloMetodo().getX(),(int)n.getContenido().getRectanguloMetodo().getY(),(int) n.getContenido().getRectanguloMetodo().getWidth(),(int)n.getContenido().getRectanguloMetodo().getHeight()); 
        }
        
     }
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

   
   
}
