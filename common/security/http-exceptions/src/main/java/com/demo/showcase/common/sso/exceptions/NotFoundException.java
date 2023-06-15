package com.demo.showcase.common.sso.exceptions;

import org.zalando.problem.Status;

public class NotFoundException extends BaseException {

    public static final String NOT_FOUND_MSG = "Объект не найден";

    public NotFoundException() {
        super(Status.NOT_FOUND, NOT_FOUND_MSG);
    }

}
