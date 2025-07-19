package net.kem198.todos_api.infrastructure.entity;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

/**
 * 
 */
public class TodosListener implements EntityListener<Todos> {

    @Override
    public void preInsert(Todos entity, PreInsertContext<Todos> context) {
    }

    @Override
    public void preUpdate(Todos entity, PreUpdateContext<Todos> context) {
    }

    @Override
    public void preDelete(Todos entity, PreDeleteContext<Todos> context) {
    }

    @Override
    public void postInsert(Todos entity, PostInsertContext<Todos> context) {
    }

    @Override
    public void postUpdate(Todos entity, PostUpdateContext<Todos> context) {
    }

    @Override
    public void postDelete(Todos entity, PostDeleteContext<Todos> context) {
    }
}
