package org.lab;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RequestReader {
    ObjectMapper objectMapper = new ObjectMapper();
    public Request read(String inputStream) throws IOException {
        return objectMapper.readValue(inputStream, Request.class);
    }
}
