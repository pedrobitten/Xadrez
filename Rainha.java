public class Rainha extends Pecas {

  private String rainha_do_jogador;
  
  public Rainha(String rainha_J){
    rainha_do_jogador = rainha_J;
  }
  
  @Override
  public String getPeca(){
    return rainha_do_jogador;
  }

}