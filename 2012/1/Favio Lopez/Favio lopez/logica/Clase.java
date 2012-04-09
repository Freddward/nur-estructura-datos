package logica;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;

public class Clase extends Dibujo
{

      private Logger logger = Logger.getRootLogger();
      
      private Vector<Atributo> atributos;
      protected String nombre;
      protected Vector<relacionNormal> relaciones;
      protected Vector<Metodo> metodos;
      protected String tipo;
      public static final int ANCHO = 100, ALTURA = 20, SEPARACION = 50;


      public Clase() {
          tipo = "Clase";
          relaciones = new Vector<relacionNormal>();
          metodos = new Vector<Metodo>();
          atributos = new Vector<Atributo>();
      }

      public void setNombre(String nombre) {
          this.nombre = nombre;
      }

      public String getNombre() {
          return nombre;
      }

      public void setRelaciones(Vector<relacionNormal> relaciones) {
          this.relaciones = relaciones;
      }

      public Vector<relacionNormal> getRelaciones() {
          return relaciones;
      }

      public void setMetodos(Vector<Metodo> metodos) {
          this.metodos = metodos;
      }

      public Vector<Metodo> getMetodos() {
          return metodos;
      }

      public String getTipo() {
          return tipo;
      }

      @Override
      public void dibujar(Graphics g) {
          dibujarClase(g, x, y);
          logger.info("clase dibujada");
      }

      private void dibujarClase(Graphics g, int x, int y) {
          area.width = ANCHO;
          g.setColor(Color.black);
          g.drawString(nombre, x + 10, y + 15);
          g.setColor(Color.BLUE);
          g.drawRect(x, y, ANCHO, ALTURA);
          int h = (atributos.size()+1) * 20;
          g.drawRect(x, y + ALTURA, ANCHO, h);
          area.height = h;
          Iterator<Atributo> itAtributos = atributos.iterator();
          int posY = y + ALTURA * 2;
          while (itAtributos.hasNext()) {
              Atributo atr = itAtributos.next();
              atr.setX(x);
              atr.setY(posY);
              posY += 20;
              g.setColor(Color.BLACK);
              atr.dibujar(g);
          }
          Iterator<relacionNormal> itrela = relaciones.iterator();
          while (itrela.hasNext()) {
              relacionNormal rel = itrela.next();
              rel.dibujar(g);
          }
          h = h + (metodos.size() + 1) * 15;
          Iterator<Metodo> itMetodos = metodos.iterator();
          posY = h + 10;
          while (itMetodos.hasNext()) {
              Metodo met = itMetodos.next();
              met.setX(x);
              met.setY(posY);
              posY += 20;
              met.dibujar(g);
          }
      }

      public void addAtributo(Atributo a) {
          if (!existeAtributo(a.getNombre()))
              atributos.addElement(a);
              logger.info("atributo añadido");
      }

      public void addRelacciones(Clase cls2) {
     
              relacionNormal rn = new relacionNormal();
              rn.setReferencia(this);
              rn.relacionar(cls2);
              relaciones.add(rn);        
      }

      public boolean existeAtributo(String nombre) {
          Iterator<Atributo> itAtributos = atributos.iterator();
          while (itAtributos.hasNext()) {
              Atributo atr = itAtributos.next();
              if (atr.getNombre().equals(nombre))
                  return true;
                  logger.info("existe atributo(s)");
          }
          logger.info("no hay atributos registrados");
          return false;
          
      }
}
