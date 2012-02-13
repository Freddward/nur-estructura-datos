/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * panelDibujo.java
 *
 * Created on 09-feb-2012, 10:37:06
 */
package Logica;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Williams
 */
public class panelDibujo extends javax.swing.JPanel implements Observer {

    private Lineas lin;
    public panelDibujo() {
        
        lin=new Lineas();
    }
     @Override
    public void paintComponent(Graphics g) {
       
         super.paintComponents(g);
        for (int i=0;i<lin.getLineas().size();i++) {
          
            g.drawLine(lin.getLineas().get(i).getX1(), lin.getLineas().get(i).getY1(), lin.getLineas().get(i).getX2(), lin.getLineas().get(i).getY2());           
         }
            
    }
     @Override
    public void update(Observable o, Object arg) {
        
        this.repaint();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
