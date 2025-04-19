
public class Tabuleiro {
  private Pecas[][] matriz = new Pecas[8][8];
  private int cont1;
  private int cont2;
  
  public Tabuleiro(){
    
    //Posicionar as pecas do jogador2 
    matriz[0][0] = new Torre("T2");
    matriz[0][7] = new Torre("T2");
    matriz[0][1] = new Bispo("B2");
    matriz[0][6] = new Bispo("B2");
    matriz[0][2] = new Cavalo("C2");
    matriz[0][5] = new Cavalo("C2");
    matriz[0][3] = new Rainha("R2");
    matriz[0][4] = new Rei("r2");
    
    //Posicionar peao do jogador2
    for (cont2 = 0; cont2 < 8; cont2++)
    {
      matriz[1][cont2] = new Peao("P2");
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
      matriz[6][cont2] = new Peao("P1");
    }
    
    //Posicionar as pecas do jogador1
    matriz[0][0] = new Torre("T1");
    matriz[0][7] = new Torre("T1");
    matriz[0][1] = new Bispo("B1");
    matriz[0][6] = new Bispo("B1");
    matriz[0][2] = new Cavalo("C1");
    matriz[0][5] = new Cavalo("C1");
    matriz[0][3] = new Rainha("R1");
    matriz[0][4] = new Rei("r1");
    
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