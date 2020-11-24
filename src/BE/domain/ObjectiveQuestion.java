package BE.domain;

import BE.domain.base.Question;
import BE.domain.base.Type;
import BE.services.QuestionService;

public class ObjectiveQuestion extends Question {

  public ObjectiveQuestion(String question) {
    super(question);
  }

  @Override
  public String createQuestion() {
    this.setTypeQuestion(Type.OBJECTIVE);
    QuestionService.createQuestion(this);
    System.out.println("Questão objetiva criada com sucesso!");
    return "Questão criada com sucesso";
  }
}
