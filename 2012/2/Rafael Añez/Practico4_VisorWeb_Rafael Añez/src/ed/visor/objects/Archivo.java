package ed.visor.objects;

import java.io.File;

/**
 * Clase que permite trabajar con los archivos del sistema de archivos
 * 
 * @author Rafael Anez
 * 
 */
public class Archivo {
	private String nombre;
	private String ruta;
	private boolean esCarpeta;
	private long tamano;

	private File archivo;

	/**
	 * Constructor de la clase Archivo
	 * 
	 * @param archivo
	 *            Instancia de la clase File
	 */
	public Archivo(File archivo) {
		this.archivo = archivo;
		this.ruta = archivo.getPath();
		this.nombre = archivo.getName();
		this.tamano = archivo.length();
		this.esCarpeta = archivo.isDirectory();
	}

	/**
	 * Permite obtener el nombre del archivo
	 * 
	 * @return El nombre del archivo
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Permite obtener si el archivo es carpeta o no
	 * 
	 * @return Un valor true o false
	 */
	public boolean isEsCarpeta() {
		return esCarpeta;
	}

	/**
	 * Permite obtener el tamano del archivo
	 * 
	 * @return El tamano el archivo en bytes
	 */
	public long getTamano() {
		return tamano;
	}

	/**
	 * Permite obtener la ruta del archivo en el sistema de archivos de la PC
	 * 
	 * @return Ruta del archivo
	 */
	public String getRuta() {
		return ruta;
	}
}
