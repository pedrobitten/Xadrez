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
	private boolean rei_esta_em_xeque_jogador1;
	private boolean rei_esta_em_xeque_jogador2;
	private int[] posicao_rei_jogador1 = new int[2];
	private int[] posicao_rei_jogador2 = new int[2];
	private int qtd_pecas_jogador1;
	private int qtd_pecas_jogador2;
	private int qtd_rodadas;
	
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
		posicao_rei_jogador1[0] = 7; //
		posicao_rei_jogador1[1] = 4; //	
		posicao_rei_jogador2[0] = 0; //teste 
		posicao_rei_jogador2[1] = 4; //
		qtd_pecas_jogador1 = 16;
		qtd_pecas_jogador2 = 16;
		qtd_rodadas = 0;
		
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

	
	private int confereXeque(int linha, int coluna, String cor_rei) {
		
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
			return 1;
		}
		
		//Pecas peca = tabuleiro.matriz[linha][coluna];
		Pecas peca = getPeca(linha, coluna);
		
		if (peca == null) {
			return 0;
		}
		
		if (peca.getCor() == cor_rei) {
			return 1;
		}
		
		return 2;
		
		
	}
	
	
	public int xequeRei(int linha, int coluna, String cor_rei) {
		
		int pecas_atacando_rei = 0;
		
		//boolean rei_esta_atacando = false;
		//Torre, rainha
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha + i;

			if (!estaDentroDosLimites(nova_linha, coluna)) {
				break;
			}
			
			int identifica_xeque = confereXeque(nova_linha, coluna, cor_rei);
			
			if (identifica_xeque == 0) {
				continue;
			}
			
			if (identifica_xeque == 2) {
				
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
			
			break;
			
		}
		
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha - i;
			
			if (!estaDentroDosLimites(nova_linha, coluna)) {
				break;
			}
			
			
			int identifica_xeque = confereXeque(nova_linha, coluna, cor_rei);
			
			if (identifica_xeque == 0) {
				continue;
			}
			
			if (identifica_xeque == 2) {
				
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
			
			break;
		
		}
		
		for (int i = 1; i < 8; i ++)
		{
			int nova_coluna = coluna + i;
			
			if (!estaDentroDosLimites(linha, nova_coluna)) {
				break;
			}
			
			int identifica_xeque = confereXeque(linha, nova_coluna, cor_rei);
			
			if (identifica_xeque == 0) {
				continue;
			}
			
			if (identifica_xeque == 2) {
				
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
			
			break;
			
		}
		
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_coluna = coluna - i;
			
			if (!estaDentroDosLimites(linha, nova_coluna)) {
				break;
			}
			
			int identifica_xeque = confereXeque(linha, nova_coluna, cor_rei);
			
			if (identifica_xeque == 0) {
				continue;
			}
			
			if (identifica_xeque == 2) {
				
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
			
			break;
		}
		
		//Bispo e rainha
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha + i;
			int nova_coluna = coluna + i;
			
			if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
				break;
			}
			
			int identifica_xeque = confereXeque(nova_linha, nova_coluna, cor_rei);
			
			if (identifica_xeque == 0) {
				continue;
			}
			
			if (identifica_xeque == 2) {
				
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
			
			break;
			
		}
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha - i;
			int nova_coluna = coluna + i;
			
			if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
				break;
			}
			
			int identifica_xeque = confereXeque(nova_linha, nova_coluna, cor_rei);
			
			if (identifica_xeque == 0) {
				continue;
			}
			
			if (identifica_xeque == 2) {
				
				if (i == 1) {
					
					if (getTipoPeca(nova_linha, nova_coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
					
					if (getTipoPeca(nova_linha, nova_coluna) == "peao" && getTurno() == "preto") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(nova_linha, nova_coluna) == "bispo" || getTipoPeca(nova_linha, nova_coluna) == "rainha"){
					
					pecas_atacando_rei += 1;
					break;
				}
			}
			
			
			break;
			
		}
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha + i;
			int nova_coluna = coluna - i;
			
			if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
				break;
			}
			
			int identifica_xeque = confereXeque(nova_linha, nova_coluna, cor_rei);
			
			if (identifica_xeque == 0) {
				continue;
			}
			
			if (identifica_xeque == 2) {
				
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
			
			
			break;
			
		}
		
		
		for (int i = 1; i < 8; i ++)
		{
			
			int nova_linha = linha - i;
			int nova_coluna = coluna - i;
			
			if (!estaDentroDosLimites(nova_linha, nova_coluna)) {
				break;
			}
			
			int identifica_xeque = confereXeque(nova_linha, nova_coluna, cor_rei);
			
			if (identifica_xeque == 0) {
				continue;
			}
			
			if (identifica_xeque == 2) {
				
				if (i == 1) {
					
					if (getTipoPeca(nova_linha, nova_coluna) == "rei") {
						pecas_atacando_rei += 1;
						break;
					}
					
					if (getTipoPeca(nova_linha, nova_coluna) == "peao" && getTurno() == "preto") {
						pecas_atacando_rei += 1;
						break;
					}
				}
				
				if (getTipoPeca(nova_linha, nova_coluna) == "bispo" || getTipoPeca(nova_linha, nova_coluna) == "rainha"){
					
					pecas_atacando_rei += 1;
					break;
				}
			}
			
			
			break;
			
		}
		
		int[][] offsets = {
                {+2, +1}, {+2, -1}, {-2, +1}, {-2, -1},
                {+1, +2}, {+1, -2}, {-1, +2}, {-1, -2}
        };
		
		for (int[] offset : offsets)
		{
			int nova_linha = linha + offset[0];
			int nova_coluna = coluna + offset[1];
			
			int identifica_xeque = confereXeque(nova_linha, nova_coluna, cor_rei);
			
			if (identifica_xeque == 2) {
				
				if (getTipoPeca(nova_linha, nova_coluna) == "cavalo"){
					pecas_atacando_rei += 1;
					break;
				}
			}
			
		}
		
		return pecas_atacando_rei;
		
	}
	
	
	public boolean getEstadoReiJogador1() {
		return rei_esta_em_xeque_jogador1;
	}
	
	public boolean getEstadoReiJogador2() {
		return rei_esta_em_xeque_jogador2;
	}
	
	public void ReiSaiuDoXequeJogador1() {
		
		if (getEstadoReiJogador1() == true) {
			rei_esta_em_xeque_jogador1 = false;
		}
	}
	
	public void ReiSaiuDoXequeJogador2() {
		
		if (getEstadoReiJogador2() == true) {
			rei_esta_em_xeque_jogador2 = false;
		}
	}
	
	/*
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
	
	*/
	
	private boolean existeLanceQueRemoveXeque(int linha, int coluna , String cor_rei) {
		

		
		for (int origem_linha = 0; origem_linha < 8; origem_linha ++)
		{
			for (int origem_coluna = 0; origem_coluna < 8; origem_coluna++)
			{
				
				Pecas peca = getPeca(origem_linha, origem_coluna);
				
				if (peca == null || !(peca.getCor() == cor_rei)) {
					continue;
				}
				
				 for (int destino_linha = 0; destino_linha < 8; destino_linha++) {
					 for (int destino_coluna = 0; destino_coluna < 8; destino_coluna++) {

						 if (origem_linha == destino_linha && origem_coluna == destino_coluna) {
							 continue;
						 }

						 // Usa o prÃ³prio validador da peÃ§a
						 if (!peca.movimentoValido(tabuleiro, origem_linha, origem_coluna, destino_linha, destino_coluna)) continue;

		                    /* -------- 3ï¸�âƒ£  SIMULA O LANCE ------------- */
		                    Pecas capturada = getPeca(destino_linha, destino_coluna);
		                    atualizaTabuleiro(origem_linha, origem_coluna, null);
		                    atualizaTabuleiro(destino_linha, destino_coluna, peca);

		                    // Se a peÃ§a era o rei, atualize posiÃ§Ã£o temporÃ¡ria
		                    int salva_rei_linha = linha; 
		                    int salva_rei_coluna = coluna;
		                    
		                    if (peca.getPeca() == "rei") {
		                        linha = destino_linha;
		                        coluna = destino_coluna;
		                    }

		                    boolean ainda_em_xeque = xequeRei(linha, coluna, cor_rei) != 0;

		                    /* -------- 4ï¸�âƒ£  DESFAZ LANCE -------------- */
		                    atualizaTabuleiro(origem_linha, origem_coluna, peca);
		                    atualizaTabuleiro(destino_linha, destino_coluna, capturada);
		                    
		                 
		                    if (peca.getPeca() == "rei") {   // restaura posiÃ§Ã£o do rei
		                        linha = salva_rei_linha;
		                        coluna = salva_rei_coluna;
		                    }

		                    /* 5ï¸�âƒ£ Se achou um lance que tira o xeque â†’ jÃ¡ retorna */
		                    if (!ainda_em_xeque) return true;
		                }
		            }
				
			}
		}
		
		return false; //xeque-mate
	}
	
	public boolean xequeMate(int linha, int coluna, String cor_rei) {
		
		int [][] direcoes = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
		Pecas rei = getPeca(linha, coluna);
		
		for (int[] direcao : direcoes)
		{
			int linha_nova = linha + direcao[0];
			int coluna_nova = coluna + direcao[1];
			
			if (!estaDentroDosLimites(linha_nova, coluna_nova)) {
				continue;
			}
			
			Pecas destino = getPeca(linha_nova, coluna_nova);
			
			if (destino != null && destino.getCor() == cor_rei) {
				continue;
			}
			
			
			atualizaTabuleiro(linha, coluna, null);
			atualizaTabuleiro(linha_nova, coluna_nova, rei);
			
			boolean ainda_em_xeque = xequeRei(linha_nova, coluna_nova, cor_rei) != 0;
			
			atualizaTabuleiro(linha, coluna, rei);
			atualizaTabuleiro(linha_nova, coluna_nova, destino);
			
			if (!ainda_em_xeque) { //rei achou fuga
				return false;
			}
			
			
		}
		
		if (existeLanceQueRemoveXeque(linha, coluna, cor_rei)) {
			return false;
		}
		
		return true;
		
	}
	
	public void diminuiQtdPecasJogador1() {
		
		qtd_pecas_jogador1 -= 1;
		
	}
	
	public void diminuiQtdPecasJogador2() {
		
		qtd_pecas_jogador2 -= 1;
		
	}
	
	public void somaQtdRodadas() {
		
		qtd_rodadas += 1;
		
	}
		
	
	public boolean Congelamento() {
		
		//if qtd_pecas_jogador1 < x && qtd_pecas_jogador2 < x
		
		//if qtd_rodadas > x
		
		//if qtd_partidas_faltam == 0
		
		return true;
		
	}
	
	

}
