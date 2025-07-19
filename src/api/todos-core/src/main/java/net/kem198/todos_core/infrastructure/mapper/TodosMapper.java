package net.kem198.todos_core.infrastructure.mapper;

import org.mapstruct.Mapper;

import net.kem198.todos_core.domain.model.Todo;
import net.kem198.todos_core.infrastructure.entity.Todos;

@Mapper(componentModel = "spring")
public interface TodosMapper {

    Todo toDomain(Todos todoResource);

    Todos toEntity(Todo todo);
}
