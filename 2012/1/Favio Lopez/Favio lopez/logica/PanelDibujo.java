package logica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class PanelDibujo extends JPanel implements MouseListener, MouseMotionListener
{
  private Logger logger = Logger.getRootLogger();

  private Grafo<Clase>g = new Grafo<Clase>();
  private Clase clsAux;
  private Diagrama diagram;
  private boolean tipo = false;

  public PanelDibujo(Clase obj) {
      if (obj != null)
          this.setBackground(Color.red);
      diagram = new Diagrama();
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
  }

  public void paintComponent(Graphics g2) {
      super.paintComponent(g2);
      if (diagram != null) {
          logger.info("diagrama no vacio");
          Graphics2D g = (Graphics2D)g2;

          BasicStroke str =new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
          g.setStroke(str);
          g.setFont(new Font("Arial", Font.BOLD, 12));
          g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
          g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
          diagram.dibujar(g);
      }
  }

  public void mouseClicked(MouseEvent e) {
      Clase aux = diagram.buscar(e.getPoint());
      if (!tipo) {
          if (aux != null) {
              if (clsAux != null) {
                  clsAux.addRelacciones(aux);
                  clsAux = null;
                  tipo = false;
                  this.repaint();
              } else {
                  clsAux = aux;
              }
              g.conectar(clsAux,aux);
          }
      }
  }

  public void mousePressed(MouseEvent e) {

  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseDragged(MouseEvent e) {

  }

  public void mouseMoved(MouseEvent e) {
  }

  public void setDiagram(Diagrama newdiagram) {
      this.diagram = newdiagram;
  }

  public Diagrama getDiagram() {
      return diagram;
  }

  public void setTipo(boolean tipo)
  {
    this.tipo = tipo;
  }

  public boolean isTipo()
  {
    return tipo;
  }
}
