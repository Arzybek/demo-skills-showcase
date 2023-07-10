package com.demo.showcase.common.sso.exceptions;

import org.zalando.problem.Status;

public class ForbiddenException extends BaseException {

    public static final String FORBIDDEN_MSG = "Доступ запрещен";

    public ForbiddenException() {
        super(Status.FORBIDDEN, FORBIDDEN_MSG);
    }

}
