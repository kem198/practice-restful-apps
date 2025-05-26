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
        // Drop example table
        jdbcTemplate.execute("DROP TABLE IF EXISTS examples_2");

        // Create example table
        jdbcTemplate.execute("""
                    CREATE TABLE IF NOT EXISTS examples_2 (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                """);

        // Insert dummy records
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM examples_2", Integer.class);
        if (count == 0) {
            jdbcTemplate.update("INSERT INTO examples_2 (name) VALUES (?), (?), (?)",
                    "Example 2-1", "Example 2-2", "Example 2-3");
        }

        // Select and logging
        var results = jdbcTemplate.queryForList("SELECT name FROM examples_2", String.class);
        System.out.println("DB SELECT results: " + results);
    }
}
