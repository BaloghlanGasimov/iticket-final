package com.example.iticketfinal.client.error;

import com.example.iticketfinal.exceptions.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountriesnowErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    private static final Logger logger = LoggerFactory.getLogger(CountriesnowErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                logger.error("Bad Request: {}", response.request().url());
                break;
            case 404:
                logger.error("Not Found: {}", response.request().url());
                return new NotFoundException("Not found: " + response.request().url(),"Action.gitlabClient.error");
            case 500:
                logger.error("Internal Server Error: {}", response.request().url());
                break;
            default:
                logger.error("Unexpected error: {}", response.request().url());
                break;
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
