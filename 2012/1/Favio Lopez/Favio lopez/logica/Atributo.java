package logica;

import java.awt.Graphics;

import org.apache.log4j.Logger;

public class Atributo extends Dibujo
{
  
  private String nombre;
  private String tipoDato;
  
  public String toString(){
     String str = "-";
     str += nombre + " : " + tipoDato;
     return str;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public String getNombre() {
       return nombre;
   }

   public void setTipoDato(String tipoDato) {
       this.tipoDato = tipoDato;
   }

   public String getTipoDato() {
       return tipoDato;
   }

   public void dibujar(Graphics g) {
     g.drawString(toString(),x,y);
   }
}
