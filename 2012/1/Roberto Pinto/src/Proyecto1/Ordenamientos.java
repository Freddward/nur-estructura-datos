/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto1;

import java.util.Observable;
import java.util.logging.Level;
import org.apache.log4j.Logger;

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
      logger.info("Inicializamos el arreglo");      
        for (int i = 0; i < Arreglo.length; i++) 
        {
            Arreglo[i]=i+1;      
        }
       desordenar();  
       logger.error("llamamos el metodo desrdenar, para desordenarlo");
      
    }


    public void desordenar()
     {
      for (int i = 0; i < Arreglo.length; i++) {
      
          int nuevaposicion=
          (int)(Math.random()*(double)Arreglo.length);
          int swap =Arreglo[i];
          Arreglo[i]=Arreglo[nuevaposicion];
          Arreglo[nuevaposicion]=swap;
          logger.error("Este metodo debe ser creado randomicamente si no no sera desordenado");
      }
     }
   
   public void Insercion(int[] Arreglo)
    {      
       
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
        
    }
    public void Seleccion(int[] Arreglo)
     {
        
         for ( int i = 0; i < Arreglo.length; i++)
         {
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
             this.MarcarCambio();
                   
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
        this.desordenar();
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
