package net.kem198.todos_core.domain.repository.todo;

import java.util.Collection;

import net.kem198.todos_core.domain.model.Todo;

public interface TodoRepository {
    Todo findById(String todoId);

    Collection<Todo> findAll();

    void create(Todo todo);

    boolean update(Todo todo);

    void delete(Todo todo);

    long countByFinished(boolean finished);
}
