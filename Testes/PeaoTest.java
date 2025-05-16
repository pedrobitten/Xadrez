package Testes;
import Model.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class PeaoTest {

	@Test
	public void testaMovimentoPeaoJogador1Anda1Casa() {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		Peao peao = new Peao("P1", 1);

		tabuleiro.movimento(peao, tabuleiro, "e2", "e3");
		assertEquals(peao, tabuleiro.matriz[5][4]);
		assertNull(tabuleiro.matriz[6][4]);	
	}
	
	@Test
	public void testaMovimentoPeaoJogador1Anda2Casas() {
		
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P1", 1);
		
		tabuleiro.movimento(peao, tabuleiro, "a2", "a4");
		assertEquals(peao, tabuleiro.matriz[4][0]);
		assertNull(tabuleiro.matriz[6][0]);
	}
	
	@Test
	public void testaMovimentoPeaoJogador1AndarMaisDe2Casas() {
		
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P1", 1);
		
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
		
		tabuleiro.movimento(peao, tabuleiro, "c2", "c5");


		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Peao nao pode andar mais de 2 casas"));
		
	}
	
	@Test
	public void testaMovimentoPeaoJogador1Andar2CasasForaPosicaoOriginal()
	{
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P1", 1);
		peao.posicao = "diferente";
		tabuleiro.matriz[4][0] = peao;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	    System.setOut(new PrintStream(outContent));
		
		tabuleiro.movimento(peao, tabuleiro, "a4", "a6");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Peao ja saiu da posicao original"));
		
	}
	
	@Test
	public void testaMovimentoPeaoJogador1PecasNoCaminho() {

		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P1", 1);
		tabuleiro.matriz[5][3] = new Cavalo("C1", 1);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	    System.setOut(new PrintStream(outContent));
		 
		tabuleiro.movimento(peao, tabuleiro, "d2", "d4");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));

	}
	
	@Test 
	public void testaMovimentoPeaoJogador1Andar1CasaDestinoOcupado() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P1", 1);
		tabuleiro.matriz[5][6] = new Peao("P2", 2);
		
		//tabuleiro.printaTabuleiro();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	    System.setOut(new PrintStream(outContent));
	    
	    tabuleiro.movimento(peao, tabuleiro, "g2", "g3");
	    
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Movimento invalido! Casa destino ocupada!"));
	}
	
	@Test 
	public void testaMovimentoPeaoJogador1Andar2CasasDestinoOcupado() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P1", 1);
		tabuleiro.matriz[4][7] = new Peao("P2", 2);
		
		//tabuleiro.printaTabuleiro();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	    System.setOut(new PrintStream(outContent));
	    
	    tabuleiro.movimento(peao, tabuleiro, "h2", "h4");
	    
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	}
	
	@Test
	public void testaMovimentoPeaoJogador2Anda1Casa() {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		Peao peao = new Peao("P2", 2);

		tabuleiro.movimento(peao, tabuleiro, "b7", "b6");
		assertEquals(peao, tabuleiro.matriz[2][1]);
		assertNull(tabuleiro.matriz[1][1]);	
	}
	
	
	@Test
	public void testaMovimentoPeaoJogador2Anda2Casas() {
		
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P2", 2);
		
		tabuleiro.movimento(peao, tabuleiro, "c7", "c6");
		assertEquals(peao, tabuleiro.matriz[2][2]);
		assertNull(tabuleiro.matriz[1][2]);
	}
	
	
	
	@Test
	public void testaMovimentoPeaoJogador2AndarMaisDe2Casas() {
		
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P2", 2);
		
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
		
		tabuleiro.movimento(peao, tabuleiro, "f7", "f3");


		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Peao nao pode andar mais de 2 casas"));
		
	}
	
	
	
	@Test
	public void testaMovimentoPeaoJogador2Andar2CasasForaPosicaoOriginal()
	{
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P2", 2);
		peao.posicao = "diferente";
		tabuleiro.matriz[2][7] = peao;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	    System.setOut(new PrintStream(outContent));
		
		tabuleiro.movimento(peao, tabuleiro, "h6", "h4");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Peao ja saiu da posicao original"));
		
	}
	
	
	
	@Test
	public void testaMovimentoPeaoJogador2PecasNoCaminho() {

		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P2", 2);
		tabuleiro.matriz[2][4] = new Cavalo("C2", 2);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	    System.setOut(new PrintStream(outContent));
		 
		tabuleiro.movimento(peao, tabuleiro, "e7", "e5");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));

	}
	
	
	@Test 
	public void testaMovimentoPeaoJogador2Andar1CasaDestinoOcupado() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P2", 2);
		tabuleiro.matriz[2][0] = new Peao("P1", 1);
		
		//tabuleiro.printaTabuleiro();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	    System.setOut(new PrintStream(outContent));
	    
	    tabuleiro.movimento(peao, tabuleiro, "a7", "a6");
	    
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Movimento invalido! Casa destino ocupada!"));
	}
	
	//Consertar
	@Test 
	public void testaMovimentoPeaoJogador2Andar2CasasDestinoOcupado() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Peao peao = new Peao("P2", 2);
		tabuleiro.matriz[3][3] = new Peao("P1", 1);
		
		//tabuleiro.printaTabuleiro();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	    System.setOut(new PrintStream(outContent));
	    
	    tabuleiro.movimento(peao, tabuleiro, "d7", "d5");
	    
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	}
	
	//testar ataque

}
