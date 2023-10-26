package org.lab;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ReceptionReader {

    ObjectMapper objectMapper = new ObjectMapper();
    public CommandExecute read(String inputStream) throws IOException {
        return objectMapper.readValue(inputStream, CommandExecute.class);
    }
}
