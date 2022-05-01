package spring.jsp.hibernate1.model;

import java.util.ArrayList;
import java.util.List;

public class Inbox {
  private List<Todo> todoList = new ArrayList<>();

  public Inbox() {
    System.out.println("New inbox created");
  }

  public List<Todo> getTodoList() {
    return todoList;
  }

  public void setTodoList(List<Todo> todoList) {
    this.todoList = todoList;
  }

  public void addTodo(Todo todo) {
    todoList.add(todo);
  }

  public void clear() {
    this.todoList.clear();
  }
}
