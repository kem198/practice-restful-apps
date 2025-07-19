package net.kem198.todos_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "net.kem198.todos_api",
        "net.kem198.todos_core"
})
public class TodosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosApiApplication.class, args);
    }

}
