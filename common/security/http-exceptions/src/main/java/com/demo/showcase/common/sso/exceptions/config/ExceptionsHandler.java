package com.demo.showcase.common.sso.exceptions.config;

import com.demo.showcase.common.sso.exceptions.BaseException;
import com.demo.showcase.common.sso.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ser.Serializers;
import feign.FeignException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class ExceptionsHandler implements ProblemHandling, HandlerAdvice {

    private ResponseEntity<Problem> create(Status status, String message, NativeWebRequest req) {
        ThrowableProblem problem = Problem.builder()
                                          .withStatus(status)
                                          .withDetail(message)
                                          .build();
        return create(problem, req);
    }

    @Override
    public boolean isCausalChainsEnabled() {
        return false;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Problem> handleBaseException(BaseException exception, NativeWebRequest request) {
        return create(exception.getHttpStatus(), exception.getMessage(), request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Problem> handleNotFoundException(NotFoundException exception, NativeWebRequest request) {
        return create(Status.NOT_FOUND, exception.getMessage(), request);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Problem> handleHibernateNoResultException(EmptyResultDataAccessException exception, NativeWebRequest request) {
        return create(Status.NOT_FOUND, NotFoundException.NOT_FOUND_MSG, request);
    }

}
