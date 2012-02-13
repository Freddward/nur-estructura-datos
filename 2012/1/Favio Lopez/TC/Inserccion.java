package TC;


import java.util.Observable;
import org.apache.log4j.Logger;

public class Inserccion extends Ordenacion {
    private Logger logger = Logger.getRootLogger();

    public Inserccion(Arreglos objArreglo) {
        arreglo = objArreglo;
    }

    public void Ordenar() {

        if (arreglo.getArreglo() == null) {
            logger.error("El arreglo esta vacio");
            logger.warn("El arreglo debe tener valores para proseguir");
        } else {
            for (int i = 1; i < arreglo.getArreglo().length; i++) {
                int j = i - 1;
                int aux = arreglo.getArreglo()[i];
                while (j >= 0 && arreglo.getArreglo()[j] > aux) {
                    arreglo.getArreglo()[j + 1] = 
                            arreglo.getArreglo()[j];
                    j--;
                }
                arreglo.getArreglo()[j + 1] = aux;
                this.arreglo.notificacion();

        try
        {
          Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
          // TODO
        }
      }
        }
    
    }
}
