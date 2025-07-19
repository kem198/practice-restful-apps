package net.kem198.todos_api.api.fizzbuzz;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FizBuzzMapper {

    FizzBuzzResource map(String result);

    // TODO: Change to FizzBuzz Model
    Object map(FizzBuzzResource fizzBuzzResource);
}
