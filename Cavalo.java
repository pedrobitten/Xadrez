public class Cavalo extends Pecas {

  private String cavalo_do_jogador;
  public int jogador;
  
  public Cavalo(String cavalo_J, int jogador_1_ou_2){
    cavalo_do_jogador = cavalo_J;
    jogador = jogador_1_ou_2;
  }
  

  public String getPeca(){
    return cavalo_do_jogador;
  }
  
  public int getJogador() {
	  return jogador;
  }

  
  public void movimentoCavalo(Tabuleiro tabuleiro, Cavalo cavalo_jogador, String coordenada_da_peca, String coordenada_do_destino)
  {
	  	char colunaChar_peca = coordenada_da_peca.toLowerCase().charAt(0);
		char linhaChar_peca = coordenada_da_peca.charAt(1);

		int coluna_peca = colunaChar_peca - 'a';
		int linha_peca = 8 - Character.getNumericValue(linhaChar_peca);
		
		char colunaChar_destino = coordenada_do_destino.toLowerCase().charAt(0);
		char linhaChar_destino = coordenada_do_destino.charAt(1);

		int coluna_destino = colunaChar_destino - 'a';
		int linha_destino = 8 - Character.getNumericValue(linhaChar_destino);
		
		int diferenca_linha = Math.abs(linha_destino - linha_peca);
		int diferenca_coluna = Math.abs(coluna_destino - coluna_peca);
		
		if ((diferenca_linha == 2 && diferenca_coluna == 1) || (diferenca_linha == 1 && diferenca_coluna == 2))
		{
			if (tabuleiro.matriz[linha_destino][coluna_destino] == null) {
				tabuleiro.matriz[linha_peca][coluna_peca] = null;
				tabuleiro.matriz[linha_destino][coluna_destino] = cavalo_jogador;
			}
			
			else {
				System.out.println("Movimento invalido: destino ocupado");
			}
		}
		
		else {
			System.out.println("Movimento invalido para o cavalo");
		}
	  
  }
  
  public void ataqueCavalo(Tabuleiro tabuleiro, Cavalo cavalo_jogador, String coordenada_cavalo, String coordenada_peca_inimiga)
  {
	  char colunaChar_cavalo = coordenada_cavalo.toLowerCase().charAt(0);
	  char linhaChar_cavalo = coordenada_cavalo.charAt(1);

	  int coluna_cavalo = colunaChar_cavalo - 'a';
	  int linha_cavalo = 8 - Character.getNumericValue(linhaChar_cavalo);
		
	  char colunaChar_peca_inimiga = coordenada_peca_inimiga.toLowerCase().charAt(0);
	  char linhaChar_peca_inimiga = coordenada_peca_inimiga.charAt(1);

	  int coluna_peca_inimiga = colunaChar_peca_inimiga - 'a';
	  int linha_peca_inimiga = 8 - Character.getNumericValue(linhaChar_peca_inimiga);
		
	  int diferenca_linha = Math.abs(linha_peca_inimiga - linha_cavalo);
	  int diferenca_coluna = Math.abs(coluna_peca_inimiga - coluna_cavalo);
	  
	  
	  if ((diferenca_linha == 2 && diferenca_coluna == 1) || (diferenca_linha == 1 && diferenca_coluna == 2))
	  {
		  Pecas peca_inimiga = tabuleiro.getPecaNaPosicao(coordenada_peca_inimiga);
		  
		  if (peca_inimiga.getJogador() == cavalo_jogador.getJogador()) {
			  System.out.println("Movimento inválido! O Cavalo não pode atacar");
			  return ;
		  }
		  
		  tabuleiro.matriz[linha_cavalo][coluna_cavalo] = null;
		  tabuleiro.matriz[linha_peca_inimiga][coluna_peca_inimiga] = cavalo_jogador;
		  	
		  	
	  }
		
	  else {
		  System.out.println("Movimento invalido para o cavalo");
	  }
	  
	  
  }

}
