package TC;

import org.apache.log4j.Logger;

public abstract class Ordenacion {

    private Logger logger = Logger.getRootLogger();
    protected Arreglos arreglo;

    public abstract void Ordenar();


}
