package BE.domain;

public class Alternative {

  private int id;
  private Question question;
  private String alternative;
  private boolean rigthAlternative;
  private boolean deleted;

  public Alternative(Question question, String alternative, boolean rigthAlternative) {
    this.question = question;
    setAlternative(alternative);
    setRigthAlternative(rigthAlternative);
    setDeleted(false);
  }
  
  public void setId(int i) {
    if (id > 0) {
      this.id = i;
    }
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

  public void setRigthAlternative(boolean accepted) {
    this.rigthAlternative = accepted;
  }

  public boolean getAccepted() {
    return this.rigthAlternative;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public boolean getDeleted() {
    return this.deleted;
  }

  public Question getAlternativeQuestion() {
    return this.question;
  }

  @Override
  public String toString() {
    return String.format("Resposta à questão '%s': '%s'\n", getAlternativeQuestion().getQuestion(), getAlternative());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Alternative alt = (Alternative) obj;
    return this.id == alt.id;
  }


}
