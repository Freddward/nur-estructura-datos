/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;


/**
 *
 * @author Williams
 */
public class Pantalla extends JFrame implements ActionListener  {
        private static Logger logger = Logger.getLogger(Pantalla.class)
    	private JCheckBox simple,quickshort;
	private JMenuBar menu;
	private JMenu ordenar,archivo;
	private JMenuItem reiniciar,empezar;
        private panelDibujo miPanel;
        private Lineas miMolde;
      
	public Pantalla()
	{
                 //this.logger.info("Inicio el programa");
                 miPanel = new panelDibujo();
                 miMolde = new Lineas();
                 this.empezar1();
                 miMolde.addObserver(miPanel);
                 this.setLocationRelativeTo(null);
                 this.getContentPane().setLayout(new BorderLayout());
                 this.getContentPane().add(miPanel,BorderLayout.CENTER);
                 menu=new JMenuBar();
		 archivo=new JMenu("Archivo");
		 ordenar=new JMenu("Ordenar");
		 empezar=new JMenuItem("Empezar");
		 reiniciar=new JMenuItem("Reiniciar");
		 simple=new JCheckBox("Simple", false);
		 quickshort=new JCheckBox("QuickShort", false);
		 ordenar.add(simple);
		 ordenar.add(quickshort);
		 ordenar.add(empezar);
		 archivo.add(reiniciar);
		 menu.add(archivo);
		 menu.add(ordenar);
                 this.add(menu, BorderLayout.NORTH);
                quickshort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simple.setSelected(false);
            }
        });
                simple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quickshort.setSelected(false);
            }
        });
                empezar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(simple.isSelected()){
                 /*  Thread work=new Thread(new Runnable()
                {
                    @Override
                    public void run() 
                    {
                       Burbuja b =new Burbuja(miMolde);
                      
                    }
                });
                work.start();*/
                new Burbuja(miMolde);
            }

                else new Quickshort(miMolde);   
            }
        });	
                reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezar1();
            }
        });
        }
        public void empezar1(){//se colocan aleatoriamente las lineas 
            Vector<MoldeLinea> si=new Vector();
            miMolde.io(si);
           // this.logger.info("Se reinicio el orden de las lineas");
            for (int i = 0; i < 538; i++) {
                int aleatorio=(int)(Math.random()*510);
               MoldeLinea m=new MoldeLinea(0,aleatorio, i, i);
               i++;
               miMolde.agregarLineas(m);
            }          
        }   
        @Override
    public void actionPerformed(ActionEvent e) {           
    }
}
