package net.kem198.todos_api.api.common.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestGlobalExceptionHandlerTests {

    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Nested
    class HandleSystemErrorTests {
        @Test
        @DisplayName("システムエラーは \"500 Internal Server Error\" で返す")
        void returns500InternalServerErrorWhenSystemError() throws JsonMappingException, JsonProcessingException {
            // Act
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "/v1/error/throw-system-error",
                    String.class);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals("An unexpected error occurred. Please contact support if the problem persists.",
                    responseBody.get("detail").asText());
        }

        @Test
        @DisplayName("システムエラーが発生したときはスタックトレースを返さない")
        void notReturnsStackTraceWhenSystemError() throws JsonMappingException, JsonProcessingException {
            // Act
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "/v1/error/throw-system-error",
                    String.class);

            // Assert
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertNull(responseBody.get("trace"));
        }
    }

    @Nested
    class HandleMethodArgumentNotValidTests {
        @Test
        @DisplayName("引数のバリデーションエラーは \"400 Bad Request\" で返す")
        void returns400BadRequestWhenMethodArgumentNotValidException()
                throws JsonMappingException, JsonProcessingException {
            // Arrange
            String requestBody = """
                        {
                            "todoDescription": "123456789012345678901234567890123456789012345678901"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/v1/todos", requestEntity, String.class);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals("Validation failed for the request.",
                    responseBody.get("detail").asText());
        }

        @Test
        @DisplayName("引数のバリデーションエラーの数だけ errors の配列を返す")
        void returnsErrorsForArgumentErrorTimes()
                throws JsonMappingException, JsonProcessingException {
            // Arrange
            String requestBody = """
                        {
                            "todoDescription": "123456789012345678901234567890123456789012345678901"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/v1/todos", requestEntity, String.class);

            // Assert
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            JsonNode errors = responseBody.get("errors");
            assertEquals(2, errors.size());
        }

        @Test
        @DisplayName("引数のバリデーションエラー要素に所定のエラー詳細内容を含む")
        void returnsErrorsWithContainedErrorDetails()
                throws JsonMappingException, JsonProcessingException {
            // Arrange
            String requestBody = """
                        {
                            "todoDescription": "123456789012345678901234567890123456789012345678901"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/v1/todos", requestEntity, String.class);

            // Assert
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            JsonNode errors = responseBody.get("errors");
            assertTrue(
                    StreamSupport.stream(errors.spliterator(), false)
                            .anyMatch((e) -> "todoTitle".equals(e.get("field").asText())
                                    && e.get("rejectedValue").isNull()
                                    && "NotNull".equals(e.get("code").asText())
                                    && "todoResource".equals(e.get("objectName").asText())
                                    && "must not be null".equals(e.get("message").asText())));
            assertTrue(
                    StreamSupport.stream(errors.spliterator(), false)
                            .anyMatch((e) -> "todoDescription".equals(e.get("field").asText())
                                    && "123456789012345678901234567890123456789012345678901"
                                            .equals(e.get("rejectedValue").asText())
                                    && "Size".equals(e.get("code").asText())
                                    && "todoResource".equals(e.get("objectName").asText())
                                    && "size must be between 1 and 50".equals(e.get("message").asText())));
        }
    }
}
