package net.kem198.todos_core.infrastructure.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import net.kem198.todos_core.infrastructure.entity.Todos;

/**
 */
@Dao
@ConfigAutowireable
public interface TodosDao {

    /**
     * @param todoId
     * @return the Todos entity
     */
    @Select
    Todos selectById(String todoId);

    /**
     * @return Todos entity list
     */
    @Select
    List<Todos> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Todos entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Todos entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Todos entity);

    /**
     * @return finished Todos count
     */
    @Select
    long countByFinished(boolean finished);
}
