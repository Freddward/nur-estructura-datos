package mandelbroot;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Panel1 extends JPanel implements MouseListener
{
  private Logica3 logica;
  public Panel1()
  {
      logica=new Logica3();
      this.addMouseListener(this);
  }
  public Panel1(Logica3 logica3)
  {
      logica3=logica;
      this.addMouseListener(this);
  }
  public void paintComponent(Graphics g)
  {
      g.drawImage(logica.getMandelbroot(),0,0,this);
  }
 public void mouseClicked(MouseEvent e)
  {
      logica.setZOOM();
      logica.getZOOM();
      logica.ConstruirMandelbroot();
      repaint();
  }

  public void mousePressed(MouseEvent e)
  {
  }

  public void mouseReleased(MouseEvent e)
  {
  }

  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }
}
