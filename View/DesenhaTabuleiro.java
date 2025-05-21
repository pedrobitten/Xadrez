package View;
import javax.imageio.ImageIO;
import javax.swing.*;

import Model.Bispo;
import Model.Cavalo;
import Model.Rainha;
import Model.Rei;
import Model.Torre;

import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;



public class DesenhaTabuleiro extends JPanel {

	private Image []imagens_peca;
	private String[] nomes_pecas = {
			"b_torre", "b_cavalo", "b_bispo", "b_dama", "b_rei", "b_bispo", "b_cavalo", "b_torre",
		    "b_peao", "b_peao", "b_peao", "b_peao", "b_peao", "b_peao", "b_peao", "b_peao",
		    "p_peao", "p_peao", "p_peao", "p_peao", "p_peao", "p_peao", "p_peao", "p_peao",
		    "p_torre", "p_cavalo", "p_bispo", "p_dama", "p_rei", "p_bispo", "p_cavalo", "p_torre"
	};
	
	public DesenhaTabuleiro() {
		carregarImagens();

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
		
		//Peao jogador1
		
		linha = 6;
		
		for (coluna = 0; coluna < 8; coluna++)
		{
			g2d.drawImage(imagens_peca[8 + coluna], coluna * tileWidth, linha * tileHeight, tileWidth, tileHeight, null);
		}
		
	}
}
