import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      Tabuleiro tabuleiro = new Tabuleiro();
      Scanner input = new Scanner(System.in);
      int comando = -1;
      String coordenada_peca = "";
      String coordenada_destino = "";
      int jogadorAtual = 1;

      
      System.out.println("Digite o comando, jogador" + jogadorAtual);
      System.out.println("1 - Movimento; 2 - Ataque; 3 - Mostrar tabuleiro");
      comando = Integer.parseInt(input.nextLine());
      while (comando != 0 && !coordenada_peca.equals("0"))
      {
    	  if (comando == 1) {
    		  System.out.println("Escolha a peça (digite a coordenada):");
    		  System.out.println("Se quiser mostrar o tabuleiro, aperte 1");
    		  
    		  coordenada_peca = input.nextLine();
    		  
    		  while (coordenada_peca.equals("1")) 
    		  {
    			  
    			  tabuleiro.printaTabuleiro();
    			  System.out.println("Escolha a peça (digite a coordenada):");
        		  System.out.println("Se quiser mostrar o tabuleiro, aperte 1");
    			  coordenada_peca = input.nextLine();
    		  }
    		  
    		  
    		  Pecas peca_escolhida = tabuleiro.getPecaNaPosicao(coordenada_peca);
    		  System.out.println("Escolha em que posicao ir (digite a coordenada):");
    		  System.out.println("Se quiser mostrar o tabuleiro, aperte 1");
			  coordenada_destino = input.nextLine();
    		  //Peao peao = (Peao)peca_escolhida;
    		  //System.out.println(peca_escolhida.getClass());
    		  tabuleiro.movimento(peca_escolhida,tabuleiro,coordenada_peca, coordenada_destino);
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
