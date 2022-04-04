package spring.jsp.hibernate1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.jsp.hibernate1.model.Todo;
import spring.jsp.hibernate1.repository.TodoRepository;
import spring.jsp.hibernate1.service.api.ITodoService;

import java.util.Collection;
import java.util.Date;

@Service
public class SimpleTodoService implements ITodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Collection<Todo> getTodosByUser(String user) {
        return todoRepository.findByUserName(user);
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
        todoRepository.save(new Todo(name, desc, targetDate, isDone));
    }

    @Override
    public void deleteTodo(Long id) {
       Todo  todo = todoRepository.findById(id);
        if (todo!=null) {
            todoRepository.delete(todo);
        }
    }

    @Override
    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }
}