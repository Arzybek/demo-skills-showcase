package com.demo.showcase.common.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableFeignClients(basePackages = "com.demo.showcase.common.feign")
@PropertySource(value = "classpath:application-http.yml", factory = YamlPropertySourceFactory.class)
public class FeignClientConfiguration {}
