package com.demo.showcase.front;

import com.demo.showcase.common.feign.ShowsDataFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients(clients = {ShowsDataFeignClient.class})
@SpringBootApplication(scanBasePackages = "com.demo.showcase", exclude = {DataSourceAutoConfiguration.class})
public class FrontEndApp {

    public static void main(String[] args) {
        SpringApplication.run(FrontEndApp.class, args);
    }
}