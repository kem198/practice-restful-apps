package net.kem198.todos_infrastructure.mapper;

import org.mapstruct.Mapper;

import net.kem198.todos_domain.model.Todo;
import net.kem198.todos_infrastructure.entity.Todos;

@Mapper(componentModel = "spring")
public interface TodosMapper {

    Todo toDomain(Todos todoResource);

    Todos toEntity(Todo todo);
}
