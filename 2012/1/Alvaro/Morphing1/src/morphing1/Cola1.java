package morphing1;

public class Cola1<E> extends Cadena1<E> {
    
    public Cola1() {
        super();
    }
    
    public void push(E obj) {
        this.insertar(obj);
    }
        public E pop (){
            E tope = raiz.getContenido();
            raiz = raiz.getSiguiente();
            return tope;
        }
}
