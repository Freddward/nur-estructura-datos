/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gato;

/**
 *
 * @author Williams
 */

public class Arbol {
    private Nodo raiz;


    public Arbol() {
        super();
        raiz = null;

    }
    public void setRaiz(Arbol.Nodo raiz) {
        this.raiz = raiz;
    }

    public Arbol.Nodo getRaiz() {
        return raiz;
    }


    static class Nodo {
      private int[][] contenido;
      private CadenaDoble hijos;

      public Nodo(int[][] o) {
          contenido = o;

          hijos = new CadenaDoble();
      }
      public void insertarHijo(int[][] obj){
        hijos.Insertar(obj);
      }

      public String toString() {
          return contenido + "(" + hijos + ")";
      }

      public void setContenido(int[][] contenido) {
          this.contenido = contenido;
      }

      public int[][] getContenido() {
          return contenido;
      }

  }
}