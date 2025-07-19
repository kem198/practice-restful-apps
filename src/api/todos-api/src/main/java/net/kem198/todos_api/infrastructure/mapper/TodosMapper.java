package net.kem198.todos_api.infrastructure.mapper;

import org.mapstruct.Mapper;

import net.kem198.todos_api.domain.model.Todo;
import net.kem198.todos_api.infrastructure.entity.Todos;

@Mapper(componentModel = "spring")
public interface TodosMapper {

    Todo toDomain(Todos todoResource);

    Todos toEntity(Todo todo);
}
