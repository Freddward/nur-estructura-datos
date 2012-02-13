/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica1;

import java.util.Observable;

import org.apache.log4j.Logger;

/**
 *
 * @author AlvaroPasquierTufiño
 */
public class Arreglo extends Observable 
{
    private static Logger logger = Logger.getLogger(Arreglo.class);
    private int[] arr;
    public int aux;
    private int TAM_MAX=200;
    private boolean estado=true;

    public boolean isEstado() 
    {
        return estado;
    }
    public Arreglo(int[]arr,int aux,Logger logger)
    {
        this.arr=arr;
        this.aux=aux;
        this.logger=logger;
    }

    public int getTAM_MAX() 
    {
        return TAM_MAX;
    }
    public int[] getArr() 
    {
       logger.info("se va a obtener el arreglo");
       return arr;
    }
    public Arreglo(int n) 
    {
        super();
        logger.info("se va a dar el tamaño al arreglo");
        arr = new int[n];
    }
//    public void inicializar() 
//    {
//        for (int i = 0; i < arr.length; i++) 
//        {
//            int x = (int) (Math.random() * 100.0);
//            arr[i] = x;
//        }
//    }
    public void inicializar()
    {   
        logger.info("Se esta incializando el arreglo");
        if(arr.length<=TAM_MAX)
        {
            for(int i=0;i<arr.length;i++)
            {
                arr[i]=i;
                logger.info("se esta recorriendo el arreglo ordenadamente");
            }
                for (int i = 0;i<arr.length; i++) 
                {
                    logger.info("se esta imprimiendo el arreglo");
                    System.out.println("["+arr[i]+"]");
                }
                    desordenar();
                    logger.info("se esta llamando al metodo desordenar");
        }
        else
        {
            logger.info("se paso de tamaño maximo");
            System.err.println("se paso del tamaño maximo que es 200 ");
            //JOptionPane.showMessageDialog( "ingrese otro tamaño porfavor");
        }
    }
    public void desordenar()
    {
        logger.info("se esta desordenando el arreglo ordenado");
        for (int i = 0; i <arr.length; i++) 
        {
            int x = (int) (Math.random() * (double)arr.length);
            int cambio=arr[i];
            arr[i]=arr[x];
            arr[x]=cambio;
        }
    }
    public void BubleSort() 
    {
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<i;j++)
            {
                if(arr[i]<arr[j])
                {
                    int ordenado=arr[j];
                    arr[j]=arr[i];
                    arr[i]=ordenado;
                    
                        
                }
            }
                this.setChanged();
                this.notifyObservers();
                try
                {
                    Thread.sleep(50);
                }
                    catch (Exception e)
                    {
                            
                    }
        }
     }
    public void QuickSort(int a[],int primero,int ultimo)
    {
        int pivote=a[(primero+ultimo)/2];
        int i=primero;
        int j=ultimo;
        do
        {
            while(a[i]<pivote)i++;
            while(a[j]>pivote)j--;
            if(i<=j)
            {
                int tmp=a[i];
                a[i]=a[j];
                a[j]=tmp;
                i++;
                j--;
                this.setChanged();
                this.notifyObservers();
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                }
            }
        }
          while(i<=j);
          if(primero<j)
          QuickSort(a,primero,j);
          if(ultimo>i)
          QuickSort(a,i,ultimo);
    }
        public String toString()
    {
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < arr.length; i++) 
        {
            resultado.append(arr[i] + " ");
        }
          return resultado.toString();
      }
}
