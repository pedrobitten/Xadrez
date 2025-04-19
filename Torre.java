public class Torre extends Pecas {

  private String torre_do_jogador;
  
  public Torre(String torre_J){
    torre_do_jogador = torre_J;
  }
  
  @Override
  public String getPeca(){
    return torre_do_jogador;
  }
}