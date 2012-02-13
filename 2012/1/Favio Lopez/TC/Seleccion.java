package TC;

import org.apache.log4j.Logger;import org.apache.log4j.Logger;

public class Seleccion extends Ordenacion {
    private  Logger logger = Logger.getRootLogger();

    public Seleccion(Arreglos objArreglo) {
        arreglo=objArreglo;
    }

    public void Ordenar() {

        if (arreglo.getArreglo() == null) {
            logger.error("El arreglo esta vacio");
            logger.info("El arreglo debe tener valores para proseguir");
        }
     
        else {
            for (int i = 0; i < arreglo.getArreglo().length; i++) {
                int menorValor = i;
                for (int j = i + 1; j < arreglo.getArreglo().length; j++) {
                    if (arreglo.getArreglo()[j] < 
                        arreglo.getArreglo()[menorValor])
                        menorValor = j;
                }
               arreglo.notificacion();
        try
        {
          Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
          // TODO
        }
        int aux = arreglo.getArreglo()[i];
                arreglo.getArreglo()[i] = 
                arreglo.getArreglo()[menorValor];
                arreglo.getArreglo()[menorValor] = aux;
            }
            logger.info("se ordeno sin problemas");
            
        }
    }
}
