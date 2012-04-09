package diagramadeclase;
import java.awt.Rectangle;
import java.util.Vector;
public class DibujarClase {
    private String nombreClase;
    private Vector<String> atributo;
    private Vector<String> metodos;
    private Rectangle rectanguloCabecera;
    private Rectangle rectanguloAtributo;
    private Rectangle rectanguloMetodo;
    private int X1,Y1;
    public DibujarClase(int x,int y,String nombre) {
        nombreClase=nombre;
        X1=x;
        Y1=y;
        atributo=new Vector();
        metodos=new Vector();
        this.rectanguloCabecera = new Rectangle(x,y,150,20);
        this.rectanguloAtributo = new Rectangle(x,this.cordenadaY(rectanguloCabecera),150, 60);
        this.rectanguloMetodo = new Rectangle(x,this.cordenadaY(rectanguloAtributo),150,60);   
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