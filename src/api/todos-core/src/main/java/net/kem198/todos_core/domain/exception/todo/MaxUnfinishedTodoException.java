package net.kem198.todos_core.domain.exception.todo;

import org.springframework.http.HttpStatus;

import net.kem198.todos_core.domain.exception.common.BusinessException;

public class MaxUnfinishedTodoException extends BusinessException {
    public MaxUnfinishedTodoException(String resourceName, long maxCount) {
        super(HttpStatus.BAD_REQUEST,
                "[E001] [" + resourceName + "] The count of un-finished Todo must not be over: " + maxCount);
    }
}
