package net.kem198.todos.api.fizzbuzz;

import java.io.Serializable;

public class FizzBuzzResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
