package BE.domain;

import BE.domain.base.Question;

public class Alternative {

  private int id;
  private Question question;
  private String alternative;
  private boolean rightAlternative; // proponho alterar para rigthAlternative (alternativa certa) proposta aceita by jair lindoso

  public Alternative(int id, Question question, String alternative, boolean rightAlternative,
      boolean deleted) {
    this.id = id;
    this.question = question;
    setAlternative(alternative);
    setRigthAlternative(rightAlternative);
  }

  public int getId() {
    return this.id;
  }

  public int getAlternativeQuestionId() {
    return this.question.getId();
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

  public Question getAlternativeQuestion() {
    return this.question;
  }

  @Override
  public String toString() {
    return String.format("Resposta à questão '%s': '%s'\n",
        getAlternativeQuestion().getQuestion(),
        getAlternative());
  }
}
