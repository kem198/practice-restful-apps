package net.kem198.todos_core.domain.service.fizzbuzz;

import org.springframework.stereotype.Service;

import net.kem198.todos_core.domain.util.FizzBuzzUtils;

@Service
public class FizzBuzzService {
    public String processFizzBuzz(int number) {
        return FizzBuzzUtils.convert(number);
    }
}
