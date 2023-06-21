package org.lab_5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class RequestAnswer {
    ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
            .modules(new JavaTimeModule())
            .build();
    public String sendPackage(CommandExecute commandExecute) throws JsonProcessingException {
        System.out.println(commandExecute);
        String json = objectMapper.writeValueAsString(commandExecute);
        System.out.println(json);
        return json;
    }
}
