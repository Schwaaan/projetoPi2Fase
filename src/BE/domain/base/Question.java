package BE.domain.base;

public abstract class Question {

  private Integer id;
  private Type typeQuestion;
  private String question;

  public Question(String question) {
    this.question = question;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Type getTypeQuestion() {
    return typeQuestion;
  }

  public void setTypeQuestion(Type typeQuestion) {
    this.typeQuestion = typeQuestion;
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

    Question question = (Question) obj;
    return this.id == question.id;
  }

  public abstract String createQuestion();
}

