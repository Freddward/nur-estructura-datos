package ed.histograma.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

/**
 * Clase que permite generar un Histograma de lineas, tomando como referencia
 * los datos obtenidos de los arreglos rojo, verde y azul que tienen un tamano
 * segun el ancho y alto de la imagen cargada mediante la clase Imagen.
 * 
 * @author Rafael Anez
 * 
 */
public class Histograma {

	private int[] rojo;
	private int[] verde;
	private int[] azul;
	private int[] cantidadTipoRojo = new int[256];;
	private int[] cantidadTipoVerde = new int[256];;
	private int[] cantidadTipoAzul = new int[256];;
	private int[] valorDelColorRGB = new int[256];
	private int ancho;
	private int alto;
	private int proporcionDeEscala;
	private int max;

	private Imagen imagen;

	private static Logger logger = Logger.getLogger(Histograma.class);

	/**
	 * Constructor de la clase Histograma. Inicializa los valores que se van a
	 * utilizar en la clase.
	 * 
	 * @param imagen
	 *            Es la imagen que se va a procesar para generar el histograma.
	 */
	public Histograma(Imagen imagen) {
		this.imagen = imagen;
		this.rojo = imagen.getRojo();
		this.verde = imagen.getVerde();
		this.azul = imagen.getAzul();
		this.ancho = imagen.getAncho();
		this.alto = imagen.getAlto();
	}

	/**
	 * Genera el histograma a partir de la informacion obtenida de la imagen
	 * cargada.
	 */
	public void generarHistograma() {
		logger.debug("Generando histograma");
		contarCantidadDeUnColorDePixel();
	}

	/**
	 * Cuenta la cantidad de pixeles que hay de un determinado color tanto de
	 * los rojos, verdes y azules, y los almacena en los arreglos
	 * cantidadTipoRojo, cantidadTipoVerde y cantidadTipoAzul que tienen 256
	 * elementos.
	 */
	protected void contarCantidadDeUnColorDePixel() {
		inicializarPosibleValorDePixel();
		for (int n = 0; n < (valorDelColorRGB.length - 1); n++) {
			int contadorRojo = 0;
			int contadorVerde = 0;
			int contadorAzul = 0;
			for (int i = 0; i < ((ancho * alto) - 1); i++) {
				if (n < 256) {
					if (valorDelColorRGB[n] == rojo[i])
						contadorRojo++;
					if (valorDelColorRGB[n] == verde[i])
						contadorVerde++;
					if (valorDelColorRGB[n] == azul[i])
						contadorAzul++;
				}
			}
			cantidadTipoRojo[n] = contadorRojo;
			cantidadTipoVerde[n] = contadorVerde;
			cantidadTipoAzul[n] = contadorAzul;
			logger.debug("Cantidad de pixeles de valor "
					+ (valorDelColorRGB[n] + 1) + ": Rojo = "
					+ cantidadTipoRojo[n] + ", Verde = " + cantidadTipoVerde[n]
					+ ", Azul = " + cantidadTipoAzul[n]);
		}
		logger.debug("Conteo de la cantidad de pixeles del mismo valor obtenidos satisfactoriamente");
	}

	/**
	 * Inicializa los valores rojo, azul y verde posibles. Los valores van del 0
	 * al 255.
	 */
	protected void inicializarPosibleValorDePixel() {
		logger.debug("Inicializando posibles valores de cada pixel");
		for (int i = 0; i < (valorDelColorRGB.length - 1); i++)
			valorDelColorRGB[i] = i;
	}

	/**
	 * Dibuja el histograa. Utiliza la informacion de los arreglos
	 * cantidadTipoRojo, cantidadTipoVerde y cantidadTipoAzul.
	 * 
	 * @param gc
	 */
	public void dibujarHistograma(Graphics gc) {
		obtenerProporcionDelHistograma();
		logger.debug("Intentando dibujar el histograma.");
		if (proporcionDeEscala == 0) {
			logger.error("Escala con valor 0. Posible division entre cero. Se muestra mensaje");
			JOptionPane.showMessageDialog(null,
					"Error al generar el Histograma. ", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int valorAumentarEscala = max / 7;
		Font fuente = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18);
		gc.setFont(fuente);
		gc.setColor(Color.BLACK);
		gc.fillRect(100, 0, 3, 900);
		gc.fillRect(0, 750, 1600, 3);
		for (int i = 0; i <= 256; i += 16) {
			String y = String.valueOf(i);
			gc.drawString(y, 105 + (i * 5), 770);
			gc.fillRect(100 + (i * 5), 750, 3, 30);
		}
		int cont = 0;
		for (int i = 0; i <= max; i += valorAumentarEscala) {
			String y = String.valueOf(i);
			gc.drawString(y, 20, 750 - (cont * 100));
			gc.fillRect(70, 750 - (cont * 100), 30, 3);
			cont++;
		}

		for (int i = 0; i < 255; i++) {
			gc.setColor(Color.RED);
			gc.drawLine((i * 5) + 100,
					750 - (cantidadTipoRojo[i] / proporcionDeEscala),
					(((i + 1) * 5) + 100),
					750 - (cantidadTipoRojo[i + 1] / proporcionDeEscala));
			gc.setColor(Color.GREEN);
			gc.drawLine((i * 5) + 100,
					750 - (cantidadTipoVerde[i] / proporcionDeEscala),
					(((i + 1) * 5) + 100),
					750 - (cantidadTipoVerde[i + 1] / proporcionDeEscala));
			gc.setColor(Color.BLUE);
			gc.drawLine((i * 5) + 100,
					750 - (cantidadTipoAzul[i] / proporcionDeEscala),
					(((i + 1) * 5) + 100),
					750 - (cantidadTipoAzul[i + 1] / proporcionDeEscala));

		}
		logger.debug("Histograma dibujado satisfactoriamente.");
	}

	/**
	 * Permite obtener la proporcion del Eje Y del Histograma. En muchos casos
	 * los valores que contienen los arreglos cantidadTipoRojo,
	 * cantidadTipoVerde y cantidadTipoAzul son mayores que el alto de la
	 * ventana, por lo tanto es necesario establecer una proporcion en los
	 * valores de la cantidad de datos, para que puedan caber en la ventana que
	 * muestra el histograma.
	 */
	protected void obtenerProporcionDelHistograma() {
		int maxRojo = 0, maxVerde = 0, maxAzul = 0;
		max = 0;
		proporcionDeEscala = 1;

		for (int i = 0; i < 256; i++) {
			if (cantidadTipoRojo[i] > maxRojo)
				maxRojo = cantidadTipoRojo[i];
			if (cantidadTipoVerde[i] > maxVerde)
				maxVerde = cantidadTipoVerde[i];
			if (cantidadTipoAzul[i] > maxAzul)
				maxAzul = cantidadTipoAzul[i];
		}
		if (maxRojo > maxVerde && maxRojo > maxAzul) {
			max = maxRojo;
		} else if (maxVerde > maxAzul) {
			max = maxVerde;
		} else {
			max = maxAzul;
		}
		logger.debug("Valor maximo del eje Y del Histograma = " + max);
		if (max <= 700)
			proporcionDeEscala = 1;
		else
			proporcionDeEscala = max / 700;

		logger.debug("Proporcion del histograma = " + proporcionDeEscala);
	}
}
