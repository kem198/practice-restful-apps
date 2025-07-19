package net.kem198.todos_api.todo;

import org.mapstruct.Mapper;

import net.kem198.todos_domain.model.Todo;

@Mapper(componentModel = "spring")
public interface TodoResourceMapper {

    Todo toDomain(TodoResource todoResource);

    TodoResource toResource(Todo todo);

}
