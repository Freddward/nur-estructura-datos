/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author G y M
 */
import java.io.File;
import org.apache.log4j.Logger;

public class Recursividad {
    private static Logger logger = Logger.getLogger(Recursividad.class);

    private Nodo<Archivos> raiz;
    private Arbol<Archivos> arb;
    private String[] descripcion = new String[100000];

   
    private int posicion;

    public Recursividad() {
 
        this.posicion = 0;
    }

    
/**
     * Creamosla carpetaBase que es un String, luego se lo pasamos a f que es de tipo File, inicializamos
     * un objeto de tipo Archivo que lo llamaremos base y le pasamos el valor de f osea la carpetaBase
     * inicializamos raiz de tipo nodo<Archivos> y le damos el valos de base
     * inicializamos arb que es un objeto de tipo Arbol<Archivos> 
     * creamos la estructura base con la capeta base y la raiz que vendria a ser el nombre del archivo base
     */
    public void iniciar() {
        
        String carpetaBase = "C://Arbol/"; // 
        File f = new File(carpetaBase); //creamos f de tipo File y le pasamos la carpeta base
        Archivos base = new Archivos(f);
        raiz = new Nodo<Archivos>(base);
        arb = new Arbol<Archivos>();
        arb.setRaiz(raiz);
        generarHtml(arb.getRaiz());
        crearEstructura(carpetaBase, raiz);
        descripcion[posicion]= "";
        
    }
    /**
     * 
     * @param dir la direccion en la que se encuentra
     * @param n la posicion del contenido del nodo
     */
    public void crearEstructura(String dir, Nodo<Archivos> n) {
        File[] files = new File(n.getContenido().getRuta()).listFiles();
		
		for (File f : files) {
			Archivos a = new Archivos(f);
			Nodo<Archivos> hijo = new Nodo<Archivos>(a);
			n.insertarHijo(hijo);
			generarHtml(hijo);
			if (a.isEsCarpeta()) {
				crearEstructura(dir + a.getNombre() + "/", hijo);
				descripcion[posicion] = "";
				posicion++;
			}
		}
    }
    
/**
     * 
     * @param n contenido del nodo
     * aqui comparamos si = a la raiz entonces es la carpeta base, si la carpeta e true entonces es carpet y si 
     * es falso entonces es un archivo y se lo agregamos a la descripcion y la posision en la que se encuentra
     */
    public void generarHtml(Nodo<Archivos> n) {
        if (n == arb.getRaiz()) {
			descripcion[posicion] = arb.toHtml(n.getContenido().getNombre());
                        logger.debug("Obteniendo la carpeta base"+arb.toHtml(n.getContenido().getNombre()));
		} else if (n.getContenido().isEsCarpeta() == true) {
			descripcion[posicion] = n.toHtml(n.getContenido().getNombre().toUpperCase());
                        logger.debug("Es una carpeta"+arb.toHtml(n.getContenido().getNombre()));
		} else {
			descripcion[posicion] = n.toHtml(n.getContenido().getNombre());
                        logger.debug("Es un archivo"+arb.toHtml(n.getContenido().getNombre()));
		}
		
		posicion++;
    }

     public String[] getDescripcion() {
        return descripcion;
    }
    
}