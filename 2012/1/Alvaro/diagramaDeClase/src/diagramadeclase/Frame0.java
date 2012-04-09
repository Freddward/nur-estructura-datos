package diagramadeclase;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Frame0 extends JFrame implements ActionListener,MouseListener
{
    private boolean estadoCreacion;
        private JMenuBar menu;
        private JMenu crear;
        private JMenuItem nuevaClase;
        private  JMenuItem nuevaRelacion;
        private JMenu relacion;
        private Grafo<DibujarClase> grafo;
        private Panel0 panel;
        public Frame0() {
            grafo=new Grafo<DibujarClase>();
            estadoCreacion=false;
            panel=new Panel0(grafo);
            menu=new JMenuBar();
            relacion=new JMenu("Relacion");
            nuevaRelacion=new JMenuItem("Crear Relacion");
            crear=new JMenu("Crear");
            nuevaClase=new JMenuItem("Crear nueva clase");
            crear.add(nuevaClase);
            relacion.add(nuevaRelacion);
            menu.add(crear);
            menu.add(relacion);
            nuevaClase.addActionListener(this);
            nuevaRelacion.addActionListener(this);
            this.addMouseListener(this);
            this.add(menu, BorderLayout.NORTH);
             this.add(panel,BorderLayout.CENTER);
            this.setSize(580,600);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setLocationRelativeTo(null);  
        }
        
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==nuevaRelacion){
                String A=JOptionPane.showInputDialog("Nombre de la Clase");
                String B=JOptionPane.showInputDialog("Nombre de la clase que desee relacionar");
                grafo.conectar(A, B);
           }
           if(e.getSource()==nuevaClase)estadoCreacion=!estadoCreacion;
          repaint();
        }

        public void mouseClicked(MouseEvent e) {
           if(estadoCreacion==true){
               String A=JOptionPane.showInputDialog("Nombre de la Clase");
               DibujarClase nuevo=new DibujarClase(e.getX(),e.getY(),A);
               grafo.addNodo(A, nuevo);   
               estadoCreacion=false;
           } 
           else{
               Iterator<Nodo<DibujarClase>> i = grafo.getNodos().values().iterator();
                while(i.hasNext()){
                Nodo<DibujarClase> n = i.next();
                if(n.getContenido().areaClase().contains(e.getPoint())) {
                    if(n.getContenido().getRectanguloAtributo().contains(e.getX(), e.getY())){
                        String A=JOptionPane.showInputDialog("Ingrese el nuevo atributo"); 
                        n.getContenido().setAtributo(A);
                     
                    }
                    if(n.getContenido().getRectanguloMetodo().contains(e.getX(), e.getY())){
                         String B=JOptionPane.showInputDialog("Ingrese el nuevo metodo");
                          n.getContenido().setMetodos(B);
                    }
                    repaint();
                     return;
                }
            }
           }
           repaint();
        }
      
        public void mousePressed(MouseEvent e) {   
        }
      
        public void mouseReleased(MouseEvent e) {
        }
        
        public void mouseEntered(MouseEvent e) {
        }
        
        public void mouseExited(MouseEvent e) {
        }
        
        public static void main(String as[]){
                   new Frame0();
                   
        }
    }
  
