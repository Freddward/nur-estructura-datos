package logica;


import java.awt.Graphics;

public class relacionNormal extends Dibujo
{
  protected Clase referencia;
  protected Clase destino;
  
    public relacionNormal() {
    }

    public void relacionar(Clase obj) {
        if (obj != null) {
            this.x = referencia.x;
            this.y = referencia.y;
            this.destino = obj;
        }
    }

    public void dibujar(Graphics g) {
      g.drawLine(x + Clase.ANCHO, y + 40, destino.x, destino.y + 40);
    }


  public void setReferencia(Clase referencia)
  {
    this.referencia = referencia;
  }

  public Clase getReferencia()
  {
    return referencia;
  }

  public void setDestino(Clase destino)
  {
    this.destino = destino;
  }

  public Clase getDestino()
  {
    return destino;
  }
}
