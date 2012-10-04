package tp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class WinFractal extends JFrame {

    private Logger logger = Logger.getLogger(WinFractal.class);
    public PanelDibujo panel;

    public static void main(String[] args) { // string para palabras letras
        String resource =
                "/auditoria.properties";
        URL configFileResource =
                WinFractal.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        WinFractal win = new WinFractal();
        win.setVisible(true);
    }
    
    public WinFractal() {
        super("Fractales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
        pack();
    }
    
    private void init() {
        // Crear barra menu
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        // Crear menu Fractales
        JMenu m1 = new JMenu("Fractales");
        mb.add(m1);
        JMenuItem mi = new JMenuItem("Cuadrado");
        mi.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                cmd_Cuadrado();
            }
        });
        m1.add(mi);
         
        mi = new JMenuItem("Triangulo");
        mi.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cmd_Triangulo();
            }
        });
        m1.add(mi);

        m1.addSeparator();

        mi = new JMenuItem("Salir");
        mi.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        m1.add(mi);
        // PanelDibujo
        panel = new PanelDibujo();
        getContentPane().add(panel);
    }
    /**
     * @param pr la profundidad de las iteraciones a hacer para el cuadrado
     */
     public void cmd_Cuadrado() {
        
        try {
            final int pr = Integer.parseInt(JOptionPane.showInputDialog("Que profundidad?"));
            if (pr > 0) {
         Runnable r = new Runnable(){

                    @Override
                    public void run() {
                        try {
                            for(int i=0; i<pr;i++){
                                panel.cambiarA(Fractal.CUADRADO, i+1);
                            Thread.sleep(1000);}
                        } catch (InterruptedException ex) {
                            logger.error("Numero Invalido", ex);
                        }
                    }
                    
                };
                Thread t = new Thread(r);
                t.start();
                logger.info("Escogio Cuadrado de Sierpinsky");
                logger.debug("El Cuadrado se inicializo en: "+pr);
                }else{
                JOptionPane.showMessageDialog(null, "El numero tiene que ser mayor a 0");
                logger.error("El numero tiene que ser mayor a 0", null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero Valido!!!");
            logger.error("El numero Ingresado contiene letras", e);
               
        }

    }
     /**
     * @param pr la profundidad de las iteraciones a hacer para el triangulo
     */
    public void cmd_Triangulo() {
        try {
            final int pr = Integer.parseInt(JOptionPane.showInputDialog("Que profundidad?"));
            if (pr > 0) {
                
                Runnable r = new Runnable(){

                    @Override
                    public void run() {
                        try {
                            for(int i=0; i<pr; i++){
                                panel.cambiarA(Fractal.TRIANGULO, i+1);
                            Thread.sleep(1000);}
                        } catch (InterruptedException ex) {
                            logger.error("Numero Invalido", ex);
                        }
                    }
                    
                };
                Thread t = new Thread(r);
                t.start();
                logger.info("Escogio Triangulo de Sierpinsky");
                logger.debug("El Triangulo se inicializo en: "+pr);
            }else{
                JOptionPane.showMessageDialog(null, "El numero tiene que ser mayor a 0");
                logger.error("El numero tiene que ser mayor a 0", null);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero Valido!!!");
            logger.error("El numero Ingresado contiene letras", e);
        }
    }

    public void salir() {
        dispose();
        System.exit(0);
    }
}