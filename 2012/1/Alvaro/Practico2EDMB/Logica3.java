package mandelbroot;

import java.awt.image.BufferedImage;

import java.util.Observable;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;


public class Logica3
{
    private static Logger logger = Logger.getLogger(Logica3.class);
    private double EjeX,EjeY,posicionX,posicionY,temporal;
    private  double ZOOM = 150;
    private BufferedImage Mandelbroot;
    private  int MAX_ITER=400;
    private double numero1;
    private double numero2;
    public BufferedImage getMandelbroot()
    {
        return Mandelbroot;
    }
        //super("Mandelbrot Set");
    public void setNumero1(double numero1)
    {
        this.numero1 = numero1;
    }
    public double getNumero1()
    {
        return numero1;
    }
    public void setNumero2(double numero2)
    {
        this.numero2 = numero2;
    }
    public double getNumero2()
    {
        return numero2;
    }
    public void setZOOM()
    {
        this.ZOOM +=150;
        logger.info("se esta aumentando el zoom");
    }
    public double getZOOM()
    {
        return ZOOM;
    }
        public Logica3()
        {
            Mandelbroot=new BufferedImage(800,700,BufferedImage.TYPE_INT_RGB);
            ConstruirMandelbroot();
        }
        public void ConstruirMandelbroot()
        {
              String cadena=JOptionPane.showInputDialog("ingrese la coordenada x");
              double numero=Double.parseDouble(cadena);
              this.setNumero1(numero1);
              String cadena2=JOptionPane.showInputDialog("ingrese la coordenada y");
              double numero2=Double.parseDouble(cadena2);
              Mandelbroot = new BufferedImage(800, 700, BufferedImage.TYPE_INT_RGB);
              for (int y = 0; y < 700; y++) 
              {
                  for (int x = 0; x < 800; x++) 
                  {
                      EjeX = EjeY = 0;
                      posicionX = (x - numero) /ZOOM;
                      posicionY = (y - numero2) / ZOOM;
                      int iter = MAX_ITER;
                      while (EjeX * EjeX +EjeY * EjeY < 4 && iter > 0) {//se ve cuales son los puntos divergente
                          temporal = EjeX * EjeX - EjeY * EjeY + posicionX;
                          EjeY = 2.0 * EjeX * EjeY + posicionY;
                          EjeX = temporal;
                          iter--;
                      }
                      Mandelbroot.setRGB(x, y, iter*(750));
                  }
              }
                  logger.info("se esta creo la imagen");
          }

  /*public double getEjeX()
  {
    return EjeX;
  }

  public double getEjeY()
  {
    return EjeY;
  }*/
}
