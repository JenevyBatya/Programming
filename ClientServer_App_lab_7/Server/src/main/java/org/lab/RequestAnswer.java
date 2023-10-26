package org.lab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestAnswer {
    ObjectMapper objectMapper = new ObjectMapper();
    public String sendPackage(CommandExecute commandExecute) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(commandExecute);
        return json;
    }
}
