package net.kem198.todos_api.infrastructure.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class DbCheckRunner implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        var results = jdbcTemplate.queryForList("SELECT name FROM examples;", String.class);
        System.out.println("DB SELECT results: " + results);
    }
}
