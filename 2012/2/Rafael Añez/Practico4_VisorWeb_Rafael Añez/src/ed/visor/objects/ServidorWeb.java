package ed.visor.objects;

import java.net.*;
import java.io.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Clase principal de la Aplicacion. Permite crear un servidor web que obtiene
 * el sistema de archivos de un determinado directorio y lo muestra en una
 * pagina web, la cual accede a la aplicacion mediante un Socket.
 * 
 * 
 * @author Rafael Anez
 * 
 */
public class ServidorWeb implements Runnable {
	EstructuraDeArchivos estr;
	Socket remote;
	private static final int PUERTO_SOCKET = 80;
	private static Logger logger = Logger.getLogger(ServidorWeb.class);

	/**
	 * Constructor de la clase ServidorWeb
	 * 
	 * @param s
	 *            Socket que permite la conexion entre el navegador web y el
	 *            programa
	 */
	public ServidorWeb(Socket s) {
		remote = s;
	}

	public void run() {
		logger.info("Conexion, Enviando datos.");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					remote.getInputStream()));
			logger.info("Generando el buffer de entrada");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error: " + e);
		}
		PrintWriter out = null;
		try {
			out = new PrintWriter(remote.getOutputStream());
			logger.info("Generando el buffer de entrada");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error: " + e);
		}
		estr = new EstructuraDeArchivos();
		estr.generarSistemaDeArchivos();
		String str = ".";
		while (!str.equals("")) {
			try {
				str = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Error: " + e);
			}
		}
		responderHtml(out, estr);
		try {
			remote.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error: " + e);
		}
	}

	/**
	 * Envia el codigo html que se va a mostrar en la pagina web
	 * 
	 * @param out
	 *            Objeto del tipo PrintWriter
	 * @param estr
	 *            Estructura de archivos a mostrar en la pagina web
	 */
	private static void responderHtml(PrintWriter out, EstructuraDeArchivos estr) {
		// Manda la respuesta
		// Manda las cabeceras
		out.println("HTTP/1.0 200 OK");
		out.println("Content-Type: text/html");
		out.println("Servidor: Bot");
		// esta linea en blanco indica el final de las cabecera
		out.println("");
		// Mandamos la pagina HTML
		out.println("<H1>Bienvenidos al servidor Web</H2>");
		out.println("<p><b>SISTEMA DE ARCHIVOS</b></p>");
		for (int i = 0; i < estr.getCodigo().length; i++) {
			if (estr.getCodigo()[i] == null)
				break;
			else
				out.println(estr.getCodigo()[i]);
		}
		out.flush();
		out.close();
	}

	public static void main(String args[]) {
		String resource = "/auditoria.properties";
		URL configFileResource = ServidorWeb.class.getResource(resource);
		PropertyConfigurator.configure(configFileResource);
		
		ServerSocket s = null;
		logger.info("Creando el servidor inicial");
		try {
			// Crea el socket del servidor inicial
			s = new ServerSocket(PUERTO_SOCKET);
			logger.info("Asignando Socket al servidor inicial. Puerto: "
					+ PUERTO_SOCKET);
			while (true) {
				try {
					logger.info("Esperando conexion...");
					Socket remote = s.accept();
					logger.info("Conexion con el Puerto " + PUERTO_SOCKET
							+ " establecida");
					Runnable servicio = new ServidorWeb(remote);
					Thread t = new Thread(servicio);
					t.start();
				} catch (Exception e) {
					logger.error("Error: " + e);
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
			return;
		} finally {
			try {
				s.close();
				logger.info("Cerrando la conexion");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Error: " + e);
			}
		}
	}
}