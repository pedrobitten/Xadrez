package Testes;
import Model.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class XequeMateTest {

    @Test
    public void testaMateDoLouco() {
        Tabuleiro tabuleiro = new Tabuleiro();

        // Limpa o tabuleiro
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tabuleiro.matriz[i][j] = null;

        // Coloca peças envolvidas no mate
        Pecas reiBranco = new Rei("branco", "rei");
        Pecas reiPreto = new Rei("preto", "rei");
        Pecas damaPreta = new Rainha("preto", "rainha");

        // Coordenadas:
        // rei branco -> e1 → linha 7, coluna 4
        // rei preto  -> e8 → linha 0, coluna 4
        // dama preta -> h4 → linha 4, coluna 7

        tabuleiro.matriz[7][4] = reiBranco;
        tabuleiro.matriz[0][4] = reiPreto;
        tabuleiro.matriz[4][7] = damaPreta;

        // Verifica que o rei branco está em xeque
        int linhaReiBranco = 7, colunaReiBranco = 4;
        int xeque = tabuleiro.xequeRei(linhaReiBranco, colunaReiBranco, "branco");
        assertTrue("O rei branco deveria estar em xeque", xeque > 0);

        // Verifica xeque-mate
        boolean mate = tabuleiro.ehXequeMate("branco");
        assertTrue("Deveria ser xeque-mate", mate);
    }
    
    @Test
    public void testaXequeSemMate() {
        Tabuleiro tabuleiro = new Tabuleiro();
    
        // Limpa o tabuleiro
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tabuleiro.matriz[i][j] = null;
    
        // Coloca rei preto e dama branca
        Pecas reiPreto = new Rei("preto", "rei");
        Pecas damaBranca = new Rainha("branco", "rainha");
    
        tabuleiro.matriz[0][4] = reiPreto;   // e8
        tabuleiro.matriz[1][4] = damaBranca; // e7 → ameaça vertical
    
        // Deixa d8 livre para fuga
        tabuleiro.matriz[0][3] = null;
    
        // Verifica xeque
        int xeque = tabuleiro.xequeRei(0, 4, "preto");
        assertTrue("O rei preto está em xeque", xeque > 0);
    
        // Verifica que NÃO é xeque-mate
        boolean mate = tabuleiro.ehXequeMate("preto");
        assertFalse("Não deveria ser xeque-mate (rei pode fugir)", mate);
    }
    
}
