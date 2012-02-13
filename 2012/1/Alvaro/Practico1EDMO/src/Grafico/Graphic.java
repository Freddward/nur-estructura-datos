package Grafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;
import logica1.Arreglo;

import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author AlvaroPasquierTufiño
 */
public class Graphic extends JFrame {

   
    private JButton btnboton = new JButton();
    private JMenuBar opciones = new JMenuBar();
    private JMenu menu = new JMenu();
    private JMenu menu2=new JMenu();
    private JMenuItem item = new JMenuItem("BubleSort");
    private JMenuItem item2=new JMenuItem("QuickSort");
    private JMenuItem item3=new JMenuItem("reiniciar");
    private Arreglo objArreglo;
    private Panel objPnl;
    public Graphic() 
    {
        //Aca se ingresa el tamaño que va a tener el arreglo
        String cadena=JOptionPane.showInputDialog("ingrese la cantidad de lineas que quiere que se ordene");
        int cantidad=Integer.parseInt(cadena);
        objArreglo = new Arreglo(cantidad);
        objArreglo.inicializar();
        /* for(int i=0;i<objarreglo.getArr().length;i++)
        {
        System.out.print("{"+objarreglo.getArr()[i]+"}");
        }*/
        objPnl=new Panel(objArreglo);
        this.setSize(600, 600);
        this.add(objPnl);
        this.setResizable(false);
        //this.setBackground(Color.YELLOW);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(objPnl, BorderLayout.CENTER);

        this.setJMenuBar(opciones);
        menu.setText("opciones");
        opciones.add(menu);
        menu2.setText("MetodosDeOrdenamiento");
        opciones.add(menu2);
        menu2.add(item);
        //Aca se agrega el algoritmo bubleSort al item
        item.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                nuevo_ActionPerformed(e);
            }
            public void nuevo_ActionPerformed(ActionEvent e) 
            {
                //Aca se inabilitan los items una vez corrido el metodo de ordenamiento
                item.setVisible(false);
                item2.setVisible(false);
                item3.setVisible(false);
                Thread work=new Thread(new Runnable()
                {
                    @Override
                    public void run() 
                    {
                    //Aca se vuelve a habilitar los items una vez finalizado el algoritmo de ordenamiento
                        objArreglo.BubleSort();
                        item.setVisible(true);
                        item2.setVisible(true);
                        item3.setVisible(true);
                    }
                });
                work.start();
            }
        });
        menu2.add(item2);
        //Aca se agrega el metodo quiksort al item
        item2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                nuevo_ActionPerformed(e);
            }
            public void nuevo_ActionPerformed(ActionEvent e) 
            {
                item.setVisible(false);
                item2.setVisible(false);
                item3.setVisible(false);
                 Thread worker=new Thread(new Runnable() 
                 {
                     @Override
                    public void run() 
                     {
                        objArreglo.QuickSort(objArreglo.getArr(),0,objArreglo.getArr().length-1);
                        item.setVisible(true);
                        item2.setVisible(true);
                        item3.setVisible(true);
                     }
                  });
            worker.start();
            }
        });
        menu.add(item3);
        //Aca se agrega el metodo inicializar al item 3
        item3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                nuevo_ActionPerformed(e);
            }
            public void nuevo_ActionPerformed(ActionEvent e)
            {
                objArreglo.inicializar();
            }
        });
    }
   
    /*public void nuevo_ActionPerformed(ActionEvent b) 
    {
        objArreglo.QuickSort(objArreglo.getArr(), 0, objArreglo.getArr().length-1);
    }*/
    public static void main(String[] s) 
    {
        String resource = "/auditoria.properties"; 
        URL configFileResource; 
        configFileResource = Arreglo.class.getResource(resource); 
        PropertyConfigurator.configure(configFileResource);
        
        JFrame frame = new Graphic();
        frame.setVisible(true);
      }
}