package Testes;
import Model.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TabuleiroTest {

	@Test
	public void peaoNasPosicoesCorretasJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();

		int coluna;
		
		for (coluna = 0; coluna < 8; coluna++)
		{
			assertEquals("P1", tabuleiro.matriz[6][coluna].getPeca());
		}

	}
	
	@Test
	public void peaoNasPosicoesCorretasJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();

		int coluna;
			
		for (coluna = 0; coluna < 8; coluna++)
		{
			assertEquals("P2", tabuleiro.matriz[1][coluna].getPeca());
		}
		
	}
	
	@Test
	public void areaDoMeioLimpa() {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		int coluna;
		int linha;
		
		for (linha = 0; linha < 4; linha++)
		{
			for (coluna = 0; coluna < 8; coluna ++)
			{
				assertNull(tabuleiro.matriz[linha+2][coluna]);
			}
		}
	}
	
	@Test
	public void pecasPosicoesCorretasJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		assertEquals("T1", tabuleiro.matriz[7][0].getPeca());
		assertEquals("C1", tabuleiro.matriz[7][1].getPeca());
		assertEquals("B1", tabuleiro.matriz[7][2].getPeca());
		assertEquals("R1", tabuleiro.matriz[7][3].getPeca());
		assertEquals("r1", tabuleiro.matriz[7][4].getPeca());
		assertEquals("B1", tabuleiro.matriz[7][5].getPeca());
		assertEquals("C1", tabuleiro.matriz[7][6].getPeca());
		assertEquals("T1", tabuleiro.matriz[7][7].getPeca());
	}
	
	@Test
	public void pecasPosicoesCorretasJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		assertEquals("T2", tabuleiro.matriz[0][0].getPeca());
		assertEquals("C2", tabuleiro.matriz[0][1].getPeca());
		assertEquals("B2", tabuleiro.matriz[0][2].getPeca());
		assertEquals("R2", tabuleiro.matriz[0][3].getPeca());
		assertEquals("r2", tabuleiro.matriz[0][4].getPeca());
		assertEquals("B2", tabuleiro.matriz[0][5].getPeca());
		assertEquals("C2", tabuleiro.matriz[0][6].getPeca());
		assertEquals("T2", tabuleiro.matriz[0][7].getPeca());
	}
	
	//testar getPecaNaPosicao
	@Test
	public void testaGetPecaNaPosicao() {
		String coordenada1 = "a1";
		String coordenada2 = "h8";
		String coordenada3 = "3";
		String coordenada4 = "i2";
		String coordenada5 = "c9";
		Tabuleiro tabuleiro = new Tabuleiro();
		int coluna1 = 0;
		int linha1 = 7;
		int linha2 = 0;
		int coluna2 = 7;
		
		Pecas peca1 = tabuleiro.getPecaNaPosicao(coordenada1);
		Pecas peca2 = tabuleiro.getPecaNaPosicao(coordenada2);
		Pecas peca3 = tabuleiro.getPecaNaPosicao(coordenada3);
		Pecas peca4 = tabuleiro.getPecaNaPosicao(coordenada4);
		Pecas peca5 = tabuleiro.getPecaNaPosicao(coordenada5);
		
		
		assertEquals(tabuleiro.matriz[linha1][coluna1], peca1);
		assertEquals(tabuleiro.matriz[linha2][coluna2], peca2);
	
		assertNull(peca3);
		assertNull(peca4);
		assertNull(peca5);
	}
	

	@Test
	public void testaMovimentoPeaoJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		Peao peao1 = new Peao("P1", 1);
		Peao peao2 = new Peao("P1", 1);
		Peao peao3 = new Peao("P1", 1);
		Peao peao4 = new Peao("P1", 1);
		Peao peao5 = new Peao("P1", 1);
		Peao peao6 = new Peao("P1", 1);
		Peao peao7 = new Peao("P1", 1);
		
		
		//Anda apenas 1 casa
		tabuleiro.movimento(peao1, tabuleiro, "e2", "e3");
		assertEquals(peao1, tabuleiro.matriz[5][4]);
		assertNull(tabuleiro.matriz[6][4]);
		
		//Anda 2 casas 
		tabuleiro.movimento(peao2, tabuleiro, "a2", "a4");
		assertEquals(peao2, tabuleiro.matriz[4][0]);
		assertNull(tabuleiro.matriz[6][0]);
		
		//Andar mais de 2 casas
		
		// Redireciona System.out para um buffer
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
		
		tabuleiro.movimento(peao3, tabuleiro, "c2", "c5");
		String output = outContent.toString().trim();
		//assertEquals("Movimento invalido! Peao nao pode andar mais de 2 casas", output);
		
		System.setOut(originalOut);
		
		
		//Andar 2 casas sabendo que o peao esta fora da posicao original

		outContent = new ByteArrayOutputStream();
	    originalOut = System.out;
	    System.setOut(new PrintStream(outContent));
		
		tabuleiro.movimento(peao2, tabuleiro, "a4", "a6");
		String output2 = outContent.toString().trim();

		assertEquals("Movimento invalido! Peao ja saiu da posicao original", output2);
		System.setOut(originalOut);
		
		//Andar sabendo que tem pecas no caminho
		
		outContent = new ByteArrayOutputStream();
	    originalOut = System.out;
	    System.setOut(new PrintStream(outContent));
	    
	    tabuleiro.matriz[5][3] = new Cavalo("C1", 1);
	    tabuleiro.movimento(peao5, tabuleiro, "d2", "d4");
	    String output3 = outContent.toString().trim();

	    //assertEquals("Movimento invalido! Ha pecas no caminho!", output3);
	    System.setOut(originalOut);
	    
	    //Fazer movimento onde o destino esta ocupado
	    
	    outContent = new ByteArrayOutputStream();
	    originalOut = System.out;
	    System.setOut(new PrintStream(outContent));
	    
	    //tabuleiro.printaTabuleiro();
	    
	    tabuleiro.matriz[6][6] = new Peao("P2", 2);
	    
	    tabuleiro.movimento(peao6, tabuleiro, "g2", "g3");
	    
	    String output4 = outContent.toString().trim();
	    System.out.print(output4);
	    
	    //assertEquals("Movimento invalido! Ha pecas no caminho!", output4);
	    System.setOut(originalOut);
	    
	    
	    tabuleiro.matriz[6][7] = new Peao("P2", 2);
	    
	    
		
		
	}
	
	//testar ataque

}
