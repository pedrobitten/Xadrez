public class Rei extends Pecas {

  private String rei_do_jogador;
  public int jogador;
  
  public Rei(String rei_J, int jogador_1_ou_2){
    rei_do_jogador = rei_J;
    jogador = jogador_1_ou_2;
  }
  
  public String getPeca(){
    return rei_do_jogador;
  }
  
  public int getJogador() {
	  return jogador;
  }

  
  public void movimentoRei(Tabuleiro tabuleiro, Rei rei_jogador, String coordenada_da_peca, String coordenada_do_destino)
  {
	char colunaChar_peca = coordenada_da_peca.toLowerCase().charAt(0);
	char linhaChar_peca = coordenada_da_peca.charAt(1);
	
	int coluna_peca = colunaChar_peca - 'a';
	int linha_peca = 8 - Character.getNumericValue(linhaChar_peca);
			
	char colunaChar_destino = coordenada_do_destino.toLowerCase().charAt(0);
	char linhaChar_destino = coordenada_do_destino.charAt(1);
	
	int coluna_destino = colunaChar_destino - 'a';
	int linha_destino = 8 - Character.getNumericValue(linhaChar_destino);
			
	int diferenca_linha = linha_destino - linha_peca;
	int diferenca_coluna = coluna_destino - coluna_peca;
			
	if (Math.abs(diferenca_linha) > 1 || Math.abs(diferenca_coluna) > 1) {
		 System.out.println("Movimento invalido! O rei so pode andar uma casa em qualquer direção.");
	     return;
	}
	
	if (tabuleiro.matriz[linha_destino][coluna_destino] != null) {
		System.out.println("Movimento invalido! Ja existe uma peca na posicao de destino");
		return;
	}
	
	tabuleiro.matriz[linha_peca][coluna_peca] = null;
	tabuleiro.matriz[linha_destino][coluna_destino] = rei_jogador;
	
	
  }

}
