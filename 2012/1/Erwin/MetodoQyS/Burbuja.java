package metodoqys;



import java.util.Observable;


public class Burbuja
  extends Observable
{
  private int vec[];
 

  public Burbuja()
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


  public void Bordenamiento()
  {
    int temp;
    for (int i = 0; i < vec.length; i++)
    {
      for (int j = 0; j < vec.length - 1; j++)
      {
        if (vec[j] > vec[j + 1])
        {
          temp = vec[j];
          vec[j] = vec[j + 1];
          vec[j + 1] = temp;
          this.setChanged();
          this.notifyObservers();
        }

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
  }
 

}


