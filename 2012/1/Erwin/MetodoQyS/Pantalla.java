package metodoqys;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.net.URL;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Pantalla
  extends JPanel
  implements ActionListener, Observer
{

  private JMenuBar menuPrincipal;
  private JMenu menuArchivo, menuOrdenamiento;
  private JMenuItem nuevo, salir, comenzar;

  private JRadioButtonMenuItem ordenamientoQ, ordenamientoB;
  private Quicksort quicksort;
  private Burbuja burbuja;
  private boolean estadoCheck;
  private JFrame frame;
  private static Logger log = Logger.getLogger(Pantalla.class);

  public Pantalla()
  {

    quicksort = new Quicksort();
    burbuja = new Burbuja();

    menuPrincipal = new JMenuBar();
    this.Menu();
    this.Menuitem();
    this.OrdenamientoQuicksort();
    this.OrdenamientoBurbuja();
    this.Comenzar();
  }

  public void Menu()
  {
    log.info("Se esta colocando al menuPrincipal ARCHIVO y ORDENAR");
    try
    {
      menuArchivo = new JMenu("ARCHIVO");
      this.menuOrdenamiento = new JMenu("ORDENAR");
      menuPrincipal.add(menuArchivo);
      menuPrincipal.add(this.menuOrdenamiento);
      
    }
    catch (Exception err)
    {
      log.error("En el menuPrincipal al agregar el menu se ha generado un problema",err);
    }


  }

  public void Menuitem()
  {
    nuevo = new JMenuItem("Nuevo", new ImageIcon("file.gif"));
    salir = new JMenuItem("Salir", new ImageIcon("Btnoff.png"));
    salir.addActionListener(this);
    menuArchivo.add(nuevo);
    nuevo.addActionListener(this);
    menuArchivo.addSeparator();
    menuArchivo.add(salir);


  }

  public void OrdenamientoQuicksort()
  {
    log.info("se va a poner un JRADIOBUTTONMENUITEM al menu de ordenamietnoQ");
    try{
    ordenamientoQ =
        new JRadioButtonMenuItem("Ordenamiento Quicksort", new ImageIcon("Quicksort.jpg"));
    this.ordenamientoQ.setHorizontalTextPosition(JMenuItem.RIGHT);
    ordenamientoQ.addActionListener(this);
    this.menuOrdenamiento.add(this.ordenamientoQ);
    }catch(Exception e)
    {
      log.error("SE HA PRODUCIDO UN ERROR",e);
    }
  }

  public void OrdenamientoBurbuja()
  {
    log.info("se va a poner un JRADIOBUTTONMENUITEM al menu de ordenamietnoB");
    try{
    this.ordenamientoB =
        new JRadioButtonMenuItem("Ordenamiento Burbuja", new ImageIcon("burbuja.png"));
    ordenamientoB.addActionListener(this);
    this.ordenamientoB.setHorizontalTextPosition(JMenuItem.RIGHT);
    this.menuOrdenamiento.add(this.ordenamientoB);
    this.menuOrdenamiento.addSeparator();
    }catch(Exception e)
    {
      log.error("SE HA PRODUCIDO UN ERROR",e);
    }
  }

  public void Comenzar()
  {
    log.info("Al menuOrdenamiento se le esta implementando el menu comenzar");
    try{
    this.comenzar = new JMenuItem("Comenzar", new ImageIcon("flecha.png"));
    comenzar.addActionListener(this);
    this.menuOrdenamiento.add(comenzar);
    }catch(Exception e)
    {
      log.error("SE HA PRODUCIDO UN ERROR",e);
    }
  }

  public void paintComponent(Graphics g)
  {
    
    super.paintComponent(g);
    if (this.estadoCheck == false)
    {
      for (int i = 0; i < quicksort.getvec().length; i++)
      {
        g.drawLine(0, i * 2, quicksort.getvec()[i], i * 2);
        g.setColor(Color.BLUE);
      }
    }
    if (this.estadoCheck == true)
    {

      for (int i = 0; i < burbuja.getvec().length; i++)
      {

        g.drawLine(0, i * 2, burbuja.getvec()[i], i * 2);

        g.setColor(Color.RED);
      }
    }
    

  }

  public static void main(String[] args)
  {


    String resource = "/auditoria.properties";
    URL configFileResource;
    configFileResource = Pantalla.class.getResource(resource);
    PropertyConfigurator.configure(configFileResource);

    Pantalla obj = new Pantalla();
    obj.frame = new JFrame("Ordenamientos");
    obj.frame.getContentPane().setBackground(Color.WHITE);
    obj.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    obj.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("java_logo.gif"));
    obj.frame.add(obj.menuPrincipal, BorderLayout.NORTH);
    obj.frame.add(obj);
    obj.frame.setSize(500, 500);
    obj.frame.setVisible(true);
    obj.frame.setLocationRelativeTo(null);


  }

  public void actionPerformed(ActionEvent e)
  {

    ///////// BOTON ORDENAMIENTO Q Y B
    try{
    if (ordenamientoB == e.getSource())
    {
      this.estadoCheck = true;
      this.ordenamientoB.setSelected(true);
      this.ordenamientoQ.setSelected(false);
    }
    else if (this.ordenamientoQ == e.getSource())
    {
      this.estadoCheck = false;
      this.ordenamientoQ.setSelected(true);
      this.ordenamientoB.setSelected(false);
    }
    /////////////////////////////////////////////
    if (this.ordenamientoB == e.getSource() ||
        this.ordenamientoQ == e.getSource())
    {
      repaint();

    }
    ////////////////////////////////////////////////////////////////////////////
    if (this.comenzar == e.getSource() && estadoCheck == true)
    {

      burbuja.addObserver(this);

      Runnable thework = new Runnable()
      {
        public void run()
        {

          burbuja.Bordenamiento();

        }
      };
      Thread worker = new Thread(thework);
      worker.start();


    }
    if (this.comenzar == e.getSource() && this.estadoCheck == false)
    {
      quicksort.addObserver(this);

      Runnable thework = new Runnable()
      {
        public void run()
        {

          quicksort.Qordenamiento(quicksort.getvec(), 0,
                                  quicksort.getvec().length - 1);

        }
      };
      Thread worker = new Thread(thework);
      worker.start();


    }
    ///////////////////////////////////////////////
    if (this.nuevo == e.getSource() && estadoCheck == true)
    {
      burbuja.Inicializar();
      burbuja.Desordenarr();

      repaint();
    }
    else if (this.nuevo == e.getSource() && estadoCheck == false)
    {
      quicksort.Inicializar();
      quicksort.Desordenarr();
      repaint();
    }
    /////////////////////////////////////////
    if (this.salir == e.getSource())
    {
      frame.dispose();

    }
    }catch(Exception p)
    {
      log.error("SE HA PRODUCIDO UN ERROR",p);
    }
  }

  public void update(Observable o, Object arg)
  {
    repaint();
  }
}
