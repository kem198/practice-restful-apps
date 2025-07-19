package net.kem198.todos_api.api.fizzbuzz;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.kem198.todos_api.domain.service.fizzbuzz.FizzBuzzService;

@RestController
@RequestMapping("/v1/fizzbuzz")
public class FizzBuzzRestController {

    private final FizzBuzzService fizzBuzzService;
    private final FizBuzzMapper fizzBuzzMapper;

    public FizzBuzzRestController(FizzBuzzService fizzBuzzService, FizBuzzMapper fizzBuzzMapper) {
        this.fizzBuzzService = fizzBuzzService;
        this.fizzBuzzMapper = fizzBuzzMapper;
    }

    @GetMapping("/convert")
    public ResponseEntity<FizzBuzzResource> convert(@RequestParam(value = "num") int number) {
        String result = fizzBuzzService.processFizzBuzz(number);
        FizzBuzzResource fizzBuzzResource = fizzBuzzMapper.map(result);
        return ResponseEntity.ok(fizzBuzzResource);
    }
}
