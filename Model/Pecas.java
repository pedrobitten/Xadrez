package Model;
public abstract class Pecas {

  public abstract String getPeca();
  
  public abstract String getCor();
  
  public abstract void movimento(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_destino);

  public abstract void ataque(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_peca_inimiga);
  
}
