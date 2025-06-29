package Testes;
import Model.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class XequeMateTest {

    // 1. Mate do Louco (h4 com dama preta, rei branco em e1)
    @Test
    public void testaMateDoLouco() {
        Tabuleiro tabuleiro = new Tabuleiro();
        limparTabuleiro(tabuleiro);

        tabuleiro.matriz[7][4] = new Rei("branco", "rei");   // e1
        tabuleiro.matriz[0][4] = new Rei("preto", "rei");    // e8
        tabuleiro.matriz[4][7] = new Rainha("preto", "rainha"); // h4

        assertTrue(tabuleiro.xequeRei(7, 4, "branco") > 0);
        assertTrue(tabuleiro.ehXequeMate("branco"));
    }

    // 2. Mate do Pastor (f7 com dama branca, rei preto em e8)
    @Test
    public void testaMateDoPastor() {
        Tabuleiro tabuleiro = new Tabuleiro();
        limparTabuleiro(tabuleiro);

        tabuleiro.matriz[0][4] = new Rei("preto", "rei");    // e8
        tabuleiro.matriz[7][4] = new Rei("branco", "rei");   // e1
        tabuleiro.matriz[5][2] = new Bispo("branco", "bispo"); // c3
        tabuleiro.matriz[3][5] = new Rainha("branco", "rainha"); // f5

        assertTrue(tabuleiro.xequeRei(0, 4, "preto") > 0);
        assertTrue(tabuleiro.ehXequeMate("preto"));
    }

    // 3. Mate com bloqueio (rei encurralado no canto)
    @Test
    public void testaXequeMateComEncurralamento() {
        Tabuleiro tabuleiro = new Tabuleiro();
        limparTabuleiro(tabuleiro);

        tabuleiro.matriz[0][0] = new Rei("preto", "rei");    // a8
        tabuleiro.matriz[1][1] = new Rainha("branco", "rainha"); // b7
        tabuleiro.matriz[2][2] = new Rei("branco", "rei");   // c6 (controla casa b8)

        assertTrue(tabuleiro.xequeRei(0, 0, "preto") > 0);
        assertTrue(tabuleiro.ehXequeMate("preto"));
    }

    // 4. Xeque-mate com ataque em diagonal (sem chance de fuga)
    @Test
    public void testaMateDiagonal() {
        Tabuleiro tabuleiro = new Tabuleiro();
        limparTabuleiro(tabuleiro);

        tabuleiro.matriz[0][7] = new Rei("preto", "rei");    // h8
        tabuleiro.matriz[1][6] = new Rainha("branco", "rainha"); // g7
        tabuleiro.matriz[2][5] = new Rei("branco", "rei");   // f6

        assertTrue(tabuleiro.xequeRei(0, 7, "preto") > 0);
        assertTrue(tabuleiro.ehXequeMate("preto"));
    }

    // Método auxiliar para limpar o tabuleiro
    private void limparTabuleiro(Tabuleiro tabuleiro) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tabuleiro.matriz[i][j] = null;
    }
}
