package net.kem198.todos_api.infrastructure.repository.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.kem198.todos_api.infrastructure.dao.TodosDao;
import net.kem198.todos_api.infrastructure.entity.Todos;
import net.kem198.todos_api.infrastructure.mapper.TodosMapper;
import org.springframework.stereotype.Repository;

import net.kem198.todos_api.domain.model.Todo;
import net.kem198.todos_api.domain.repository.todo.TodoRepository;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private final TodosDao todosDao;
    private final TodosMapper todosMapper;

    public TodoRepositoryImpl(TodosDao todosDao, TodosMapper todosMapper) {
        this.todosDao = todosDao;
        this.todosMapper = todosMapper;
    }

    @Override
    public Todo findById(String todoId) {
        Todos entity = todosDao.selectById(todoId);
        return todosMapper.toDomain(entity);
    }

    @Override
    public Collection<Todo> findAll() {
        List<Todos> entities = todosDao.selectAll();
        List<Todo> todos = new ArrayList<>();
        for (Todos entity : entities) {
            todos.add(todosMapper.toDomain(entity));
        }
        return todos;
    }

    @Override
    public void create(Todo todo) {
        Todos entity = todosMapper.toEntity(todo);
        todosDao.insert(entity);
    }

    @Override
    public boolean update(Todo todo) {
        Todos entity = todosMapper.toEntity(todo);
        return todosDao.update(entity) > 0;
    }

    @Override
    public void delete(Todo todo) {
        Todos entity = todosMapper.toEntity(todo);
        todosDao.delete(entity);
    }

    @Override
    public long countByFinished(boolean finished) {
        return todosDao.countByFinished(finished);
    }
}
