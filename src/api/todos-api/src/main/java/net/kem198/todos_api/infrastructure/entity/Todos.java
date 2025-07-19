package net.kem198.todos_api.infrastructure.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

/**
 */
@Entity(listener = TodosListener.class, metamodel = @Metamodel)
@Table(name = "todos")
public class Todos extends AbstractTodos {

    /** */
    @Id
    @Column(name = "todo_id")
    String todoId;

    /** */
    @Column(name = "todo_title")
    String todoTitle;

    /** */
    @Column(name = "todo_description")
    String todoDescription;

    /** */
    @Column(name = "finished")
    Boolean finished;

    /** */
    @Column(name = "created_at")
    LocalDateTime createdAt;

    /** 
     * Returns the todoId.
     * 
     * @return the todoId
     */
    public String getTodoId() {
        return todoId;
    }

    /** 
     * Sets the todoId.
     * 
     * @param todoId the todoId
     */
    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    /** 
     * Returns the todoTitle.
     * 
     * @return the todoTitle
     */
    public String getTodoTitle() {
        return todoTitle;
    }

    /** 
     * Sets the todoTitle.
     * 
     * @param todoTitle the todoTitle
     */
    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    /** 
     * Returns the todoDescription.
     * 
     * @return the todoDescription
     */
    public String getTodoDescription() {
        return todoDescription;
    }

    /** 
     * Sets the todoDescription.
     * 
     * @param todoDescription the todoDescription
     */
    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    /** 
     * Returns the finished.
     * 
     * @return the finished
     */
    public Boolean getFinished() {
        return finished;
    }

    /** 
     * Sets the finished.
     * 
     * @param finished the finished
     */
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    /** 
     * Returns the createdAt.
     * 
     * @return the createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /** 
     * Sets the createdAt.
     * 
     * @param createdAt the createdAt
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
