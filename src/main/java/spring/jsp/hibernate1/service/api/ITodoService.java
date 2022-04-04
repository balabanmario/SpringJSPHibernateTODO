package spring.jsp.hibernate1.service.api;

import org.springframework.transaction.annotation.Transactional;
import spring.jsp.hibernate1.model.Todo;

import java.util.Collection;
import java.util.Date;

@Transactional
public interface ITodoService {

    Collection<Todo> getTodosByUser(String user);

   Todo getTodoById(Long id);

    void updateTodo(Todo todo);

    void addTodo(String name, String desc, Date targetDate, boolean isDone);

    void deleteTodo(Long id);

    void saveTodo(Todo todo);
}