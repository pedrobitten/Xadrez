public class Cavalo extends Pecas {

  private String cavalo_do_jogador;
  public int jogador;
  
  public Cavalo(String cavalo_J, int jogador_1_ou_2){
    cavalo_do_jogador = cavalo_J;
    jogador = jogador_1_ou_2;
  }
  

  public String getPeca(){
    return cavalo_do_jogador;
  }

}
