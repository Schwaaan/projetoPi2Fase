package BE.domain;

public class Alternative {

  private String alternative;
  private boolean rightAlternative; // proponho alterar para rigthAlternative (alternativa certa) proposta aceita by jair lindoso

  public Alternative(String text, boolean isTrue) {
    this.alternative = text;
    this.rightAlternative = isTrue;
  }

  public void setAlternative(String alternative) {
    this.alternative = alternative;
  }

  public String getAlternative() {
    return this.alternative;
  }

  public void setRigthAlternative(boolean rigthAlternative) {
    this.rightAlternative = rigthAlternative;
  }

  public boolean getRigthAlternative() {
    return this.rightAlternative;
  }

  @Override
  public String toString() {
    return String.format("Resposta à questão '%s': '%s'\n",
        //   getAlternativeQuestion().getQuestion(),
        getAlternative());
  }
}
