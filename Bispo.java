public class Bispo extends Pecas {

  private String bispo_do_jogador;
  private int jogador;
  
  public Bispo(String bispo_J, int jogador_1_ou_2){
    bispo_do_jogador = bispo_J;
    jogador = jogador_1_ou_2;
  }
  
  public String getPeca(){
    return bispo_do_jogador;
  }
  
  public int getJogador() {
    return jogador;
  }
  
  public void movimento(Tabuleiro tabuleiro, String coordenada_da_peca, String coordenada_do_destino)
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
		
		if (Math.abs(diferenca_linha) != Math.abs(diferenca_coluna)) {
			System.out.println("Movimento inválido! O bispo só pode se mover na diagonal");
			return ;
		}
		
		//Direção da movimentação
		int direcao_linha = Integer.signum(diferenca_linha);
		int direcao_coluna = Integer.signum(diferenca_coluna);
		
		int linha = linha_peca + direcao_linha;
		int coluna = coluna_peca + direcao_coluna;
		
		//Verificando se há peças no meio do caminho
		while(linha != linha_destino && coluna != coluna_destino)
		{
			if (tabuleiro.matriz[linha][coluna] != null) {
				System.out.println("Caminho bloqueado! Não é possível mover o bispo.");
	            return;
			}
			
			linha += direcao_linha;
			coluna += direcao_coluna;
		}
		
		
		tabuleiro.matriz[linha_peca][coluna_peca] = null;
		tabuleiro.matriz[linha_destino][coluna_destino] = this;
	  
  }
  
  public void ataque(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_peca_inimiga)
  {
	  char colunaChar_bispo = coordenada_peca.toLowerCase().charAt(0);
	  char linhaChar_bispo = coordenada_peca.charAt(1);

	  int coluna_bispo = colunaChar_bispo - 'a';
	  int linha_bispo = 8 - Character.getNumericValue(linhaChar_bispo);
		
	  char colunaChar_peca_inimiga = coordenada_peca_inimiga.toLowerCase().charAt(0);
	  char linhaChar_peca_inimiga = coordenada_peca_inimiga.charAt(1);

	  int coluna_peca_inimiga = colunaChar_peca_inimiga - 'a';
	  int linha_peca_inimiga = 8 - Character.getNumericValue(linhaChar_peca_inimiga);
	  
	  int diferenca_linha = linha_peca_inimiga - linha_bispo;
	  int diferenca_coluna = coluna_peca_inimiga - coluna_bispo;
		
	  if (Math.abs(diferenca_linha) != Math.abs(diferenca_coluna)) {
		  System.out.println("Movimento inválido! O bispo só pode se mover na diagonal");
		  return ;
	  }
	  
	  int direcao_linha = Integer.signum(diferenca_linha);
	  int direcao_coluna = Integer.signum(diferenca_coluna);
		
	  int linha = linha_bispo + direcao_linha;
	  int coluna = coluna_bispo + direcao_coluna;
		
	  //Verificando se há peças no meio do caminho
	  while(linha != linha_peca_inimiga && coluna != coluna_peca_inimiga)
	  {
		  if (tabuleiro.matriz[linha][coluna] != null) {
			  System.out.println("Caminho bloqueado! Não é possível mover o bispo.");
			  return;
		  }
			
		  linha += direcao_linha;
		  coluna += direcao_coluna;
	  }
	  

	  Pecas peca_inimiga = tabuleiro.getPecaNaPosicao(coordenada_peca_inimiga);

	  if (peca_inimiga.getJogador() == this.getJogador()) {
		System.out.println("Movimento invalido! O bispo nao pode atacar");
		return ;
	  }
	
	  tabuleiro.matriz[linha_peca_inimiga][coluna_peca_inimiga] = this;
	  tabuleiro.matriz[linha_bispo][coluna_bispo] = null;
	
  }

}
