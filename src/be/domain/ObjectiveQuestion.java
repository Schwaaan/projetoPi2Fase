package be.domain;

import be.domain.base.Question;
import be.domain.base.TypeQuestion;
import be.services.QuestionService;
import java.util.List;

public class ObjectiveQuestion extends Question {

  private List<Alternative> alternativeList;

  @Override
  public String createQuestion() {
    this.setTypeQuestion(TypeQuestion.OBJECTIVE);
    QuestionService.insert(this);
    System.out.println("Questão objetiva criada com sucesso!");
    return "Questão criada com sucesso";
  }

  public List<Alternative> getAlternativeList() {
    return alternativeList;
  }

  @Override
  public void setAlternativeList(List<Alternative> alternativeList) {
    this.alternativeList = alternativeList;
  }
}
