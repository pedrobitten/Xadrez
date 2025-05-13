package Model;


public class Tabuleiro {
  public Pecas[][] matriz = new Pecas[8][8];
  private int cont1;
  private int cont2;
  
  public Tabuleiro(){
    
    //Posicionar as pecas do jogador2 
    matriz[0][0] = new Torre("T2", 2);
    matriz[0][7] = new Torre("T2", 2);
    matriz[0][1] = new Cavalo("C2", 2);
    matriz[0][6] = new Cavalo("C2", 2);
    matriz[0][2] = new Bispo("B2", 2);
    matriz[0][5] = new Bispo("B2", 2);
    matriz[0][3] = new Rainha("R2", 2);
    matriz[0][4] = new Rei("r2", 2);
    
    //Posicionar peao do jogador2
    for (cont2 = 0; cont2 < 8; cont2++)
    {
      matriz[1][cont2] = new Peao("P2", 2);
    }
    
    //Resto do Tabuleiro
    for (cont1 = 2; cont1 < 6; cont1++)
    {
      for (cont2 = 0; cont2 < 8; cont2++)
      {
        matriz[cont1][cont2] = null;
      } 
    }
    
    //Posicionar peao do jogador1
    for (cont2 = 0; cont2 < 8; cont2++)
    {
      matriz[6][cont2] = new Peao("P1", 1);
    }
    
    //apenas teste
    
   
    
    //matriz[6][4] = null;

    
    //Posicionar as pecas do jogador1
    matriz[7][0] = new Torre("T1", 1);
    matriz[7][7] = new Torre("T1", 1);
    matriz[7][1] = new Cavalo("C1", 1);
    matriz[7][6] = new Cavalo("C1", 1);
    matriz[7][2] = new Bispo("B1", 1);
    matriz[7][5] = new Bispo("B1", 1);
    matriz[7][3] = new Rainha("R1", 1);
    matriz[7][4] = new Rei("r1", 1);
    
  }
  
  public Pecas getPecaNaPosicao(String coordenada) {
	  if (coordenada.length() != 2) {
		  return null;
	  }
	  
	  char colunaChar = coordenada.toLowerCase().charAt(0);
	  char linhaChar = coordenada.charAt(1);
	  
	  int coluna = colunaChar - 'a';
	  int linha = 8 - Character.getNumericValue(linhaChar);
	  
	  if (coluna < 0 || coluna > 7 || linha < 0 || linha > 7) {
		  return null;
	  }
	  
	  
	  return matriz[linha][coluna];
  }
  
  public void movimento(Pecas peca, Tabuleiro tabuleiro, String coordenada, String coordenada_destino)
  {
	  
	  if (peca instanceof Peao) {
	
		  Peao peao = (Peao)peca;
		 

		  peao.movimento(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  else if (peca instanceof Torre) {
		  Torre torre = (Torre)peca;
		  
		  torre.movimento(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  else if (peca instanceof Bispo) {
		  Bispo bispo = (Bispo)peca;
		  
		  bispo.movimento(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  else if (peca instanceof Rainha) {
		  Rainha rainha = (Rainha)peca;
		  rainha.movimento(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  else if (peca instanceof Rei) {
		  Rei rei = (Rei) peca;
		  rei.movimento(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  else if (peca instanceof Cavalo) {
		  Cavalo cavalo = (Cavalo) peca;
		  
		  cavalo.movimento(tabuleiro, coordenada, coordenada_destino);
		  
	  }
			
	  
  }
  
  public void ataque(Pecas peca, Tabuleiro tabuleiro, String coordenada, String coordenada_destino)
  {
	  if (peca instanceof Peao) {
	
		  Peao peao = (Peao)peca;
		 
		  peao.ataque(tabuleiro, coordenada, coordenada_destino);;
	  }
	  
	  
	  else if (peca instanceof Torre) {
		  Torre torre = (Torre)peca;
		  
		  torre.ataque(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  
	  else if (peca instanceof Bispo) {
		  Bispo bispo = (Bispo)peca;
		  
		  bispo.ataque(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  
	  
	  else if (peca instanceof Rainha) {
		  Rainha rainha = (Rainha)peca;
		  
		  rainha.ataque(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  
	  
	  else if (peca instanceof Rei) {
		  Rei rei = (Rei) peca;
		  
		  rei.ataque(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  
	  
	  else if (peca instanceof Cavalo) {
		  Cavalo cavalo = (Cavalo) peca;
		  
		  cavalo.ataque(tabuleiro, coordenada, coordenada_destino);
		  
	  }
	  
	  
  }
  
  public void printaTabuleiro(){
    
    for (cont1 = 0; cont1 < 8; cont1++)
    {
      for (cont2 = 0; cont2 < 8; cont2++)
      {
        if (matriz[cont1][cont2] == null){
          System.out.print("00");
        }
        
        else{
          System.out.print("" + matriz[cont1][cont2].getPeca() + "");  
        }
        
      }
      
      System.out.println();
    }
    
  }
}