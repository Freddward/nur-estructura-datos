/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author G y M
 * La Clase Servidor se encarga de hacer la coneccion con el local host por el puerto que le indiquemos
 * en este caso esta saliendo por el 8888, tambien aqui sacamos los resultados del arbol en html
 * para sacarlo por la web.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Servidor implements Runnable {

    private static Logger logger = Logger.getLogger(Servidor.class);
    private Socket s;
    private Recursividad recu;
/**
     * 
     * @param s Es un objeto de Socket que es el que nos ayuda a comunicarnos por el puerto 
     */
    public Servidor(Socket s) {
        this.s = s;       
    }

    /**
     * 
     * @param out parametro de salida del PrintScream
     * @param recu objeto de nuestra clase Recursividad
     * 
     * En este metodo le damos los paametros para sacar por pantalla
     */
    private void responderHtml(PrintStream out, Recursividad recu) {
        //String r = "Content-Type: text/html\r\n\r\n<html>Hola</html>\r\n";        
        // out.println("Content-Type: text/html");
        //out.println(r);
        logger.info("Sacando datos por pantalla...");
        out.println("Tipo de Contenido: text/html");
        out.println("");
        for (int i = 0; i < recu.getDescripcion().length; i++) {
            if (recu.getDescripcion()[i] == null) {
                break;
            } else {
                out.println(recu.getDescripcion()[i]);
            }
        }
        out.flush();
        out.close();
        
    }
/**
     * run es un metodo abstracto de la clase Runnable por este metodo sacamos todo a la pantalla 
     * 
     */
//Servicio
    @Override
    public void run() {
        PrintStream out;
        try {
            out = new PrintStream(s.getOutputStream());
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            
            String mensaje = "" + new Date();
            out.println(mensaje);
            out.println("");
            recu = new Recursividad(); //inicializamos el objeto de tipo Recursividad
            recu.iniciar();
            String cmd = "";
            while (true) {
                cmd = in.readLine();
                if (cmd.startsWith("GET")) {
                    responderHtml(out, recu); //imprimimos en pantalla
                    break;
                }
                //System.out.println("<---[CLT] " + cmd);
            }
            responderHtml(out, recu); //imprimimos en pantalla
            try {
                s.close();
            } catch (IOException e) {
                
                logger.error("Error " + e);
            }
            s.close();
        } catch (IOException ex) {
        }
    }

    public static void main(String[] args) {
        String resource =
                "/auditoria.properties";
        URL configFileResource =
                Servidor.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        
        
        ServerSocket srv = null;
        logger.info("Conexion por el puerto 8888");
        try {
            srv = new ServerSocket(8888);   // Coneccion por el puerto 8888
            while (true) {
                Socket s = srv.accept();
                Runnable servicio = new Servidor(s); //inicializamos un objeto de tipo Runnable
                Thread t = new Thread(servicio);
                t.start(); //Empezamos a correr
            }
        } catch (Exception e) {;
            logger.error("Error de conexion", e);
        } finally {
            try {
                srv.close();
                logger.info("Se cerro la conexion");
            } catch (IOException ex) {
                logger.error("Ocurrio un fallo al cerrarce", ex);
            }
        }
    }
}
