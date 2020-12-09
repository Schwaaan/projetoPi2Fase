package be.domain.base;

import be.domain.Alternative;
import java.util.List;
import java.util.Locale;

public abstract class Question {

  private Integer id;
  private TypeQuestion typeQuestion;
  private String question;
  private boolean isDeleted;

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
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

  public TypeQuestion getTypeQuestion() {
    return typeQuestion;
  }

  public void setTypeQuestion(TypeQuestion typeQuestion) {
    this.typeQuestion = typeQuestion;
  }

  public void setTypeQuestion(String type) {
    String typeQuestion = type.toUpperCase(new Locale(type));

    if (typeQuestion.equals("DISCURSIVA")) {
      setTypeQuestion(TypeQuestion.DISCURSIVE);
    } else if (typeQuestion.equals("OBJETIVA")) {
      setTypeQuestion(TypeQuestion.OBJECTIVE);
    }
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

  public abstract boolean createQuestion();

  public void setAlternativeList(List<Alternative> alternativeList) {
  }

}