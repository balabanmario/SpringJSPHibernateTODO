package spring.jsp.hibernate1.model;

public class SubTask {
  private int id;
  private String name;
  private Tag tag;

  public SubTask() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    return "SubTask{" + "id=" + id + ", name='" + name + '\'' + ", tag=" + tag + '}';
  }
}
