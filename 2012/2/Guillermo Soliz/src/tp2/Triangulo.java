/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.awt.Color;
import java.awt.Graphics;
import org.apache.log4j.Logger;

/**
 *
 * @author G y M
 * En esta clase se hara el Sierpinsky con triangulos
 */

public class Triangulo extends Fractal {
    
    private Logger logger = Logger.getLogger(Triangulo.class);
    private double DimensionFractal = 0.866;//Math.sin(3.14 / 3.);
    /**
     * 
     * @param prof indica el numero de las iteraciones que se van a realizar
     */
    public Triangulo(int prof) {
        this.profundidad = prof;
        this.nombre = "Triangulo";
    }
    /**
     * 
     * @param g 
     * Es un objeto generico
     */
    @Override
    public void dibujar(Graphics g) {
        hacerTriangulo(200, 30, 356, 300, this.profundidad, g);
    }
    /**
     * 
     * @param x1 posicion para el primer vertice del triangulo, el el eje de las x
     * @param y1 posicion para el primer vertice del triangulo, en el eje de las y
     * @param x2 posicion para el segundo vertice del triangulo, en el eje de las x
     * @param y2 posicion para el segundo vertice del triangulo, en el eje de las y
     * @param n cuando esta en 1 pinta las lineas de las coordenadas y aumenta a 2 para hacer los calculos recursivos para las iteraciones
     * @param gc objeto generico de Graphics
     * @param x3 realiza los calculos para tercer vertice del triangulo en el eje de las x
     * @param xy realiza los calculos para tercer vertice del triangulo en el eje de las y
     * @param puntoMediox1 Realiza los calculos para sacar los puntos medios entre x1 y x2
     * @param puntoMedioy1 Realiza los calculos para sacar los puntos medios entre y1 y y2
     * @param puntoMediox2 Realiza los calculos para sacar los puntos medios entre x3 y x2
     * @param puntoMedioy2 Realiza los calculos para sacar los puntos medios entre y3 y y2
     * @param puntoMediox3 Realiza los calculos para sacar los puntos medios entre x1 y x3
     * @param puntoMedioy3 Realiza los calculos para sacar los puntos medios entre y1 y y3
     */ 
    public void hacerTriangulo(double x1, double y1, double x2, double y2, int n, Graphics gc) {
        
        double dx = (x2 - x1) / 2.;
        double dy = (y2 - y1) / 2.;
        double x3 = x1 + dx - 2 * dy * DimensionFractal;
        double y3 = y1 + dy + 2 * dx * DimensionFractal;
        
        double puntoMediox1 = (x2 + x1) / 2.;
        double puntoMedioy1 = (y2 + y1) / 2.;
        double puntoMediox2 = (x3 + x2) / 2.;
        double puntoMedioy2 = (y3 + y2) / 2.;
        double puntoMediox3 = (x1 + x3) / 2.;
        double puntoMedioy3 = (y1 + y3) / 2.;
       
           if (n == 1) {
            gc.setColor(Color.BLUE.darker());
            gc.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
            gc.drawLine((int) x2, (int) y2, (int) x3, (int) y3);
            gc.drawLine((int) x3, (int) y3, (int) x1, (int) y1);
        } else {
            hacerTriangulo(x1, y1, puntoMediox1, puntoMedioy1, n - 1, gc);
            hacerTriangulo(puntoMediox1, puntoMedioy1, x2, y2, n - 1, gc);
            hacerTriangulo(puntoMediox3, puntoMedioy3, puntoMediox2, puntoMedioy2, n - 1, gc);
            
        }
        
        
    }
}
