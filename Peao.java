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
  
  public int getJogador() {
	  return jogador;
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
  
  public void ataquePeao(Tabuleiro tabuleiro, Peao peao_jogador, String coordenada_peao, String coordenada_peca_inimiga)
  {
	  //Testar para peao do jogador 2
	  char colunaChar_peao = coordenada_peao.toLowerCase().charAt(0);
	  char linhaChar_peao = coordenada_peao.charAt(1);
	  
	  int coluna_peao = colunaChar_peao - 'a';
	  int linha_peao = 8 - Character.getNumericValue(linhaChar_peao);
	  
	  char colunaChar_peca_inimiga = coordenada_peca_inimiga.toLowerCase().charAt(0);
	  char linhaChar_peca_inimiga = coordenada_peca_inimiga.charAt(1);
	  
	  int coluna_peca_inimiga = colunaChar_peca_inimiga - 'a';
	  int linha_peca_inimiga = 8 - Character.getNumericValue(linhaChar_peca_inimiga);
	  
	  int diferenca_linha = linha_peca_inimiga - linha_peao;
	  int diferenca_coluna = coluna_peca_inimiga - coluna_peao;
	  
	  if (Math.abs(diferenca_linha) > 1 && Math.abs(diferenca_coluna) > 1) {
		  System.out.println("Movimento inválido! O peão não pode atacar");
		  return ;
	  }
	  
	  Pecas peca_inimiga = tabuleiro.getPecaNaPosicao(coordenada_peca_inimiga);
	  
	  if (peca_inimiga.getJogador() == peao_jogador.getJogador()) {
		  System.out.println("Movimento inválido! O peão não pode atacar");
		  return ;
	  }
	  
	  

	  tabuleiro.matriz[linha_peca_inimiga][coluna_peca_inimiga] = peao_jogador;
	  tabuleiro.matriz[linha_peao][coluna_peao] = null;
	  
	  
	  
  }
  
}
  
  
