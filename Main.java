import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      Tabuleiro tabuleiro = new Tabuleiro();
      Scanner input = new Scanner(System.in);
      int comando = -1;
      String coordenada = "";
      int jogadorAtual = 1;

      
      System.out.println("Digite o comando, jogador" + jogadorAtual);
      System.out.println("1 - Movimento; 2 - Ataque; 3 - Mostrar tabuleiro");
      comando = Integer.parseInt(input.nextLine());
      while (comando != 0 && !coordenada.equals("0"))
      {
    	  if (comando == 1) {
    		  System.out.println("Escolha a peça (digite a coordenada):");
    		  System.out.println("Se quiser mostrar o tabuleiro, aperte 1");
    		  
    		  coordenada = input.nextLine();
    		  
    		  while (coordenada.equals("1")) 
    		  {
    			  
    			  tabuleiro.printaTabuleiro();
    			  System.out.println("Escolha a peça (digite a coordenada):");
        		  System.out.println("Se quiser mostrar o tabuleiro, aperte 1");
    			  coordenada = input.nextLine();
    		  }
    		  
    		  
    		  Pecas peca_escolhida = tabuleiro.getPecaNaPosicao(coordenada);
    		  //Peao peao = (Peao)peca_escolhida;
    		  //System.out.println(peca_escolhida.getClass());
    		  tabuleiro.movimento(peca_escolhida,tabuleiro,coordenada);
    		  tabuleiro.printaTabuleiro();
    		  jogadorAtual = 2;
    		  
    		  System.out.println("Digite o comando, jogador" + jogadorAtual);
    	      System.out.println("1 - Movimento; 2 - Ataque; 3 - Mostrar tabuleiro");
    	      comando = Integer.parseInt(input.nextLine());
    	  }
    		  
    	  
    	  else if (comando == 3) {
    		  tabuleiro.printaTabuleiro();
    		  System.out.println("Digite o comando, jogador" + jogadorAtual);
    	      System.out.println("1 - Movimento; 2 - Ataque; 3 - Mostrar tabuleiro");
    	      comando = input.nextInt();
    	  }
     
      }
    }
}
