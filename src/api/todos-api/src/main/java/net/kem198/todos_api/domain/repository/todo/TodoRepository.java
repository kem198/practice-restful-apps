package net.kem198.todos_api.domain.repository.todo;

import java.util.Collection;

import net.kem198.todos_api.domain.model.Todo;

public interface TodoRepository {
    Todo findById(String todoId);

    Collection<Todo> findAll();

    void create(Todo todo);

    boolean update(Todo todo);

    void delete(Todo todo);

    long countByFinished(boolean finished);
}
