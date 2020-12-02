package be.domain;

public class Alternative {

  private Integer id;
  private String alternative;
  private boolean rightAlternative;
  private boolean deleted;

  public Alternative(String text, boolean isTrue) {
    this.alternative = text;
    this.rightAlternative = isTrue;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public boolean isRightAlternative() {
    return rightAlternative;
  }

  public void setRightAlternative(boolean rightAlternative) {
    this.rightAlternative = rightAlternative;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public void setAlternative(String alternative) {
    this.alternative = alternative;
  }

  public String getAlternative() {
    return this.alternative;
  }

  public void setRigthAlternative(boolean rigthAlternative) {
    this.rightAlternative = rigthAlternative;
  }

  public boolean getRigthAlternative() {
    return this.rightAlternative;
  }

  @Override
  public String toString() {
    return String.format("%s: '%s'\n",
        getClass().getName(), getAlternative());
  }
}
