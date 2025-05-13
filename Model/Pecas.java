package Model;
public abstract class Pecas {

  public abstract String getPeca();
  
  public abstract int getJogador();
  
  public abstract void movimento(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_destino);

  public abstract void ataque(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_peca_inimiga);
  
}