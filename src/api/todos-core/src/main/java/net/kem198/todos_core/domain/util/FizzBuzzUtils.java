package net.kem198.todos_core.domain.util;

public class FizzBuzzUtils {
    public static String convert(int num) {
        if (num % 3 == 0 && num % 5 == 0) {
            return "FizzBuzz";
        }
        if (num % 3 == 0) {
            return "Fizz";
        }
        if (num % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(num);
    }
}
