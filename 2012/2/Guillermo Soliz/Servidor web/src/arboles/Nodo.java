/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import org.apache.log4j.Logger;

/**
 *
 * @author G y M
 * Aqui creamos los nodos del arbol, ya sean los hijos o por ultimo las hojas
 */
public class Nodo<E> {
    private static Logger logger = Logger.getLogger(Nodo.class);
      public E contenido;
      public CadenaDoble<Nodo<E>> hijo;
      
      public Nodo(){
          super();
      }
      /**
       * 
       * @param contenido Lo que tiene el nodo dentro
       */
      public Nodo(E contenido) {
          this();
          setContenido(contenido);

          hijo = new CadenaDoble<Nodo<E>>();
          
     
      }
      /**
       * 
       * @return contenido
       */
      public E getContenido() {
		return this.contenido;
	}
      /**
       * 
       * @param contenido El contenido del nodo 
       */

	public void setContenido(E contenido) {
		this.contenido = contenido;
	}
        /**
         * 
         * @return El nodo hijo
         */

        public CadenaDoble<Nodo<E>> getHijo() {
            if(this.hijo == null){
                return new CadenaDoble<Nodo<E>>();
            }
            return this.hijo;
        }
        
        public void setHijos(CadenaDoble<Nodo<E>> hijo){
            this.hijo = hijo;
        }
        /**
         * 
         * @return 0 si es la primera vez, luego el tamano en que se encuentra el hijo
         */
        public int getNumeroDeHijo(){
            if(hijo == null){
                return 0;
            }
            return hijo.tamaño();
        }
      
      /**
         * 
         * @param hijos le coloca el valor de hijo de tipo cadena doble  a hijos de tipo nodo
         */
      public void insertarHijo(Nodo<E> hijos){
        if (hijo == null) {
			hijo = new CadenaDoble<Nodo<E>>();
		}
		hijo.Insertar(hijos);
       // hijos.insertarHijo(hijos);
      }

      public String toString() {
          return contenido + "(" + hijo + ")";
      }
      public void insertarHijosEn(int direccion, Nodo<E> hijos){
          if(direccion == getNumeroDeHijo()){
              insertarHijo(hijos);
          }
      }     
    public String toHtml(String nombre){
        return "    "+ nombre;
    }
  }
