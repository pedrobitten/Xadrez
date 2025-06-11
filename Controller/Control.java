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
	private JFrame janela_inicial;
	private boolean roque_disponivel_jogador1;
	private boolean roque_disponivel_jogador2;
	private boolean torre1_ja_movimentou_jogador1;
	private boolean torre1_ja_movimentou_jogador2;
	private boolean torre2_ja_movimentou_jogador1;
	private boolean torre2_ja_movimentou_jogador2;
	private boolean rei_ja_movimentou_jogador1;
	private boolean rei_ja_movimentou_jogador2;
	public boolean rei_esta_em_xeque_jogador1;
	public boolean rei_esta_em_xeque_jogador2;
	public int pecas_atacando_rei_jogador1;
	public int pecas_atacando_rei_jogador2;
	private int[] posicao_rei_jogador1 = new int[2];
	private int[] posicao_rei_jogador2 = new int[2];
	
	public Control() {
		
		turno = "branco";
		roque_disponivel_jogador1 = true;
		roque_disponivel_jogador2 = true;
		torre1_ja_movimentou_jogador1 = false;
		torre1_ja_movimentou_jogador2 = false;
		torre2_ja_movimentou_jogador1 = false;
		torre2_ja_movimentou_jogador2 = false;
		rei_ja_movimentou_jogador1 = false;
		rei_ja_movimentou_jogador2 = false;
		rei_esta_em_xeque_jogador1 = false;
		rei_esta_em_xeque_jogador2 = false;
		pecas_atacando_rei_jogador1 = 0;
		pecas_atacando_rei_jogador2 = 0;
		posicao_rei_jogador1[0] = 2; //
		posicao_rei_jogador1[1] = 2; //	
		posicao_rei_jogador2[0] = 4; //teste 
		posicao_rei_jogador2[1] = 4; //
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
	
	public String getTipoPeca(int linha, int coluna) {
		return tabuleiro.matriz[linha][coluna].getPeca();
	}
	
	public String getCorPeca(int linha, int coluna) {
		return tabuleiro.matriz[linha][coluna].getCor();
	}
	
	public void atualizaTabuleiro(int linha, int coluna, Pecas peca_jogador) {
		
		tabuleiro.matriz[linha][coluna] = peca_jogador;
		
	}
	
	public int[] getPosicaoReiJogador1() {
		return posicao_rei_jogador1;
	}
	
	public int[] getPosicaoReiJogador2() {
		return posicao_rei_jogador2;
	}
	
	public void setPosicaoReiJogador1(int linha, int coluna) {
		posicao_rei_jogador1[0] = linha;
		posicao_rei_jogador1[1] = coluna;
	}
	
	public void setPosicaoReiJogador2(int linha, int coluna) {
		posicao_rei_jogador2[0] = linha;
		posicao_rei_jogador2[1] = coluna;
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
	
	
	public void descobreTorre(int coluna_torre, String cor) {
		
		if (cor == "branco") {
			
			if (coluna_torre == 0) {
				
				torre1_ja_movimentou_jogador1 = true;
				
			}
			
			else {

				torre2_ja_movimentou_jogador1 = true;
				
			}
		}
		
		else {
			
			if (coluna_torre == 0) {
				
				torre1_ja_movimentou_jogador2 = true;

			}
			
			else {
				
				torre2_ja_movimentou_jogador2 = true;

			}
			
		}
		
		
		
	}
	
	
	public boolean roque(int linha, int coluna, int linha_antiga, int coluna_antiga, Pecas peca_selecionada) {
		
		int inicio = Math.min(coluna, coluna_antiga) + 1;
        int fim = Math.max(coluna, coluna_antiga);
        
        
        for (int cont = inicio; cont < fim; cont++)
        {
            if (tabuleiro.matriz[linha_antiga][cont] != null) {
                return false;
            }
        }
		
		if (peca_selecionada.getCor() == "branco") {
			
			if (roque_disponivel_jogador1 == false) {
				return false;
			}
			
			if (rei_ja_movimentou_jogador1 == true) {
				return false;
			}
			
			if (coluna != 0) {

			
				
				if (torre2_ja_movimentou_jogador1 == true) {
					return false;
				}
				
				torre2_ja_movimentou_jogador1 = true;
				tabuleiro.matriz[linha_antiga][coluna_antiga + 1] = new Torre("branco", "torre");
				tabuleiro.matriz[linha][coluna - 1] = peca_selecionada;
				
			}
			
			else {
				
				if (torre1_ja_movimentou_jogador1 == true) {
					return false;
				}
				
				torre1_ja_movimentou_jogador1 = true;
				tabuleiro.matriz[linha_antiga][coluna_antiga - 1] = new Torre("branco", "torre");
				tabuleiro.matriz[linha][coluna + 2] = peca_selecionada;
				
			}
			
			rei_ja_movimentou_jogador1 = true;
			roque_disponivel_jogador1 = false;
			
		}
		
		else {
			
			if (roque_disponivel_jogador2 == false) {
				return false;
			}
			
			if (rei_ja_movimentou_jogador2 == true) {
				return false;
			}
			
			if (coluna != 0) {
	
				
				if (torre2_ja_movimentou_jogador2 == true) {
					return false;
				}
				
				torre2_ja_movimentou_jogador2 = true;
				tabuleiro.matriz[linha_antiga][coluna_antiga + 1] = new Torre("preto", "torre");
				tabuleiro.matriz[linha][coluna - 1] = peca_selecionada;
			}
			
			else {
				
				if (torre1_ja_movimentou_jogador2 == true) {
					return false;
				}
				
				torre1_ja_movimentou_jogador2 = true;
				tabuleiro.matriz[linha_antiga][coluna_antiga - 1] = new Torre("preto", "torre");
				tabuleiro.matriz[linha][coluna + 2] = peca_selecionada;
				
			}
			
			rei_ja_movimentou_jogador2 = true;
			roque_disponivel_jogador2 = false;
		}
		
		
	
		tabuleiro.matriz[linha_antiga][coluna_antiga] = null;
		tabuleiro.matriz[linha][coluna] = null;
		

		return true;
		
	}
	
	private boolean estaDentroDosLimites(int linha, int coluna) {
	    return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
	}

	
	private boolean confereXeque(int linha, int coluna) {
		
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
			return false;
		}
		
		//Pecas peca = tabuleiro.matriz[linha][coluna];
		Pecas peca = this.getPeca(linha, coluna);
		
		if (peca != null) {
			
			//return peca.getPeca() == "rei" && !(peca.getCor() == Control.getController().getTurno());
			
			if (peca.getPeca() == "rei" && !(peca.getCor() == Control.getController().getTurno())) {

				
				return true;
			}
			
			
			else {
				return false; //qubra loop
			}
			
			
		}
		
		return false;
	}
	
	//vai depender da peca
	private boolean confereXeque2(int linha, int coluna) {
		
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
			return false;
		}
		
		//Pecas peca = tabuleiro.matriz[linha][coluna];
		Pecas peca = this.getPeca(linha, coluna);
		
		
		if (peca != null) {

			//return peca.getPeca() == "rei" && !(peca.getCor() == Control.getController().getTurno());
			
			if (peca.getCor() == Control.getController().getTurno()) {
				System.out.print("Foi condicao");
				return true;
			}
			
			
			else {
				return false; //qubra loop
			}
			
			
		}
		
		return false;
	}
	
	/*
	public void marcacaoXeque(String cor, Pecas peca) {
		
		if (cor == "branco") {
			rei_esta_em_xeque_jogador2 = true;
		}
		
		else {
			rei_esta_em_xeque_jogador1 = true;
		}
		
		System.out.print(peca);
		peca.mudaEstadoXeque();
		
	}
	*/
	
	
	/*
	public boolean xeque(int linha, int coluna) {
		
		if (this.getPeca(linha, coluna).getPeca() == "bispo") {
			for (int i = 1; i < 8 ; i++)
			{
				int nova_linha = linha + i;
				int nova_coluna = coluna + i;
				
				if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
					break;
				}
				
				if(confereXeque(nova_linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
					break;
				} 
						
			}
			
			for (int i = 1; i < 8 ; i++)
			{
				
				int nova_linha = linha + i;
				int nova_coluna = coluna - i;
				
				if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
					break;
				}
				
				if(confereXeque(nova_linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
					break;
				} 
			
			}
			
			for (int i = 1; i < 8 ; i++)
			{
				int nova_linha = linha - i;
				int nova_coluna = coluna + i;
				
				if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
					break;
				}
				
				if(confereXeque(nova_linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
					break;
				}
				
			}
			
			for (int i = 1; i < 8 ; i++)
			{
				
				int nova_linha = linha - i;
				int nova_coluna = coluna - i;
				
				if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
					break;
				}
				
				if(confereXeque(nova_linha, nova_coluna)) {
	
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
					break;
				}
				
			}
		}
		
		else if (this.getPeca(linha, coluna).getPeca() == "rainha") {
			for (int i = 1; i < 8; i ++)
			{
				int nova_linha = linha + i;
				
				if (!estaDentroDosLimites(nova_linha, coluna)) {
					break;
				}
				
				if (confereXeque(nova_linha, coluna)){
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, coluna) != null) {
					break;
				}
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_linha = linha - i;
				
				if (!estaDentroDosLimites(nova_linha, coluna)) {
					break;
				}
				
				if (confereXeque(nova_linha, coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, coluna) != null) {
					break;
				}
			
			}
			
			for (int i = 1; i < 8; i ++)
			{
				int nova_coluna = coluna + i;
				
				if (!estaDentroDosLimites(linha, nova_coluna)) {
					break;
				}
				
				if (confereXeque(linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(linha, nova_coluna) != null) {
					break;
				}
				
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_coluna = coluna - i;
				
				if (!estaDentroDosLimites(linha, nova_coluna)) {
					break;
				}
				
				if (confereXeque(linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(linha, nova_coluna) != null) {
					break;
				}
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_linha = linha + i;
				int nova_coluna = coluna + i;
				
				if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
					break;
				}
				
				if (confereXeque(nova_linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
					break;
				}
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_linha = linha - i;
				int nova_coluna = coluna + i;
				
				if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
					break;
				}
				
				if (confereXeque(nova_linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
					break;
				}
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_linha = linha + i;
				int nova_coluna = coluna - i;
				
				if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
					break;
				}
				
				if (confereXeque(nova_linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
					break;
				}
				
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_linha = linha - i;
				int nova_coluna = coluna - i;
				
				if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
					break;
				}
				
				if (confereXeque(nova_linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
					break;
				}
				
			}
			
		}
		
		else if (this.getPeca(linha, coluna).getPeca() == "torre") {
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_linha = linha + i;
				
				if (!estaDentroDosLimites(nova_linha, coluna)) break;
				
				if (confereXeque(nova_linha, coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, coluna) != null) {
					break;
				}
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_linha = linha - i;
				
				if (!estaDentroDosLimites(nova_linha, coluna)) break;
				
				if (confereXeque(nova_linha, coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(nova_linha, coluna) != null) {
					break;
				}
				
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_coluna = coluna + i;
				
				if (!estaDentroDosLimites(linha, nova_coluna)) break;
				
				if (confereXeque(linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(linha, nova_coluna) != null) {
					break;
				}
				
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				
				int nova_coluna = coluna - i;
				
				if (!estaDentroDosLimites(linha, nova_coluna)) break;
				
				if (confereXeque(linha, nova_coluna)) {
					return true;
				}
				
				if (Control.getController().getPeca(linha, nova_coluna) != null) {
					break;
				}
							
			}
			
		}
		
		else if (this.getTipoPeca(linha, coluna) == "peao") {
			
			int direcao = (this.getCorPeca(linha, coluna) == "branco") ? -1 : 1;
			
			int nova_coluna_direita = coluna + direcao;
			int nova_coluna_esquerda = coluna - direcao;
			int nova_linha = linha + direcao;
			
			if (confereXeque(nova_linha, nova_coluna_direita)) {
				//System.out.print(this.getPeca(linha, coluna));
				//System.out.print("\n");
				marcacaoXeque(Control.getController().getTurno(), this.getPeca(linha, coluna));
				return true;
			}
			
			if (confereXeque(nova_linha, nova_coluna_esquerda)) {
				//System.out.print(this.getPeca(linha, coluna));
				//System.out.print("\n");
				marcacaoXeque(Control.getController().getTurno(), this.getPeca(linha, coluna));
				return true;
			}
		}
		
		else if (this.getTipoPeca(linha, coluna) == "rei") {
			
			if (confereXeque(linha + 1, coluna)) {
				return true;
			}
			
			if (confereXeque(linha - 1, coluna)) {
				return true;
			}
			
			if (confereXeque(linha, coluna + 1)) {
				return true;
			}
			
			if (confereXeque(linha, coluna - 1)) {
				return true;
			}
			
			if (confereXeque(linha + 1, coluna + 1)) {
				return true;
			}
			
			if (confereXeque(linha - 1, coluna + 1)) {
				return true;
			}
			
			if (confereXeque(linha + 1, coluna - 1)) {
				return true;
			}
			
			if (confereXeque(linha - 1, coluna - 1)) {
				return true;
			}
			
		}
		
		
		else if (this.getTipoPeca(linha, coluna) == "cavalo") {
			
			int[][] offsets = {
	                {+2, +1}, {+2, -1}, {-2, +1}, {-2, -1},
	                {+1, +2}, {+1, -2}, {-1, +2}, {-1, -2}
	        };
			
			for (int[] offset : offsets)
			{
				int nova_linha = linha + offset[0];
				int nova_coluna = coluna + offset[1];
				if (confereXeque(nova_linha, nova_coluna)) {
					return true;
				}
			}
		}
		
		
		return false;
		
	}
	*/
	
	public int xequeRei(int linha, int coluna) {
		
		int pecas_atacando_rei = 0;
		
		//boolean rei_esta_atacando = false;
		//Torre, rainha
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha + i;

			if (!estaDentroDosLimites(nova_linha, coluna)) {
				break;
			}
			
			if (confereXeque2(nova_linha, coluna)){
				
				if (i == 1) {
					
					if (getTipoPeca(nova_linha, coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(nova_linha, coluna) == "torre" || getTipoPeca(nova_linha, coluna) == "rainha"){
					
					pecas_atacando_rei += 1;
					break;
					
				}
				
			}
			
			if (Control.getController().getPeca(nova_linha, coluna) != null) {
				break;
			}
		}
		
		
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha - i;
			
			if (!estaDentroDosLimites(nova_linha, coluna)) {
				break;
			}
			
			
			if (confereXeque2(nova_linha, coluna)) {
				
				if (i == 1) {
					
					if (getTipoPeca(nova_linha, coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(nova_linha, coluna) == "torre" || getTipoPeca(nova_linha, coluna) == "rainha"){

					pecas_atacando_rei += 1;
					break;
					
				}
				
				
			}
			
			if (Control.getController().getPeca(nova_linha, coluna) != null) {
				break;
			}
		
		}
		
		for (int i = 1; i < 8; i ++)
		{
			int nova_coluna = coluna + i;
			
			if (!estaDentroDosLimites(linha, nova_coluna)) {
				break;
			}
			
			if (confereXeque2(linha, nova_coluna)) {
				
				if (i == 1) {
					
					if (getTipoPeca(linha, nova_coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(linha, nova_coluna) == "torre" || getTipoPeca(linha, nova_coluna) == "rainha"){
					pecas_atacando_rei += 1;
					break;
				}
			}
			
			if (Control.getController().getPeca(linha, nova_coluna) != null) {
				break;
			}
			
		}
		
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_coluna = coluna - i;
			
			if (!estaDentroDosLimites(linha, nova_coluna)) {
				break;
			}
			
			if (confereXeque2(linha, nova_coluna)) {
				
				if (i == 1) {
					
					if (getTipoPeca(linha, nova_coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(linha, nova_coluna) == "torre" || getTipoPeca(linha, nova_coluna) == "rainha"){
					pecas_atacando_rei += 1;
					break;
				}
			}
			
			if (Control.getController().getPeca(linha, nova_coluna) != null) {
				break;
			}
		}
		
		//Bispo e rainha
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha + i;
			int nova_coluna = coluna + i;
			
			if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
				break;
			}
			
			if (confereXeque2(nova_linha, nova_coluna)) {
				
				if (i == 1) {
					
					if (getTipoPeca(nova_linha, nova_coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
					
					if (getTipoPeca(nova_linha, nova_coluna) == "peao" && getTurno() == "branco") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(nova_linha, nova_coluna) == "bispo" || getTipoPeca(nova_linha, nova_coluna) == "rainha"){
					pecas_atacando_rei += 1;
					break;
				}
				
				
			}
			
			if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
				break;
			}
			
		}
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha - i;
			int nova_coluna = coluna + i;
			
			if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
				break;
			}
			
			if (confereXeque2(nova_linha, nova_coluna)) {
				
				if (i == 1) {
					
					if (getTipoPeca(nova_linha, nova_coluna) == "peao" && getTurno() == "preto") {
						pecas_atacando_rei += 1;
						break;
					}
					
					if (getTipoPeca(nova_linha, nova_coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(nova_linha, nova_coluna) == "bispo" || getTipoPeca(nova_linha, nova_coluna) == "rainha"){
					pecas_atacando_rei += 1;
					break;
				}
			}
			
			if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
				break;
			}
			
		}
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha + i;
			int nova_coluna = coluna - i;
			
			if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
				break;
			}
			
			if (confereXeque2(nova_linha, nova_coluna)) {
				
				if (i == 1) {
					
					if (getTipoPeca(nova_linha, nova_coluna) == "peao" && getTurno() == "branco") {
						pecas_atacando_rei += 1;
						break;
					}
					
					if (getTipoPeca(nova_linha, nova_coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(nova_linha, nova_coluna) == "bispo" || getTipoPeca(nova_linha, nova_coluna) == "rainha"){
					pecas_atacando_rei += 1;
					break;
				}
			}
			
			if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
				break;
			}
			
		}
		
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha - i;
			int nova_coluna = coluna - i;
			
			if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
				break;
			}
			
			if (confereXeque2(nova_linha, nova_coluna)) {
				
				if (i == 1) {
					
					if (getTipoPeca(nova_linha, nova_coluna) == "peao" && getTurno() == "preto") {
						pecas_atacando_rei += 1;
						break;
					}
					
					if (getTipoPeca(nova_linha, nova_coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(nova_linha, nova_coluna) == "bispo" || getTipoPeca(nova_linha, nova_coluna) == "rainha"){
					pecas_atacando_rei += 1;
					break;
				}
			}
			
			if (Control.getController().getPeca(nova_linha, nova_coluna) != null) {
				break;
			}
			
		}
		
		int[][] offsets = {
                {+2, +1}, {+2, -1}, {-2, +1}, {-2, -1},
                {+1, +2}, {+1, -2}, {-1, +2}, {-1, -2}
        };
		
		for (int[] offset : offsets)
		{
			int nova_linha = linha + offset[0];
			int nova_coluna = coluna + offset[1];
			if (confereXeque2(nova_linha, nova_coluna)) {
				
				if (getTipoPeca(nova_linha, nova_coluna) == "cavalo"){
					pecas_atacando_rei += 1;
					break;
				}
				
			}
		}
		
		/*
		if (getTurno() == "branco") {
			pecas_atacando_rei_jogador2 = pecas_atacando_rei;
			return pecas_atac
		}
		
		else {
			pecas_atacando_rei_jogador1 = pecas_atacando_rei;
		}
		*/
		
		
		
		return pecas_atacando_rei;
		
	}
	
	public boolean getEstadoReiJogador1() {
		return rei_esta_em_xeque_jogador1;
	}
	
	public boolean getEstadoReiJogador2() {
		return rei_esta_em_xeque_jogador2;
	}
	
	public Pecas promocaoPeao() {
		
		
		DialogPromocaoPeao dialog = new DialogPromocaoPeao(Control.getController().getTurno(), null);
		dialog.setVisible(true);
		
		String escolha = dialog.getEscolha();
		Pecas novaPeca = null;
		
		if (escolha == "rainha") {
			novaPeca = new Rainha(Control.getController().getTurno(), "rainha");
		}
		
		else if (escolha == "torre") {
			novaPeca = new Torre(Control.getController().getTurno(), "torre");
		}
		
		else if (escolha == "bispo") {
			novaPeca = new Bispo(Control.getController().getTurno(), "bispo");
		}
		
		else if (escolha == "cavalo") {
			novaPeca = new Cavalo(Control.getController().getTurno(), "cavalo");
		}
		
		
		return novaPeca;
		
	}
	

}
