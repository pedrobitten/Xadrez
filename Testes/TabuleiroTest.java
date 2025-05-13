package Testes;
import Model.Peao;
import Model.Tabuleiro;
import static org.junit.Assert.*;
import org.junit.Test;

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
	
	//testar movimento
	//testar ataque

}
