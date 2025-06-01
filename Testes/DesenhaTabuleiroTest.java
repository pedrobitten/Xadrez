package Testes;
import View.*;
import Model.*;

import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import org.junit.*;
import Model.*;

public class DesenhaTabuleiroTest {
    private DesenhaTabuleiro tabuleiro;
    private JFrame frame;

    @Before
    public void setUp() {
        frame = new JFrame();
        tabuleiro = new DesenhaTabuleiro();
        frame.add(tabuleiro);
        frame.setSize(800, 800);
    }

    @Test
    public void testInicializacao() {
        assertEquals("branco", tabuleiro.turno);
        assertFalse(tabuleiro.jogada_iniciada);
        assertTrue(tabuleiro.notifica_click_em_peca);
    }

    @Test
    public void testNotificaClickEmPeca() {
        // Cria um mock de peça
        Pecas pecaMock = new Pecas("peao", "branco") {
            @Override
            public Image getImage() {
                return null;
            }
        };
        
        // Configura o tabuleiro com uma peça
        tabuleiro.tabuleiro.matriz[0][0] = pecaMock;
        
        // Simula clique na peça
        MouseEvent mockEvent = new MouseEvent(
            tabuleiro, 
            MouseEvent.MOUSE_CLICKED, 
            System.currentTimeMillis(), 
            0, 
            10, 10, // Coordenadas dentro do primeiro quadrado
            1, 
            false
        );
        
        tabuleiro.getMouseListeners()[0].mouseClicked(mockEvent);
        
        assertEquals(pecaMock, tabuleiro.peca_selecionada);
        assertTrue(tabuleiro.jogada_iniciada);
    }

    @Test
    public void testPintaSeVazio() {
        Graphics2D g2d = (Graphics2D) tabuleiro.getGraphics();
        ArrayList<ArrayList<Integer>> coordenadas = new ArrayList<>();
        
        // Testa pintar quadrado vazio
        boolean result = tabuleiro.pintaSeVazio(g2d, 0, 0, "branco", coordenadas);
        assertTrue(result);
        assertEquals(1, coordenadas.size());
        
        // Testa tentar pintar fora do tabuleiro
        coordenadas.clear();
        result = tabuleiro.pintaSeVazio(g2d, -1, 0, "branco", coordenadas);
        assertFalse(result);
        assertEquals(0, coordenadas.size());
    }
}