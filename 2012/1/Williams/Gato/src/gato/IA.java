/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gato;

import gato.Arbol.Nodo;
import org.apache.log4j.Logger;

/**
 *
 * @author Williams
 */
public class IA{
    private static Logger logger = Logger.getLogger(IA.class);
    private int x,y;
    private Arbol arbol;
    private int [][] posibles;
    private int [][] jugada;
    private boolean estado;
    private int[]jugadaFinal;
    private int valorJugada;
    public IA(){
        arbol=new Arbol(); 
        jugadaFinal=new int[2];
        estado=true;
        x=0;y=0;
        posibles=new int[3][3];
        jugada=new int [3][3];
        jugadaFinal=new int[2];
        valorJugada=0;
    }
    public void PosiblesJugada(int [][] po){
        jugada=po;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                posibles[i][j]=jugada[i][j];
            }
        }
        
        estado=true;
        
        Arbol.Nodo n=new Nodo(jugada);
        while(estado){
            this.espacioVacio();
            int a[][]=new int [3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                      a[i][j]=jugada[i][j];
                }
            }
            a[x][y]=1;
            this.MejorJugada(a);
            n.insertarHijo(a);
        }
        logger.info("se terminaron de insertar las posibles jugadas");
    }

    public  int[] getJugadaFinal() {
        return jugadaFinal;
    }
    
    public void MejorJugada(int [][]a){
        int temp=0;
        int[][]posible=a;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
              temp+=posible[i][j]==0?-(valor(i,j)):valor(i,j);
              if(posible[i][j]==0)temp-=valor(i,j);
              if(posible[i][j]==1)temp+=valor(i,j);
            }
        }           
        if(valorJugada<temp){
            valorJugada=temp;
            jugadaFinal[0]=x;
            jugadaFinal[1]=y;
        }
    }
    public int valor(int x1,int y1){
        if(x1==0&&y1==0 || x1==0&&y1==2 ||x1==2&&y1==0 ||x1==2&&y1==2)return 3;
        if(x1==0&&y1==1 || x1==1&&y1==0 ||x1==1&&y1==2 ||x1==2&&y1==1)return 4;
        return 5;
   }
    public void espacioVacio(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(posibles[i][j]==9){
                    posibles[i][j]=1;
                    x=i; y=j;
                    return;
                }
            }
        }
        estado=false;
}    
}
