package com.example.iticketfinal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@ConfigurationProperties(prefix = "application")
@Configuration
public class ApplicationProperties {
    private final Client client = new Client();

    @Getter
    @Setter
    public static class Client {

        private final Countriesnow countriesnow = new Countriesnow();


        @Setter
        @Getter
        public static class Countriesnow {

            private final Credentials credentials = new Credentials();

            @Setter
            @Getter
            public static class Credentials {
                private String username;
                private String token;
            }
        }

    }
}
