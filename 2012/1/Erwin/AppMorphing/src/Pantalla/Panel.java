package Pantalla;

import Estructura.Cola;

import Estructura.Pila;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;

import java.io.File;
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;


public class Panel
  extends JPanel
  implements ActionListener
{

  protected JMenuBar menuP;
  private JMenu opciones;
  private JMenuItem empezar, reiniciar;
  private Morphing morphing;
  private BufferedImage imagenInicial;
  private BufferedImage imagenIntermedia;
  private BufferedImage imagenFinal;
  private BufferedImage imagen;
  private Cola<BufferedImage> cola;
  private Pila<BufferedImage> pila;
  private Cola<BufferedImage> auxiliar;
  private JButton bSiguiente = new JButton();
  private JButton bAnterior = new JButton();
  private int cont1, cont2;
  private static Logger logger = Logger.getLogger(Panel.class);

  public Panel()
  {
    super();

    menuP = new JMenuBar();
    morphing = new Morphing();

    cont1 = 0;
    cont2 = 0;
    cola = new Cola<BufferedImage>();
    pila = new Pila<BufferedImage>();
    this.imagenInicial =
        new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    this.imagenInicial = loadImage("image.jpg");
    this.imagenFinal =
        new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    this.imagenFinal = loadImage("image2.jpg");
    this.imagenIntermedia =
        new BufferedImage(700, 450, BufferedImage.TYPE_INT_RGB);
    this.imagen=loadImage("Lighthouse.jpg");
    this.imagenIntermedia =
        new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    opciones = new JMenu("Opciones");

    bSiguiente.setText("Siguiente");
    bSiguiente.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          boton_siguiente(e);

        }
      });

    bAnterior.setText("Anterior");

    bAnterior.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          boton_anterior(e);
        }
      });
    this.add(bAnterior);
    this.add(bSiguiente);
    empezar = new JMenuItem("Comenzar");
    empezar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          item_empezar(e);
        }
      });
    reiniciar = new JMenuItem("Reiniciar");
    reiniciar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          item_reiniciar(e);
        }
      });

    opciones.add(empezar);
    opciones.add(reiniciar);
    menuP.add(opciones);


  }

  public static BufferedImage loadImage(String pathname)
  {
    logger.info("se carga la imagen");
    BufferedImage img = null;
    try
    {
      img = ImageIO.read(new File(pathname));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return img;
  }

  @Override
  public void paintComponent(Graphics g)
  {

    logger.info("se esta dibujando la imagen");
    g.drawImage(imagen, 0, 0, this);
    g.drawImage(imagenInicial, 0, 120, null);
    g.drawImage(imagenIntermedia, 242, 120, null);
    g.drawImage(imagenFinal, 484, 120, null);
    
  }


  public void boton_siguiente(ActionEvent e)
  {
    logger.info("se impementa cola");
    auxiliar = new Cola<BufferedImage>();
    llenar(auxiliar);
    for (int i = 0; i < cont2; i++)
    {
      pila.push(auxiliar.pop());
    }
    if (cola.tamano() > 0)
    {
      imagenIntermedia = cola.pop();
      pila.push(imagenIntermedia);
      cont1++;
      repaint();
      bAnterior.setEnabled(true);
    }
    else
    {
      bSiguiente.setEnabled(false);

    }
  }

  public void llenar(Cola<BufferedImage> ponerImagen)
  {
    while (ponerImagen.tamano() <= morphing.getImagenSiguiente())
    {
      ponerImagen.push(morphing.morphingImage(imagenInicial, imagenFinal,
                                              (ponerImagen.tamano())));
    }
  }

  public void boton_anterior(ActionEvent e)
  {
    logger.info("se implementa Pila");
    auxiliar = new Cola<BufferedImage>();
    llenar(auxiliar);
    cola = new Cola<BufferedImage>();
    for (int i = 0; i < pila.tamano(); i++)
    {
      if (auxiliar.tamano() < pila.tamano())
      {
        auxiliar.pop();
      }
    }
    if (auxiliar.tamano() > 0)
    {
      cola = auxiliar;
    }
    if (pila.tamano() > 0)
    {
      imagenIntermedia = pila.pop();
      cont1--;
      cont2 = cont1;
      repaint();

      bSiguiente.setEnabled(true);
    }
    else
    {
      bAnterior.setEnabled(false);

    }


  }

  public void item_reiniciar(ActionEvent e)
  {
    logger.info("Se va reiniciar el dibujo");
    imagenIntermedia =
        new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    repaint();
    while (cola.tamano() <= morphing.getImagenSiguiente())
    {
      cola.push(morphing.morphingImage(imagenInicial, imagenFinal,
                                       (cola.tamano())));
    }
    bSiguiente.setEnabled(true);
    bAnterior.setEnabled(true);
  }

  public void item_empezar(ActionEvent e)
  {

    String x =
      JOptionPane.showInputDialog("Ingrese los paso en lo que quiere ver la imagen");
    int nodo = 0;
    boolean validar = true;

    if (x == null)
    {
      validar = false;
    }
    else
    {
      try
      {
        nodo = Integer.parseInt(x);
      }
      catch (NumberFormatException g)
      {
        validar = false;
        JOptionPane.showMessageDialog(null, "numero incorrecto");
      }

      if (validar)
      {
        this.validar(nodo);
      }

    }

  }

  public void validar(int a)
  {
    logger.info("Inicializa la imagen");
    morphing.setImagenSiguiente(a);
    while (cola.tamano() <= morphing.getImagenSiguiente())
    {
      cola.push(morphing.morphingImage(imagenInicial, imagenFinal,
                                       (cola.tamano())));
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {

  }


}


