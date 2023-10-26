package org.lab;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommandExecute {
    private String response;
    private boolean success;
    private boolean newCommand;
    private Integer detail;
    private Object hashtable; // Добавленное поле "hashtable"

    @JsonCreator
    public CommandExecute(
            @JsonProperty("response") String response,
            @JsonProperty("success") boolean success

    ) {
        this.response = response;
        this.success = success;

    }

    // Геттеры и сеттеры для полей

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isNewCommand() {
        return newCommand;
    }

    public void setNewCommand(boolean newCommand) {
        this.newCommand = newCommand;
    }

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
    }

    public Object getHashtable() {
        return hashtable;
    }

    public void setHashtable(Object hashtable) {
        this.hashtable = hashtable;
    }
}
