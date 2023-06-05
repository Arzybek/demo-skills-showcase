package com.demo.showcase.common.exceptions.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

@Configuration
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class})
public class ProblemConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModules(
            new ProblemModule().withStackTraces(),
            new ConstraintViolationProblemModule()).findAndRegisterModules();
    }

}
