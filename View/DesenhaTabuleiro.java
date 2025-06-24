package View;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.MouseEvent;
import Model.*;
import java.util.ArrayList;
import Controller.Control;

import Model.ObserverTabuleiro;

public class DesenhaTabuleiro extends JPanel implements ObserverTabuleiro {

	private Tabuleiro tabuleiro;

	private Pecas peca_selecionada = null;
	private boolean jogada_iniciada = false;
	private boolean notifica_click_em_peca = true;
	private boolean pinta_quadrados_rosa = false;
	private boolean escolhe_casa_destino = false;
	private ArrayList<ArrayList<ArrayList<Integer>>> vetor_de_coordenadas = new ArrayList<>();
	
	private int coordenada_x = -1;
	private int coordenada_y = -1;
	private int linha = -1;
	private int coluna = -1;
	private int tileWidth;
    private int tileHeight;
    private int linha_antiga = -1;
    private int coluna_antiga = -1;
	
	public DesenhaTabuleiro(Tabuleiro tabuleiro) {
    		this.tabuleiro = tabuleiro;
    		tabuleiro.adicionarObservador(this);

    		this.addMouseListener(new Mouse(this) {
        	
    			@Override
				public void mouseClicked(MouseEvent e) {
    				
    				if (SwingUtilities.isRightMouseButton(e)) {
    					Control.getController().salvarJogo();
    				}
    				
    				else {
    					tileWidth = getWidth() / 8;
    		            tileHeight = getHeight() / 8;
    	
    					coordenada_x = e.getX() / tileWidth; // coluna
    					coordenada_y = e.getY() / tileHeight; //linha
    					
    					//Control.get
    					notificaClickEmPeca(); 
    					escolheCasaDestino(vetor_de_coordenadas);
    				}
    				
    				
			}
			
		});
		
		
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		tileWidth = getWidth() / 8;
	    tileHeight = getHeight() / 8;
		
		desenhaTabuleiro(g);
		
		pintaQuadradosRosas(g);
		
		movimentoPeca();
	}
	
	private void desenhaTabuleiro(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		//Desenha linhas horizontais
		for (int i = 0; i <= 8; i++)
		{
			double altura = i * 75.00;
			Point2D p1 = new Point2D.Double(0, altura);
			Point2D p2 = new Point2D.Double(650, altura);
			g2d.draw(new Line2D.Double(p1, p2));
		}
		
		//Desenha linhas verticais
		for (int i = 0; i <= 8; i++)
		{
			double largura = i * 73.00;
			Point2D p1 = new Point2D.Double(largura, 0);
			Point2D p2 = new Point2D.Double(largura, 670);
			g2d.draw(new Line2D.Double(p1, p2));
		}
		
		double largura = 650 / 8.0;
		double altura = 670 / 8.0;
		
		//Pinta as casas
		for (int i = 0; i < 64; i++)
		{	
			int linha = i / 8;
			int coluna = i % 8;
			
			double leftX = coluna * largura;
			double topY = linha * altura;
			
			Rectangle2D casa=new
			Rectangle2D.Double(leftX, topY, largura, altura);
			
			if ((linha + coluna) % 2 == 0) {
				g2d.setPaint(Color.WHITE);
			}
			
			else {
				g2d.setPaint(Color.BLACK);
			}
			
			g2d.fill(casa);
		}
		
		//Posicionar pecas
		
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Pecas peca = Control.getController().getPeca(i, j);
				
				if (peca != null) {
					 g2d.drawImage(peca.getImage(), j * tileWidth, i * tileHeight, tileWidth, tileHeight, null);
				}
				
			}
		}
		
	}
	
	private void notificaClickEmPeca() {
		
		linha = coordenada_y;
		coluna = coordenada_x;
		
		if (notifica_click_em_peca == false) {
			return ;
		}
		
	
		Pecas pecaClicada = Control.getController().getPeca(linha, coluna);

		
		if (pecaClicada != null) {
			
			if (pecaClicada.getCor() == Control.getController().getTurno()) {
				if (pecaClicada.getPeca().equalsIgnoreCase("peao")) {
					
					JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Peão clicado");
	                
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("bispo")) {
					
					JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Bispo clicado");
					
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("torre")) {
					
					
					JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Torre clicado");
					
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("rei")) {
	                
					JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Rei clicado");
					
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("rainha")) {
					
				
					JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Rainha clicado");

	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("cavalo")) {
	                
				
					JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Cavalo clicado");
					
	            }
				
				
				jogada_iniciada = true;
				notifica_click_em_peca = false;
				peca_selecionada = pecaClicada;
				linha_antiga = coordenada_y;
				coluna_antiga = coordenada_x;
			}
			
		}
		
		else {
			JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Espaco vazio clicado");
		}
		
		
		repaint();
	}
	
	private void pintaQuadradosRosas(Graphics g) {
		
		if (jogada_iniciada == false) {
			return;
		}
		
		if (peca_selecionada == null) {
			return;
		}
		
		
		linha = coordenada_y;
		coluna = coordenada_x;
		
		Graphics2D g2d = (Graphics2D) g;

		ArrayList<ArrayList<Integer>> coordenadas_vertical = new ArrayList<>();
		ArrayList<ArrayList<Integer>> coordenadas_diagonal = new ArrayList<>();
		ArrayList<ArrayList<Integer>> coordenadas_horizontal = new ArrayList<>();
		ArrayList<ArrayList<Integer>> coordenadas_cavalo = new ArrayList<>();
		
		if (peca_selecionada.getPeca() == "peao") {
			

			
			int direcao = (peca_selecionada.getCor() == "branco") ? -1 : 1;
			int linha_atual = linha;
			int coluna_atual = coluna;
			
			int linha_frente = linha_atual + direcao;
			
			if(movimentoPeao(g2d, linha_frente, coluna_atual, coordenadas_vertical)) {
					
				boolean peao_na_base = (peca_selecionada.getCor().equals("branco") && linha_atual == 6) ||
                        (peca_selecionada.getCor().equals("preto") && linha_atual == 1);
				
				if (peao_na_base) {
					int linha_dois_passos = linha_atual + (2 * direcao);
					
					movimentoPeao(g2d, linha_dois_passos, coluna_atual, coordenadas_vertical);
				}
				
			}
			
			int linha_ataque = linha_atual + direcao;
				
				
			ataquePeao(g2d, linha_ataque, coluna_atual + 1, peca_selecionada.getCor(), coordenadas_diagonal);
					 	
		
			ataquePeao(g2d, linha_ataque, coluna_atual - 1, peca_selecionada.getCor(), coordenadas_diagonal);
	
			vetor_de_coordenadas.add(coordenadas_vertical);
			
			if (!(coordenadas_diagonal.isEmpty())) {
				vetor_de_coordenadas.add(coordenadas_diagonal);
			}
		
		}
	
		else if (peca_selecionada.getPeca() == "torre") {

			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna, peca_selecionada.getCor(), coordenadas_vertical)) {
					break;
				}
				
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				if (!pintaSeVazio(g2d, linha - i , coluna, peca_selecionada.getCor(), coordenadas_vertical)) {
					break;
				}
				
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha , coluna + i, peca_selecionada.getCor(), coordenadas_horizontal)) {
					break;
				}
				
				
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha , coluna - i, peca_selecionada.getCor(), coordenadas_horizontal)) {
					break;

				}
				
			}
			
			vetor_de_coordenadas.add(coordenadas_horizontal);
			vetor_de_coordenadas.add(coordenadas_vertical);
			
		}
		
		
		
		else if (peca_selecionada.getPeca() ==  "rainha"){
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna, peca_selecionada.getCor(), coordenadas_vertical)) {
					break;
				}
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				if (!pintaSeVazio(g2d, linha - i, coluna, peca_selecionada.getCor(), coordenadas_vertical)) {
					break;	
				}
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				if (!pintaSeVazio(g2d, linha , coluna + i, peca_selecionada.getCor(), coordenadas_horizontal)) {
					break;
				}
				
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha , coluna - i, peca_selecionada.getCor(), coordenadas_horizontal)) {
					break;
				}
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i , coluna + i,  peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna + i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna - i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna - i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
			}
			
			vetor_de_coordenadas.add(coordenadas_horizontal);
			vetor_de_coordenadas.add(coordenadas_vertical);
			vetor_de_coordenadas.add(coordenadas_diagonal);
		}
		
		
		
		else if (peca_selecionada.getPeca() == "cavalo") {
			
			
			
			int[][] offsets = {
	                {+2, +1}, {+2, -1}, {-2, +1}, {-2, -1},
	                {+1, +2}, {+1, -2}, {-1, +2}, {-1, -2}
	        };
			
			for (int[] offset : offsets)
			{
				int nova_linha = linha + offset[0];
				int nova_coluna = coluna + offset[1];
				pintaSeVazio(g2d, nova_linha, nova_coluna, peca_selecionada.getCor(), coordenadas_cavalo);
			}
			
			vetor_de_coordenadas.add(coordenadas_cavalo);
		}
		
		
		else if (peca_selecionada.getPeca() ==  "bispo") {
			
			for (int i = 1; i < 8 ; i++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna + i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
			}
			
			for (int i = 1; i < 8 ; i++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna - i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
			}
			
			for (int i = 1; i < 8 ; i++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna + i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
			}
			
			for (int i = 1; i < 8 ; i++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna - i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
			}
			
			vetor_de_coordenadas.add(coordenadas_diagonal);
		}
		
		
		else if (peca_selecionada.getPeca() == "rei") {
			for (int i = 1; i < 2; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna, peca_selecionada.getCor(), coordenadas_vertical)) {
					break;
				}
			}
			
			for (int i = 1; i < 2; i ++)
			{
				
				if (!pintaSeVazio(g2d, linha - i, coluna, peca_selecionada.getCor(), coordenadas_vertical)) {
					break;
				}
				
			}
			
			for (int i = 1; i < 2; i ++)
			{
				
				if (!pintaSeVazio(g2d, linha , coluna + i, peca_selecionada.getCor(), coordenadas_horizontal)) {
					break;

				}
			
			}
			
			
			for (int i = 1; i < 2; i ++)
			{
				if (!pintaSeVazio(g2d, linha , coluna - i, peca_selecionada.getCor(), coordenadas_horizontal)) {
					break;
				}
				
			}
			
			for (int i = 1; i < 2; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i , coluna + i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}

			}
			
			for (int i = 1; i < 2; i ++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna + i,  peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
			}
			
			for (int i = 1; i < 2; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna - i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
			}
			
			
			for (int i = 1; i < 2; i ++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna - i, peca_selecionada.getCor(), coordenadas_diagonal)) {
					break;
				}
				
			}
			
			vetor_de_coordenadas.add(coordenadas_horizontal);
			vetor_de_coordenadas.add(coordenadas_vertical);
			vetor_de_coordenadas.add(coordenadas_diagonal);
			
		}
			
	
		pinta_quadrados_rosa = true;
		jogada_iniciada = false;
		
		
		return;

	}
	
	private void escolheCasaDestino(ArrayList<ArrayList<ArrayList<Integer>>> vetor_coordenadas) {
		
		if (pinta_quadrados_rosa == false) {
			return;
		}
		
		int linha_destino = coordenada_y;
		int coluna_destino = coordenada_x;
		boolean destino_valido = false;
		
		
		for (ArrayList<ArrayList<Integer>> coordenadas : vetor_coordenadas)
		{
			for (ArrayList<Integer> coordenada_escolhida : coordenadas)
			{

				if (coordenada_escolhida.get(0) == linha_destino && coordenada_escolhida.get(1) == coluna_destino) {
					
					destino_valido = true;
					break;
				}
				
				if (destino_valido) {
					break;
				}
			}
			
	
			
		}
		
		if (destino_valido) {
			escolhe_casa_destino = true;
			pinta_quadrados_rosa = false;
			repaint();
			return;
		}
		
		Pecas peca_escolhida = Control.getController().getPeca(linha_destino, coluna_destino);
		//Roque
		if (peca_escolhida != null && peca_escolhida.getPeca() == "torre" && peca_escolhida.getCor() == Control.getController().getTurno()) {

			if (Control.getController().roque(linha_destino, coluna_destino, linha_antiga, coluna_antiga, peca_selecionada) == false) {
				coordenada_y = linha_destino;
				coordenada_x = coluna_destino;
					
				jogadaReiniciada();
				return;
			}
				
				
			else {
				fimDaJogada(0);
				return ;
			}
				
			
			
		}
		
		
		//Escolher outra peca
		if (peca_escolhida != null && peca_escolhida.getCor() == Control.getController().getTurno()) {
			coordenada_y = linha_destino;
			coordenada_x = coluna_destino;
			
			jogadaReiniciada();

			return;
		}
		
		
		//Toca no tabuleiro
		fimDaJogada(1);
		

	}
	
	//Colocar no controle
	private void movimentoPeca() {
		
		//Condicao para usar essa funcao
		if (escolhe_casa_destino == false) {
			return;
		}
		
		int linha_destino = coordenada_y;
		int coluna_destino = coordenada_x;
		
		String turno_atual = Control.getController().getTurno();
		boolean rei_em_xeque = (turno_atual == "preto") ? Control.getController().getEstadoReiJogador2() : Control.getController().getEstadoReiJogador1();

		
		if (rei_em_xeque && peca_selecionada.getPeca() != "rei") {
			JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Rei em xeque!! Movimente o rei");
			fimDaJogada(1);
			return;
		}
		
		if (peca_selecionada.getPeca() == "torre") {
			Control.getController().descobreTorre(coluna_destino, turno_atual);
		}
		
		else if (peca_selecionada.getPeca() == "peao") {
			
			if (linha_destino == 7 || linha_destino == 0) {
				
				Pecas substitui_peao = Control.getController().promocaoPeao();
				Control.getController().atualizaTabuleiro(linha_destino, coluna_destino, substitui_peao);
				Control.getController().atualizaTabuleiro(linha_antiga, coluna_antiga, null);
				fimDaJogada(0);
				
				return; 
				
			}
		}
		
		else if (peca_selecionada.getPeca() == "rei") {
			
			Control.getController().atualizaTabuleiro(linha_destino, coluna_destino, peca_selecionada);
            Control.getController().atualizaTabuleiro(linha_antiga, coluna_antiga, null);

            int novo_estado = Control.getController().xequeRei(linha_destino, coluna_destino, turno_atual);
            
            if (novo_estado == 0) {
            	if (turno_atual == "branco") {
            		
            		Control.getController().ReiSaiuDoXequeJogador1();
            		Control.getController().setPosicaoReiJogador1(linha_destino, coluna_destino);
            	}
            	
            	else {
            		Control.getController().ReiSaiuDoXequeJogador2();
            		Control.getController().setPosicaoReiJogador2(linha_destino, coluna_destino);
            	}
            }
            
            else {
            	
            	JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Nao eh possivel movimentar para essa casa");
            	Control.getController().atualizaTabuleiro(linha_antiga, coluna_antiga, peca_selecionada);
                Control.getController().atualizaTabuleiro(linha_destino, coluna_destino, null);
                
                if (turno_atual == "branco") {
                	Control.getController().setPosicaoReiJogador1(linha_antiga, coluna_antiga);
                }
                
                else {
                	Control.getController().setPosicaoReiJogador2(linha_antiga, coluna_antiga);
                }
                
                fimDaJogada(1);
                return;
            }
            
		}
		
		
		Control.getController().atualizaTabuleiro(linha_destino, coluna_destino, peca_selecionada);
		Control.getController().atualizaTabuleiro(linha_antiga, coluna_antiga, null);
		
		
		int[] posicao_rei_inimigo = (turno_atual == "preto") 
				? Control.getController().getPosicaoReiJogador1()
				: Control.getController().getPosicaoReiJogador2();
		
		String cor_do_rei = (turno_atual == "preto") ? "branco" : "preto";
		
		int linha_rei = posicao_rei_inimigo[0];
		int coluna_rei = posicao_rei_inimigo[1];
		
		if (Control.getController().xequeRei(linha_rei, coluna_rei, cor_do_rei) != 0) {
			
			if (Control.getController().xequeMate(linha_rei, coluna_rei, cor_do_rei)) {
				JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Xeque mate!!");
				return;
			}
			
			else {
				JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Xeque!!");
			}
			
		}
		
		
		fimDaJogada(0);
		
		
		return;
		
				
	}
	
	private void fimDaJogada(int condicao) {
		
		notifica_click_em_peca = true;
		
		jogada_iniciada = false;
		pinta_quadrados_rosa = false;
		escolhe_casa_destino = false;
		peca_selecionada = null;
		

		vetor_de_coordenadas.clear();
		linha_antiga = -1;
		coluna_antiga = -1;
		linha = -1;
		coluna = -1;
		coordenada_y = -1;
		coordenada_x = -1;

		if (condicao != 1) {
			Control.getController().mudancaDeTurno();
		}
		
		
		repaint();
	}
	
	
	private void jogadaReiniciada() {
			
		peca_selecionada = Control.getController().getPeca(coordenada_y, coordenada_x);
		pinta_quadrados_rosa = false;
		jogada_iniciada = true;
		
		linha_antiga = linha;
		coluna_antiga = coluna;
		
		vetor_de_coordenadas.clear();

		repaint();
	}
	
	
	private boolean ataquePeao(Graphics2D g2d, int linha, int coluna, String cor_atual, ArrayList<ArrayList<Integer>> coordenada_movimento) {
		
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {

	        return false;

	    }
		
		Pecas peca_na_casa = Control.getController().getPeca(linha, coluna);

		if (peca_na_casa != null && !peca_na_casa.getCor().equals(cor_atual)) {
			double leftX = coluna * tileWidth;
		    double topY = linha * tileHeight;
		    Rectangle2D casa = new Rectangle2D.Double(leftX, topY, tileWidth, tileHeight);
		    g2d.setPaint(Color.pink);
		    g2d.fill(casa);
		    
		    ArrayList<Integer> coordenadas_casas_possiveis = new ArrayList<>();
	        coordenadas_casas_possiveis.add(linha);
	        coordenadas_casas_possiveis.add(coluna);
	        coordenada_movimento.add(coordenadas_casas_possiveis);
		    
		    return true;
		}
		
		return false;
		
	}
	
	private boolean movimentoPeao(Graphics2D g2d, int linha, int coluna, ArrayList<ArrayList<Integer>> coordenada_movimento) {
		
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {

	        return false;

	    }
		
		if (Control.getController().getPeca(linha, coluna) == null) {	
		    double leftX = coluna * tileWidth;
		    double topY = linha * tileHeight;
		    Rectangle2D casa = new Rectangle2D.Double(leftX, topY, tileWidth, tileHeight);
		    g2d.setPaint(Color.pink);
		    g2d.fill(casa);
		    
		    ArrayList<Integer> coordenadas_casas_possiveis = new ArrayList<>();
			coordenadas_casas_possiveis.add(linha);
			coordenadas_casas_possiveis.add(coluna);
			coordenada_movimento.add(coordenadas_casas_possiveis);
		    
		    return true;  
		}
		
		return false;
		
		
	}
	
	private boolean pintaSeVazio(Graphics2D g2d, int linha, int coluna, String cor_atual, ArrayList<ArrayList<Integer>> coordenada_movimento) {
		
		
	    if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {

	        return false;

	    }
	    
		  if (Control.getController().getPeca(linha, coluna) == null) {
	    	double leftX = coluna * tileWidth;
		    double topY = linha * tileHeight;
		    Rectangle2D casa = new Rectangle2D.Double(leftX, topY, tileWidth, tileHeight);
		    g2d.setPaint(Color.pink);
		    g2d.fill(casa);
		    
		    ArrayList<Integer> coordenadas_casas_possiveis = new ArrayList<>();
			coordenadas_casas_possiveis.add(linha);
			coordenadas_casas_possiveis.add(coluna);
			coordenada_movimento.add(coordenadas_casas_possiveis);
		    
		    return true; 
		}
	    
	    Pecas peca_na_casa = Control.getController().getPeca(linha, coluna);

	    if (!peca_na_casa.getCor().equals(cor_atual)) {

	    	 double leftX = coluna * tileWidth;
	         double topY = linha * tileHeight;
	         Rectangle2D casa = new Rectangle2D.Double(leftX, topY, tileWidth, tileHeight);
	         g2d.setPaint(Color.pink); 
	         g2d.fill(casa);
	         
	         ArrayList<Integer> coordenadas_casas_possiveis = new ArrayList<>();
	         coordenadas_casas_possiveis.add(linha);
	         coordenadas_casas_possiveis.add(coluna);
	         coordenada_movimento.add(coordenadas_casas_possiveis);
	         
	         return false; // para o loop
	    }
	    
	    return false;
	    
	}
	
	
    @Override
    public void atualizar(Tabuleiro tabuleiro) {
        repaint();
    }
    
}
