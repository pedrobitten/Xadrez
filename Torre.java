public class Torre extends Pecas {

  private String torre_do_jogador;
  public int jogador;
  
  
  public Torre(String torre_J, int jogador_1_ou_2){
    torre_do_jogador = torre_J;
    jogador = jogador_1_ou_2;
  }
  
  public String getPeca(){
    return torre_do_jogador;
  }
  
  
  //problema no movimento para esquerda
  public void movimentoTorre(Tabuleiro tabuleiro, Torre torre_jogador,  String coordenada_da_peca, String coordenada_do_destino)
  {
	char colunaChar_peca = coordenada_da_peca.toLowerCase().charAt(0);
	char linhaChar_peca = coordenada_da_peca.charAt(1);

	int coluna_peca = colunaChar_peca - 'a';
	int linha_peca = 8 - Character.getNumericValue(linhaChar_peca);
	
	char colunaChar_destino = coordenada_do_destino.toLowerCase().charAt(0);
	char linhaChar_destino = coordenada_do_destino.charAt(1);

	int coluna_destino = colunaChar_destino - 'a';
	int linha_destino = 8 - Character.getNumericValue(linhaChar_destino);
	
	boolean linha_reta = (coluna_peca == coluna_destino || linha_peca == linha_destino);
	
	if (!linha_reta) {
		System.out.println("Movimento inválido! A torre só pode andar em linha reta.");
        return;
	}
	
	if (linha_peca == linha_destino) {
        int inicio = Math.min(coluna_peca, coluna_destino) + 1;
        int fim = Math.max(coluna_peca, coluna_destino);
        for (int cont = inicio; cont < fim; cont++)
        {
            if (tabuleiro.matriz[linha_peca][cont] != null) {
                System.out.println("Há peças no caminho!");
                return;
            }
        }
    }
	
	else if (coluna_peca == coluna_destino) {
        int inicio = Math.min(linha_peca, linha_destino) + 1;
        int fim = Math.max(linha_peca, linha_destino);
        for (int cont = inicio; cont < fim; cont++)
        {
            if (tabuleiro.matriz[cont][coluna_peca] != null) {
                System.out.println("Há peças no caminho!");
                return;
            }
        }
    }
		  
	
	tabuleiro.matriz[linha_peca][coluna_peca] = null;
	tabuleiro.matriz[linha_destino][coluna_destino] = torre_jogador;
		
	
  }
}
