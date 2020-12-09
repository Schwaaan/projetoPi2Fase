package be.domain;

import be.domain.base.Question;
import be.domain.base.TypeQuestion;
import be.services.QuestionService;
import java.util.List;

public class ObjectiveQuestion extends Question {

  private List<Alternative> alternativeList;

  @Override
  public boolean createQuestion() {
    this.setTypeQuestion(TypeQuestion.OBJECTIVE);
    boolean create = QuestionService.insert(this);
    System.out.println("Questão objetiva criada com sucesso!");
    return create;
  }

  public List<Alternative> getAlternativeList() {
    return alternativeList;
  }

  @Override
  public void setAlternativeList(List<Alternative> alternativeList) {
    this.alternativeList = alternativeList;
  }

}