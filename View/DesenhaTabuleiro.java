package View;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.MouseEvent;
import Model.*;
import java.util.ArrayList;

public class DesenhaTabuleiro extends JPanel {

	private Tabuleiro tabuleiro = new Tabuleiro(); 

	private String turno = "preto";
	
	private Pecas peca_selecionada = null;
	private boolean jogada_iniciada = false;
	private boolean notifica_click_em_peca = true;
	private boolean pinta_quadrados_rosa = false;
	private boolean escolhe_casa_destino = false;
	private ArrayList<ArrayList<ArrayList<Integer>>> vetor_de_coordenadas = new ArrayList<>();
	private boolean roque_disponivel_jogador1 = true;
	private boolean roque_disponivel_jogador2 = true;
	private boolean torre1_ja_movimentou_jogador1 = false;
	private boolean torre1_ja_movimentou_jogador2 = false;
	private boolean torre2_ja_movimentou_jogador1 = false;
	private boolean torre2_ja_movimentou_jogador2 = false;
	private boolean rei_ja_movimentou_jogador1 = false;
	private boolean rei_ja_movimentou_jogador2 = false;
	private Pecas torre_escolhida = null;
	private boolean jogada_reiniciada = false;
	
	private int coordenada_x = -1;
	private int coordenada_y = -1;
	private int linha = -1;
	private int coluna = -1;
	private int tileWidth;
    private int tileHeight;
    private int linha_antiga = -1;
    private int coluna_antiga = -1;
    
	
	public DesenhaTabuleiro() {

		
		this.addMouseListener(new Mouse(this)
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				tileWidth = getWidth() / 8;
	            tileHeight = getHeight() / 8;

				coordenada_x = e.getX() / tileWidth; // coluna
				coordenada_y = e.getY() / tileHeight; //linha
				
				notificaClickEmPeca(); // desativar a funcao
				escolheCasaDestino(vetor_de_coordenadas);
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
		
		movimentoPeca(g);
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
				Pecas peca = tabuleiro.matriz[i][j];
				
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
		
		
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
			return;
		}
		
		Pecas pecaClicada = tabuleiro.matriz[linha][coluna];
		
		
		if (pecaClicada != null) {
			
			if (pecaClicada.getCor() == turno) {
				if (pecaClicada.getPeca().equalsIgnoreCase("peao")) {
					
					if (jogada_reiniciada == false) {
						JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Peão clicado");
					}
	                
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("bispo")) {
					
					if (jogada_reiniciada == false) {
						JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Bispo clicado");
					}
					
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("torre")) {
	                
					if (jogada_reiniciada == false) {
						JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Torre clicado");
					}
					
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("rei")) {
	                
					if (jogada_reiniciada == false) {
						JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Rei clicado");

					}
					
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("rainha")) {
					
					if (jogada_reiniciada == false) {
						JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Rainha clicado");
					}
					
	                

	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("cavalo")) {
	                
					if (jogada_reiniciada == false) {
						JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Cavalo clicado");
					}
					
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
		
		
		linha = coordenada_y;
		coluna = coordenada_x;
		
		Graphics2D g2d = (Graphics2D) g;
		ArrayList<Integer> coordenadas_casas_possiveis;
		ArrayList<ArrayList<Integer>> coordenadas_vertical = new ArrayList<>();
		ArrayList<ArrayList<Integer>> coordenadas_diagonal = new ArrayList<>();
		ArrayList<ArrayList<Integer>> coordenadas_horizontal = new ArrayList<>();
		ArrayList<ArrayList<Integer>> coordenadas_cavalo = new ArrayList<>();
		
		System.out.print(coordenada_y);
		System.out.print(coordenada_x);
		
		if (peca_selecionada.getPeca() == "peao") {
			

			
			int direcao = (peca_selecionada.getCor() == "branco") ? -1 : 1;
			int ultima_linha = -1;
			
			for (int i = 1; i < 2; i ++)
			{
				int nova_linha = linha + (i  * direcao);
				if(movimentoPeao(g2d, nova_linha, coluna, i)) {
					
					coordenadas_casas_possiveis = new ArrayList<>();
					coordenadas_casas_possiveis.add(nova_linha);
					coordenadas_casas_possiveis.add(coluna);
					coordenadas_vertical.add(coordenadas_casas_possiveis);
					ultima_linha = nova_linha;
				}
				
				else {
					ultima_linha = nova_linha;
					break;
				}
				
				
			}
				
				
			if (ataquePeao(g2d, ultima_linha, coluna + 1, peca_selecionada.getCor())) {
					 	coordenadas_casas_possiveis = new ArrayList<>();
				        coordenadas_casas_possiveis.add(ultima_linha);
				        coordenadas_casas_possiveis.add(coluna + 1);
				        coordenadas_diagonal.add(coordenadas_casas_possiveis);
			}
				
				
				
			if (ataquePeao(g2d, ultima_linha, coluna - 1, peca_selecionada.getCor())) {
					coordenadas_casas_possiveis = new ArrayList<>();
			        coordenadas_casas_possiveis.add(ultima_linha);
			        coordenadas_casas_possiveis.add(coluna - 1);
			        coordenadas_diagonal.add(coordenadas_casas_possiveis);
			}

			
				
			
			
			
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
		
		for (ArrayList<ArrayList<Integer>> coordenadas : vetor_coordenadas)
		{
			for (ArrayList<Integer> coordenada_escolhida : coordenadas)
			{

				if (coordenada_escolhida.get(0) == linha_destino && coordenada_escolhida.get(1) == coluna_destino) {
			
					escolhe_casa_destino = true;
					pinta_quadrados_rosa = false;
					repaint();
					return;
				}
			}
			
		}
		
		/*
		if (peca_selecionada.getPeca() == "rei") {
			if (roque_disponivel_jogador1 == true || roque_disponivel_jogador2 == true) {
				if (roque(linha_destino, coluna_destino) == false) {
					jogadaReiniciada();
					return;
				}
			}
			
		}
		*/
		
		if (tabuleiro.matriz[linha_destino][coluna_destino] != null && tabuleiro.matriz[linha_destino][coluna_destino].getCor() == turno) {
	
			jogadaReiniciada();
			return;
		}
		
		
		fimDaJogada();
		

	}
	

	private void movimentoPeca(Graphics g) {
		
		//Condicao para usar essa funcao
		if (escolhe_casa_destino == false) {
			return;
		}
		
		int linha_destino = coordenada_y;
		int coluna_destino = coordenada_x;
		Graphics2D g2d = (Graphics2D) g;
		
		if (torre_escolhida != null) {
	
			g2d.drawImage(peca_selecionada.getImage(), coluna_destino * tileWidth, linha_destino * tileHeight, tileWidth, tileHeight, null);
			g2d.drawImage(torre_escolhida.getImage(), coluna_antiga * tileWidth, linha_antiga * tileHeight, tileWidth, tileHeight, null);
			tabuleiro.matriz[linha_destino][coluna_destino] = peca_selecionada;
			tabuleiro.matriz[linha_antiga][coluna_antiga] = torre_escolhida;
		}
		
		else {
			g2d.drawImage(peca_selecionada.getImage(), coluna_destino * tileWidth, linha_destino * tileHeight, tileWidth, tileHeight, null);
			tabuleiro.matriz[linha_destino][coluna_destino] = peca_selecionada;
			tabuleiro.matriz[linha_antiga][coluna_antiga] = null;
		}
		
		//Fim da jogada
		
		fimDaJogada();
		
		//Mudanca de turno
		if (turno == "branco") {
			turno = "preto";
		}
		
		else {
			turno = "branco";
		}
		

	}
	
	private void fimDaJogada() {
		
		notifica_click_em_peca = true;
		
		jogada_iniciada = false;
		pinta_quadrados_rosa = false;
		escolhe_casa_destino = false;
		peca_selecionada = null;
		torre_escolhida = null;
		
		repaint();
	}
	
	private void jogadaReiniciada() {
		
		notifica_click_em_peca = true;
		pinta_quadrados_rosa = false;
		jogada_iniciada = true;
		repaint();
	}
	
	private boolean ataquePeao(Graphics2D g2d, int linha, int coluna, String cor_atual) {
		
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {

	        return false;

	    }
		
		Pecas peca_na_casa = tabuleiro.matriz[linha][coluna];
		
		if (tabuleiro.matriz[linha][coluna] != null && !peca_na_casa.getCor().equals(cor_atual)) {
			double leftX = coluna * tileWidth;
		    double topY = linha * tileHeight;
		    Rectangle2D casa = new Rectangle2D.Double(leftX, topY, tileWidth, tileHeight);
		    g2d.setPaint(Color.pink);
		    g2d.fill(casa);
		    return true;
		}
		
		return false;
		
	}
	
	private boolean movimentoPeao(Graphics2D g2d, int linha, int coluna, int cont) {
		
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {

	        return false;

	    }
		
	
		if (tabuleiro.matriz[linha][coluna] == null) {
			
			if (linha == 1 || linha == 6) {

				double leftX = coluna * tileWidth;
			    double topY = linha * tileHeight;
			    Rectangle2D casa = new Rectangle2D.Double(leftX, topY, tileWidth, tileHeight);
			    g2d.setPaint(Color.pink);
			    g2d.fill(casa);
			}
			
		    double leftX = coluna * tileWidth;
		    double topY = linha * tileHeight;
		    Rectangle2D casa = new Rectangle2D.Double(leftX, topY, tileWidth, tileHeight);
		    g2d.setPaint(Color.pink);
		    g2d.fill(casa);
		    return true;  
		}
		
		return false;
		
		
	}
	
	private boolean pintaSeVazio(Graphics2D g2d, int linha, int coluna, String cor_atual, ArrayList<ArrayList<Integer>> coordenada_movimento) {
		
		
	    if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {

	        return false;

	    }
	    
	    if (tabuleiro.matriz[linha][coluna] == null) {
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
	    

	    Pecas peca_na_casa = tabuleiro.matriz[linha][coluna];
	    
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
	
	private boolean roque(int linha, int coluna) {
		
		if (tabuleiro.matriz[linha][coluna].getPeca() != "torre") {
			return false;
		}
		
		int inicio = Math.min(coluna, coluna_antiga) + 1;
        int fim = Math.max(coluna, coluna_antiga);
        
        
        for (int cont = inicio; cont < fim; cont++)
        {
            if (tabuleiro.matriz[linha_antiga][cont] != null) {
                return false;
            }
        }
		
		int jogador;
		
		if (peca_selecionada.getCor() == "branco") {
			jogador = 1;
		}
		
		else {
			jogador = 2;
		}
		
		if (jogador == 1) {
			
			if (rei_ja_movimentou_jogador1 == true) {
				return false;
			}
			
			if (torre1_ja_movimentou_jogador1 == true) {
				return false;
			}
			
			if (torre2_ja_movimentou_jogador1 == true) {
				return false;
			}
			
			roque_disponivel_jogador1 = false;
			
			if (Math.abs(coluna) - Math.abs(coluna_antiga) == 3) {
				torre2_ja_movimentou_jogador1 = true;
			}
			
			else {
				torre1_ja_movimentou_jogador1 = true;
			}
			
		}
		
		else {
			
			if (rei_ja_movimentou_jogador2 == true) {
				return false;
			}
			
			if (torre1_ja_movimentou_jogador2 == true) {
				return false;
			}
			
			if (torre2_ja_movimentou_jogador2 == true) {
				return false;
			}
			
			roque_disponivel_jogador2 = false;
			
			if (Math.abs(coluna) - Math.abs(coluna_antiga) == 3) {
				torre1_ja_movimentou_jogador2 = true;
			}
			
			else {
				torre2_ja_movimentou_jogador2 = true;
			}
		}
		
		
		escolhe_casa_destino = true;
		pinta_quadrados_rosa = false;
		torre_escolhida = tabuleiro.matriz[linha][coluna];
		
		repaint();
		
		return true;
		
	}
	
	 

	
		
}

