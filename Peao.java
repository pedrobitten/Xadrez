public class Peao extends Pecas {
  
  private String peao_do_jogador;
  private int jogador;
  public String posicao;
  
  public Peao(String peao_J, int jogador_1_ou_2){
    peao_do_jogador = peao_J;
    jogador = jogador_1_ou_2;
    posicao = "original";
  }
  

  public String getPeca(){
    return peao_do_jogador;
  }
  
  public void movimentoPeao(Tabuleiro tabuleiro, Peao peao_jogador, int tipo_de_movimento, String coordenada)
  {
	  if (tipo_de_movimento == 2 && peao_jogador.posicao != "original") {
		  System.out.println("Erro");
	  }
	  
	  else {
		  char colunaChar = coordenada.toLowerCase().charAt(0);
		  char linhaChar = coordenada.charAt(1);
		  int direcao;
		  
		  if (peao_jogador.jogador == 1) {
			  direcao = -1;
		  }
		  
		  else {
			  direcao = 1;
		  }
		 
		  int coluna = colunaChar - 'a';
		  int linha = 8 - Character.getNumericValue(linhaChar);
		  
		  if (tipo_de_movimento == 2) {
			  tabuleiro.matriz[linha + 2 * direcao][coluna] = peao_jogador;
			  tabuleiro.matriz[linha][coluna] = null;
			  peao_jogador.posicao = "diferente";
		  }
		  
		  else if (tipo_de_movimento == 1) {
			  System.out.println("Foi condicao");
			  tabuleiro.matriz[linha + direcao][coluna] = peao_jogador;
			  tabuleiro.matriz[linha][coluna] = null;
			  peao_jogador.posicao = "diferente";
		  }
		  
		  
	  }
	  
  }
  
}
  
  
