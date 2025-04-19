public class Peao extends Pecas {
  
  private String peao_do_jogador;
  
  public Peao(String peao_J){
    peao_do_jogador = peao_J;
  }
  
  @Override
  public String getPeca(){
    return peao_do_jogador;
  }
  
  
}