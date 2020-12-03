package be.domain.base;

public enum TypeQuestion {

  DISCURSIVE("Discursiva"), OBJECTIVE("Objetiva");

  private final String type;

  private TypeQuestion(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}