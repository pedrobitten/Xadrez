package Model;
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
	  int direcao = (jogador == 1) ? -1 : 1;
			

			
	  if (Math.abs(diferenca_linha) > 2) {
		  System.out.println("Movimento invalido! Peao nao pode andar mais de 2 casas");
		  return;
	  }
					  
	  if (Math.abs(diferenca_linha) == 2 && !this.posicao.equals("original")) {
		  System.out.println("Movimento invalido! Peao ja saiu da posicao original");
		  return;
	  }
			
			
	  // Movimento reto 
	  if (coluna_peca == coluna_destino) {
		  int distancia = linha_destino - linha_peca;

		  // Movimento de 2 casas
		  if (distancia == 2 * direcao) {
			  int meio = linha_peca + direcao;
			  
			  if (tabuleiro.matriz[meio][coluna_peca] != null || tabuleiro.matriz[linha_destino][coluna_destino] != null) {
				  System.out.println("Movimento invalido! Ha pecas no caminho!");
				  return;
			  }
		  }

		  // Movimento de 1 casa
		  else if (distancia == 1 * direcao) {
			  if (tabuleiro.matriz[linha_destino][coluna_destino] != null) {
				  System.out.println("Movimento invalido! Casa destino ocupada!");
				  return;
			  }
		  }

	  }
		
		//Atualiza tabuleiro
		
		
		//Corrigir a promoção
		if (linha_destino == 0 & this.getJogador() == 1) {
			tabuleiro.matriz[linha_peca][coluna_peca] = null;
			tabuleiro.matriz[linha_destino][coluna_destino] = new Rainha("R1", 1);
		}
		
		else if (linha_destino == 7 & this.getJogador() == 2) {
			tabuleiro.matriz[linha_peca][coluna_peca] = null;
			tabuleiro.matriz[linha_destino][coluna_destino] = new Rainha("R2", 2);
		
		}
		
		else {
			
			this.posicao = "diferente";
			
			tabuleiro.matriz[linha_destino][coluna_destino] = this;
			
			tabuleiro.matriz[linha_peca][coluna_peca] = null;
		}
				 
					    
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
  
  
