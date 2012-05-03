package logica;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import org.apache.log4j.Logger;

public class VentanaLaberinto extends JFrame implements KeyListener, Runnable {
    private BorderLayout layoutMain = new BorderLayout();
    private PanelDibujo panelCenter;
    private String origen = "63", destino = "1";

    private DibujoGrafo elDibujoGrafo;
    Thread hilo;
    private static final Logger logger = 
        Logger.getLogger(VentanaLaberinto.class);
    private Point p = new Point();

    public VentanaLaberinto() {
        try {
            p.x=30;
            p.y=30;
            hilo = new Thread(this);
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        
        this.getContentPane().setLayout(layoutMain);
        this.pack();

        this.setTitle("Practico Pacman");
        
        panelCenter = new PanelDibujo();
        this.setContentPane(panelCenter);
        this.addKeyListener(this);
                
        FileReader fr = new FileReader("D:\\lab\\Datos\\nodos1.txt");
        FileReader fr2 = new FileReader("D:\\lab\\Datos\\nodos1.txt");
        BufferedReader bf = new BufferedReader(fr);
        BufferedReader bf2 = new BufferedReader(fr2);
        FileReader fr1 = new FileReader("D:\\lab\\Datos\\arcos1.txt");
        BufferedReader bf1 = new BufferedReader(fr1);


        try {
            inicializarGrafo();
            elDibujoGrafo.leerNodosLaberinto(bf);
            elDibujoGrafo.llenarMatriz(bf2);
        } catch (Exception err) {
            logger.error("No pudo leer los nodos del archivo", err);
            JOptionPane.showMessageDialog(this, 
                                          "El archivo nodos no se puede leer o no esta en el formato correcto");
        }


        try {
            elDibujoGrafo.leerArcosLaberinto(bf1);
        } catch (Exception err) {
            logger.error("No pudo leer los arcos del archivo", err);
            JOptionPane.showMessageDialog(this, 
                                          "El archivo arcos no se puede leer o no esta en el formato correcto");
        }

        elDibujoGrafo.calcularParedes();
        elDibujoGrafo.tamano();
        System.out.println(elDibujoGrafo.getMaximaFila1() + " " + 
                           elDibujoGrafo.getMaximaColumna1());
        this.setSize(elDibujoGrafo.getMaximaFila1(), 
                     elDibujoGrafo.getMaximaColumna1());

        //Starta
        start();
    }

    private void start(String origen, String destino) {
        Pila<Grafo.Nodo<DibujoNodo>> camino = 
            elDibujoGrafo.getGrafo().caminoMasCortoDijkstra(origen, destino);
        if (camino != null) {

            elDibujoGrafo.setUltimoCamino(camino);
            for (int i = 0; i < camino.tamano(); i++) {
                panelCenter.setPointenemy(5 + i, 7);
                panelCenter.repaint();
                Object a = camino.pop();
                System.out.println("" + a);
                try {

                } catch (Exception e) {

                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public void inicializarGrafo() {
        Grafo<DibujoNodo> grafo = new Grafo<DibujoNodo>();
        grafo.addObserver(panelCenter);
        this.elDibujoGrafo = new DibujoGrafo(grafo);
    }


    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            
            if (elDibujoGrafo.setActualPos(elDibujoGrafo.getActual()[0], 
                                           elDibujoGrafo.getActual()[1] + 1)) {
                p.x += 30;
                panelCenter.setPoint(p.x, p.y);
                panelCenter.repaint();
                destino = "" + elDibujoGrafo.ObtenerValorJugador();
                stop();
                start();
            } else {
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { //VK.UP  VK
           
            if (elDibujoGrafo.setActualPos(elDibujoGrafo.getActual()[0], 
                                           elDibujoGrafo.getActual()[1] - 1)) {
                p.x -= 30;
                panelCenter.setPoint(p.x, p.y);
                panelCenter.repaint();
                destino = "" + elDibujoGrafo.ObtenerValorJugador();
                stop();
                start();
            } else {
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) { //VK.UP  VK
            if (elDibujoGrafo.setActualPos(elDibujoGrafo.getActual()[0] - 1, 
                                           elDibujoGrafo.getActual()[1])) {
                p.y -= 30;
                panelCenter.setPoint(p.x, p.y);
                panelCenter.repaint();
                destino = "" + elDibujoGrafo.ObtenerValorJugador();
                stop();
                start();
            } else {
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) { //VK.UP  VK
           
            if (elDibujoGrafo.setActualPos(elDibujoGrafo.getActual()[0] + 1, 
                                           elDibujoGrafo.getActual()[1])) {
                p.y += 30;
                panelCenter.setPoint(p.x, p.y);
                panelCenter.repaint();
                destino = "" + elDibujoGrafo.ObtenerValorJugador();
                stop();
                start();
            } else {
            }
        }
    }

    public void run() {
        try {
        logger.info("empieza a caminar el enemy");
            int tamano = 0;
            Pila<Grafo.Nodo<DibujoNodo>> camino = 
                elDibujoGrafo.getGrafo().caminoMasCortoDijkstra(origen, 
                                                                destino);
            logger.info("calculando dijkstra");
            Pila<Grafo.Nodo<DibujoNodo>> camino1 = 
                elDibujoGrafo.getGrafo().caminoMasCortoDijkstra(origen, 
                                                                destino);
            tamano = camino1.tamano();
            logger.info("esperando respuesta de camino final");
            elDibujoGrafo.setUltimoCamino(camino);
            if (camino != null) {
                for (int i = 0; i < tamano; i++) {
                    DibujoNodo a = camino1.pop().getValor();
                    origen = a.getNombre();
                    logger.info("origen = "+origen);
                    panelCenter.setPointenemy(a.getFila(), a.getColumna());
                    panelCenter.repaint();
                    Thread.sleep(200);
                    if (i == (tamano - 1)) {
                        JOptionPane.showMessageDialog(null, "Jaja te pille!!!!");
                        logger.info("encontrado destino");
                    }
                }
            } else {
                System.out.println("Nulo");
            }
        } catch (InterruptedException x) {
        }
    }

    public void start() {
    logger.info("empieza recorrido");
        hilo = new Thread(this);
        this.hilo.start();
    }

    public void stop() {
       logger.info("stop");
        this.hilo.stop();
    }

    public void keyReleased(KeyEvent e) {
    }
}
