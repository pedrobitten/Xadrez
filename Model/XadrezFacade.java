package Model;

import View.*;
import Controller.*;

public class XadrezFacade {
    private Tabuleiro tabuleiro;
    private Control control;
    private DesenhaTabuleiro view;

    public XadrezFacade() {
        this.tabuleiro = new Tabuleiro();
        this.control = Control.getController();
        this.view = new DesenhaTabuleiro();
    }

    // Método para inicializar o jogo
    public void iniciarJogo() {
        JanelaInicial janela = new JanelaInicial("Xadrez");
        janela.setVisible(true);
    }

    public boolean isXeque(String corRei) {
        int[] posicaoRei = (corRei.equals("branco")) 
            ? control.getPosicaoReiJogador1() 
            : control.getPosicaoReiJogador2();
        
        return control.xequeRei(posicaoRei[0], posicaoRei[1], corRei) > 0;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void atualizarView() {
        view.repaint();
    }
}
