package org.lab_5;

public class CommandExecute {
    private String response;
    private boolean success;
    private Integer detail;
    public CommandExecute(String response, boolean success) {
        this.response=response;
        this.success=success;
    }

    public CommandExecute(String response, boolean success, Integer detail) {
        this.response = response;
        this.success = success;
        this.detail = detail;
    }

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
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
}
