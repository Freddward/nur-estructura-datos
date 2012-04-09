
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sun.java2d.loops.DrawRect;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Williams
 */
public class DibujaClase {
    private String nombreClase;
    private Vector<String> atributo;
    private Vector<String> metodos;
    private Rectangle rectanguloCabecera;
    private Rectangle rectanguloAtributo;
    private Rectangle rectanguloMetodo;
    private int X1,Y1;
    public DibujaClase(int x,int y,String nombre) {
        nombreClase=nombre; 
        X1=x;Y1=y;
        atributo=new Vector();
        metodos=new Vector();
        this.rectanguloCabecera = new Rectangle(x,y,150,20);
        this.rectanguloAtributo = new Rectangle(x,this.cordenadaY(rectanguloCabecera),150, 60);
        this.rectanguloMetodo = new Rectangle(x,this.cordenadaY(rectanguloAtributo),150,20);   
    }
    public int puntoMedioX(){
        double temp=(X1+X1+rectanguloMetodo.getWidth())/2;
        return (int)temp;
    }
     public int puntoMedioY(){
        double temp=(Y1+Y1+rectanguloMetodo.getHeight())/2;
        return (int)temp;
    }

    public void AltoAtributo(int altoAtributo) {
        rectanguloAtributo.setSize((int)rectanguloAtributo.getWidth(),(int)rectanguloAtributo.getHeight()+altoAtributo);
         rectanguloMetodo.setLocation((int)rectanguloMetodo.getX(),(int)rectanguloMetodo.getY()+altoAtributo);
    }

    public void AltoMetodo(int altoMetodo) {
        rectanguloMetodo.setSize((int)rectanguloMetodo.getWidth(),(int)rectanguloMetodo.getHeight()+altoMetodo);
    }
    
    public Vector<String> getAtributo() {
        return atributo;
    }
    
    public int getX1() {
        return X1;
    }
    public int getY1() {
        return Y1;
    }
    public Vector<String> getMetodos() {
        return metodos;
    }
    public Rectangle getRectanguloCabecera() {
        return rectanguloCabecera;
    }
    public Rectangle getRectanguloAtributo() {
        return rectanguloAtributo;
    }
    public Rectangle getRectanguloMetodo() {
        return rectanguloMetodo;
    }
    public void setAtributo(String atributo) {
        this.atributo.add(atributo);
    }
    public void setMetodos(String metodos){
        this.metodos.add(metodos);
    }
    public void agregarAtributo(String atributo) {
        this.atributo.add(atributo);
    }
    public void agregarMetodos(String metodos) {
        this.metodos.add(metodos);
    }
    public int cordenadaY(Rectangle r){
        int a=(int)(r.getY()+r.getHeight());
        return a;
    }
    public Rectangle areaClase(){
        int alto=rectanguloAtributo.height+rectanguloCabecera.height+rectanguloMetodo.height;
        return new Rectangle(X1,Y1,rectanguloCabecera.width,alto);
        
    } 
    public String getNombreClase() {
        return nombreClase;
    }
}
