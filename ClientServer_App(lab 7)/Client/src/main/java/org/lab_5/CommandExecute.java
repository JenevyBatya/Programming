package org.lab_5;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommandExecute {
    private String response;
    private boolean success;
    private boolean newCommand;
    private Integer detail;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public CommandExecute(@JsonProperty("response") String response, @JsonProperty("success") boolean success) {
        this.response = response;
        this.success = success;

    }


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

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
    }
}
