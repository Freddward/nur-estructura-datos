package diagramadeclase;

import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;

public class Panel0  extends JPanel {
   private Grafo<DibujarClase> grafo;

    public Panel0(Grafo<DibujarClase> grafo) {
        this.grafo = grafo;
    }
     public void paint(Graphics g) {
        super.paint(g);
        Iterator<Nodo<DibujarClase>> i = grafo.getNodos().values().iterator();
        while(i.hasNext()){ 
            Nodo<DibujarClase> n = i.next();
	    int tamaño=15;
            int contador=0;
            if(contador<=4)
            {
	    for (int j = 0; j < n.getContenido().getAtributo().size(); j++) {
                 g.drawString(n.getContenido().getAtributo().get(j),(int)n.getContenido().getRectanguloAtributo().getX()
                 ,(int)n.getContenido().getRectanguloAtributo().getY()+tamaño);
                 tamaño+=15;
            }
              tamaño=15;}
              
          for (int j = 0; j < n.getContenido().getMetodos().size(); j++) {
               g.drawString(n.getContenido().getMetodos().get(j),(int)n.getContenido().getRectanguloAtributo().getX()
               ,(int)n.getContenido().getRectanguloMetodo().getY()+tamaño);
               tamaño+=15;
          }
            g.drawString(n.getContenido().getNombreClase(),n.getContenido().getX1()+60,n.getContenido().getY1()+15);
            g.drawRect((int)n.getContenido().getRectanguloCabecera().getX(),(int)n.getContenido().getRectanguloCabecera().getY(),
            (int) n.getContenido().getRectanguloCabecera().getWidth(),(int)n.getContenido().getRectanguloCabecera().getHeight());
            
            g.drawRect((int)n.getContenido().getRectanguloAtributo().getX(),(int)n.getContenido().getRectanguloAtributo().getY(),
            (int) n.getContenido().getRectanguloAtributo().getWidth(),(int)n.getContenido().getRectanguloAtributo().getHeight());
            
            g.drawRect((int)n.getContenido().getRectanguloMetodo().getX(),(int)n.getContenido().getRectanguloMetodo().getY(),
            (int) n.getContenido().getRectanguloMetodo().getWidth(),(int)n.getContenido().getRectanguloMetodo().getHeight());
            
        }
        
     }
    
    
}
 
  


