package View;

import static org.junit.Assert.*;
import java.awt.event.*;
import javax.swing.*;
import org.junit.*;

public class JanelaInicialTest {
    private JanelaInicial janela;

    @Before
    public void setUp() {
        janela = new JanelaInicial("Teste");
    }

    @Test
    public void testInicializacao() {
        assertEquals("Teste", janela.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, janela.getDefaultCloseOperation());
        assertTrue(janela.getSize().width == 400 && janela.getSize().height == 300);
    }

    @Test
    public void testBotaoIniciarJogo() {
        // Encontra o botão "Iniciar Jogo"
        JButton iniciarJogo = null;
        for (Component c : janela.getContentPane().getComponents()) {
            if (c instanceof JPanel) {
                for (Component btn : ((JPanel)c).getComponents()) {
                    if (btn instanceof JButton && ((JButton)btn).getText().equals("Iniciar Jogo")) {
                        iniciarJogo = (JButton)btn;
                        break;
                    }
                }
            }
        }
        
        assertNotNull(iniciarJogo);
        
        // Simula clique (teste visual)
        iniciarJogo.doClick();
    }
}