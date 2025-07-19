package net.kem198.todos_api.domain.exception.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public abstract class BusinessException extends RuntimeException {
    private final ProblemDetail problemDetail;

    protected BusinessException(HttpStatus status, String detail) {
        super(detail);
        this.problemDetail = ProblemDetail.forStatus(status);
        this.problemDetail.setDetail(detail);
    }

    public ProblemDetail getProblemDetail() {
        return problemDetail;
    }
}
