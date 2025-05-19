package View;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DesenhaTabuleiro extends JPanel {

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
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
	}
}
