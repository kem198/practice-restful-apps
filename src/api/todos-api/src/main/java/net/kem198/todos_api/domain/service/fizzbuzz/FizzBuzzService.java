package net.kem198.todos_api.domain.service.fizzbuzz;

import org.springframework.stereotype.Service;

import net.kem198.todos_api.domain.util.FizzBuzzUtils;

@Service
public class FizzBuzzService {
    public String processFizzBuzz(int number) {
        return FizzBuzzUtils.convert(number);
    }
}
