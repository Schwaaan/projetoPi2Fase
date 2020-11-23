package BE.services;

import BE.domain.Alternative;
import BE.domain.base.Question;

import java.util.ArrayList;
import java.util.List;

public class AlternativeService {

  List<Alternative> alternatives = new ArrayList<>();

  public Alternative createAlternative(int id, Question question, String alternative,
      boolean accepted, boolean deleted) {
    Alternative tempAlternative = new Alternative(id, question, alternative, accepted, deleted);
    alternatives.add(tempAlternative);
    return tempAlternative;
  }

  public void deleteAlternative(Alternative alternative) {
    alternatives.remove(alternative);
    System.out.printf("Alternativa %s excluída. Situação campo deleted: %b", alternative,
        alternative.getId());
  }
}
