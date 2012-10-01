/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author G y M
 * aqui sacamos todos los datos del archivo, como ser la ruta, si es carpeta, da direccion, el nombre
 */
import java.io.File;

public class Archivos {
	private String nombre;
	private String ruta;
	private boolean esCarpeta;
	private long tamano;

	private File archivo;
        /**
         * 
         * @param archivo objeto de tipo File, saca los datos que le pedimos del archivo
         */
	public Archivos(File archivo) {
		this.archivo = archivo;
		this.ruta = archivo.getPath();
		this.nombre = archivo.getName();
		this.tamano = archivo.length();
		this.esCarpeta = archivo.isDirectory();
	}
        /**         *
         * @return nos devuelve el nombre
         */

	public String getNombre() {
		return nombre;
	}

	public boolean isEsCarpeta() {
		return esCarpeta;
	}
        /**
         * 
         * @return nos devuelve el tamaño
         */

	public long getTamano() {
		return tamano;
	}
        /**
         * 
         * @return nos devuelve la ruta
         */

	public String getRuta() {
		return ruta;
	}
}
