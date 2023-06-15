package com.demo.showcase.common.sso.exceptions;

import lombok.Getter;
import org.zalando.problem.Status;

@Getter
public class BaseException extends RuntimeException {

    private final Status httpStatus;

    public BaseException(Status httpStatus, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
    }

}
