package spring.jsp.hibernate1.repository;

import spring.jsp.hibernate1.model.Todo;

import java.util.Collection;

public interface TodoRepository {

    Collection<Todo> findByUserName(String lastName);

    Todo findById(Long id);

    void save(Todo owner);

    void delete(Todo todo);


}