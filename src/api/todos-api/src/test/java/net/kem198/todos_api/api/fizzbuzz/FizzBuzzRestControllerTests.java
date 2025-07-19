package net.kem198.todos_api.api.fizzbuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FizzBuzzRestControllerTests {

    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Nested
    class GetRequestTests {
        @Nested
        @DisplayName("正常なリクエストでは FizzBuzz 結果のレスポンスを返す")
        class ValidRequestTests {
            @Test
            @DisplayName("\"?num=3\" でリクエストされた場合は {\"result\": \"Fizz\"} を返す")
            void returnsFizzFor3() throws Exception {
                // Act
                ResponseEntity<String> response = restTemplate.getForEntity("/v1/fizzbuzz/convert?num=3",
                        String.class);

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                JsonNode responseBody = objectMapper.readTree(response.getBody());
                assertEquals("Fizz", responseBody.get("result").asText());
            }
        }

        @Nested
        @DisplayName("不正なリクエストではエラーレスポンスを返す")
        class InvalidRequestTests {
            @Test
            @DisplayName("パラメータ無しでリクエストされた場合はエラーレスポンスを返す")
            void returnsErrorResponseForMissingParameter() throws Exception {
                // Act
                ResponseEntity<String> response = restTemplate.getForEntity("/v1/fizzbuzz/convert", String.class);

                // Assert
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                JsonNode responseBody = objectMapper.readTree(response.getBody());
                assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), responseBody.get("title").asText());
                assertEquals("Required parameter 'num' is not present.", responseBody.get("detail").asText());
            }

            @Test
            @DisplayName("数値として扱えない値でリクエストされた場合はエラーレスポンスを返す")
            void returnsErrorResponseForInvalidNumber() throws Exception {
                // Act
                ResponseEntity<String> response = restTemplate.getForEntity("/v1/fizzbuzz/convert?num=abc",
                        String.class);

                // Assert
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                JsonNode responseBody = objectMapper.readTree(response.getBody());
                assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), responseBody.get("title").asText());
                assertEquals("Failed to convert 'num' with value: 'abc'",
                        responseBody.get("detail").asText());
            }
        }
    }
}
