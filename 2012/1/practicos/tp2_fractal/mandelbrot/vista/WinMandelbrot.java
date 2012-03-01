package mandelbrot.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mandelbrot.control.ControlMouse;
import mandelbrot.control.Fractalizador;

import mandelbrot.modelo.CuadroZoom;
import mandelbrot.modelo.Matriz;

import org.apache.log4j.Logger;

/**
 * Esta clase es el frame principal que se encargara de instanciar los controles
 * que son amarrados al modelo que se debe observar
 */
public class WinMandelbrot extends JFrame implements Observer {
    
    private final static Logger logger = 
        Logger.getLogger(WinMandelbrot.class);
    
    private BorderLayout layoutMain = new BorderLayout();
    private PanelDibujo panelCenter;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuHelpAbout = new JMenuItem();
    private JLabel statusBar = new JLabel();
    private JMenuItem menuFileReset = new JMenuItem();
    
    /**
     * El control que se encarga de realizar la logica de negocios del programa
     */
    private Fractalizador control;
    /**
     * El control que se encarga de realizar la logica de negocios del zoom
     */
    private ControlMouse controlZoom;

    /**
     * Constructor simple
     */
    public WinMandelbrot() {
        try {
            inicializarMandelbrot();
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setJMenuBar( menuBar );
        this.getContentPane().setLayout( layoutMain );
        panelCenter.setLayout( null );
        this.setSize( new Dimension(400, 300) );
        this.setTitle( "Mandelbrot Viewer" );
        menuFile.setText( "File" );
        menuFileExit.setText( "Exit" );
        menuFileExit.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        menuHelp.setText("Help");
        menuHelpAbout.setText("About");
        menuHelpAbout.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        helpAbout_ActionPerformed(ae);
                    }
                });
        statusBar.setText( "" );
        menuFileReset.setText("Reset");
        menuFileReset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menuFileReset_actionPerformed(e);
                    }
                });
        menuFile.add(menuFileReset);
        menuFile.addSeparator();
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuHelp);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        this.getContentPane().add(panelCenter, BorderLayout.CENTER);

        logger.info("Se llama a pack para que se actualizen los tamanos de los componentes");
        this.pack();    
    }

    /**
     * El manejador del evento exit del menu file
     * @param e
     */
    void fileExit_ActionPerformed(ActionEvent e) {
        logger.info("El usuario hizo clic en exit");
        System.exit(0);
    }
    
    /**
     * El manejador del evento exit del menu file
     * @param e
     */
    void helpAbout_ActionPerformed(ActionEvent e) {
        logger.info("El usuario hizo clic para mostrar la ventana about");
        JOptionPane.showMessageDialog(this, new WinMandelbrot_About(), 
                                      "About", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Asigna lo que tiene que asignar al controlador
     */
    private void inicializarMandelbrot() {
        // crea el panel donde se dibuja
        this.panelCenter = new PanelDibujo(this);
        Matriz m = new Matriz();
        // crea el control de hacer el fractal
        logger.info("Crea el control conectado al modelo y a la vista");
        control = new Fractalizador(m, this.panelCenter);
        // Nos hacemos observadores del fractal para el status
        logger.info("Conecta el observador winmandelbrot con el observado (modelo)");
        m.addObserver(this);
        
        // dibuja el fractal
        logger.info("Calcula el fractal la primera vez con los valores por defecto");
        control.calcularMatrizImagen();
        
        // crea el control para hacer zoom
        logger.info("Crea el control para hacer zoom y le pasa los parametros necesarios");
        CuadroZoom objZoom = new CuadroZoom();
        controlZoom = new ControlMouse(objZoom, this.panelCenter, control);
    }

    /**
     * Vuelve las coordenadas del mandelbrot a -2,2 - 2,-2
     * @param e
     */
    private void menuFileReset_actionPerformed(ActionEvent e) {
        logger.info("Se resetearon los valores del modelo de fractal mandelbrot");
        control.getModel().reset();
        control.calcularMatrizImagen();
    }
    
    /**
     * Este es el metodo al que llama cuando el modelo cambia, es para mostrar
     * en el status el valor de las coordenadas
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        Matriz m = control.getModel();
        this.statusBar.setText("(" + m.getR0() + " , " + m.getI0() + 
            " ) - ( " + m.getR1() + " , " + m.getI1() + " )");
    }
}
