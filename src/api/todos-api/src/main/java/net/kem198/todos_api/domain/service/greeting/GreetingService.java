package net.kem198.todos_api.domain.service.greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import net.kem198.todos_api.api.greeting.GreetingResource;

@Service
public class GreetingService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    public GreetingResource processGreeting(String name) {

        GreetingResource greetingDto = new GreetingResource(counter.incrementAndGet(), String.format(template, name));
        return greetingDto;
    }
}
