/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mandelbrot;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author G y M
 */
public class Pantalla {
    
      private static Logger logger = Logger.getLogger(Pantalla.class);

    
    public static void main(String[] args) {
      String resource = "/Auditoria.properties";
        URL configFileResource;
        configFileResource = Mandelbrot.class.getResource(resource);
        configFileResource = Pantalla.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
    Frame frame = new Frame("Fractal Viewer");
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    logger.info("Se inicializa el arreglo");

    Applet applet = new Mandelbrot();
    frame.add(applet, BorderLayout.CENTER);
    frame.setSize(400, 400);
    frame.show();
    applet.init();
    applet.start();
  }
}
