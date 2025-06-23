package View;
import javax.swing.*;

import Controller.Control;

import java.awt.event.*;
import Model.*;

public class JanelaInicial extends JFrame {
	
	public JanelaInicial(String titulo) {
		super(titulo);
		
		JButton iniciar_jogo = new JButton("Iniciar Jogo");
		JButton continuar_jogo = new JButton("Continuar Jogo"); //Apenas na 4� Itera��o
		JPanel p = new JPanel();
		
		iniciar_jogo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				Frame tabuleiro = new Frame();
				tabuleiro.setTitle("Xadrez");
		    	tabuleiro.setVisible(true);
			}
		});
		
		continuar_jogo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Control.getController().carregarJogo(JanelaInicial.this);
				
				dispose();
				
				Frame tabuleiro = new Frame();
				tabuleiro.setTitle("Xadrez");
		    	tabuleiro.setVisible(true);
			}
			
		});
		
		
		p.add(iniciar_jogo);
		p.add(continuar_jogo);
		

		getContentPane().add(p);
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
}
