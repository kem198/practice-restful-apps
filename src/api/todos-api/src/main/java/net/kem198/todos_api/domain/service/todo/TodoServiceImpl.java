package net.kem198.todos_api.domain.service.todo;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.kem198.todos_api.domain.exception.common.ResourceNotFoundException;
import net.kem198.todos_api.domain.exception.todo.AlreadyFinishedTodoException;
import net.kem198.todos_api.domain.exception.todo.MaxUnfinishedTodoException;
import net.kem198.todos_api.domain.model.Todo;
import net.kem198.todos_api.domain.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
    private static final long MAX_UNFINISHED_COUNT = 5;

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Todo findOne(String todoId) {
        Todo todo = todoRepository.findById(todoId);
        if (todo == null) {
            throw new ResourceNotFoundException(this.getClass().getName(), todoId);
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
            throw new MaxUnfinishedTodoException(this.getClass().getName(), MAX_UNFINISHED_COUNT);
        }

        String todoId = UUID.randomUUID().toString();
        Date createdAt = new Date();

        todo.setTodoId(todoId);
        todo.setCreatedAt(createdAt);
        todo.setFinished(false);

        todoRepository.create(todo);

        return todo;
    }

    @Override
    public Todo finish(String todoId) {
        Todo todo = findOne(todoId);
        if (todo.isFinished()) {
            throw new AlreadyFinishedTodoException(this.getClass().getName());
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
