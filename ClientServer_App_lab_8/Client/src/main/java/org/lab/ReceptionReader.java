package org.lab;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

public class ReceptionReader {

    ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
            .modules(new JavaTimeModule())
            .build();
    public CommandExecute read(String inputStream) throws IOException {
        return objectMapper.readValue(inputStream, CommandExecute.class);
    }
}
