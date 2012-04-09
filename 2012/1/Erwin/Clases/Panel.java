package metodo;

import java.awt.BorderLayout;

import java.awt.Graphics;


import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Vector;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class Panel
  extends JPanel
  implements ActionListener, MouseListener
{
  private JMenu crear;
  private JMenuItem nuevaClase;
  private Rectangle titulo;
  private JMenuBar menuP;
  private Vector<String> atributos;
  private int x, y, ancho, altura;
  private boolean estado;
  private String nombreClase;
  
  private Grafo <Panel> t=new Grafo<Panel>();


  public Panel()
  {
    super();

    estado = false;
    x = 30;
    y = 40;
    ancho = 200;
    altura = 200;
    atributos = new Vector<String>();
    this.setLayout(new BorderLayout());
    menuP = new JMenuBar();
    crear = new JMenu("Crear");

    nuevaClase = new JMenuItem("Nueva  Clase");
    crear.add(nuevaClase);
    nuevaClase.addActionListener(this);

    this.addMouseListener(this);
    menuP.add(crear);
    this.add(menuP, BorderLayout.NORTH);
    titulo = new Rectangle(x, y, ancho, x);

  }

  public void paintComponent(Graphics g)
  {
    if (estado == true)
    {
      g.drawRect(x, y, ancho, x);
      g.drawRect(x, y + x, ancho, altura);
      g.drawRect(x, ancho + y + x, ancho, x);
      g.drawString(nombreClase, (x + ancho) / 2, (y + altura) / 4);
      for (int i = 0; i < atributos.size(); i++)
      {
        g.drawString(atributos.get(0), x, y);
      }


      //repaint();
    }
  }

  public void actionPerformed(ActionEvent e)
  {
    Logger log = Logger.getLogger(Frame.class);

    if (nuevaClase.equals(e.getSource()))
    {
      log.info("se presiono el boton");
      String a = JOptionPane.showInputDialog("Nombre de la Clase");
      t.addNodo(a, this);
      nombreClase = a;
      estado = true;
    }

    repaint();


  }

  public void mouseClicked(MouseEvent e)
  {


  }

  public void mousePressed(MouseEvent e)
  {
  }

  public void mouseReleased(MouseEvent e)
  {
    e.getX();
    e.getY();
    System.out.println(e.getX() + "X");
    System.out.println(e.getY() + "Y");


  }

  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }
}
