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
  
  //metodo de movimento do peao
  public void movimento(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_destino)
  {
	  	//Conversoes das coordenadas em inteiros
		char colunaChar_peca = coordenada_peca.toLowerCase().charAt(0);
		char linhaChar_peca = coordenada_peca.charAt(1);
				  
		int coluna_peca = colunaChar_peca - 'a';
		int linha_peca = 8 - Character.getNumericValue(linhaChar_peca);
				 
		char colunaChar_destino = coordenada_destino.toLowerCase().charAt(0);
		char linhaChar_destino = coordenada_destino.charAt(1);
		
		int coluna_destino = colunaChar_destino - 'a';
		int linha_destino = 8 - Character.getNumericValue(linhaChar_destino);
				  
		int diferenca_linha = linha_destino - linha_peca;
				  
		if (Math.abs(diferenca_linha) == 2 && !this.posicao.equals("original")) {
			System.out.println("Movimento invalido!");
			return;
		}
		
		//verificar se tem pecas no caminho
		int inicio = Math.min(linha_peca, linha_destino);
		int fim = Math.max(linha_peca, linha_destino);
			      
		for (int cont = inicio; cont < fim; cont++)
		{
			if (tabuleiro.matriz[cont][coluna_peca] != null) {
				System.out.println("Ha pecas no caminho!");
				return;
			}
		}
				 

		//Atualiza tabuleiro
		this.posicao = "diferente";

		tabuleiro.matriz[linha_destino][coluna_destino] = this;
	
		tabuleiro.matriz[linha_peca][coluna_peca] = null;
				    
  }
  
  public void ataque(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_peca_inimiga)
  {
	  //Testar para peao do jogador 2
	  char colunaChar_peao = coordenada_peca.toLowerCase().charAt(0);
	  char linhaChar_peao = coordenada_peca.charAt(1);
	  
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
	  
	  if (peca_inimiga.getJogador() == this.getJogador()) {
		  System.out.println("Movimento inválido! O peão não pode atacar");
		  return ;
	  }
	  
	  

	  tabuleiro.matriz[linha_peca_inimiga][coluna_peca_inimiga] = this;
	  tabuleiro.matriz[linha_peao][coluna_peao] = null;
	  
  }
  
}
  
  
