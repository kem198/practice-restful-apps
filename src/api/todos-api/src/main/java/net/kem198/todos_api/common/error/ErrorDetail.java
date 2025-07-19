package net.kem198.todos_api.common.error;

import org.springframework.validation.FieldError;

public class ErrorDetail {
    private final String field;
    private final Object rejectedValue;
    private final String code;
    private final String objectName;
    private final String message;

    public ErrorDetail(String field, Object rejectedValue, String code, String objectName, String message) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.code = code;
        this.objectName = objectName;
        this.message = message;
    }

    public ErrorDetail(FieldError fieldError) {
        this(
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getCode(),
                fieldError.getObjectName(),
                fieldError.getDefaultMessage());
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public String getCode() {
        return code;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getMessage() {
        return message;
    }
}
