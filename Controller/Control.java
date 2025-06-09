package Controller;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.MouseEvent;
import Model.*;
import java.util.ArrayList;
import View.*;


import View.DesenhaTabuleiro;


public class Control {
	
	private static Control controller = null;
	public Tabuleiro tabuleiro = new Tabuleiro();
	private String turno;
	//private boolean notifica_click_em_peca = true;
	private JFrame janela_inicial;
	
	public Control() {
		
		turno = "branco";
		
	}
	
	//Singleton
	public static Control getController() {
		
		if (controller == null) {
			controller = new Control();
		}
		
		return controller;
		
	}
	
	
	public String getTurno() {
		return turno;
	}
	

	public void posicionaPecas(Graphics g2d, int tileWidth, int tileHeight) {
		
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Pecas peca = tabuleiro.matriz[i][j];
				
				if (peca != null) {
					 g2d.drawImage(peca.getImage(), j * tileWidth, i * tileHeight, tileWidth, tileHeight, null);
				}
				
			}
		}
		
	}
	
	public Pecas getPeca(int linha, int coluna) {
		
		return tabuleiro.matriz[linha][coluna];
		
	}
	
	public void atualizaTabuleiro(int linha, int coluna, Pecas peca_jogador) {
		
		tabuleiro.matriz[linha][coluna] = peca_jogador;
		
	}
	
	public void mudancaDeTurno() {
		
		
		//Mudanca de turno
		if (turno == "branco") {
			turno = "preto";
		}
				
		else {
			turno = "branco";
		}
		
		
	}
	
	
	/*
	public void reiniciaJogada() {
		
		peca_selecionada = tabuleiro.matriz[coordenada_y][coordenada_x];
		pinta_quadrados_rosa = false;
		jogada_iniciada = true;
		
		linha_antiga = linha;
		coluna_antiga = coluna;
		
		vetor_de_coordenadas.clear();

		repaint();
		
	}
	*/
	
	
}
