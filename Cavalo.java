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

}
