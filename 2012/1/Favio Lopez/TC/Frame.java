package TC;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;

import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import javax.swing.JMenuItem;

import org.apache.log4j.Logger;import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Frame extends JFrame {

   
    private  Logger logger = Logger.getRootLogger();
    
    private JMenuBar mBar = new JMenuBar();
    
    private JMenu desorden = new JMenu();
    private JMenuItem desordenar = new JMenuItem();
  
    private JMenu ordenar = new JMenu();
    private JMenuItem inserccion = new JMenuItem();
    private JMenuItem seleccion = new JMenuItem();
    private JMenuItem exit = new JMenuItem();
   
    private Panel panel;
    private Arreglos objArreglo;


    //Constructor

    public Frame() {
       repaint();
       iniciar();
    }

    private void iniciar()  {
        this.setJMenuBar(mBar);
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(300,300);
        this.setVisible(true);
        this.setLocation(250,150);
        //this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Practico ordenamiento");
        desorden.setText("Desordenar");
        ordenar.setText("Ordenar");
        exit.setText("Exit");
        

        desordenar.setText("Desordenar");
        desordenar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        desordenar_ap(e);
                    }
                });
       
        exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        exit_ap(e);
                    }
                });


        inserccion.setText("Insercion");
        inserccion.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        inserccion_ap(e);
                    }
                });
        seleccion.setText("Seleccion");
        seleccion.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        seleccion_ap(e);
                    }
                });
       
      
       
        ordenar.add(inserccion);
        ordenar.add(seleccion);
        ordenar.add(exit);
        mBar.add(ordenar);
        mBar.add(desorden);
        desorden.add(desordenar);

        
        int o = Integer.parseInt(JOptionPane.showInputDialog("tamaño del vector"));

        objArreglo = new Arreglos(o);
        panel = new Panel(objArreglo);
        this.getContentPane().add(panel, BorderLayout.NORTH);
        repaint();
        

    }

    public void exit_ap(ActionEvent e) {

        System.exit(EXIT_ON_CLOSE);
    }

    public void desordenar_ap(ActionEvent e) {
        objArreglo.desordenar();
        repaint();
    }

    public void seleccion_ap(ActionEvent e) {
        
            Runnable res = new Runnable()
            {

        public void run()
        {
           Seleccion objs= new Seleccion(objArreglo);
            objs.Ordenar();
        }
      };
    Thread worker = new Thread(res);
            worker.start();
       
         
            logger.info("Se uso el metodo de ordenamiento de seleccion");

       
    }

    public void inserccion_ap(ActionEvent e) {
        Runnable res = new Runnable()
        {

        public void run()
        { 
            Inserccion obji= new Inserccion(objArreglo);
            obji.Ordenar();
        }
      };
    Thread worker = new Thread(res);
        worker.start();
        
       
        logger.info("Se uso el metodo de ordenamiento de insercion");
      
    }
    
  public static void main(String[] args) {

      String resource = "/auditoria.properties";
      URL configFileResource;
      configFileResource = Frame.class.getResource(resource);
      PropertyConfigurator.configure(configFileResource);

      new Frame();
  }
   
}

