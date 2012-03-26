/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gato;

/**
 *
 * @author Williams
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;

import javax.swing.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Botones extends  JFrame implements ActionListener 
{
        private static Logger logger = Logger.getLogger(Botones.class);
	static String nombres[]={" ","Computadora","Hubo un empate"};
	private JButton [][]botones;
	private JButton reiniciar;
	private Panel panelPrincipal;
	private Panel panel;
	private int matriz[][];
	private int empate;
        //private IA inteligenciaArtificial;
	public Botones() 
	{
		super("Tres en raya");
                logger.info("Inicio Juego");
              // inteligenciaArtificial=new IA();
		int matriz2[][]={{9,9,9},{9,9,9},{9,9,9}};
		matriz=matriz2;
		empate=0;
		botones= new JButton[3][3];
		panel=new Panel(new GridLayout(3,3));
		reiniciar=new JButton("Reiniciar");
		panelPrincipal= new Panel(new BorderLayout(1,1));
	}
    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }  
	public void setBotones()
	{
		nombres[0]=JOptionPane.showInputDialog("Nombre del primer jugador 1");
                logger.info("nombre del jugador es " + nombres[0]);
		for (int i = 0; i <3 ; i++) {
			for (int j = 0; j < 3; j++) {
				botones [i][j]=new JButton(" ");
				panel.add(botones[i][j]);
				botones[i][j].addActionListener(this);
			}
		}
		reiniciar.addActionListener(this);
		panelPrincipal.add(panel, BorderLayout.CENTER);
		panelPrincipal.add(reiniciar, BorderLayout.SOUTH);
		this.add(panelPrincipal);
                setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (reiniciar==e.getSource())reinicio();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
                        if(botones[i][j]==e.getSource())
			{
					matriz[i][j]=0;
					botones[i][j].setText("X");
					botones[i][j].setEnabled(false);
                                        logger.info("Jugador "+nombres[0]+ " le dio en el boton" +i+" "+j);
					this.regla(i,j);  
                                  
                                        IA inteligenciaArtificial=new IA();
                                        inteligenciaArtificial.PosiblesJugada(matriz);
                                        int[]posicion=inteligenciaArtificial.getJugadaFinal();
					matriz[posicion[0]][posicion[1]]=1;
                                        botones[posicion[0]][posicion[1]].setText("0");
                                        botones[posicion[0]][posicion[1]].setEnabled(false);
                                        logger.info("la Computadora le dio en el boton "+posicion[0]+" "+posicion[1]);
                                       this.regla(posicion[0],posicion[1]);		
			}
		}
	}	
	}
	public void regla(int x,int y)
	{
		int Ganador=5;
		if(empate==8)Ganador=2;
		if(x==0 && y==0) if (matriz[x+1][y+1]==(matriz[x][y]) && matriz[x+2][y+2]==(matriz[x][y]) || matriz[x][y+1]==(matriz[x][y]) && matriz[x][y+2]==(matriz[x][y]) || matriz[x+1][y]==(matriz[x][y]) && matriz[x+2][y]==(matriz[x][y]))Ganador=matriz[x][y];
		if(x==0	&& y==1) if (matriz[x][y-1]==(matriz[x][y]) && matriz[x][y+1]==(matriz[x][y]) || matriz[x+1][y]==(matriz[x][y]) && matriz[x+2][y]==(matriz[x][y]))Ganador=matriz[x][y];
		if(x==0 && y==2) if (matriz[x][y-1]==(matriz[x][y]) && matriz[x][y-2]==(matriz[x][y]) || matriz[x+1][y]==(matriz[x][y]) && matriz[x+2][y]==(matriz[x][y])|| matriz[x+1][y-1]==(matriz[x][y]) && matriz[x+2][y-2]==(matriz[x][y]))Ganador=matriz[x][y];
		if(x==1 && y==0) if (matriz[x-1][y]==(matriz[x][y]) && matriz[x+1][y]==(matriz[x][y]) || matriz[x][y+1]==(matriz[x][y]) && matriz[x][y+2]==(matriz[x][y]))Ganador=matriz[x][y];
		if(x==1 && y==1) if (matriz[x-1][y-1]==(matriz[x][y]) && matriz[x+1][y+1]==(matriz[x][y]) || matriz[x-1][y+1]==(matriz[x][y]) && matriz[x+1][y-1]==(matriz[x][y]) || matriz[x][y-1]==(matriz[x][y]) && matriz[x][y+1]==(matriz[x][y])|| matriz[x-1][y]==(matriz[x][y]) && matriz[x+1][y]==(matriz[x][y]))Ganador=matriz[x][y];
		if(x==1 && y==2) if (matriz[x-1][y]==(matriz[x][y]) && matriz[x+1][y]==(matriz[x][y]) || matriz[x][y-1]==(matriz[x][y]) && matriz[x][y-2]==(matriz[x][y]))Ganador=matriz[x][y];
		if(x==2 && y==0) if (matriz[x-1][y]==(matriz[x][y]) && matriz[x-2][y]==(matriz[x][y]) || matriz[x][y+1]==(matriz[x][y]) && matriz[x][y+2]==(matriz[x][y]) || matriz[x-1][y+1]==(matriz[x][y]) && matriz[x-2][y+2]==(matriz[x][y]))Ganador=matriz[x][y];
                if(x==2 && y==1) if (matriz[x][y+1]==(matriz[x][y]) && matriz[x][y-1]==(matriz[x][y]) || matriz[x-1][y]==(matriz[x][y]) && matriz[x-2][y]==(matriz[x][y]))Ganador=matriz[x][y];
                if(x==2 && y==2) if (matriz[x][y-1]==(matriz[x][y]) && matriz[x][y-2]==(matriz[x][y]) || matriz[x][y-1]==(matriz[x][y]) && matriz[x][y-2]==(matriz[x][y]) || matriz[x-1][y-1]==(matriz[x][y]) && matriz[x-2][y-2]==(matriz[x][y]))Ganador=matriz[x][y];
		empate++;
	    if (Ganador!=5)	
                {
				System.out.println("El Ganador fue " +nombres[Ganador]);
                                logger.info("ganador es " +nombres[Ganador] );
				bloquear();
                }
	}
	public void reinicio()
	{
		for (int i = 0; i < 3; i++) 
		{
			for (int j = 0; j < 3; j++)
			{
				botones[i][j].setEnabled(true);
				botones[i][j].setText(" ");
				matriz[i][j]=9;
				empate=0;
			}
		}
	}
	public void bloquear()
	{
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				botones[i][j].setEnabled(false);
			}
		}
	}
        public static void main(String[] args) {
		String resource = "/auditoria.properties";
                URL configFileResource; 
                configFileResource = Botones.class.getResource(resource);
                PropertyConfigurator.configure(configFileResource);
                Botones obj=new Botones();
		obj.setBotones();
                
	}
}


