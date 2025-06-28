package Testes;
import Model.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class XequeMateTest {

    @Test
    public void testaMateDoLouco() {
        Tabuleiro tabuleiro = new Tabuleiro();

        // Limpa o tabuleiro (caso Tabuleiro inicialize com todas as peças)
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tabuleiro.matriz[i][j] = null;

        // Posiciona peças
        Rei reiBranco = new Rei("R1", 1);
        Rei reiPreto = new Rei("R2", 2);
        Rainha damaPreta = new Rainha("Q2", 2);

        tabuleiro.matriz[7][4] = reiBranco; // e1
        tabuleiro.matriz[0][4] = reiPreto;  // e8
        tabuleiro.matriz[3][7] = damaPreta; // h5 (ameaça o rei branco via diagonal)

        // Define coordenadas do rei branco
        int linhaReiBranco = 7; // e1
        int colunaReiBranco = 4;

        // Verifica xeque
        int xeque = tabuleiro.xequeRei(linhaReiBranco, colunaReiBranco, "branco");
        assertTrue(xeque > 0);

        // Verifica xeque-mate
        boolean mate = tabuleiro.ehXequeMate("branco");
        assertTrue(mate);
    }

    @Test
    public void testaMateDoPastor() {
        Tabuleiro tabuleiro = new Tabuleiro();
    
        // Limpa o tabuleiro
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tabuleiro.matriz[i][j] = null;

        // Posiciona peças
        Rei reiPreto = new Rei("R2", 2);
        Rei reiBranco = new Rei("R1", 1);
        Bispo bispoBranco = new Bispo("B1", 1);
        Rainha damaBranca = new Rainha("Q1", 1);

        tabuleiro.matriz[0][4] = reiPreto;    // e8
        tabuleiro.matriz[7][4] = reiBranco;   // e1
        tabuleiro.matriz[5][2] = bispoBranco; // c3
        tabuleiro.matriz[3][5] = damaBranca;  // f5 → ameaça f7

        // Xeque mate em f7
        tabuleiro.matriz[1][5] = null; // f7 está livre
        tabuleiro.matriz[1][6] = null; // g7 também (sem escape)

        int xeque = tabuleiro.xequeRei(0, 4, "preto");
        assertTrue(xeque > 0);

        boolean mate = tabuleiro.ehXequeMate("preto");
        assertTrue(mate);
    }

    @Test
    public void testaXequeSemMate() {
        Tabuleiro tabuleiro = new Tabuleiro();

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tabuleiro.matriz[i][j] = null;

        Rei reiPreto = new Rei("R2", 2);
        Rainha damaBranca = new Rainha("Q1", 1);

        tabuleiro.matriz[0][4] = reiPreto;    // e8
        tabuleiro.matriz[1][4] = damaBranca;  // e7 → xeque frontal

        // Casa d8 está livre para o rei fugir
        tabuleiro.matriz[0][3] = null;

        int xeque = tabuleiro.xequeRei(0, 4, "preto");
        assertTrue(xeque > 0);

        boolean mate = tabuleiro.ehXequeMate("preto");
        assertFalse(mate); // Rei pode fugir para d8
    }

    @Test
    public void testaAfogamentoEmpate() {
        Tabuleiro tabuleiro = new Tabuleiro();

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tabuleiro.matriz[i][j] = null;

        Rei reiPreto = new Rei("R2", 2);
        Rei reiBranco = new Rei("R1", 1);
        Rainha damaBranca = new Rainha("Q1", 1);

        tabuleiro.matriz[0][7] = reiPreto;     // h8
        tabuleiro.matriz[2][6] = damaBranca;   // g6
        tabuleiro.matriz[2][5] = reiBranco;    // f6 (controla casas de fuga)

        // Rei preto não está em xeque, mas não pode mover

        int xeque = tabuleiro.xequeRei(0, 7, "preto");
        assertEquals(0, xeque);

        boolean mate = tabuleiro.ehXequeMate("preto");
        assertFalse(mate); // Não é mate, é empate por afogamento
    }
}
