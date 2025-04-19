public class Bispo extends Pecas {

  private String bispo_do_jogador;
  
  public Bispo(String bispo_J){
    bispo_do_jogador = bispo_J;
  }
  
  @Override
  public String getPeca(){
    return bispo_do_jogador;
  }

}