package ed.histograma.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

/**
 * Clase que permite cargar una imagen desde el disco duro, obteniendo su ruta.
 * Obtiene el valor RGB de cada uno de sus pixeles, almacena en tres arreglos
 * unidimensinales de tamano 256 distintos la cantidad de pixeles rojo, verde y
 * azul que hay de cada uno de los valores RGB (0 a 255). Mediante la
 * informacion obtenida de cada pixel, pinta en la ventana principal la imagen
 * cargada.
 * 
 * @author Rafael Anez
 * 
 */
public class Imagen {
	private String ruta;
	private int ancho;
	private int alto;
	private int[] rojo;
	private int[] verde;
	private int[] azul;
	private BufferedImage bufimg;

	private static Logger logger = Logger.getLogger(Imagen.class);

	/**
	 * Constructor de la clase Imagen. Inicializa el valor de la ruta de la
	 * imagen.
	 * 
	 * @param ruta
	 */
	public Imagen(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Permite cargar una imagen que se encuentra en el disco duro de la
	 * computadora, utilizando su direccion en el sistema de archivos. Obtiene
	 * el ancho y alto de la imagen, la informacion del color RGB de cada pixel,
	 * y lo almacena en los arreglos unidimensionales de 256 elementos
	 * rojo[256], verde[256] y azul[256]. Proporciona al metodo dibujarPixel los
	 * valores necesarios para dibujar la imagen que muestra la ventana
	 * principal.
	 * 
	 * @param gc
	 */
	public void leerArchivo(Graphics gc) {
		if (ruta == null) {
			logger.error("No hay ruta de la imagen. No se pudo cargar la imagen");
			return;
		}
		try {
			File archivoImagen = new File(ruta);
			bufimg = ImageIO.read(archivoImagen);
			this.ancho = bufimg.getWidth();
			this.alto = bufimg.getHeight();
			rojo = new int[ancho * alto];
			verde = new int[ancho * alto];
			azul = new int[ancho * alto];
			logger.debug("Imagen cargada. " + "Acho =" + ancho + ", Alto = "
					+ alto);
			logger.debug("Obteniendo valor RGB de los pixeles");
			int cont = 0;
			for (int y = 0; y < bufimg.getHeight(); y++) {
				for (int x = 0; x < bufimg.getWidth(); x++) {
					int pixel = bufimg.getRGB(x, y);
					int r = pixel & 0x00ff0000;
					int v = pixel & 0x0000ff00;
					int a = pixel & 0x000000ff;
					v = v >> 8;
					r = r >> 16;
					// logger.debug("Pixel en posicion [" + y + "][" + x + "]"
					// + ". Valores: Rojo = " + r + ", Verde = " + v
					// + ", Azul = " + a);
					rojo[cont] = r;
					verde[cont] = v;
					azul[cont] = a;
					this.dibujarPixel((800 - (ancho / 2) + x),
							(400 - (alto / 2)) + y, r, v, a, gc);
					cont++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dibujarPixel(int posX, int posY, int r, int v, int a,
			Graphics gc) {
		gc.setColor(new Color(r, v, a));
		gc.drawLine(posX, posY, posX, posY);
	}

	/**
	 * Permite obtener la ruta del archivo .jpg o .gif
	 * 
	 * @return
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Permite obtener el ancho de la imagen.
	 * 
	 * @return
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Permite obtener el alto de la imagen.
	 * 
	 * @return
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Permite obtener el arreglo de cantidad de cada pixel rojo.
	 * 
	 * @return
	 */
	public int[] getRojo() {
		return rojo;
	}

	/**
	 * Permite obtener el arreglo de cantidad de cada pixel verde.
	 * 
	 * @return
	 */
	public int[] getVerde() {
		return verde;
	}

	/**
	 * Permite obtener el arreglo de cantidad de cada pixel azul.
	 * 
	 * @return
	 */
	public int[] getAzul() {
		return azul;
	}
}
