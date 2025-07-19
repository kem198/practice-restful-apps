package net.kem198.todos_api.domain.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FizzBuzzUtilsTests {
    @Nested
    class ConvertMethodTests {
        @Nested
        @DisplayName("3 の倍数の場合は「Fizz」に変換する")
        class ConvertsMultiplesOf3ToFizz {
            @Test
            @DisplayName("3 を渡すと文字列 Fizz を返す")
            void returnsFizzFor3() {
                // Act
                String actual = FizzBuzzUtils.convert(3);

                // Assert
                assertEquals("Fizz", actual);
            }
        }

        @Nested
        @DisplayName("5 の倍数の場合は「Buzz」に変換する")
        class ConvertsMultiplesOf5ToBuzz {
            @Test
            @DisplayName("5 を渡すと文字列 Buzz を返す")
            void returnsBuzzFor5() {
                // Act
                String actual = FizzBuzzUtils.convert(5);

                // Assert
                assertEquals("Buzz", actual);
            }
        }

        @Nested
        @DisplayName("3 と 5 両方の倍数の場合は「FizzBuzz」に変換する")
        class ConvertsMultiplesOf3And5ToFizzBuzz {
            @Test
            @DisplayName("15 を渡すと文字列 FizzBuzz を返す")
            void returnsFizzBuzzFor15() {
                // Act
                String actual = FizzBuzzUtils.convert(15);

                // Assert
                assertEquals("FizzBuzz", actual);
            }
        }

        @Nested
        @DisplayName("その他の数の場合はそのまま文字列に変換する")
        class ConvertsOtherNumbersToString {
            @Test
            @DisplayName("1 を渡すと文字列 1 を返す")
            void returns1For1() {
                // Act
                String actual = FizzBuzzUtils.convert(1);

                // Assert
                assertEquals("1", actual);
            }
        }
    }
}
