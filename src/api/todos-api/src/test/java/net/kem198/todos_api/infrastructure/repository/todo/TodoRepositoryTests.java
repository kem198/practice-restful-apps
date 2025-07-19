package net.kem198.todos_api.infrastructure.repository.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.kem198.todos_api.domain.model.Todo;
import net.kem198.todos_api.domain.repository.todo.TodoRepository;

@SpringBootTest
public class TodoRepositoryTests {
    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository.findAll().forEach(todo -> todoRepository.delete(todo));
    }

    @Nested
    class FindByIdTests {
        @Test
        @DisplayName("引数の todoId と一致する Todo を返す")
        public void returnsTodoForMatchingTodoId() {
            // Arrange
            Todo expectedTodo = makeTestTodo("Test Todo", false);
            todoRepository.create(expectedTodo);

            // Act
            Todo actualTodo = todoRepository.findById(expectedTodo.getTodoId());

            // Assert
            assertNotNull(actualTodo);
            assertEquals(expectedTodo.getTodoId(), actualTodo.getTodoId());
            assertEquals(expectedTodo.getTodoTitle(), actualTodo.getTodoTitle());
            assertEquals(expectedTodo.getTodoDescription(), actualTodo.getTodoDescription());
            assertEquals(expectedTodo.isFinished(), actualTodo.isFinished());
        }

        @Test
        @DisplayName("引数の todoId と一致する Todo が存在しない場合は null を返す")
        public void returnsNullForNonExistentTodoId() {
            // Arrange
            String nonExistentId = UUID.randomUUID().toString();

            // Act
            Todo actualTodo = todoRepository.findById(nonExistentId);

            // Assert
            assertNull(actualTodo);
        }
    }

    @Nested
    class FindAllTests {
        @Test
        @DisplayName("全ての Todo を Collection 形式で返す")
        public void returnsAllTodosAsCollection() {
            // Arrange
            Todo todo1 = makeTestTodo("Test Todo 1", false);
            Todo todo2 = makeTestTodo("Test Todo 2", true);
            todoRepository.create(todo1);
            todoRepository.create(todo2);

            // Act
            Collection<Todo> todos = todoRepository.findAll();

            // Assert
            assertEquals(2, todos.size());
            assertTrue(todos.stream().anyMatch(todo -> todo.getTodoId().equals(todo1.getTodoId())));
            assertTrue(todos.stream().anyMatch(todo -> todo.getTodoId().equals(todo2.getTodoId())));
        }

        @Test
        @DisplayName("Todo が存在しない場合は空の Collection を返す")
        public void returnsEmptyCollectionWhenNoTodosExist() {
            // Act
            Collection<Todo> todos = todoRepository.findAll();

            // Assert
            assertNotNull(todos);
            assertTrue(todos.isEmpty());
        }
    }

    @Nested
    class CreateTests {
        @Test
        @DisplayName("Todo を作成する")
        public void createsTodoSuccessfully() {
            // Arrange
            Todo newTodo = makeTestTodo("New Todo", false);

            // Act
            todoRepository.create(newTodo);

            // Assert
            Todo createdTodo = todoRepository.findById(newTodo.getTodoId());
            assertNotNull(createdTodo);
            assertEquals(newTodo.getTodoId(), createdTodo.getTodoId());
            assertEquals(newTodo.getTodoTitle(), createdTodo.getTodoTitle());
            assertEquals(newTodo.getTodoDescription(), createdTodo.getTodoDescription());
            assertEquals(newTodo.isFinished(), createdTodo.isFinished());
        }
    }

    @Nested
    class UpdateTests {
        @Test
        @DisplayName("存在する Todo を更新する")
        public void updatesTodoSuccessfully() {
            // Arrange
            Todo originalTodo = makeTestTodo("Original Todo", false);
            todoRepository.create(originalTodo);

            originalTodo.setTodoTitle("Updated Todo");
            originalTodo.setTodoDescription("Updated Description");
            originalTodo.setFinished(true);

            // Act
            boolean result = todoRepository.update(originalTodo);

            // Assert
            assertTrue(result);
            Todo updatedTodo = todoRepository.findById(originalTodo.getTodoId());
            assertEquals("Updated Todo", updatedTodo.getTodoTitle());
            assertEquals("Updated Description", updatedTodo.getTodoDescription());
            assertTrue(updatedTodo.isFinished());
        }

        @Test
        @DisplayName("存在しない Todo の更新は false を返す")
        public void returnsFalseForNonExistentTodo() {
            // Arrange
            Todo nonExistentTodo = makeTestTodo("Non-existent Todo", false);

            // Act
            boolean result = todoRepository.update(nonExistentTodo);

            // Assert
            assertFalse(result);
        }
    }

    @Nested
    class DeleteTests {
        @Test
        @DisplayName("存在する Todo を削除する")
        public void deletesTodoSuccessfully() {
            // Arrange
            Todo todoToDelete = makeTestTodo("Todo to Delete", false);
            todoRepository.create(todoToDelete);

            // Act
            todoRepository.delete(todoToDelete);

            // Assert
            Todo deletedTodo = todoRepository.findById(todoToDelete.getTodoId());
            assertNull(deletedTodo);
        }

        @Test
        @DisplayName("存在しない Todo の削除でもエラーが発生しない")
        public void doesNotThrowErrorForNonExistentTodo() {
            // Arrange
            Todo nonExistentTodo = makeTestTodo("Non-existent Todo", false);

            // Act & Assert (例外が発生しないことを確認)
            todoRepository.delete(nonExistentTodo);
        }
    }

    @Nested
    class CountByFinishedTests {
        @Test
        @DisplayName("未完了の Todo の数を返す")
        public void returnsCorrectCountForUnfinishedTodos() {
            // Arrange
            Todo unfinishedTodo1 = makeTestTodo("Unfinished Todo 1", false);
            Todo unfinishedTodo2 = makeTestTodo("Unfinished Todo 2", false);
            Todo finishedTodo = makeTestTodo("Finished Todo", true);

            todoRepository.create(unfinishedTodo1);
            todoRepository.create(unfinishedTodo2);
            todoRepository.create(finishedTodo);

            // Act
            long unfinishedCount = todoRepository.countByFinished(false);

            // Assert
            assertEquals(2, unfinishedCount);
        }

        @Test
        @DisplayName("完了済みの Todo の数を返す")
        public void returnsCorrectCountForFinishedTodos() {
            // Arrange
            Todo unfinishedTodo = makeTestTodo("Unfinished Todo", false);
            Todo finishedTodo1 = makeTestTodo("Finished Todo 1", true);
            Todo finishedTodo2 = makeTestTodo("Finished Todo 2", true);

            todoRepository.create(unfinishedTodo);
            todoRepository.create(finishedTodo1);
            todoRepository.create(finishedTodo2);

            // Act
            long finishedCount = todoRepository.countByFinished(true);

            // Assert
            assertEquals(2, finishedCount);
        }

        @Test
        @DisplayName("該当する Todo が存在しない場合は 0 を返す")
        public void returnsZeroWhenNoMatchingTodosExist() {
            // Arrange
            Todo finishedTodo = makeTestTodo("Finished Todo", true);
            todoRepository.create(finishedTodo);

            // Act
            long unfinishedCount = todoRepository.countByFinished(false);

            // Assert
            assertEquals(0, unfinishedCount);
        }
    }

    private Todo makeTestTodo(String title, boolean finished) {
        Todo todo = new Todo();
        todo.setTodoId(UUID.randomUUID().toString());
        todo.setTodoTitle(title);
        todo.setTodoDescription("Test Description");
        todo.setFinished(finished);
        todo.setCreatedAt(new Date());
        return todo;
    }
}
