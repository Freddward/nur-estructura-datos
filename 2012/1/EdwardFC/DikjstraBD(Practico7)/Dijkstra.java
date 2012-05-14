/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author EdwardFC
 */
public class Dijkstra {

    private Grafo grafo;
    private int[] distancia;
    private int[] anterior;
    

    public Dijkstra(Grafo g) {
        grafo = g;
        distancia = new int[226];
        anterior = new int[226];
        Arrays.fill(distancia, 999);
        Arrays.fill(anterior, 0);
    }
    
    public Vector<Integer> getRecorrido(int de, int a) {//224 0
       Vector<Integer> recorrido = new Vector<Integer>();
        int fin = a;
        while (true){
           
            recorrido.add(fin);
            fin = anterior[fin];
            if(fin==de)break;
        }
        for (int i = 0; i < anterior.length; i++) {
            System.out.println(i +" "+anterior[i]+" "+distancia[i]);
        }
        
        return recorrido;

    }
    public  void reiniciar(){
      Iterator<Nodo<Integer>> i=grafo.getNodos().values().iterator();
      while(i.hasNext())i.next().setVisitado(false);
    }
    public Vector<Integer> distanciaMasCorta(int de, int a) {
        reiniciar();
        Vector<Nodo<Integer>> porVisitar=new Vector<Nodo<Integer>>();
        porVisitar.add(new Nodo<Integer>(""+de,de));
        distancia[de]=0;
        for (int i = 0; i <porVisitar.size(); i++) {
            Nodo<Integer> visitado=grafo.getNodos().get(porVisitar.get(i).getId());//1
            Iterator<Nodo<Integer>> i2 = visitado.getConexiones().values().iterator();
            visitado.setVisitado(true);
            while (i2.hasNext()){
                Nodo<Integer> n = i2.next();
                if(n.isVisitado()==false){
                    porVisitar.add(n);
                   // n.setVisitado(true);
                    this.calculo(visitado,n);
                }
            }
        }
        return this.getRecorrido(de, a);
    }
     public void calculo(Nodo<Integer> de,Nodo<Integer> a){
        anterior[a.getContenido()]=de.getContenido();
        int nuevaDistancia=distancia[de.getContenido()]+1;
        distancia[a.getContenido()]=distancia[a.getContenido()]>nuevaDistancia?nuevaDistancia:distancia[a.getContenido()];
    }
}
