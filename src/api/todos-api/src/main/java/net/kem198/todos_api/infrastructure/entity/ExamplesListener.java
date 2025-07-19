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
public class ExamplesListener implements EntityListener<Examples> {

    @Override
    public void preInsert(Examples entity, PreInsertContext<Examples> context) {
    }

    @Override
    public void preUpdate(Examples entity, PreUpdateContext<Examples> context) {
    }

    @Override
    public void preDelete(Examples entity, PreDeleteContext<Examples> context) {
    }

    @Override
    public void postInsert(Examples entity, PostInsertContext<Examples> context) {
    }

    @Override
    public void postUpdate(Examples entity, PostUpdateContext<Examples> context) {
    }

    @Override
    public void postDelete(Examples entity, PostDeleteContext<Examples> context) {
    }
}
