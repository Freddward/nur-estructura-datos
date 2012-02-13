/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Graphics.Screen;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author EdwardFC
 */
public class Controller {

    private static Logger logger = Logger.getLogger(Controller.class);

    public static void main(String[] Args) {
        String resource = "Auditory.properties";
        URL configFileResource;
        configFileResource = Controller.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        Screen obj = new Screen();
        logger.info("Se inició la Aplicación");
        
    }
}
