package logica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Frame
  extends JFrame
{
  private Logger logger = Logger.getRootLogger();
  
  private JMenuBar menuBar = new JMenuBar();
  private Grafo<Clase> g = new Grafo<Clase>();
  private JButton jbtAddClase = new JButton();

  private PanelDibujo pDibujo;
  Diagrama d = new Diagrama();
  private JLabel lGraficador = new JLabel();

  Atributo atr = new Atributo();

  private JPanel panelCenter = new JPanel();
  private JButton jbtAddRelacion = new JButton();

  private JPanel jpClase = new JPanel();

  public Frame() {
      try {
          pDibujo = new PanelDibujo(null);
          jbInit();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  private void jbInit() throws Exception {
      this.setJMenuBar(menuBar);
      this.getContentPane().setLayout(null);
      this.setSize(new Dimension(796, 537));
      this.setTitle("Diagramador");
      jbtAddClase.setText("new class");
      jbtAddClase.setBounds(new Rectangle(50, 20, 115, 25));
      jbtAddClase.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      jbtClase_actionPerformed(e);
                  }
              });
      panelCenter.setBounds(new Rectangle(235, 35, 550, 405));
      panelCenter.setLayout(null);
      panelCenter.setBorder(BorderFactory.createLineBorder(Color.black, 1));
      jbtAddRelacion.setText("new relation");
      jbtAddRelacion.setBounds(new Rectangle(50, 70, 115, 25));
      jbtAddRelacion.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      jbtEspecializacion_actionPerformed(e);
                  }
              });
      jpClase.setBounds(new Rectangle(5, 35, 225, 130));
      jpClase.setLayout(null);

      panelCenter.add(pDibujo, BorderLayout.CENTER);
      pDibujo.setBounds(10, 75, 500, 300);
      pDibujo.setVisible(true);
      pDibujo.setBorder(BorderFactory.createLineBorder(Color.black, 1));

      jpClase.add(jbtAddClase, null);
      jpClase.add(jbtAddRelacion, null);

      this.getContentPane().add(jpClase, null);
      this.getContentPane().add(panelCenter, null);
      this.getContentPane().add(lGraficador, null);
  }
  private void jbtClase_actionPerformed(ActionEvent e) {
      clsClase();
      logger.info("ingreso de nueva clase");
  }
  
  public void clsClase(){
      
      Clase c = new Clase();
      String clsNombre = JOptionPane.showInputDialog("agregue clase");
      c.setNombre(clsNombre);
      logger.info("nombre de clase agregada");
      
      atr = new Atributo();
      String atrNombre = JOptionPane.showInputDialog("agregue atributo");
      atr.setNombre(atrNombre);
      logger.info("atributo agregado");
      
      atr.setTipoDato("String");
      c.addAtributo(atr);
      d.anadir(c);
      g.addNodo(clsNombre, c);
      pDibujo.setDiagram(d);
      logger.info("se creo una nueva clase");
      this.repaint();
  }

  private void jbtEspecializacion_actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(null,"apunte de donde a donde quiere hacer la relacion?");
              pDibujo.setTipo(false);
              logger.info("se ingreso a relacionar");
          }

  public static void main(String[] args) {
      Frame frm = new Frame();
      frm.setDefaultCloseOperation(frm.DISPOSE_ON_CLOSE);
      frm.setVisible(true);
    String resource = "/auditoria.properties";
         URL configFileResource;
         configFileResource = Frame.class.getResource(resource);
         PropertyConfigurator.configure(configFileResource);
  }
}
