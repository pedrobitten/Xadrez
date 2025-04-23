public class Cavalo extends Pecas {

  private String cavalo_do_jogador;
  
  public Cavalo(String cavalo_J){
    cavalo_do_jogador = cavalo_J;
  }
  

  public String getPeca(){
    return cavalo_do_jogador;
  }

}
