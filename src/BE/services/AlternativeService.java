package BE.services;

import BE.domain.Alternative;

import java.util.ArrayList;
import java.util.List;

public class AlternativeService {

  private static int index = 0;
  private static List<Alternative> alternatives = new ArrayList<>();

  public static List<Alternative> getAlternatives() {
    return alternatives;
  }

  public static void createAlternative(Alternative alternative) {
    alternative.setId(++index);
    alternatives.add(alternative);
    System.out.println(alternative);
  }

  public static void updateAlternative(Alternative alternative) {
    int index = alternatives.indexOf(alternative);
    if (index >= 0) {
      alternatives.set(index, alternative);
    }
  }

  public static void deleteAlternative(Alternative alternative) {
    alternatives.remove(alternative);
    System.out.printf("Alternativa %s excluída. Situação campo deleted: %b", alternative, alternative.getDeleted());
  }
}
