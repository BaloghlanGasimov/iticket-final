package com.example.iticketfinal.client;

import com.example.iticketfinal.client.error.CountriesnowErrorDecoder;
import com.example.iticketfinal.client.model.CountriesnowBaseResp;
import com.example.iticketfinal.config.ApplicationProperties;
import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "countriesnow",
        url = "${application.client.countriesnow.credentials.url}",
        configuration = CountriesnowClient.FeignConfiguration.class
)
public interface CountriesnowClient {

    @GetMapping("/countries/positions")
    CountriesnowBaseResp getCountries();


    @Slf4j
    @RequiredArgsConstructor
    class FeignConfiguration {
        final ApplicationProperties applicationProperties;

        @Bean
        public Retryer retryer() {
            return new Retryer.Default(1000, 2000, 1);
        }

        @Bean
        public ErrorDecoder errorDecoder() {
            return new CountriesnowErrorDecoder();
        }

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

    }
}
