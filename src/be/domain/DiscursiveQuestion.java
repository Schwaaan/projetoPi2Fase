package be.domain;

import be.domain.base.Question;
import be.domain.base.TypeQuestion;
import be.services.QuestionService;

public class DiscursiveQuestion extends Question {

  @Override
  public boolean createQuestion() {
    this.setTypeQuestion(TypeQuestion.DISCURSIVE);
    boolean create = QuestionService.insert(this);
    System.out.println("Questão discursiva criada com sucesso!");
    return create;
  }
}
