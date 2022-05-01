package spring.jsp.hibernate1.model;

public class Tag {
  private String value;

  public Tag() {
  }

  public Tag(String val) {
    this.value = val;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Tag{" + "value='" + value + '\'' + '}';
  }
}
