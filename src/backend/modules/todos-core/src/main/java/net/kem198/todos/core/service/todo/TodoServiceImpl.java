package net.kem198.todos.core.service.todo;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.kem198.todos.core.exception.common.ResourceNotFoundException;
import net.kem198.todos.core.exception.todo.AlreadyFinishedTodoException;
import net.kem198.todos.core.exception.todo.MaxUnfinishedTodoException;
import net.kem198.todos.core.model.Todo;
import net.kem198.todos.core.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
    private static final long MAX_UNFINISHED_COUNT = 100;

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Todo findOne(String todoId) {
        Todo todo = todoRepository.findById(todoId);
        if (todo == null) {
            throw new ResourceNotFoundException("todoId", todoId);
        }
        return todo;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo create(Todo todo) {
        long unfinishedCount = todoRepository.countByFinished(false);
        if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
            throw new MaxUnfinishedTodoException(MAX_UNFINISHED_COUNT);
        }

        String todoId = UUID.randomUUID().toString();
        Date createdAt = new Date();

        todo.setTodoId(todoId);
        todo.setCreatedAt(createdAt);
        todo.setFinished(Boolean.TRUE.equals(todo.isFinished()));

        todoRepository.create(todo);

        return todo;
    }

    @Override
    public Todo finish(String todoId) {
        Todo todo = findOne(todoId);
        if (todo.isFinished()) {
            throw new AlreadyFinishedTodoException();
        }
        todo.setFinished(true);
        todoRepository.update(todo);
        return todo;
    }

    @Override
    public void delete(String todoId) {
        Todo todo = findOne(todoId);
        todoRepository.delete(todo);
    }

}
