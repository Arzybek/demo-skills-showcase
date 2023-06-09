package com.demo.showcase.sds;

import com.demo.showcase.common.sso.WebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.demo.showcase")
@EntityScan(basePackages = "com.demo.showcase")
public class ShowsDataApp {

	public static void main(String[] args) {
		SpringApplication.run(ShowsDataApp.class, args);
	}

}
