package be.domain;

public class Alternative {

  private String alternative;
  private int idQuestion;
  private boolean rightAlternative;
  private boolean isDeleted;

  public Alternative(String text, boolean isTrue) {
    this.alternative = text;
    this.rightAlternative = isTrue;
  }

  public int getIdQuestion() {
    return idQuestion;
  }

  public void setIdQuestion(int idQuestion) {
    this.idQuestion = idQuestion;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
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
    return String.format("%s: '%s'\n",
        getClass().getName(), getAlternative());
  }

}