/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import java.util.Observable;

import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Roberto A Pinto S
 */
public class Ordenamientos extends Observable {
     private static Logger logger = Logger.getLogger(Ordenamientos.class);
    
    private int[] Arreglo;
    
 public Ordenamientos(int tamanoArreglo) 
   {
      Arreglo =new int[tamanoArreglo];
        for (int i = 0; i < Arreglo.length; i++) 
        {
            Arreglo[i]=i+1;      
        }
        logger.info("Inicializamos el arreglo");
        //logger.info("Inicializamos el arreglo");
       this.desordenar();  
       
    }


    public void desordenar()
     {
      for (int i = 0; i < Arreglo.length; i++) {
      
          int nuevaposicion=
          (int)(Math.random()*(double)Arreglo.length);
          int swap =Arreglo[i];
          Arreglo[i]=Arreglo[nuevaposicion];
          Arreglo[nuevaposicion]=swap;
      }
      //logger.info("Desordenamos el arreglo");
      logger.info("Desordenamos el arreglo");
     }
   
   public void Insercion(int[] Arreglo)
    {      
      // System.out.println("dadad");
    for (int i = 1; i < Arreglo.length; i++) 
        {
            int j = i - 1;
            int aux = Arreglo[ i ];
            while ( j >= 0  && Arreglo[ j ] > aux ) 
            {
                    Arreglo[j + 1] = Arreglo[ j ];
                    j--;
            }
            Arreglo[j + 1] = aux;
         }
        logger.info("Ordenamiento con el metodo Insercion");
    }
    public void Seleccion(int[] Arreglo)
     {System.out.println("jolald");
        
         for ( int i = 0; i < Arreglo.length; i++)
         {
             //logger.info("Metodo de ordenacion de Seleccion");
                 int menor = i;
                 for (int j = i + 1; j < Arreglo.length; j++) 
                 {
                         if (Arreglo[ j ] < Arreglo[ menor ])
                                 menor = j;
                 }
                 int aux = Arreglo[ i ];
                 Arreglo[ i ] = Arreglo[ menor ];
                 Arreglo[ menor ] = aux;            
         }
         
                 
     }
     public void quiksort(int Arreglo[],int iz,int de)
     {
         //System.out.println("Holas");
        // logger.info("Metodo de ordenacion de QuickSort");
       int t, i=iz, d=de, mid;
    
       if(de>iz)
       {
         mid=Arreglo[(iz+de)/2];
         while(i<d)
         {
           while((i<de)&&(Arreglo[i]<mid))  ++i;
           while((d>iz)&&(Arreglo[d]>mid))  --d;
           if(i<=d)
           {
             t    = Arreglo[i];
             Arreglo[i] = Arreglo[d];
             Arreglo[d] = t;
             
             ++i;
             --d;
             /**this.setChanged();
             this.MarcarCambio();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        java.util.logging.Logger.getLogger(Ordenamientos.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
           }
           
         }
    
         if(iz<d) quiksort(Arreglo,iz,d);
         if(i<de) quiksort(Arreglo,i,de);
         
       }
       
     }
    public void setArreglo(int arreglo)
    {
        Arreglo =new int[arreglo];
        for (int i = 0; i < Arreglo.length; i++) 
        {
            Arreglo[i]=i+1;      
        }
        logger.info("setArreglo modifca el arreglo");
        //this.desordenar();
    }
    
    public static int[] ordenarSimple(int[] elarreglo) 
    {
        for (int i = 0; i < elarreglo.length; i++) 
        {
            for (int j = i + 1; j < elarreglo.length; j++) 
            {
                if (elarreglo[i] > elarreglo[j]) 
                {
                    int aux = elarreglo[i];
                    elarreglo[i] = elarreglo[j];
                    elarreglo[j] = aux;
                }
            }
        }
        return elarreglo;
        //logger.debug("Metodo de ordenacion simple");
    }

    public int[] getArreglo() {
        
        return Arreglo;
    }

    
    public void MarcarCambio()
    {
        this.setChanged();
        this.notifyObservers();
    } 
}
