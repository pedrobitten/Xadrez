package View;
import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.*;


public class DesenhaTabuleiro extends JPanel {

	private Image []imagens_peca;
	private String[] nomes_pecas = {
			"b_torre", "b_cavalo", "b_bispo", "b_dama", "b_rei", "b_bispo", "b_cavalo", "b_torre",
		    "b_peao", "b_peao", "b_peao", "b_peao", "b_peao", "b_peao", "b_peao", "b_peao",
		    "p_peao", "p_peao", "p_peao", "p_peao", "p_peao", "p_peao", "p_peao", "p_peao",
		    "p_torre", "p_cavalo", "p_bispo", "p_dama", "p_rei", "p_bispo", "p_cavalo", "p_torre"
	};
	
	private Tabuleiro tabuleiro = new Tabuleiro(); 
	
	private String turno = "preto";
	
	private boolean peao_selecionado = false;
	private boolean torre_selecionada = false;
	private boolean rainha_selecionada = false;
	private boolean cavalo_selecionado = false;
	private boolean bispo_selecionado = false;
	private boolean rei_selecionado = false;
	private Pecas peca_selecionada;
	private boolean notifica_click_em_peca = false;
	
	private boolean mouse_esquerdo = false;
	public int coordenada_x = -1;
	public int coordenada_y = -1;
	public int linha = -1;
	public int coluna = -1;
	public int tileWidth;
    public int tileHeight;
    
	
	public DesenhaTabuleiro() {
		carregarImagens();
		
		this.addMouseListener(new Mouse(this)
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				tileWidth = getWidth() / 8;
	            tileHeight = getHeight() / 8;

				coordenada_x = e.getX() / tileWidth; // coluna
				coordenada_y = e.getY() / tileHeight; //linha
				//System.out.println("Foi clique");
				//System.out.print(coordenada_y);
				notificaClickEmPeca();
				
			}
			
		});
		
		
		
	}
	
	private void carregarImagens() {
		imagens_peca = new Image[32];
		
		for (int i = 0; i < 32; i++)
		{
			String caminho = "D:/java_2025.1/Pecas/Pecas_1/" + nomes_pecas[i] + ".gif";
			//System.out.print(caminho);
			//System.out.println("\n");
			
			try {
				imagens_peca[i] = ImageIO.read(new File(caminho));
			}
			
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
	
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int tileWidth = getWidth() / 8;
	    int tileHeight = getHeight() / 8;
		
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
		
		//Pecas jogador2
		
		int linha = 0;
		int coluna;
		
		for (coluna = 0; coluna < 8; coluna++)
		{
			g2d.drawImage(imagens_peca[24 + coluna], coluna * tileWidth, linha * tileHeight, tileWidth, tileHeight, null);
		}
		
		//Peao jogador2
		
		linha = 1;
		
		for (coluna = 0; coluna < 8; coluna++)
		{
			g2d.drawImage(imagens_peca[16 + coluna], coluna * tileWidth, linha * tileHeight, tileWidth, tileHeight, null);
		}
		
		//Pecas jogador1
		
		linha = 7;
		
		
		for (coluna = 0; coluna < 8; coluna++)
		{
			g2d.drawImage(imagens_peca[coluna], coluna * tileWidth, linha * tileHeight, tileWidth, tileHeight, null);
		}
		
		//linha = 4;
		
		//g2d.drawImage(imagens_peca[21], 3 * tileWidth, linha * tileHeight, tileWidth, tileHeight, null);
		
		//Peao jogador1
		
		
		linha = 6;
		
		for (coluna = 0; coluna < 8; coluna++)
		{
			g2d.drawImage(imagens_peca[8 + coluna], coluna * tileWidth, linha * tileHeight, tileWidth, tileHeight, null);
		}
	
		
		
		pintaQuadradosVermelhos(g);
		

	}
	
	private void notificaClickEmPeca() {
		
		linha = coordenada_y;
		coluna = coordenada_x;
		
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
			return;
		}
		
		Pecas pecaClicada = tabuleiro.matriz[linha][coluna];
		
		
		if (pecaClicada != null) {
			
			if (pecaClicada.getCor() == turno) {
				if (pecaClicada.getPeca().equalsIgnoreCase("peao")) {
	                JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Peão clicado");
	                peca_selecionada = pecaClicada;
	                peao_selecionado = true;
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("bispo")) {
	                JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Bispo clicado");
	                bispo_selecionado = true;
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("torre")) {
	                JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Torre clicado");
	                torre_selecionada = true;
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("rei")) {
	                JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Rei clicado");
	                rei_selecionado = true;
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("rainha")) {
	                JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Rainha clicado");
	                rainha_selecionada = true;
	            }
				
				else if (pecaClicada.getPeca().equalsIgnoreCase("cavalo")) {
	                JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Cavalo clicado");
	                cavalo_selecionado = true;
	            }
				
				/*
				//Mudanca de turno
				if (turno == "branco") {
					turno = "preto";
				}
				
				else {
					turno = "branco";
				}
				*/
				
				
				notifica_click_em_peca = true;
			}
			
		}
		
		else {
			JOptionPane.showMessageDialog(DesenhaTabuleiro.this, "Espaco vazio clicado");
		}
		
		
		repaint();
	}
	
	private void pintaQuadradosVermelhos(Graphics g) {
		
		if (notifica_click_em_peca == false) {
			return;
		}
		
		
		linha = coordenada_y;
		coluna = coordenada_x;
		
		Graphics2D g2d = (Graphics2D) g;

		
		
		if (peao_selecionado == true) {
			
			int direcao = (peca_selecionada.getCor() == "branco") ? -1 : 1;
			
			
			for (int i = 0; i < 2; i ++)
			{
				int nova_linha = linha + (i + 1) * direcao;
				if(!pintaSeVazio(g2d, nova_linha, coluna)) break;
			}
			
			Peao peao_escolhido;
			peao_escolhido = (Peao)peca_selecionada;
			peao_escolhido.posicao = "diferente";
			
		}
		
		
		else if (torre_selecionada == true) {
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna)) break;
			}
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna)) break;
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha , coluna + i)) break;	
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha , coluna - i)) break;
			}
		
		}
		
		else if (rainha_selecionada == true){
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna)) break;
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				if (!pintaSeVazio(g2d, linha - i, coluna)) break;
				
			}
			
			for (int i = 1; i < 8; i ++)
			{
				
				if (!pintaSeVazio(g2d, linha , coluna + i)) break;
				
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha , coluna - i)) break;
			}
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i , coluna + i)) break;
			}
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna + i)) break;
			}
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna - i)) break;
			}
			
			
			for (int i = 1; i < 8; i ++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna - i)) break;
				
			}
			
		}
		
		else if (cavalo_selecionado == true) {
			
			int[][] offsets = {
	                {+2, +1}, {+2, -1}, {-2, +1}, {-2, -1},
	                {+1, +2}, {+1, -2}, {-1, +2}, {-1, -2}
	            };
			
			for (int[] offset : offsets)
			{
				int nova_linha = linha + offset[0];
				int nova_coluna = coluna + offset[1];
				if(pintaSeVazio(g2d, nova_linha, nova_coluna));
			}
		}
		
		else if (bispo_selecionado == true) {
			
			for (int i = 1; i < 8 ; i++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna + i)) break;
			}
			
			for (int i = 1; i < 8 ; i++)
			{
				if (!pintaSeVazio(g2d, linha + i, coluna - i)) break;
			}
			
			for (int i = 1; i < 8 ; i++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna + i)) break;
			}
			
			for (int i = 1; i < 8 ; i++)
			{
				if (!pintaSeVazio(g2d, linha - i, coluna - i)) break;
			}
		}
		
		else if (rei_selecionado == true) {
			for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dx != 0 || dy != 0) {
                        if (pintaSeVazio(g2d, linha + dy, coluna + dx));
                    }
                }
            }
		}
		
		
		notifica_click_em_peca = false;
		return;

	}
	
	private boolean pintaSeVazio(Graphics2D g2d, int linha, int coluna) {
		
		
		
	    if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
	    	//System.out.println("foi condicao");
	        return false;

	    }

	    if (tabuleiro.matriz[linha][coluna] == null) {
	    	double leftX = coluna * tileWidth;
	        double topY = linha * tileHeight;
	        Rectangle2D casa = new Rectangle2D.Double(leftX, topY, tileWidth, tileHeight);
	        g2d.setPaint(Color.RED);
	        g2d.fill(casa);
	        return true; // 
	    } 
	    
	    
	      
	    return false;
	    
	}

	
		
}

