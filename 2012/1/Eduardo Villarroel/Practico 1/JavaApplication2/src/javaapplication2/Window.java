/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 *
 * @author Eduardo
 */
public class Window extends JFrame {

    private Panel objpn;
    private Arreglo objArreglo;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuOpciones = new JMenu();
    private JMenuItem menuItemOrdenarSeleccion = new JMenuItem();
    private JMenuItem menuItemOrdenarQuickSort = new JMenuItem();
    private JMenuItem menuItemDesordenar = new JMenuItem();
    private JMenuItem menuItemExit = new JMenuItem();

    public Window() {
        objArreglo = new Arreglo(300);
        objpn = new Panel(objArreglo);

        this.setSize(600, 660);
        this.getContentPane().add(objpn);
        this.setTitle("Algoritmos de Ordenacion");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(menuBar);
        this.setCursor(new Cursor(12));

        menuBar.add(menuOpciones);
        menuOpciones.add(menuItemDesordenar);
        menuOpciones.add(menuItemOrdenarSeleccion);
        menuOpciones.add(menuItemOrdenarQuickSort);
        menuOpciones.add(menuItemExit);

        menuOpciones.setText("Opciones");
        menuItemDesordenar.setText("Desordenar Arreglo");
        menuItemOrdenarSeleccion.setText("Algoritmo Seleccion");
        menuItemOrdenarQuickSort.setText("Algoritmo QuickSort");
        menuItemExit.setText("Salir");

        BotonesdeAction();

    }

    public void BotonesdeAction() {
        //BOTON DESORDENAR
        menuItemDesordenar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    objArreglo.desordenar();
                } catch (Exception f) {
                    System.out.println("error");
                }
            }
        });
        //BOTON ORDENAR USANDO EL ALGORITMO DE SELECCION
        menuItemOrdenarSeleccion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemDesordenar.setEnabled(false);
                menuItemOrdenarQuickSort.setEnabled(false);
                menuItemOrdenarSeleccion.setEnabled(false);
                setCursor(new Cursor(3));
                Thread worker = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        objArreglo.ordenarSeleccion();
                        menuItemDesordenar.setEnabled(true);
                        menuItemOrdenarQuickSort.setEnabled(true);
                        menuItemOrdenarSeleccion.setEnabled(true);
                        setCursor(new Cursor(12));
                    }
                });
                worker.start();
            }
        });
        // BOTON PARA ORDENAR USANDO EL ALGORITMO QUICKSORT
        menuItemOrdenarQuickSort.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemDesordenar.setEnabled(false);
                menuItemOrdenarQuickSort.setEnabled(false);
                menuItemOrdenarSeleccion.setEnabled(false);
                setCursor(new Cursor(3));
                Thread worker = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        objArreglo.ordenarQuickSort();
                        menuItemDesordenar.setEnabled(true);
                        menuItemOrdenarQuickSort.setEnabled(true);
                        menuItemOrdenarSeleccion.setEnabled(true);
                        setCursor(new Cursor(12));
                    }
                });
                worker.start();
            }
        });
        //BOTON PARA SALIR
        menuItemExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


}