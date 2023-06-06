package com.demo.showcase.common.exceptions;

import org.zalando.problem.Status;

public class NotFoundException extends BaseException {

    public NotFoundException() {
        super(Status.NOT_FOUND, "Объект не найден");
    }

}
