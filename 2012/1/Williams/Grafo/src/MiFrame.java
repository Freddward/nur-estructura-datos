
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Williams
 */
public class MiFrame extends JFrame implements ActionListener,MouseListener{
    private static Logger logger = Logger.getLogger(MiFrame.class);
    private boolean estadoCreacion;
    private JMenuBar menu;
    private JMenu crear;
    private JMenuItem nuevaClase;
    private  JMenuItem nuevaRelacion;
    private JMenu relacion;
    private Grafo<DibujaClase> grafo;
    private MiPanel panel;
    public MiFrame() {
        grafo=new Grafo<DibujaClase>();
        estadoCreacion=false;
        panel=new MiPanel(grafo);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==nuevaRelacion){
            String A=JOptionPane.showInputDialog("Nombre de la Clase");
            String B=JOptionPane.showInputDialog("Nombre de la clase que desee relacionar");
            logger.info("Se relaciono la clase "+A+" con la clase "+B);
            grafo.conectar(A, B);
       }
       if(e.getSource()==nuevaClase)estadoCreacion=!estadoCreacion;
      repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if(estadoCreacion){
           String A=JOptionPane.showInputDialog("Nombre de la Clase");
           logger.info("Se creo la clase "+A);
           estadoCreacion=false;
           DibujaClase nuevo=new DibujaClase(e.getX(),e.getY(),A);
           grafo.addNodo(A, nuevo);    
       } 
       else{
           Iterator<Nodo<DibujaClase>> i = grafo.getNodos().values().iterator();
            while(i.hasNext()){
            Nodo<DibujaClase> n = i.next();
            if(n.getContenido().areaClase().contains(e.getPoint())) {
                if(n.getContenido().getRectanguloAtributo().contains(e.getX(), e.getY())){
                    String A=JOptionPane.showInputDialog("Ingrese el nuevo atributo"); 
                    logger.info("se añadio el atributo "+A+" a la clase "+n.getId());
                    n.getContenido().setAtributo(A);   
                }
                if(n.getContenido().getRectanguloMetodo().contains(e.getX(), e.getY())){
                     String A=JOptionPane.showInputDialog("Ingrese el nuevo metodo");
                      logger.info("se añadio el Metodo "+A+" a la clase "+n.getId());
                      n.getContenido().setMetodos(A);
                }
                repaint();
                 return;
            }
        }
       }
       repaint();
    }
    @Override
    public void mousePressed(MouseEvent e) {   
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public static void main(String as[]){
         String resource = "/auditoria.properties";
                URL configFileResource; 
                configFileResource = MiFrame.class.getResource(resource);
                PropertyConfigurator.configure(configFileResource);      
        new MiFrame();
               
    }
}
