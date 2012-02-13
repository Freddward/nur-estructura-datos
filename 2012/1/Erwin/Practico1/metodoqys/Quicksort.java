package metodoqys;

import java.util.Observable;

public class Quicksort extends Observable
{
  private int vec[];

  public Quicksort()
  {    
    vec = new int[200];
    
    
    
  }


  public void Inicializar()
  {
    for (int i = 0; i < vec.length; i++)
    {
      vec[i] = i + 1;
    }
  }

  public int[] getvec()
  {
    return vec;
  }
  public void Desordenarr() {
         for (int i = vec.length - 1; i >= 0; i--) {
             int x = (int) (Math.random() * (i + 1));
             int aux = vec[i];
             vec[i] = vec[x];
             vec[x] = aux;
         }
     }
   
  public void Qordenamiento(int[] a, int primero, int ultimo)
    {
     
     
      int central = (primero + ultimo) / 2;
      int pivote = a[central];
      int i = primero;
      int j = ultimo;

      do
      {
        while (a[i] < pivote)
          i++;
        while (a[j] > pivote)
          j--;
        if (i <= j)
        {
          int tmp = a[i];
          a[i] = a[j];
          
          a[j] = tmp;
          this.setChanged();
          this.notifyObservers();
          i++;
          j--;
         
        }
        try
        {
          
            Thread.sleep(50);
        
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
      }
      
      while (i <= j);
      if (primero < j)
      {
        Qordenamiento(a, primero, j);
      }
      if (i < ultimo)
      {
        Qordenamiento(a, i, ultimo);
      }
    }
  

 
}
