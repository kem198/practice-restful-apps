package net.kem198.todos_api.domain.service.todo;

import java.util.Collection;

import net.kem198.todos_api.domain.model.Todo;

public interface TodoService {
    Todo findOne(String todoId);

    Collection<Todo> findAll();

    Todo create(Todo todo);

    Todo finish(String todoId);

    void delete(String todoId);
}
