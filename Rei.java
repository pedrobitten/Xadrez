public class Rei extends Pecas {

  private String rei_do_jogador;
  
  public Rei(String rei_J){
    rei_do_jogador = rei_J;
  }
  
  @Override
  public String getPeca(){
    return rei_do_jogador;
  }

}