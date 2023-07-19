package com.demo.showcase.common.actuator;

import com.demo.showcase.common.utils.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application-actuator.yml", factory = YamlPropertySourceFactory.class)
public class ActuatorConfiguration {}
